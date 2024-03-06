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
	UniformRandomStream x = new UniformRandomStream(deltaL, deltaH); // Placeholder, hämtas från runSim
	
	public PickupTime(double deltaL, double deltaH) {
		this.deltaH = deltaH;
		this.deltaL = deltaL;
	}
	
	public double getNextTime(double currentTime) {
		
		return x.next() + currentTime;
	}
	
}
