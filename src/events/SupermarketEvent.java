package events;

import state.SupermarketState;
import state.CustomerSource;
import general.Event;
import general.EventQueue;
import general.SimState;
import state.Customer;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/**
 * Mellanklass mellan den generella händelsen och de specifika händelserna. Gör
 * de gemensamma delarna som att uppdatera tillståndet och notifiera
 * observerare.
 */
public abstract class SupermarketEvent extends Event {
	
	/**
	 * Tar emot en kund, hanterar kunden från eventen
	 */
	protected Customer customer;

	/**
	 * Konstruktor med både tid som händelsen inträffar och en kund. Gäller för de
	 * händelser som hanterar en specifik kund.
	 * 
	 * @param eventTime the time the event happens.
	 * @param customer the customer that performs the event.
	 */
	public SupermarketEvent(double eventTime, Customer customer) {
		super(eventTime);
		this.customer = customer;
	}

	/**
	 * Konstruktor med endast tid som händelsen inträffar. Gäller för de händelser
	 * som inte behandlar en kund.
	 * 
	 * @param eventTime the time the event happens.
	 */
	public SupermarketEvent(double eventTime) {
		super(eventTime);
	}

	/**
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state,
	 * EventQueue eventQueue) metod. Ändrar tillståndet och påkallar sedan den
	 * specifika händelsens exekveringsmetod.
	 * 
	 * @param simstate a SimState.
	 * @param eventQueue an EventQueue.
	 */
	@Override
	public void SpecificExe(SimState simstate, EventQueue eventQueue) {
		SupermarketState state = (SupermarketState) simstate;
		if (customer != null) {
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
	 * 
	 * @param state a SupermarketState.
	 * @param eventQueue an EventQueue.
	 */
	public abstract void SupermarketSpecificExe(SupermarketState state, EventQueue eventQueue);

	/**
	 * Abstrakt metod som överskrivs av de specifika händelserna. Ska returnera en
	 * sträng som beskriver de specifika händelserna.
	 * @return specificEvent;
	 */
	public abstract String getSpecificEvent();
}
