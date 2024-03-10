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
	 * @param ID
	 */
	public Customer(int ID) {
		this.id = ID;

	}

	/**
	 * Returnerar kundens id
	 * @return
	 */
	public int getId() {
		return id;
	}
	

}
