package state;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Ett interface för den generella simulatorn. Här återfinns två metoder,
 * getTime() och simBreak() för att hålla reda på simulatorns nuvarande
 * tillstånd. När simState implementeras, ska klassen vara observable.
 */
@SuppressWarnings("deprecation")
public class SimState extends Observable { // Vid implementering, klassen som detta interface implementeras av ska också ärva Observable
	
	/*
	 * Tillståndsvariabler för generella tillståndsklassen
	 */
	private double currentTime;
	private double previousTime=0;
	private boolean simActive;
	
	public SimState() {
		this.simActive = true;
		this.currentTime = 0;
	}
	
	/*
	 * Ger den specifika tiden för simuleringen. Detta är den aktuell uträknad tid, dit simuleringen
	 * hunnit.
	 */
	public double getTime() {
		return currentTime;
	}
	
	public double getPreviousTime() {
		return previousTime;
	}
	
	public void setTime(double newCurrentTime) {
		previousTime=currentTime;
		currentTime=newCurrentTime;
	}
	/*
	 * Returnar om simulatorn ska fortsätta, dvs om denna ger "false" fortsätter simulatorn. Fungerar som
	 * en nödbroms
	 */
	public void simBreak() {
//		if (getTime() == 999) { // Vid tiden 999 ska simulatorn avslutas
//			notifyObservers();
//			setChanged();
//			return true;
//		} else {
//			notifyObservers();
//			setChanged();
//			return false;
//		}
		setChanged();
		notifyObservers();
		this.simActive = false;
		
		
	}
	public boolean getSimActive() {
		return this.simActive;
	}
	
	public void notifyObserver() {
		notifyObservers();
	}
	
}
