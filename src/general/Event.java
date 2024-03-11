package general;

import state.SupermarketState;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Abstrakt klass som ärvs av de specifika eventen. Innehåller metoder för att
 * hålla reda på tiden händelsen inträffar, det som ska hända när händelsen
 * inträffar och en abstrakt metod som representerar det som händer i de
 * specifika händelserna.
 */

public abstract class Event {
	private double eventTime;

	/**
	 * Konstruktorn håller reda på tiden händelsen inträffar.
	 * 
	 * @param eventTime
	 */
	public Event(double eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * Getter för händelsens tid.
	 * 
	 * @return eventTime.
	 */
	public double getEventTime() {
		return eventTime;
	}

	/**
	 * Händelsens utförandemetod. Uppdaterar tillståndets tid till händelsens tid
	 * och utför sedan den specifika delen av händelsen.
	 * 
	 * @param state
	 * @param eventQueue
	 */
	public void exeEvent(SimState state, EventQueue eventQueue) {
		if (eventTime != state.getStopTime()) {
			state.setTime(eventTime);
		}
		SpecificExe(state, eventQueue);
	}

	/**
	 * Abstrakt metod som representerar de specifika händelserna.
	 * 
	 * @param state
	 * @param eventQueue
	 */
	public abstract void SpecificExe(SimState state, EventQueue eventQueue);

}