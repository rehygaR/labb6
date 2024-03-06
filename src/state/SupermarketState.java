package state;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass ska hålla reda på tillståndet som snabbköpet har i ett specifikt
 * tillfälle. Den ärver den abstrakta klassen simState och implementerar de
 * generella metoderna från den som är gemensam för alla olika sorters
 * simuleringar. 
 */

@SuppressWarnings("unused")
public class SupermarketState extends SimState {

	/*
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
	private int customerID;
	private double arrivalLambda;
	private double pickupL;
	private double pickupH;
	private double paymentL;
	private double paymentH;
	private String currentEvent;
	
	//private double currentTime; // Nuvarande tiden för hela simuleringen. Sluta räknas efter att sista kunden betalat.
	
	//protected double currentTimeCustomer = 0; // Nuvarande tiden för en specifik kund. Sluta räknas efter att sista kunden betalat.
	
//	public ArrivalTime arrive;// = new ArrivalTime(4.0); // Ankomsttidskälla, 4.0 bytsut mot det man anger i runSim
//	public PickupTime pickup;// = new PickupTime(0.5,1.0); // Plocktidskälla, parametrar byts ut mot de angivna i runSim ( variabler)
//	public PaymentTime payment; // = new PaymentTime(0.5,1.0); // Betalningstidskälla, samma som ovan
	private FIFO queue; //= new FIFO();
	
	public SupermarketState(int antalKassor, int maxCustomers, double arrivalLambda,
			double pickupL, double pickupH, double paymentL, double paymentH, double closingTime){ // Konstruktor, behövs detta?
		this.numCheckouts = antalKassor;
		//this.currentTime = super.currentTime;
		this.numOfCustomers = 0;
		this.freeCheckouts = numCheckouts;
		this.queue = new FIFO();
//		this.pickup = new PickupTime(pickupL, pickupH);
//		this.payment = new PaymentTime(paymentL, paymentH);
		this.maxNumOfCustomers = maxCustomers;
		this.sumTimeCustomersInQueue = 0.0;
		this.numCustomersLeaving = 0;
		this.sumTimeCustomersInQueue = 0.0;
		this.numCustomersMissed = 0;
		this.totalQueuedCustomers = 0;
		this.closingTime = closingTime;
		this.customerID = 0;
		this.arrivalLambda = arrivalLambda;
		this.pickupL = pickupL;
		this.pickupH = pickupH;
		this.paymentH = paymentH;
		this.paymentL = paymentL;
	}
	
	/*
	 * Getters & Setters
	 */
	public boolean open() {
		if (getTime() >= closingTime) { // 10 placeholder, 10 står för tiden när butiken stänger
			return false;
		} else {
			return true;
		}
	}
	
	public int getMaxNumOfCustomers() {
		return this.maxNumOfCustomers;
	}
	
	public int getCurrentCustomers() {
		return this.numOfCustomers;
	}
	
	public void setCurrrentCustomers(int numOfCustomers) {
		this.numOfCustomers = numOfCustomers;
	}
	
	public int getNumCheckouts() {
		return this.numCheckouts;
	}
	
	public int getFreeCashiers() {
		return this.freeCheckouts;
	}
	
	public void setFreeCashiers(int freeCheckouts) {
		this.freeCheckouts = freeCheckouts;
	}
	
	public double getFreeCashierTime() { // Troligtvis fel beräkning!!!
		this.sumTimeFreeCheckouts = (double) getFreeCashiers() * getTime();
		return this.sumTimeFreeCheckouts;
	}
	
	
	public int getTotalPayingCustomers() {
		return this.numCustomersLeaving;
	}
	
	public void setTotalPayingCustomers(int numCustomersLeaving) {
		this.numCustomersLeaving = numCustomersLeaving;
	}
	
	
	public double getQueueTime() { // Troligtvis fel beräknad!!!!
		this.sumTimeCustomersInQueue = (double) queue.size() * getPaymentTime();
		return this.sumTimeCustomersInQueue;
	}
	
	public int getMissedCustomers() {
		return this.numCustomersMissed;
	}
	
	public void setMissedCustomers(int numOfCustomersMissed) {
		this.numCustomersMissed = numOfCustomersMissed;
	}
	
	public int getQueuedCustomers() { // Returnerar antal Customers i FIFO kön (en int)
		return this.queue.size();
	}
	
	public String getStringQueue() { // Returnerar en sträng av kön, id på kund och vilken plats (index 0 = längst fram, FIFO)
		return this.queue.toString();
	}
	
	public double getAverageFreeCashierTime() { // Returnerar den tid i snitt som kassor är lediga FEL!!!
		this.sumTimeFreeCheckouts = (double) getFreeCashiers() * currentTime;
		return this.sumTimeFreeCheckouts;
	}
	
	public double getAverageQueueTime() { // Returnerar den tid i snitt som kunder får köa FEL!!!
		this.sumTimeCustomersInQueue = (double) queue.size() * getPaymentTime();
		return this.sumTimeCustomersInQueue;
	}
	
	public double getFreeCashierPercentage() {
		return getFreeCashierTime() / getTime();
	}
	
	public double getClosingTime() {
		return this.closingTime;
	}
	
	public double getArrivalTime() { // Ger ett nytt slumptal på AnkomstTid
		return new ArrivalTime(arrivalLambda).getNextTime(currentTime);
	}
	
	public double getPickupTime() {
		return new PickupTime(pickupL, pickupH).getNextTime(currentTime);
	}
	
	public double getPaymentTime() {
		return new PaymentTime(paymentL, paymentH).getNextTime(currentTime);
	}
	
	public int getCurrentCustomerID() {
		return this.customerID;
	}
	
	public String getCurrentEvent() {
		return this.currentEvent;
	}
	
	public void setCurrentEvent(String currentEvent) {
		this.currentEvent = currentEvent;
	}
	
	public FIFO getFIFO() { // Returnerar FIFO kön
		return this.queue;
	}
	
	public void addFIFO(Customer cr) { // Lägger till en Customer i FIFO kön
		this.queue.add(cr);
	}
	
	public void removeFirstFIFO() { // Tar bort det första Customern i FIFO kön
		this.queue.removeFirst();
	}
	
	public void isEmptyFIFO() { // Returnerar true eller false beroende på om kön är tom eller ej
		this.queue.isEmpty();
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
