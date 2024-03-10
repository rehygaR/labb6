package events;

import state.SupermarketState;
import state.CustomerSource;
//import state.SimState;
import state.SimState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika starthändelsen. Syftet är att starta upp kedjan med händelser genom att skapa en framtida ankomsthändelse.
 */
public class StartEvent extends Event{
	/**
	 * Konstruktorn sätter händelsens tid till 0.
	 */
	public StartEvent() {
		super(0);
	}
	
	/**
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och lägger till en ankomsthändelse till EventQueue.
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		state.setCurrentEvent("Start");
		state.notifyObserver();
		eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), state.getCS().newCustomer()));
	}
}
