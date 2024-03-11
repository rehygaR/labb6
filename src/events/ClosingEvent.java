package events;

import general.EventQueue;
import state.SupermarketState;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika stängningshändelsen. Uppdaterar tillståndet till stängning.
 */
public class ClosingEvent extends SupermarketEvent {
	/**
	 * Konstruktorn håller reda på händelsens tid.
	 * 
	 * @param eventTime
	 */
	public ClosingEvent(double eventTime) {
		super(eventTime);
	}

	/**
	 * Returnerar en sträng som beskriver vilken sorts händelse som inträffar.
	 * 
	 * @return "Stänger"
	 */
	@Override
	public String getSpecificEvent() {
		return "Stänger";
	}

	/**
	 * Överskriver den generella händelsens SupermarketSpecificExe(SupermarketState
	 * state, EventQueue eventQueue) metod. I detta fall innehåller den ingenting
	 * eftersom alla specifika delar finns i överklassen SupermarketEvent.
	 * 
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SupermarketSpecificExe(SupermarketState state, EventQueue eventQueue) {

	}
}
