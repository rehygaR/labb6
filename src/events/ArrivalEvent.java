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
public class ArrivalEvent extends SupermarketEvent {
	
	/**
	 * Konstruktorn håller reda på händelsens tid och vilken kund som utför händelsen.
	 * @param eventTime
	 * @param customer
	 */
	public ArrivalEvent(double eventTime, Customer customer) {
		super(eventTime,customer);
	}
	
	/**
	 * Returnerar en sträng som beskriver vilken sorts händelse som inträffar.
	 * @return "Ankomst"
	 */
	@Override
	public String getSpecificEvent() {
		return "Ankomst";
	}
	
	/**
	 * Överskriver den generella händelsens SupermarketSpecificExe(SupermarketState state, EventQueue eventQueue) metod.
	 * Hanterar den ankomna kunden och skapar en ny ankomsthändelse om snabbköpet är öppet.
	 * @param state
	 * @param eventQueue
	 */
	@Override
	public void SupermarketSpecificExe (SupermarketState state, EventQueue eventQueue) {
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
