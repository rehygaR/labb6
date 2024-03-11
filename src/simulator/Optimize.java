package simulator;

import static simulator.K.*;

import java.util.Random;

import events.ClosingEvent;
import events.StartEvent;
import events.StopEvent;
import general.EventQueue;
import general.Simulator;
import state.SupermarketState;
import view.SuperMarketView;

/**
 * @author Vilma Axling, David Strommer, Jonatan Fredriksson
 */

/**
 * Klass vars syfte är att optimera simulatorns parametrar
 */
public class Optimize {

	/**
	 * Main metod som kör klassens optimerings metoder
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SupermarketState test = getState(4, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, LOW_PAYMENT_TIME,
				HIGH_PAYMENT_TIME, END_TIME, STOP_TIME, SEED);

		int test2 = getOptimalNumCashiers(M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, LOW_PAYMENT_TIME,
				HIGH_PAYMENT_TIME, END_TIME, STOP_TIME, SEED);
		
		printParameters();

		System.out.println("Optimalt antal kassor för test 2 (metod 2): " + test2);

		int test3 = getOptimalMaxOptimalCashiers(SEED);

		System.out.println("Optimalt antal kassor för test 3 (metod 3): " + test3);
	}

	/**
	 * Ger tillståndet med de givna parametrarna (kör simuleringen utan att skriva
	 * ut)
	 * 
	 * @param N
	 * @param M
	 * @param L
	 * @param LOW_COLLECTION_TIME
	 * @param HIGH_COLLECTION_TIME
	 * @param LOW_PAYMENT_TIME
	 * @param HIGH_PAYMENT_TIME
	 * @param END_TIME
	 * @param STOP_TIME
	 * @param SEED
	 * @return state
	 */
	private static SupermarketState getState(int N, int M, double L, double LOW_COLLECTION_TIME,
			double HIGH_COLLECTION_TIME, double LOW_PAYMENT_TIME, double HIGH_PAYMENT_TIME, double END_TIME,
			double STOP_TIME, int SEED) {

		SupermarketState state = new SupermarketState(N, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
				LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME, STOP_TIME, SEED);

		EventQueue eventQueue = new EventQueue();
		eventQueue.addEvent(new StartEvent());
		eventQueue.addEvent(new ClosingEvent(END_TIME)); //
		eventQueue.addEvent(new StopEvent(STOP_TIME)); // StopEvent måste ha double argument

		SuperMarketView view = new SuperMarketView(state, false);
		Simulator simulator = new Simulator();
		simulator.run(state, eventQueue);

		return state;

	}

	/**
	 * Denna metod returnerar optimala antalet kassor
	 * 
	 * @param M
	 * @param L
	 * @param LOW_COLLECTION_TIME
	 * @param HIGH_COLLECTION_TIME
	 * @param LOW_PAYMENT_TIME
	 * @param HIGH_PAYMENT_TIME
	 * @param END_TIME
	 * @param STOP_TIME
	 * @param SEED
	 * @return Nopt
	 */
	private static int getOptimalNumCashiers(int M, double L, double LOW_COLLECTION_TIME, double HIGH_COLLECTION_TIME,
			double LOW_PAYMENT_TIME, double HIGH_PAYMENT_TIME, double END_TIME, double STOP_TIME, int SEED) {

		int Nopt = 0;
		int prevMissedCustomers = SEED;

		for (int i = 1; i <= M; i++) {

			SupermarketState optimal = getState(i, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, LOW_PAYMENT_TIME,
					HIGH_PAYMENT_TIME, END_TIME, STOP_TIME, SEED);

			if (optimal.getMissedCustomers() < prevMissedCustomers) {
				prevMissedCustomers = optimal.getMissedCustomers();
				Nopt = i;

			}

		}
		return Nopt;
	}

	/**
	 * Ger det maximala minimala antalet kassor.
	 * 
	 * @param SEED
	 * @return Nopt3
	 */
	private static int getOptimalMaxOptimalCashiers(int SEED) {

		Random rnd = new Random(SEED);
		int Nopt3 = 0;
		int Nopt;
		int counter = 0;

		while (counter < 100) {

			counter += 1;
			Nopt = getOptimalNumCashiers(M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, LOW_PAYMENT_TIME,
					HIGH_PAYMENT_TIME, END_TIME, STOP_TIME, rnd.nextInt());

			if (Nopt3 < Nopt) {
				Nopt3 = Nopt;
				counter = 0;
			}

		}
		

		return Nopt3;
	}
	
	private static void printParameters() {
		System.out.println("PARAMETRAR \n ==========");
		System.out.println("Max som ryms, M..........: " + String.valueOf(M));
		System.out.println("Ankomsthastighet, lambda.: " + String.valueOf(L));
		System.out.println("Plocktider, [P_min..Pmax]: " + "[" + String.valueOf(LOW_COLLECTION_TIME) + ".."
				+ String.valueOf(HIGH_COLLECTION_TIME) + "]");
		System.out.println("Betaltider, [K_min..Kmax]: " + "[" + String.valueOf(LOW_PAYMENT_TIME) + ".."
				+ String.valueOf(HIGH_PAYMENT_TIME) + "]");
		System.out.println("Frö, f...................: " + String.valueOf(SEED) + "\n");
		
		System.out.println("Stängning sker tiden " + END_TIME + " och stophändelsen sker tiden " + STOP_TIME +  "\n");
	}

}
