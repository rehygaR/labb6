package events;

import state.SupermarketState;

public class ClosingEvent extends Event {
	public ClosingEvent(double eventTime) {
		super(eventTime);
	}
	@Override
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		state.setCurrentEvent("Stänger");
    }
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Stänger";
//	}

}
