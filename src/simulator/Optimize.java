package simulator;

import static random.K.*;

import java.util.Random;

import events.ClosingEvent;
import events.EventQueue;
import events.StartEvent;
import events.StopEvent;
import state.SupermarketState;
import view.SuperMarketView;

public class Optimize {

	public static void main(String[] args) {
//		SupermarketState state = new SupermarketState(1, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
//				LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME);
//		int Nopt = 0;
//		int prevMissedCustomers = state.getMissedCustomers();
//		
//		for (int i = 2; i <=  M; i++) {
//			SupermarketState state = new SupermarketState(i, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
//					LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME);
//			
			
			
//			if(state.getMissedCustomers() < prevMissedCustomers) {
//				prevMissedCustomers = state.getMissedCustomers();
//				Nopt = i;
//				
//			}
		
		SupermarketState test = metod1(4, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
				LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME, SEED);
		
		
		int test2 = metod2(M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
				LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME, SEED);
		
		System.out.println("Optimalt antal kassor för test 2: " + test2);
		
		int test3 = metod3(SEED);
		
		System.out.println("Optimalt antal kassor för test 3: " + test3);
		}
	
		
		
		

	
	
	public static SupermarketState metod1(int N, int M, double L, double LOW_COLLECTION_TIME, double HIGH_COLLECTION_TIME,
			double LOW_PAYMENT_TIME, double HIGH_PAYMENT_TIME, double END_TIME, double STOP_TIME, int SEED) {
		
		SupermarketState state = new SupermarketState(N, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
				LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME, SEED);
		
		
		EventQueue eventQueue = new EventQueue();
		eventQueue.addEvent(new StartEvent());
		eventQueue.addEvent(new ClosingEvent(END_TIME)); //
		eventQueue.addEvent(new StopEvent(STOP_TIME)); //StopEvent måste ha double argument
		
		SuperMarketView view = new SuperMarketView(state, false);
		

		Simulator simulator = new Simulator();
		simulator.run(state, eventQueue);
		
		return state;
		
	}
	
	/**
	 * Denna metod returnerar optimala antalet kassor
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
	public static int metod2(int M, double L, double LOW_COLLECTION_TIME, double HIGH_COLLECTION_TIME,
			double LOW_PAYMENT_TIME, double HIGH_PAYMENT_TIME, double END_TIME, double STOP_TIME, int SEED) {
			
		int Nopt = 0;
		int prevMissedCustomers = SEED;
		
		for (int i = 1; i <=  M; i++) {
//			SupermarketState state = new SupermarketState(i, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
//					LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME, SEED);
			SupermarketState optimal = metod1(i, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
					LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME, SEED);
			
			
			if(optimal.getMissedCustomers() < prevMissedCustomers) {
				prevMissedCustomers = optimal.getMissedCustomers();
				Nopt = i;
				
			}
			
		}
		return Nopt;
	}
	
	public static int metod3(int SEED) {
		
		Random rnd = new Random(SEED);
		int Nopt3 = 0;
		int Nopt;
		int counter = 0;
		
		while (counter < 100) {
			
			counter += 1;
			Nopt = metod2(M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
					LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME, rnd.nextInt());
			
			if (Nopt3 < Nopt) {
				Nopt3 = Nopt;
				counter = 0;
			}
			
		}
		
		return Nopt3;
	}

}
	
//	public static double recRaiseHalf(double x, int k) {
//	counter2 = counter2 + 1;
//
//	int y = (int) Math.floor(k / 2);
//
//	if (k == 0) {
//		return 1.0;
//	} else if (k % 2 == 0) {
//		return recRaiseHalf(x * x, y);
//	} else {
//		return x * recRaiseHalf(x * x, y);
//	}
//}
