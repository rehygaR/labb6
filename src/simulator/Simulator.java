package simulator;

import events.*;
import state.SimState;
import state.SupermarketState;
import view.SimView;
import view.SuperMarketView;


public class Simulator {
	
	
	
	
	
	public void run(SupermarketState state, EventQueue eventQueue) {
		
		while (state.getSimActive()) {
			eventQueue.nextEvent().exeEvent(state, eventQueue);
			
		}
		
		
		
	}

}
