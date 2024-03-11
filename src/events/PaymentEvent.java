package events;

import general.EventQueue;
import state.Customer;
import state.SupermarketState;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika betalningshändelsen. Syftet är att uppdatera tillståndet och om
 * det finns kunder i kassakön får den längst fram i kön komma fram till kassan
 * och betala. Om kassakön är tom blir kassan istället ledig.
 */
public class PaymentEvent extends SupermarketEvent {

	/**
	 * Konstruktorn håller reda på tiden händelsen sker och vilken kund som utför
	 * händelsen.
	 * 
	 * @param eventTime
	 * @param customer
	 */
	public PaymentEvent(double eventTime, Customer customer) {
		super(eventTime, customer);
	}

	/**
	 * Returnerar en sträng som beskriver vilken sorts händelse som inträffar.
	 * 
	 * @return "Betalning"
	 */
	public String getSpecificEvent() {
		return "Betalning";
	}

	/**
	 * Överskriver den generella händelsens SupermarketSpecificExe(SupermarketState
	 * state, EventQueue eventQueue) metod. Ändrar tillståndet och skapar en
	 * framtida betalingshändelse för den första kunden i kassakön om det finns en
	 * sådan, annars ökas antalet lediga kassor.
	 * 
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SupermarketSpecificExe(SupermarketState state, EventQueue eventQueue) {
		state.minusCurrentCustomers();
		state.addTotalPayingCustomers();
		if (state.getQueuedCustomers() > 0) {
			eventQueue.addEvent(new PaymentEvent(state.getPaymentTime(), (Customer) state.getFIFO().first()));
			state.removeFirstFIFO();
		} else {
			state.setFreeCashiers(state.getFreeCashiers() + 1);
		}

	}
}
