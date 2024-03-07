package events;



import state.SimState;
import state.SupermarketState;

/*
 * Abstrakt klass som ärvs av de specifika eventen. Har två metoder, time() och exeEvent().
 * getEventTime() håller reda på tiden händelsen utförs
 * exeEvent(State state) ser till att händelsen utförs och påverrkar tillståndet.
 * setEventTime(double eventTime) sätter tiden som händelsen ska utföras.
 */

public abstract class Event {
	private double eventTime; //Tiden händelsen inträffar
	
	public Event(double eventTime) { //Konstruktor
		this.eventTime=eventTime;
	}
	
	public void setEventTime(double eventTime) {
		this.eventTime=eventTime;
	}
	
	public double getEventTime() {
		return eventTime;
	}
	
//	public String getName() {
//		return "Event";
//	}
	
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		state.setTime(eventTime);
		SpecificExe(state, eventQueue);
	}
	
	public abstract void SpecificExe (SupermarketState state, EventQueue eventQueue);
	

	

}