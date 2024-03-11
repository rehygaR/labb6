package state;

import random.UniformRandomStream;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass är en tidsskälla för plocktider. Uppgiften denna har är att
 * returnera nästa tid, dvs den tiden som är efter händelsen har utförts.
 */
public class PickupTime {

	private double deltaH;
	private double deltaL;
	private long seed;
	private UniformRandomStream x;

	/**
	 * Konstruktor, skapar en ny randomiserad tid för betalningstider.
	 * 
	 * @param deltaL Lägre lambdat
	 * @param deltaH Högre lambdat
	 * @param seed Fröet för slumpningen
	 */
	public PickupTime(double deltaL, double deltaH, long seed) {
		this.deltaH = deltaH;
		this.deltaL = deltaL;
		this.seed = seed;
		this.x = new UniformRandomStream(this.deltaL, this.deltaH, this.seed);
	}

	/**
	 * Ger nästa tiden för simulatorn (nuvarande tid + delta)
	 * 
	 * @param currentTime Nuvarande tiden
	 * @return this.x.next() + currentTime
	 */
	public double getNextTime(double currentTime) {

		return this.x.next() + currentTime;
	}

}
