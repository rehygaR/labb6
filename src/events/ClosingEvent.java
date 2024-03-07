package events;

import state.SupermarketState;

public class ClosingEvent extends Event {
	public ClosingEvent(double eventTime) {
		super(eventTime);
	}
	
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		super.exeEvent(state);
		state.setCurrentEvent("Stänger");
    }
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Stänger";
//	}

}
