package state;

import random.ExponentialRandomStream;
import random.UniformRandomStream;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass är en tidsskälla för betalningstider. Uppgiften denna har är att
 * returnera nästa tid, dvs den tiden som är efter händelsen har utförts.
 */
public class PaymentTime {

	private double deltaL;
	private double deltaH;
	private long seed;
	UniformRandomStream x;

	/**
	 * Konstruktor, skapar en ny randomiserad tid för betalningstider.
	 * 
	 * @param deltaLower
	 * @param deltaHigher
	 * @param seed
	 */
	public PaymentTime(double deltaLower, double deltaHigher, int seed) {
		this.deltaH = deltaHigher;
		this.deltaL = deltaLower;
		this.seed = seed;
		this.x = new UniformRandomStream(this.deltaL, this.deltaH, this.seed);
	}

	/**
	 * Ger nästa tiden för simulatorn (nuvarande tid + delta)
	 * 
	 * @param currentTime
	 * @return
	 */
	public double getNextTime(double currentTime) {

		return this.x.next() + currentTime;
	}

}
