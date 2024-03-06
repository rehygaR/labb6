package state;

public class Customer {
	CustomerSource customerSource = new CustomerSource();
	private int id;
//	private double arrivalTime;
//	private double pickUpTime;
//	private double paymentTime;
	

	public Customer() {
		this.id = customerSource.newCustomerID();
//		this.arrivalTime = arrivalTime;
//		this.pickUpTime = pickUpTime;
//		this.paymentTime = paymentTime;
	}
	
	/*
	 * Getter
	 */
	public int getId() {
		return id;
	}
	
//	public double getArrivalTime() {
//		return arrivalTime;
//	}
//	
//	public double getPickupTime() {
//		return pickUpTime;
//	}
//	
//	public double paymentTime() {
//		return paymentTime;
//	}
}
