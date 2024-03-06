package events;

import state.SupermarketState;

public class ClosingEvent extends Event {
	public ClosingEvent(double eventTime) {
		super(eventTime);
	}
	
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		state.currentEvent = "Stänger";
    }
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Stänger";
//	}

}
