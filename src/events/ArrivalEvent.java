package events;
import state.SimState;
import state.Customer;
import state.SupermarketState;
import state.ArrivalTime;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/*
 * Den specifika ankomsthändelsen. Innehåller endast konstruktor och SpecificExe (SupermarketState state, EventQueue eventQueue)
 * som först påverkar tillståndet och därefter lägger till en upplockningshändelse för den ankomna kunden och en ny ankomsthändelse
 * i EventQueue.
 */
public class ArrivalEvent extends Event {
	Customer customer;
	public ArrivalEvent(double eventTime, Customer customer) {
		super(eventTime);
		this.customer=customer;
	}
	
	/*
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och lägger därefter till en upplockningshändelse för den ankomna kunden och en ny ankomsthändelse
	 * i EventQueue.
	 */
	@Override
	public void SpecificExe (SupermarketState state, EventQueue eventQueue) {
		
		state.setCurrentCustomerID(customer.getId());
		state.setCurrentEvent("Ankomst");
		state.updateFreeCashierTime();
		state.updateTotalQueueTime();
		if (state.open()==false) {
			return;
		}else if(state.getCurrentCustomers() == state.getMaxNumOfCustomers()){
			state.setMissedCustomers(); //Lägger till en missad kund.
			eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), state.getCS().newCustomer()));//Skapar en framtida ankomsthändelse.
		}else {
			state.addCurrrentCustomers();
			eventQueue.addEvent(new PickUpEvent(state.getPickupTime(), customer)); //Skapar en framtida upplockningshändelse.
			eventQueue.addEvent(new ArrivalEvent(state.getArrivalTime(), state.getCS().newCustomer()));//Skapar en framtida ankomsthändelse.
		}
	}
}
