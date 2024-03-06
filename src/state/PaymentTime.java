package state;

import random.UniformRandomStream;


/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass är en tidsskälla för ankomsttid. Uppgiften denna har är att returnera nästa tid,
 * dvs den tiden som är efter händelsen har utförts.
 */
public class PaymentTime {
	
	private double deltaL;
	private double deltaH;
	UniformRandomStream x = new UniformRandomStream(this.deltaL,this.deltaH);
	
	public PaymentTime(double deltaLower, double deltaHigher) {
		this.deltaL = deltaLower;
		this.deltaH = deltaHigher;
	}

	
	public double getNextTime(double currentTime) {
		
		return x.next() + currentTime;
	}

}
