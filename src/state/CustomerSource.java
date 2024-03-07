package state;

public class CustomerSource {
	private int nextID;

	public CustomerSource() {
		nextID=0;
	}
	
	public Customer newCustomer() {
		return new Customer(nextID++);
	}
	
	

}
