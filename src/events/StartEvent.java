package events;

import state.SupermarketState;
import state.CustomerSource;
//import state.SimState;
import state.SimState;

public class StartEvent extends Event{
	public StartEvent() {
		super(0);
	}

	@Override
	public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		//super.exeEvent(state);
		state.setCurrentEvent("Start");
		eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), state.getCS().newCustomer())); //
		//eventQueue.addEvent(new ClosingEvent(state.getClosingTime()));
		//System.out.print("start");
	}



	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Start";
//	}

}
