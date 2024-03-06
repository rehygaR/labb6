package simulator;

import events.*;
import state.SimState;


public class Simulator {
	
	EventQueue eventQueue = new EventQueue();
	
	

	public Simulator() {
		
		eventQueue.addEvent(new StartEvent());
		
	}
	
	public void run(SimState state) {
		while (state.getSimActive()) {
			eventQueue.nextEvent().exeEvent(state, eventQueue);
		}
	}

}
