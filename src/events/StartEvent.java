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
public class StartEvent extends SupermarketEvent{
	/**
	 * Konstruktorn sätter händelsens tid till 0.
	 */
	public StartEvent() {
		super(0);
	}
	
	/**
	 * Returnerar en sträng som beskriver vilken sorts händelse som inträffar.
	 * @return "Start"
	 */
	@Override
	public String getSpecificEvent() {
		return "Start";
	}
	/**
	 * Överskriver den generella händelsens SupermarketSpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Lägger till en ankomsthändelse till EventQueue.
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SupermarketSpecificExe(SupermarketState state, EventQueue eventQueue) {
		eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), state.getCS().newCustomer()));
	}
}
