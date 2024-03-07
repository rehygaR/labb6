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
		//state.customerID = this.customer.getId();
    	super.exeEvent(state);
    	state.setCurrentCustomerID(this.customer.getId());
		state.setCurrentEvent("Betalning");
		state.minusCurrentCustomers();
		state.addTotalPayingCustomers();
		if(state.getQueuedCustomers()>0) {
			eventQueue.addEvent(new PaymentEvent(state.getPaymentTime(),(Customer) state.getFIFO().first()));
			state.removeFirstFIFO();
		}else {
			state.setFreeCashiers(state.getFreeCashiers()+1);
		}
    }
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Betalning";
//	}

}
