package events;
import state.SimState;
import state.Customer;
import state.SupermarketState;
import state.ArrivalTime;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Den specifika ankomsthändelsen. Så länge snabbköpet är öppet skapas för varje ankommen kund en ny ankomsthändelse för en
 * ny kund och om det inte är fullt släpps den ankomna kunden in och en framtida upplockningshändelse skapas för den. 
 * Om snabbköpet är stängt händer ingenting med den ankomna kunden och ingen ny ankomsthändelse skapas.
 */
public class ArrivalEvent extends Event {
	Customer customer;
	
	/**
	 * Konstruktorn håller reda på händelsens tid och håller reda på vilken kund som utför händelsen.
	 * @param eventTime
	 * @param customer
	 */
	public ArrivalEvent(double eventTime, Customer customer) {
		super(eventTime);
		this.customer=customer;
	}
	
	/**
	 * Överskriver den generella händelsens SpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Ändrar tillståndet och hanterar därefter den ankomna kunden och skapar en ny ankomsthändelse om snabbköpet är öppet.
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SpecificExe (SupermarketState state, EventQueue eventQueue) {
		
		state.setCurrentCustomerID(customer.getId());
		state.setCurrentEvent("Ankomst");
		state.updateFreeCashierTime();
		state.updateTotalQueueTime();
		state.notifyObserver();
		
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
