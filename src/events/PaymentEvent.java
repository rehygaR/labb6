package events;

import state.Customer;
import state.SupermarketState;

public class PaymentEvent extends Event{
	Customer customer;
	public PaymentEvent(double eventTime, Customer customer) {
        super(eventTime);
        this.customer=customer;
    }
	
	
    public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		state.customerID = this.customer.getId();
		state.currentEvent = "Betalning";
		state.numOfCustomers-=1;
		state.numCustomersLeaving+=1;
		if(state.numCustomersInQueue>0) {
			eventQueue.addEvent(new PaymentEvent(state.getPaymentTime(),this.customer));
			state.numCustomersInQueue-=1;
		}else {
			state.freeCheckouts+=1;
		}
    }
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Betalning";
//	}

}
