package state;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/**
 * Klass som skapar en kund som enbart håller reda på sitt kund id
 */
public class Customer {
	private int id;

	/**
	 * Konstruktor för när man skapar en ny kund
	 * 
	 * @param ID ID:et för kunden
	 */
	public Customer(int ID) {
		this.id = ID;

	}

	/**
	 * Returnerar kundens id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

}
