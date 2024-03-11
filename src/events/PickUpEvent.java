package events;

import state.Customer;

import java.util.Queue;

import general.EventQueue;
import state.SupermarketState;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika upplockningshändelsen. Om det finns en ledig kassa får kunden
 * gå och betala direkt annars ställs kunden i kassakön.
 */
public class PickUpEvent extends SupermarketEvent {

	/**
	 * Konstruktorn håller reda på tiden händelsen sker och vilken kund som utför
	 * händelsen.
	 * 
	 * @param eventTime
	 * @param customer
	 */
	public PickUpEvent(double eventTime, Customer customer) {
		super(eventTime, customer);
	}

	/**
	 * Returnerar en sträng som beskriver vilken sorts händelse som inträffar.
	 * 
	 * @return "Plock"
	 */
	@Override
	public String getSpecificEvent() {
		return "Plock";
	}

	/**
	 * Överskriver den generella händelsens SupermarketSpecificExe(SupermarketState
	 * state, EventQueue eventQueue) metod. Ändrar tillståndet och skapar en
	 * framtida betalningshändelse för kunden om det finns lediga kassor, annars
	 * placeras kunden i kassakön.
	 * 
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SupermarketSpecificExe(SupermarketState state, EventQueue eventQueue) {
		if (state.getFreeCashiers() > 0) {
			state.setFreeCashiers(state.getFreeCashiers() - 1);
			eventQueue.addEvent(new PaymentEvent(state.getPaymentTime(), customer));
		} else {
			state.addFIFO(customer);
		}

	}
}
