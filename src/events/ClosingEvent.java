package events;

import state.SupermarketState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika stängningshändelsen. Uppdaterar tillståndet till stängning.
 */
public class ClosingEvent extends Event {
	/**
	 * Konstruktorn håller reda på händelsens tid.
	 * @param eventTime
	 */
	public ClosingEvent(double eventTime) {
		super(eventTime);
	}
	
	/**
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Uppdaterar tillståndet.
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		state.setCurrentEvent("Stänger");
		state.updateFreeCashierTime();
		state.updateTotalQueueTime();
		state.notifyObserver();
    }
}
