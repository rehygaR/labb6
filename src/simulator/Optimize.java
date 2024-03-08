package simulator;

import static random.K.END_TIME;
import static random.K.HIGH_COLLECTION_TIME;
import static random.K.HIGH_PAYMENT_TIME;
import static random.K.L;
import static random.K.LOW_COLLECTION_TIME;
import static random.K.LOW_PAYMENT_TIME;
import static random.K.M;
import static random.K.STOP_TIME;

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
			
		SupermarketState test = metod1();
		}
		
		

	
	
	public SupermarketState metod1(int N, int M, int L, double LOW_COLLECTION_TIME, double HIGH_COLLECTION_TIME,
			double LOW_PAYMENT_TIME, double HIGH_PAYMENT_TIME, double END_TIME, double STOP_TIME) {
		
		SupermarketState state = new SupermarketState(N, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME,
				LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, END_TIME,STOP_TIME);
		
		
		EventQueue eventQueue = new EventQueue();
		eventQueue.addEvent(new StartEvent());
		eventQueue.addEvent(new ClosingEvent(END_TIME)); //
		eventQueue.addEvent(new StopEvent(STOP_TIME)); //StopEvent måste ha double argument
		
		SuperMarketView view = new SuperMarketView(state);
		

		Simulator simulator = new Simulator();
		simulator.run(state, eventQueue, view, false);
		
		return state;
		
	}
	
	public int metod2(int M, int L, double LOW_COLLECTION_TIME, double HIGH_COLLECTION_TIME,
			double LOW_PAYMENT_TIME, double HIGH_PAYMENT_TIME, double END_TIME, double STOP_TIME) {
		
		
		
	}

}
