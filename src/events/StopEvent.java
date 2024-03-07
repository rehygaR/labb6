package events;

import state.SupermarketState;

public class StopEvent extends Event{
	public StopEvent(double stopTime) {
		super(stopTime);
	}
	
	
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		super.exeEvent(state);
		state.setCurrentEvent("Stop");
		state.simBreak();
	}
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Stop";
//	}

}
