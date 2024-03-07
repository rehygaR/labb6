package simulator;

import events.*;
import state.SimState;
import view.SimView;
import view.SuperMarketView;


public class Simulator {
	
	
	
	
	
	public void run(SimState state, EventQueue eventQueue, SimView view) {
		int x = 0;
		view.printStart();
		
		while (state.getSimActive()) {
			eventQueue.nextEvent().exeEvent(state, eventQueue);
			state.notifyObserver();
			view.printEvent();
			if (x > 100) {
				break;
			}
			x++;
		}
		view.printStopEvent();
	}

}
