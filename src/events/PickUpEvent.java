package events;

import state.Customer;

import java.util.Queue;

import state.SupermarketState;

public class PickUpEvent extends Event {
	Customer customer;
	public PickUpEvent(double eventTime, Customer customer) {
		super(eventTime);
		this.customer=customer;
	}
	
	
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		state.customerID = this.customer.getId();
		state.currentEvent = "Plock";
		if (state.getFreeCashiers()>0) {
			state.freeCheckouts-=1;
			eventQueue.addEvent(new PaymentEvent(state.getPaymentTime(), this.customer));		
		}else {
			state.numCustomersInQueue+=1;
			state.queue.add(this.customer); // Så här?
			//Placera kunden i kassakön
		}
	}
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Plock";
//	}

}
