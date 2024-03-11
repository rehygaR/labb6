package general;

import events.*;
import state.SupermarketState;
import view.SuperMarketView;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/**
 * Klass som "kör" simulatorn
 */
public class Simulator {

	/**
	 * Metod som exekverar näststående event så länge nödbromsen inte är i
	 * 
	 * @param state
	 * @param eventQueue
	 */
	public void run(SupermarketState state, EventQueue eventQueue) {

		while (state.getSimActive()) {
			eventQueue.nextEvent().exeEvent(state, eventQueue);

		}

	}

}
