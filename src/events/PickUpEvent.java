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
		super.exeEvent(state);
		state.setCurrentCustomerID(customer.getId());
		state.setCurrentEvent("Plock");
		if (state.getFreeCashiers()>0) {
			state.setFreeCashiers(state.getFreeCashiers()-1);
			eventQueue.addEvent(new PaymentEvent(state.getPaymentTime(), customer));		
		}else {
			state.addFIFO(customer); // Så här?
			//Placera kunden i kassakön
		}
		
	}
	
//	@Override
//	public String getName() { // Till supermarketview
//		return "Plock";
//	}

}
