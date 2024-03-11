package simulator;

import static random.K.*;

import events.ClosingEvent;
import events.EventQueue;
import events.StartEvent;
import events.StopEvent;
import state.SupermarketState;
import view.SuperMarketView;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/**
 * Klass som "sätter igång" simulatorn med givna parametrar
 */
public class RunSim {

	/**
	 * Main metod som skapar en state, eventqueue med 3 events och en vy, sedan körs
	 * simulatorn
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 2;

		SupermarketState state = new SupermarketState(N, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
				LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME, STOP_TIME, SEED);

		EventQueue eventQueue = new EventQueue();
		eventQueue.addEvent(new StartEvent());
		eventQueue.addEvent(new ClosingEvent(END_TIME)); //
		eventQueue.addEvent(new StopEvent(STOP_TIME)); // StopEvent måste ha double argument

		SuperMarketView view = new SuperMarketView(state, true);

		Simulator simulator = new Simulator();
		simulator.run(state, eventQueue);
	}

}
