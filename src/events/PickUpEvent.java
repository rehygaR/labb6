package events;

import state.Customer;

import java.util.Queue;

import state.SupermarketState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/*
 * Den specifika upplockningshändelsen. Innehåller endast konstruktor och SpecificExe (SupermarketState state, EventQueue eventQueue)
 * som först påverkar tillståndet och därefter antingen skapar en framtida betalingshändelse för den specifika kunden eller placerar 
 * den i kassakön.
 */
public class PickUpEvent extends Event {
	Customer customer;
	public PickUpEvent(double eventTime, Customer customer) {
		super(eventTime);
		this.customer=customer;
	}
	
	/*
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och skapar en framtida betalingshändelse för kunden om det finns lediga kassor, 
	 * annars placeras kunden i kassakön.
	 */
	@Override
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		state.setCurrentCustomerID(customer.getId());
		state.setCurrentEvent("Plock");
		state.updateFreeCashierTime();
		state.updateTotalQueueTime();
		state.notifyObserver();
		if (state.getFreeCashiers()>0) {
			state.setFreeCashiers(state.getFreeCashiers()-1);
			eventQueue.addEvent(new PaymentEvent(state.getPaymentTime(), customer));		
		}else {
			state.addFIFO(customer);
		}
		
	}
}
