package view;

import static simulator.K.*;

import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import state.SupermarketState;
import general.Event;
import general.EventQueue;
import general.SimState;
import general.SimView;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/**
 * En klass för den specifika SupermarketView vars uppgift är att observera
 * staten och skriva utskrifter då staten ändras.
 */
public class SuperMarketView extends SimView {

	private DecimalFormat df = new DecimalFormat("0.00");
	private SupermarketState state;
	private double lastPaymentTime;
	private int updateNr = 0;

	/**
	 * 
	 * @param state
	 * @param print Konstruktor som ser till att view instansen observerar den state
	 *              som skickats in har även en print boolean som skickas med
	 *              beroende om man vill ha utskrifter eller inte
	 */
	public SuperMarketView(SupermarketState state, boolean print) {
		this.state = state;
		if (print) {
			state.addObserver(this);
		}

	}

	/**
	 * Printar simulatorns parametrar
	 */
	@Override
	public void printStart() {
		System.out.println("PARAMETRAR \n ==========");
		System.out.println("Antal kassor, N..........: " + String.valueOf(state.getNumCheckouts()));
		System.out.println("Max som ryms, M..........: " + String.valueOf(M));
		System.out.println("Ankomsthastighet, lambda.: " + String.valueOf(L));
		System.out.println("Plocktider, [P_min..Pmax]: " + "[" + String.valueOf(LOW_COLLECTION_TIME) + ".."
				+ String.valueOf(HIGH_COLLECTION_TIME) + "]");
		System.out.println("Betaltider, [K_min..Kmax]: " + "[" + String.valueOf(LOW_PAYMENT_TIME) + ".."
				+ String.valueOf(HIGH_PAYMENT_TIME) + "]");
		System.out.println("Frö, f...................: " + String.valueOf(SEED));

		System.out.println("\nFÖRLOPP\n=======");
		System.out.println("   Tid Händelse  Kund  ?  led   ledT   I   $   :-(   köat   köT   köar  [Kassakö..]");
	}

	/**
	 * Printar det specifika eventet
	 */
	@Override
	public void printEvent() {
		if (state.getCurrentEvent() != "Stop") {
			System.out.print(customFormat(String.valueOf(df.format(state.getTime())), 6) + " ");
		} else {
			System.out.print(customFormat(String.valueOf(df.format(state.getStopTime())), 6) + " ");
		}
		System.out.print(state.getCurrentEvent());
		switch (state.getCurrentEvent()) { // Switch sats för att ge rätt inkrement
		case "Start":
			System.out.print("\n");

			return;

		case "Ankomst":
			System.out.print("  ");
			break;

		case "Plock":
			System.out.print("    ");
			break;

		case "Betalning":
			lastPaymentTime = state.getTime();
			break;

		case "Stop":
			System.out.print("\n");
			return;
		}

		if (state.getCurrentEvent() == "Stänger") {
			System.out.print(("    ---  "));
		} else {
			System.out.print(customFormat(String.valueOf(state.getCurrentCustomerID()), 5) + "  ");
		}

		if (state.open()) {
			System.out.print("Ö");
		} else {
			System.out.print("S");
		}

		System.out.print(customFormat(String.valueOf(state.getFreeCashiers()), 5)); // antal lediga kassor

		System.out.print(customFormat(String.valueOf(df.format(state.getFreeCashierTime())), 7)); // tid då kassorna
																									// varit lediga
		System.out.print(customFormat(String.valueOf(state.getCurrentCustomers()), 4)); // antalet kunder i butiken
		System.out.print(customFormat(String.valueOf(state.getTotalPayingCustomers()), 4));// antal kunder som handlat
		System.out.print(customFormat(String.valueOf(state.getMissedCustomers()), 5)); // antal missade kunder
		System.out.print(customFormat(String.valueOf(state.getTotalQueuedCustomers()), 7)); // antal som varit i FIFO
																							// kön
		System.out.print(customFormat(String.valueOf(df.format(state.getTotalQueueTime())), 7)); // total kötid
		System.out.print(customFormat(String.valueOf(state.getQueuedCustomers()), 7) + "  "); // antal i kö just nu
		System.out.println(state.getStringQueue()); // vilka kunder som är i kön (getStringQueue ska returnera en sträng
													// och inte en ArrayList

	}

	/**
	 * Printar de slutresultat av simulationen
	 */
	@Override
	public void printResult() {

		System.out.println("\nRESULTAT\n========\n");
		System.out.println("1) Av " + String.valueOf((state.getTotalPayingCustomers() + state.getMissedCustomers()))
				+ " kunder handlade " + String.valueOf(state.getTotalPayingCustomers()) + " medan "
				+ String.valueOf(state.getMissedCustomers()) + " missades.\n");

		System.out.println("2) Total tid " + String.valueOf(state.getNumCheckouts()) + " kassor varit lediga: "
				+ String.valueOf(df.format(state.getFreeCashierTime())) + " te.\nGenomsnittlig ledig kassatid: "
				+ String.valueOf(df.format(state.getFreeCashierTime() / state.getNumCheckouts())) + " te (dvs "
				+ String.valueOf(
						df.format(state.getFreeCashierTime() / state.getNumCheckouts() / lastPaymentTime * 100))
				+ "% av tiden från öppning tills sista kunden betalat).\n");

		System.out.println("3) Total tid " + String.valueOf(state.getTotalQueuedCustomers()) + " kunder tvingats köa: "
				+ String.valueOf(df.format(state.getTotalQueueTime())) + " te.\nGenomsnittlig kötid: "
				+ String.valueOf(df.format(state.getTotalQueueTime() / state.getTotalQueuedCustomers())) + " te.");

	}

	/**
	 * En uppdate metod som kallas då state notifierar observatören
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (updateNr == 0) {
			printStart();
		}
		if (!state.getSimActive()) {
			printResult();
		} else {
			printEvent();
		}

		updateNr++;
	}

	private String customFormat(String form, int spacing) {
		String newString = "";
		int copyFrom = spacing - form.length();

		for (int i = 0; i < copyFrom; i++) {

			newString = newString + " ";

		}

		return newString + form;

	}

}
