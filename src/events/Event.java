package events;

import state.SimState;
import state.SupermarketState;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/*
 * Abstrakt klass som ärvs av de specifika eventen. Metoder:
 * getEventTime() returnerar tiden eventet händer.
 * exeEvent(SupermarketState state, EventQueue eventQueue) ser till att eventets händelse inträffar och ändrar 
 * tillståndets parametrar till de aktuella.
 * SpecificExe (SupermarketState state, EventQueue eventQueue) är en abstrakt metod som överskrivs av de specifika eventen.
 */

public abstract class Event {
	private double eventTime;
	
	/*
	 * Konstruktorn sätter denna händelses tid.
	 */
	public Event(double eventTime) {
		this.eventTime=eventTime;
	}
	
	/*
	 * Returnerar tiden händelsen inträffar.
	 */
	public double getEventTime() {
		return eventTime;
	}
	
	/*
	 * Ändrar tillståndets tid till händelsens tid och påkallar sedan den specifika händelsens exekveringsmetod.
	 */
	public void exeEvent(SupermarketState state, EventQueue eventQueue) {
		if(eventTime!=state.getStopTime()) {
			state.setTime(eventTime);
		}
		SpecificExe(state, eventQueue);
		
	}
	
	
	/*
	 * Abstract metod som överskrivs av de specifika händelsernas exekveringsmetoder.
	 */
	public abstract void SpecificExe (SupermarketState state, EventQueue eventQueue);
	

	

}