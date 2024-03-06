package events;

import state.SupermarketState;
import state.Customer;

public class StartEvent extends Event{
	public StartEvent() {
		super(0);
	}
	
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		state.currentEvent = "Start";
		eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), new Customer()));
		eventQueue.addEvent(new ClosingEvent(state.getClosingTime()));
	}
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Start";
//	}

}
