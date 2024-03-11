package state;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/**
 * Klass som skapar nya kunder
 */
public class CustomerSource {
	private int nextID;

	/**
	 * Konstruktor som sätter start id för första kunden som 0
	 */
	public CustomerSource() {
		nextID = 0;
	}

	/**
	 * Metod som skapar en ny customer med ett nytt id
	 * 
	 * @return new Customer(nextID++)
	 */
	public Customer newCustomer() {
		return new Customer(nextID++);
	}

}
