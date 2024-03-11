package state;

import random.ExponentialRandomStream;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass är en tidsskälla för ankomsttider. Uppgiften denna har är att
 * returnera nästa tid, dvs den tiden som är efter händelsen har utförts.
 */
public class ArrivalTime {

	private long seed;
	private double delta;
	private ExponentialRandomStream x;

	/**
	 * Konstruktor, skapar en ny randomiserad tid för ankomsttider.
	 * 
	 * @param delta Deltat för randomiseringen
	 * @param seed Fröet för slumpgeneratorn
	 */
	public ArrivalTime(double delta, int seed) {
		this.delta = delta;
		this.seed = seed;
		this.x = new ExponentialRandomStream(this.delta, this.seed);
	}

	/**
	 * Ger nästa tiden för simulatorn (nuvarande tid + delta)
	 * 
	 * @param currentTime Nuvarande tid
	 * @return this.x.next() + currentTime
	 */
	public double getNextTime(double currentTime) {

		return this.x.next() + currentTime;
	}

}
