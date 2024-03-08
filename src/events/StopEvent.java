package events;

import state.SupermarketState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/*
 * Den specifika stophändelsen. Innehåller endast konstruktor och SpecificExe (SupermarketState state, EventQueue eventQueue)
 * som påverkar tillståndet och drar i stoppar simuleringen.
 */
public class StopEvent extends Event{
	public StopEvent(double stopTime) {
		super(stopTime);
	}
	
	/*
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och stoppar simuleringen.
	 */
	@Override
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		state.setCurrentEvent("Stop");
		state.notifyObserver();
		state.simBreak();
		state.notifyObserver();
	}
}
