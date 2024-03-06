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
	public int numOfCustomers; // Antal customers i butiken
	private int numCheckouts; // Ska tilldelas från vår runSim där detta anges
	public int freeCheckouts; // Detta ska räknas ut genom att ta i akt på FIFO tillståndet och antal personer i snabbköpet
	private double sumTimeFreeCheckouts; // Summering av tiden som det funnits lediga kassor. Slutas räknas då sista kunden betalat och lämnat snabbköpet. (Även om det kommer en ny kund som inte kommer in pga stängning)
	public int numCustomersLeaving; // Antal kunder som handlat och lämnar snabbköpet (genomgått alla kund-händelser)
	public int numCustomersInQueue; // Antal kunder som är i kassakön (FIFO:n)
	private double sumTimeCustomersInQueue; // Summering av tiden som en kund stått i kön.
	public int numCustomersMissed; // Missade kunder
	public int totalQueuedCustomers;
	private double closingTime;
	public int customerID;
	private double arrivalLambda;
	private double pickupL;
	private double pickupH;
	private double paymentL;
	private double paymentH;
	public String currentEvent;
	
	//private double currentTime; // Nuvarande tiden för hela simuleringen. Sluta räknas efter att sista kunden betalat.
	
	//protected double currentTimeCustomer = 0; // Nuvarande tiden för en specifik kund. Sluta räknas efter att sista kunden betalat.
	
	public ArrivalTime arrive;// = new ArrivalTime(4.0); // Ankomsttidskälla, 4.0 bytsut mot det man anger i runSim
	public PickupTime pickup;// = new PickupTime(0.5,1.0); // Plocktidskälla, parametrar byts ut mot de angivna i runSim ( variabler)
	public PaymentTime payment; // = new PaymentTime(0.5,1.0); // Betalningstidskälla, samma som ovan
	public FIFO queue; //= new FIFO();
	
	public SupermarketState(int antalKassor, int maxCustomers, double arrivalLambda,
			double pickupL, double pickupH, double paymentL, double paymentH, double closingTime){ // Konstruktor, behövs detta?
		this.numCheckouts = antalKassor;
		//this.currentTime = super.currentTime;
		this.numOfCustomers = 0;
		this.freeCheckouts = numCheckouts;
		this.queue = new FIFO();
		this.pickup = new PickupTime(pickupL, pickupH);
		this.payment = new PaymentTime(paymentL, paymentH);
		this.maxNumOfCustomers = maxCustomers;
		this.sumTimeCustomersInQueue = 0.0;
		this.numCustomersLeaving = 0;
		this.numCustomersInQueue = 0;
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
	 * Getters
	 */
	public boolean open() {
		if (getTime() >= closingTime) { // 10 placeholder, 10 står för tiden när butiken stänger
			return false;
		} else {
			return true;
		}
	}
	
	public int getMaxNumOfCustomers() {
		return maxNumOfCustomers;
	}
	
	public int getCurrentCustomers() {
		return numOfCustomers;
	}
	
	public int getNumCheckouts() {
		return numCheckouts;
	}
	
	public int getFreeCashiers() {
		return freeCheckouts;
	}
	
	public double getFreeCashierTime() {
		sumTimeFreeCheckouts = (double) getFreeCashiers() * getTime();
		return sumTimeFreeCheckouts;
	}
	
	public int getTotalPayingCustomers() {
		return numCustomersLeaving;
	}
	
	public int getCurrentInQueue() {
		return numCustomersInQueue;
	}
	
	public double getQueueTime() {
		sumTimeCustomersInQueue = (double) getCurrentInQueue() * payment.getNextTime(currentTime);
		return sumTimeCustomersInQueue;
	}
	
	public int getMissedCustomers() {
		return numCustomersMissed;
	}
	
	public int getQueuedCustomers() {
		return totalQueuedCustomers;
	}
	
	public String getStringQueue() { // Returnerar en sträng av kön, id på kund och vilken plats (index 0 = längst fram, FIFO)
		return queue.toString();
	}
	
	public double getAverageFreeCashierTime() { // Returnerar den tid i snitt som kassor är lediga
		sumTimeFreeCheckouts = (double) getFreeCashiers() * currentTime;
		return sumTimeFreeCheckouts;
	}
	
	public double getAverageQueueTime() { // Returnerar den tid i snitt som kunder får köa
		sumTimeCustomersInQueue = (double) getCurrentInQueue() * payment.getNextTime(currentTime);
		return sumTimeCustomersInQueue;
	}
	
	public double getFreeCashierPercentage() {
		return getFreeCashierTime() / getTime();
	}
	
	public double getClosingTime() {
		return closingTime;
	}
	
	public double getArrivalTime() {
		return new ArrivalTime(arrivalLambda).getNextTime(currentTime);
	}
	
	public double getPickupTime() {
		return new PickupTime(pickupL, pickupH).getNextTime(currentTime);
	}
	
	public double getPaymentTime() {
		return new PaymentTime(paymentL, paymentH).getNextTime(currentTime);
	}
	
	public int getCurrentCustomerID() {
		return customerID;
	}
	
	public String getCurrentEvent() {
		return currentEvent;
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
