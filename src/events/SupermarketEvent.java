package events;

import state.SupermarketState;
import state.CustomerSource;
import state.Customer;

public abstract class SupermarketEvent extends Event{
	Customer customer;
	
	/**
	 * Konstruktor med både tid som händelsen inträffar och en kund. Gäller för de händelser som hanterar en specifik kund.
	 * @param eventTime
	 * @param customer
	 */
	public SupermarketEvent(double eventTime, Customer customer) {
		super(eventTime);
        this.customer=customer;
	}
	
	/**
	 * Konstruktor med endast tid som händelsen inträffar. Gäller för de händelser som inte behandlar en kund.
	 * @param eventTime
	 */
	public SupermarketEvent(double eventTime) {
		super(eventTime);
	}
	
	/**
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och påkallar sedan den specifika händelsens exekveringsmetod.
	 * @param state
	 * @param eventQueue
	 */
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		if (customer!=null) {
			state.setCurrentCustomerID(customer.getId());
		}
		state.setCurrentEvent(getSpecificEvent());
		if (state.open()) {
			state.updateFreeCashierTime();
			state.updateTotalQueueTime();
		} else if (getSpecificEvent() != "Ankomst") {
			state.updateFreeCashierTime();
			state.updateTotalQueueTime();
		}
		state.notifyObserver();
		SupermarketSpecificExe(state, eventQueue);
	}
	
	/**
	 * Abstrakt metod som representerar de specifika händelserna.
	 * @param state
	 * @param eventQueue
	 */
	public abstract void SupermarketSpecificExe (SupermarketState state, EventQueue eventQueue);
	
	/**
	 * Abstrakt metod som returnerar en sträng som beskriver de specifika händelserna.
	 * @return String
	 */
	public abstract String getSpecificEvent();
}
