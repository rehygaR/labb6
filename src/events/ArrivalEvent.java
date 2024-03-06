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
	 Hej
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

	
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		// TODO Auto-generated method stub
		
		state.customerID = this.customer.getId();
		state.currentEvent = "Ankomst";
		if (state.open()==false) {
			return;
		}else if(state.getCurrentCustomers() == state.getMaxNumOfCustomers()){
			state.numCustomersMissed++;
		}else {
			state.numOfCustomers++;
			eventQueue.addEvent(new PickUpEvent(state.getPickupTime(), this.customer)); // Ger tiden för pickupevent
			eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), new Customer()));
		}
		

	}
	
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Ankomst";
//	}


}
