package events;

import state.SupermarketState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/*
 * Den specifika stängningshändelsen. Innehåller endast konstruktor och SpecificExe (SupermarketState state, EventQueue eventQueue)
 * som endast påverkar tillståndet.
 */
public class ClosingEvent extends Event {
	public ClosingEvent(double eventTime) {
		super(eventTime);
	}
	
	/*
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet till "Stänger".
	 */
	@Override
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		
		
		state.setCurrentEvent("Stänger");
		state.updateFreeCashierTime();
		state.updateTotalQueueTime();
		state.notifyObserver();
		
    }
}
