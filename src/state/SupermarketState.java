package state;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/**
 * Denna klass ska hålla reda på tillståndet som snabbköpet har i ett specifikt
 * tillfälle. Den ärver den abstrakta klassen simState och implementerar de
 * generella metoderna från den som är gemensam för alla olika sorters
 * simuleringar. 
 */
public class SupermarketState extends SimState {

	/**
	 * Tilltståndsvariabler / Statistik
	 */
	private int maxNumOfCustomers;
	private int numOfCustomers; // Antal customers i butiken
	private int numCheckouts; // Ska tilldelas från vår runSim där detta anges
	private int freeCheckouts; // Detta ska räknas ut genom att ta i akt på FIFO tillståndet och antal personer i snabbköpet
	private double sumTimeFreeCheckouts; // Summering av tiden som det funnits lediga kassor. Slutas räknas då sista kunden betalat och lämnat snabbköpet. (Även om det kommer en ny kund som inte kommer in pga stängning)
	private int numCustomersLeaving; // Antal kunder som handlat och lämnar snabbköpet (genomgått alla kund-händelser)
	private double sumTimeCustomersInQueue; // Summering av tiden som en kund stått i kön.
	private int numCustomersMissed; // Missade kunder
	private int totalQueuedCustomers;
	private double closingTime;
	private double stopTime;
	private int customerID;
	private double arrivalLambda;
	private double pickupL;
	private double pickupH;
	private double paymentL;
	private double paymentH;
	private String currentEvent;
	
	//private double currentTime; // Nuvarande tiden för hela simuleringen. Sluta räknas efter att sista kunden betalat.
	
	//protected double currentTimeCustomer = 0; // Nuvarande tiden för en specifik kund. Sluta räknas efter att sista kunden betalat.
	
	private ArrivalTime arrive;// = new ArrivalTime(4.0); // Ankomsttidskälla, 4.0 bytsut mot det man anger i runSim
	private PickupTime pickup;// = new PickupTime(0.5,1.0); // Plocktidskälla, parametrar byts ut mot de angivna i runSim ( variabler)
	private PaymentTime payment; // = new PaymentTime(0.5,1.0); // Betalningstidskälla, samma som ovan
	private FIFO queue; //= new FIFO();
	private CustomerSource CS;
	
	/**
	 * Konstruktor, skapar en instans av SupermarketState som håller reda på tillståndet i generatorn
	 * @param antalKassor
	 * @param maxCustomers
	 * @param arrivalLambda
	 * @param pickupL
	 * @param pickupH
	 * @param paymentL
	 * @param paymentH
	 * @param closingTime
	 */
	public SupermarketState(int antalKassor, int maxCustomers, double arrivalLambda,
			double pickupL, double pickupH, double paymentL, double paymentH, double closingTime, double stopTime){ // Konstruktor, behövs detta?
		this.numCheckouts = antalKassor;
		this.numOfCustomers = 0;
		this.freeCheckouts = numCheckouts;
		this.queue = new FIFO();
		this.arrive= new ArrivalTime(arrivalLambda);
		this.pickup = new PickupTime(pickupL, pickupH);
		this.payment = new PaymentTime(paymentL, paymentH);
		this.maxNumOfCustomers = maxCustomers;
		this.sumTimeCustomersInQueue = 0.0;
		this.numCustomersLeaving = 0;
		this.numCustomersMissed = 0;
		this.totalQueuedCustomers = 0;
		this.closingTime = closingTime;
		this.stopTime=stopTime;
		this.customerID = 0;
		this.arrivalLambda = arrivalLambda;
		this.pickupL = pickupL;
		this.pickupH = pickupH;
		this.paymentH = paymentH;
		this.paymentL = paymentL;
		this.currentEvent = "";
		this.CS=new CustomerSource();
		
	}
	
	/***
	 * Getters & Setters
	 */
	
	/**
	 * Metod som returnerar om butiken är öppen eller ej
	 * @return false or true
	 */
	public boolean open() {
		if (getTime() >= closingTime) { // 10 placeholder, 10 står för tiden när butiken stänger
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returnerar maximala antalet kunder
	 * @return maxNumOfCustomers
	 */
	public int getMaxNumOfCustomers() {
		return this.maxNumOfCustomers;
	}
	
	/**
	 * Returnerar antalet kunder
	 * @return numOfCustomers
	 */
	public int getCurrentCustomers() {
		return this.numOfCustomers;
	}
	
	/**
	 * Plussar på 1 till antalet kunder 
	 */
	public void addCurrrentCustomers() {
		this.numOfCustomers += 1;
	}
	
	public void minusCurrentCustomers() {
		this.numOfCustomers -= 1;
	}
	
	/**
	 * Ger antalet kassor
	 * @return numCheckouts
	 */
	public int getNumCheckouts() {
		return this.numCheckouts;
	}
	
	/**
	 * Ger antalet lediga kassor
	 * @return freeCheckouts
	 */
	public int getFreeCashiers() {
		return this.freeCheckouts;
	}
	
	/**
	 * Sätter antalet lediga kassor
	 * @param freeCheckouts
	 */
	public void setFreeCashiers(int freeCheckouts) {
		this.freeCheckouts = freeCheckouts;
	}
	
	/**
	 * Ger den lediga tiden kassorna har
	 * @return sumTimeFreeCheckouts
	 */
	public void updateFreeCashierTime() {
		this.sumTimeFreeCheckouts += (double) getFreeCashiers() * (getTime()-getPreviousTime());
	}
	
	public double getFreeCashierTime() {
		return this.sumTimeFreeCheckouts;
	}
	
	/**
	 * Ger det totala antalet av kunderna som betalat (som då också lämnar butiken)
	 * @return numCustomersLeaving
	 */
	public int getTotalPayingCustomers() {
		return this.numCustomersLeaving;
	}
	
	
	/**
	 * Lägger till 1 på antal kunder som betalat (som också då lämnar butiken)
	 */
	public void addTotalPayingCustomers() {
		this.numCustomersLeaving += 1;
	}

	
	public int getTotalQueuedCustomers() {
		return this.totalQueuedCustomers;
	}
	
	
	/**
	 * Ger kö-tiden
	 * @return sumTimeCustomersInQueue
	 */
	public void updateTotalQueueTime() {
		this.sumTimeCustomersInQueue += (double) queue.size() * (getTime()-getPreviousTime());
	}
	public double getTotalQueueTime() {
		return this.sumTimeCustomersInQueue;
	}
	
	/**
	 * Ger antalet kunder som inte får komma in och handla (pga butiken är stängd)
	 * @return numCustomersMissed
	 */
	public int getMissedCustomers() {
		return this.numCustomersMissed;
	}
	
	/**
	 * Lägger till 1 på kunder som missats pga stängning
	 */
	public void setMissedCustomers() {
		this.numCustomersMissed += 1;
	}
	
	/**
	 * Returnerar antalet kunder som är i FIFO kön
	 * @return queue.size()
	 */
	public int getQueuedCustomers() { // Returnerar antal Customers i FIFO kön (en int)
		return this.queue.size();
	}
	
	/**
	 * Ger kön i string format
	 * @return queue.toString()
	 */
	public String getStringQueue() { // Returnerar en sträng av kön, id på kund och vilken plats (index 0 = längst fram, FIFO)
		return this.queue.toString();
	}
	
	/**
	 * Ger den genomsnittliga lediga tiden som kassorna har
	 * @return sumTimeFreeCheckouts
	 */
//	public double getAverageFreeCashierTime() { // Returnerar den tid i snitt som kassor är lediga FEL!!!
//		return getFreeCashierTime()/numCheckouts;
//	}
	
	/**
	 * Ger den genomsnittliga kö tiden för kunderna
	 * @return sumTimeCustomersInQueue
	 */
//	public double getAverageQueueTime() { // Returnerar den tid i snitt som kunder får köa FEL!!!
//		return getQueueTime()/numCustomersLeaving;
//	}
	
	/**
	 * Ger den procentuella tiden som kassorna varit lediga
	 * @return getFreeCashierTime() / getTime()
	 */
//	public double getFreeCashierPercentage() {
//		return getFreeCashierTime() / getTime();
//	}
	
	/**
	 * Ger den tid där butiken ska stänga
	 * @return closingTime
	 */
	public double getClosingTime() {
		return this.closingTime;
	}
	
	
	public double getStopTime() {
		return this.stopTime;
	}
	/**
	 * Ger tiden för nästa händelse
	 * @return new ArrivalTime(arrivalLambda).getNextTime(getTime())
	 */
	public double getArrivalTime() { // Ger ett nytt slumptal på AnkomstTid
		return arrive.getNextTime(getTime());
	}
	
	/**
	 * Ger tiden för nästa händelse
	 * @return new PickupTime(pickupL, pickupH).getNextTime(getTime())
	 */
	public double getPickupTime() {
		return new PickupTime(pickupL, pickupH).getNextTime(getTime());
	}
	
	/**
	 * Ger tiden för nästa händelse
	 * @return new PickupTime(paymentL, paymentH).getNextTime(getTime())
	 */
	public double getPaymentTime() {
		return new PaymentTime(paymentL, paymentH).getNextTime(getTime());
	}
	
	/**
	 * Ger ID:et på kunden
	 * @return cutomerID
	 */
	public int getCurrentCustomerID() {
		return this.customerID;
	}
	
	public void setCurrentCustomerID(int id) {
		this.customerID = id;
	}
	
	/**
	 * Ger det nuvarande händelsen
	 * @return currentEvent
	 */
	public String getCurrentEvent() {
		return this.currentEvent;
	}
	
	/**
	 * Sätter den nuvarande händelsen
	 * @param currentEvent
	 */
	public void setCurrentEvent(String currentEvent) {
		this.currentEvent = currentEvent;
	}
	
	/**
	 * Ger FIFO kön
	 * @return queue
	 */
	public FIFO getFIFO() { // Returnerar FIFO-kön
		return this.queue;
	}
	
	/**
	 * Lägger till en kund till FIFO-kön, samt lägger till en kund i totalQueuedCustomers (statistik)
	 * @param cr
	 */
	public void addFIFO(Customer cr) { // Lägger till en Customer i FIFO kön
		totalQueuedCustomers = totalQueuedCustomers + 1;
		this.queue.add(cr);
	}
	
	/**
	 * Tar bort den första kunden i FIFO-kön
	 */
	public void removeFirstFIFO() { // Tar bort det första Customern i FIFO kön
		this.queue.removeFirst();
	}
	
	/**
	 * Ger true eller false beroende på om kön är tom eller ej
	 */
	public void isEmptyFIFO() { // Returnerar true eller false beroende på om kön är tom eller ej
		this.queue.isEmpty();
	}
	
	public CustomerSource getCS() {
		return CS;
	}
	
	
//	public addTime(Event event) { // Lägger till tid beroende på vilket event
//		
//		if (event == ArrivalEvent) {
//			this.currentTime = arrive.getNextTime(currentTime);
//		} else if (event == ClosingEvent) {
//			this.currentTime = 10.0; // Tiden som butiken ska stänga
//		} else if (event == PaymentEvent) {								Görs i den enskilda eventsen
//			this.currentTime = payment.getNextTime(currentTime);
//		} else if (event == PickUpEvent) {
//			this.currentTime = pickup.getNextTime(currentTime);
//		} else if (event == StartEvent) {
//			this.currentTime = 0.0;
//		} else if (event == StopEvent) {
//			this.currentTime = 999;// Tiden då simulatorn ska stänga av
//		}
//		
//	}
	
//	@Override
//	public double getTime() { // Aktuella tiden som simulatorn hunnit i det tillfälle som metoden anropas.
//		// TODO Auto-generated method stub
//		
//		/*
//		 * Hur ska jag koda dessa?
//		 * Typ currentTime = currentTime + event.time();
//		 * notifyObservers();
//		 * setChanged();									Görs i generella
//		 * if (sistaKundLämnat == true) {
//		 * 		return currentTime; ?
//		 * }
//		 */
//		
//		return currentTime;
//	}

//	@Override
//	public boolean simBreak() { // Om simuleringen ska avslutas så sätts denna till true.
//		// TODO Auto-generated method stub
//		
//		/*
//		 * Hur ska jag koda dessa?
//		 * if (simEnd == true) {
//		 * 		return true;
//		 * 		notifyObservers();
//		 * 		setChanged();								Görs i generella
//		 * } else {
//		 * 		notifyObservers();
//		 * 		setChanged();
//		 * 		return false; ?
//		 * }
//		 */
//		
//		notifyObservers();
//		setChanged();
//		return false;
//	}
	
}
