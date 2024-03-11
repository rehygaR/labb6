package events;

import general.Event;
import general.EventQueue;
import general.SimState;
import state.SupermarketState;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika stophändelsen. Syftet är att stoppa hela simuleringen.
 */
public class StopEvent extends Event {
	/**
	 * Konstruktorn håller reda på händelsens tid.
	 * 
	 * @param eventTime the time the event happens.
	 */
	public StopEvent(double eventTime) {
		super(eventTime);
	}

	/**
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state,
	 * EventQueue eventQueue) metod. Ändrar tillståndet och stoppar simuleringen.
	 * 
	 * @param state a SimState.
	 * @param eventQueue an EventQueue.
	 */
	@Override
	public void SpecificExe(SimState simstate, EventQueue eventQueue) {
		SupermarketState state = (SupermarketState) simstate;
		state.setCurrentEvent("Stop");
		state.notifyObserver();
		state.simBreak();
		state.notifyObserver();
	}
}
