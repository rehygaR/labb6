package view;




import java.util.Observer;
import events.Event;
import state.SupermarketState;
import state.SimState;
import events.EventQueue;

import static random.K.*;

public class SuperMarketView extends simView {
	private SupermarketState state;
	
	public SuperMarketView(SupermarketState state) {
		this.state = state;
		state.addObserver(this);
	}

	public void printStart() {
		System.out.println("PARAMETRAR \n ==========");
		System.out.println("Antal kassor, N..........: " + String.valueOf(N));
		System.out.println("Max som ryms, M..........: " + String.valueOf(M));
		System.out.println("Ankomsthastighet, lambda.: " + String.valueOf(L));
		System.out.println("Plocktider, [P_min..Pmax]: " + "[" + String.valueOf(LOW_COLLECTION_TIME) + ".." + String.valueOf(HIGH_COLLECTION_TIME) + "]");
		System.out.println("Betaltider, [K_min..Kmax]: " + "[" + String.valueOf(LOW_PAYMENT_TIME) + ".." + String.valueOf(HIGH_PAYMENT_TIME) + "]");
		System.out.println("Frö, f...................: " + String.valueOf(SEED));
		
		System.out.println("FÖRLOPP\n=======");
		System.out.println("   Tid Händelse  Kund  ?  led   ledT   I   $   :-(   köat   köT   köar  [Kassakö..]");
	}

	public void printEvent() { 			// event ska vara EventQueue eller Event? Hur får vi vilket event i String format?
		System.out.println(String.valueOf(state.getTime()) + " ");				//tid
		System.out.print(state.getCurrentEvent());										//händelsetyp
		switch(state.getCurrentEvent()) {													//switch sats för att ge rätt inkrement beroende på vilken typ av händelse det är
			case "Start": return;
			
			case "Ankomst": System.out.print("      ");
			
			case "Plock": System.out.print("        ");
			
			case "Betalning": System.out.print("    ");
			
			case "Stänger": System.out.print("      ");
		}
			
		System.out.print(String.valueOf(state.getCurrentCustomerID()) + "  ");								//kund nummer
		
		if(state.open()) {
			System.out.print("Ö    ");
		}
		else {
			System.out.print("S    ");
		}
		
		System.out.print(String.valueOf(state.getFreeCashiers()) + "    ");		//antal lediga kassor
		System.out.print(String.valueOf(state.getFreeCashierTime()) + "    ");		//tid då kassorna varit lediga
		System.out.print(String.valueOf(state.getCurrentCustomers()) + "    ");	//antalet kunder i butiken
		System.out.print(String.valueOf(state.getTotalPayingCustomers()) + "    ");//antal kunder som handlat
		System.out.print(String.valueOf(state.getMissedCustomers()) + "     ");		//antal missade  kunder
		System.out.print(String.valueOf(state.getQueuedCustomers()) + "    ");		//antal som varit i FIFO kön
		System.out.print(String.valueOf(state.getQueueTime())+ "      ");			//total kötid
		System.out.print(String.valueOf(state.getCurrentInQueue()) + "  ");		//antal i kö just nu
		System.out.print(state.getStringQueue());					//vilka kunder som är i kön (getStringQueue ska returnera en sträng och inte en ArrayList
	}
	
	
	public void printStopEvent() {
		System.out.println(String.valueOf(state.getTime()));				//tid
		System.out.print("Stop\n");								
		
		System.out.println("RESULTAT\n========\n");
		System.out.println("1) Av " + String.valueOf((state.getTotalPayingCustomers() + state.getMissedCustomers())) 
				+ " kunder handlade " + String.valueOf(state.getTotalPayingCustomers()) + " medan "
				+ String.valueOf(state.getMissedCustomers()) + " missades.\n");
		
		System.out.println("2) Total tid " + String.valueOf(N) + " kassor varit lediga: " + String.valueOf(state.getFreeCashierTime()) 
				+ " te.\nGenomsnittlig ledig kassatid: " + String.valueOf(state.getAverageFreeCashierTime()) 
				+ "te (dvs" + String.valueOf(state.getFreeCashierPercentage()) + "% av tiden från öppning tills sista kunden betalat).\n");
		
		System.out.println("3) Total tid " + String.valueOf(state.getQueuedCustomers()) 
				+ " kunder tvingats köa: " + String.valueOf(state.getQueueTime()) 
				+ " te.\nGenomsnittlig kötid: " + String.valueOf(state.getAverageQueueTime()) + " te.");
		
	}
	
	/*
	 * boolean open()	returnerar true om öppet
	 * int getFreeCashiers()	returnerar antal lediga kassor
	 * double getFreeCashierTime()	returnerar hur länge kassorna stått ledigt
	 * int getCurrentCustomers()	returnerar hur många som är inne i butiken
	 * int getTotalPayingCustomers()	returnerar hur många som betalt
	 * int getMissedCustomers()	returnerar hur många som inte gått in i butiken pga fullt
	 * int getQueuedCustomers()	returnerar hur många som varit i FIFO kön
	 * double getQueueTime()	returnerar totala tiden som alla kunder stått i kö
	 * int getCurrentInQueue()	returnerar hur många som är i kön just nu
	 * String getStringQueue()	returnerar en sträng av kön
	 * double getAverageFreeCashierTime() returnerar snitt tiden då kassorna varit lediga
	 * double getAverageQueueTime() returnerar snitt tiden som kunder köat
	 */
	

}
