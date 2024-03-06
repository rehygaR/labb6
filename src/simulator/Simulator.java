package simulator;

import events.*;
import state.SimState;


public class Simulator {
	
	EventQueue eventQueue;
	
	
	
	public void run(SimState state, EventQueue eventQueue) {
		
		while (state.getSimActive()) {
			eventQueue.nextEvent().exeEvent(state, eventQueue);
		}
	}

}
