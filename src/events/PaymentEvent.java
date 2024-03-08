package events;

import state.Customer;
import state.SupermarketState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/*
 * Den specifika betalningshändelsen. Innehåller endast konstruktor och SpecificExe (SupermarketState state, EventQueue eventQueue)
 * som först påverkar tillståndet och därefter antingen skapar en framtida betalningshändelse för den första kunden i kassakön 
 * eller ökar antalet lediga kassor.
 */
public class PaymentEvent extends Event{
	Customer customer;
	public PaymentEvent(double eventTime, Customer customer) {
        super(eventTime);
        this.customer=customer;
    }
	
	/*
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och skapar en framtida betalingshändelse för den första kunden i kassakön om det finns en sådan, 
	 * annars ökas antalet lediga kassor.
	 */
	@Override
    public void SpecificExe(SupermarketState state, EventQueue eventQueue) {
		
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
		state.updateFreeCashierTime();
		state.updateTotalQueueTime();
    }
}
