package events;

import state.SupermarketState;
import state.Customer;
//import state.SimState;
import state.SimState;

public class StartEvent extends Event{
	public StartEvent() {
		super(0);
	}
	
	
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		//super.exeEvent(state);
		state.setCurrentEvent("Start");
		eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), new Customer()));
		//eventQueue.addEvent(new ClosingEvent(state.getClosingTime()));
		System.out.print("start");
	}


	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Start";
//	}

}
