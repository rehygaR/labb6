package events;
import state.SimState;
import state.Customer;
import state.SupermarketState;
import state.ArrivalTime;
 

public class ArrivalEvent extends Event {
	Customer customer;
	public ArrivalEvent(double eventTime, Customer customer) {
		super(eventTime);
		this.customer=customer;
	}
	 
//	@Override
//	public void exeEvent(SimState state, EventQueue eventQueue) {
//		if (state.open()==false) {
//			continue;
//		}else if(compare(state.getCurrentCustomers(), state.maxNumberOfCustomers)==0){
//			State.numCustomersMissed+=1;
//		}else {
//			state.numberOfCustomers+=1;
//			eventQueue.addEvent(new PickUpEvent(state.getPickupTime()));
//			eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime()));
//		}
//	}
	@Override
	public void SpecificExe (SupermarketState state, EventQueue eventQueue) {
		
		state.setCurrentCustomerID(customer.getId());
		state.setCurrentEvent("Ankomst");
		if (state.open()==false) {
			return;
		}else if(state.getCurrentCustomers() == state.getMaxNumOfCustomers()){
			state.setMissedCustomers(); // Adderar en till missade kunder
		}else {
			state.addCurrrentCustomers();
			eventQueue.addEvent(new PickUpEvent(state.getPickupTime(), customer)); // Ger tiden för pickupevent 
			eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), state.getCS().newCustomer())); //state.getArrivalTime()
		}
		
		

	}

	
	
	
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Ankomst";
//	}


}
