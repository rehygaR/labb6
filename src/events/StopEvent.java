package events;

import state.SupermarketState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika stophändelsen. Syftet är att stoppa hela simuleringen.
 */
public class StopEvent extends Event{
	public StopEvent(double stopTime) {
		super(stopTime);
	}
	
	/**
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och stoppar simuleringen.
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		state.setCurrentEvent("Stop");
		state.notifyObserver();
		state.simBreak();
		state.notifyObserver();
	}
}
