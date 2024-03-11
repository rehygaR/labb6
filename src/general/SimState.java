package general;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * En klass för den generella simulatorn. Här återfinns två metoder, getTime()
 * och simBreak() för att hålla reda på simulatorns nuvarande tillstånd. När
 * simState implementeras, ska klassen vara observable.
 */
@SuppressWarnings("deprecation")
public class SimState extends Observable { // Vid implementering, klassen som detta interface implementeras av ska också
											// ärva Observable

	/*
	 * Tillståndsvariabler för generella tillståndsklassen
	 */
	private double currentTime;
	private double previousTime = 0;
	private boolean simActive;
	private double stopTime;

	/**
	 * Konstruktor, skapar en instans av SimState
	 */
	public SimState() {
		this.simActive = true;
		this.currentTime = 0;
		this.stopTime = 999;
	}

	/*
	 * Ger den specifika tiden för simuleringen. Detta är den aktuell uträknad tid,
	 * dit simuleringen hunnit.
	 */
	public double getTime() {
		return currentTime;
	}

	/**
	 * Ger den stängningstiden då butiken ska stänga (INTE då simulatorn ska sluta)
	 * 
	 * @return stopTime the time the event stops.
	 */
	public double getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(double stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * Ger den tidigare tiden
	 * 
	 * @return previousTime the time of the previous event.
	 */
	public double getPreviousTime() {
		return previousTime;
	}

	/**
	 * Sätter tiden till den nya aktuella tiden, samt lägger till den gamla tiden
	 * till i variabeln previousTime, vilket används till beräkningar
	 * 
	 * @param newCurrentTime the time the latest event happens.
	 */
	public void setTime(double newCurrentTime) {
		previousTime = currentTime;
		currentTime = newCurrentTime;
	}

	/*
	 * Returnar om simulatorn ska fortsätta, dvs denna ger variabeln simActive =
	 * "false" och avslutar simulatorn. Fungerar som en nödbroms
	 */
	public void simBreak() {

		this.simActive = false;

	}

	/**
	 * Ger true eller false beroende om simulatorn ska fortsätta eller avslutas
	 * 
	 * @return true eller false
	 */
	public boolean getSimActive() {
		return this.simActive;
	}

	/**
	 * Uppdateringsmetod, används för att uppdatera observerare att något hänt med
	 * tillståndet. Tillkallas i de specifika eventsen.
	 */
	public void notifyObserver() {
		setChanged();
		notifyObservers();
	}

}
