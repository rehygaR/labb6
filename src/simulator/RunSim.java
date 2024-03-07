package simulator;
import static random.K.*;

import events.ClosingEvent;
import events.EventQueue;
import events.StartEvent;
import events.StopEvent;
import state.SupermarketState;
import view.SuperMarketView;

public class RunSim {
	
	

	public static void main(String[] args) {
		int N = 4;
		
		SupermarketState state = new SupermarketState(N, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
				LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME);
		
		
		EventQueue eventQueue = new EventQueue();
		eventQueue.addEvent(new StartEvent());
		eventQueue.addEvent(new ClosingEvent(100)); //END_TIME
		eventQueue.addEvent(new StopEvent(STOP_TIME)); //StopEvent måste ha double argument
		
		SuperMarketView view = new SuperMarketView(state);
		

		Simulator simulator = new Simulator();
		simulator.run(state, eventQueue, view);
	}

}
