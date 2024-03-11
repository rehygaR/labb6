package events;

import state.SupermarketState;
import state.SimState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika stophändelsen. Syftet är att stoppa hela simuleringen.
 */
public class StopEvent extends Event{
	public StopEvent(double eventTime) {
		super(eventTime);
	}
	
	/**
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och stoppar simuleringen.
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SpecificExe(SimState simstate, EventQueue eventQueue) {
		SupermarketState state=(SupermarketState) simstate;
		state.setCurrentEvent("Stop");
		state.notifyObserver();
		state.simBreak();
		state.notifyObserver();
	}
}
