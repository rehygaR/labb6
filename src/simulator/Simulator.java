package simulator;

import events.*;
import state.SimState;
import state.SupermarketState;
import view.SimView;
import view.SuperMarketView;


public class Simulator {
	
	
	
	
	
	public void run(SupermarketState state, EventQueue eventQueue, SimView view, boolean print) {
		int x = 0;
		
		if (print) {
			view.printStart();
		}
		
		
		while (state.getSimActive()) {
			eventQueue.nextEvent().exeEvent(state, eventQueue);
			//eventQueue.nextEvent().exeEvent(state);
			state.notifyObserver();
			
			if (print) {
				view.printEvent();
			}
			
//			if (x > 10) { // debug
//				break;
//			}
//			x++;
		}
		
		if (print) {
			view.printResult();
		}
		
	}

}
