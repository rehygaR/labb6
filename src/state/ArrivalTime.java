package state;

import random.ExponentialRandomStream;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass är en tidsskälla för ankomsttid. Uppgiften denna har är att returnera nästa tid,
 * dvs den tiden som är efter händelsen har utförts.
 */
public class ArrivalTime {
	
//	public double addArrivalTime() {
//		
//		notifyObservers();
//		setChanged();
//		return super.currentTimeCustomer = super.currentTimeCustomer + getArrivalTime();
//	}
	
	private double delta;
	ExponentialRandomStream x = new ExponentialRandomStream(this.delta);
	
	public ArrivalTime(double delta) {
		this.delta = delta;
	}
	
	public double getNextTime(double currentTime) {	
		
		/*
		 *4.0 parametern är placeholder just nu. .	
		 *Parametern anger det snittantal kunder som anländer per tidsenhet.	
		 *Om t ex l=4 kunder anländer per timme (tidsenhet),	
		 *är den förväntade tiden mellan på varandra följande kunder ¼ timmar (15 minuter).	
		 *Anges denna parameter av runSim???
		 */
		return x.next() + currentTime;
	}
	
//	public static void main(String[] args) { // Test av klass!
//		supermarketState y = new supermarketState();
//		arrivalTime x = new arrivalTime();
//		System.out.println(x.addArrivalTime());
//		System.out.println(x.addArrivalTime());
//		System.out.println(x.addArrivalTime());
//		System.out.println(x.addArrivalTime());
//		System.out.println(y.currentTimeCustomer);
//		
//	}
	
}

