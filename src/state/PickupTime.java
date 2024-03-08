package state;

import random.UniformRandomStream;


/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass är en tidsskälla för ankomsttid. Uppgiften denna har är att returnera nästa tid,
 * dvs den tiden som är efter händelsen har utförts.
 */
public class PickupTime {

	private double deltaH;
	private double deltaL;
	private long seed;
	UniformRandomStream x; // Placeholder, hämtas från runSim
	
//	public PickupTime(double deltaL, double deltaH) {
//		this.deltaH = deltaH;
//		this.deltaL = deltaL;
//		this.x = new UniformRandomStream(this.deltaH, this.deltaL);
//	}
	
	public PickupTime(double deltaL, double deltaH, long seed) {
		this.deltaH = deltaH;
		this.deltaL = deltaL;
		this.seed = seed;
		this.x = new UniformRandomStream(this.deltaL, this.deltaH, this.seed);
	}
	
	public double getNextTime(double currentTime) {
		
		return this.x.next() + currentTime;
	}
	
}
