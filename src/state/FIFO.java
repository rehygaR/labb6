package state;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import events.Event;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass är en First-In-First-Out kö som ska hantera kunderna i
 * simuleringen.
 */
public class FIFO {

	private ArrayList<Object> queue = new ArrayList<Object>();

	public void add(Object arg0) { // Lägger till i kön genom ArrayLists interna kommando

		this.queue.add(arg0);

	}

	public Object first() throws NoSuchElementException { // Skickar tillbaka det fösta elementet i listan
		// TODO Auto-generated method stub

		if (this.queue.size() == 0) { // Om kön är tom, kastar den ett undantag istället
			throw new NoSuchElementException("The size of the queue is 0");
		} else {
			return this.queue.get(0);
		}

	}

	public boolean isEmpty() { // Kollar om kön är tom
		// TODO Auto-generated method stub

		if (this.queue.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int maxSize() { // Retunerar köns max storlek, dvs ArrayListans storlek (antal element men också
							// de platser i listan som har null)
		// TODO Auto-generated method stub

		int maxSize = 0;

		for (int i = 0; i < this.queue.toArray().length; i++) {
			maxSize = maxSize + 1;
		}

		return maxSize;
	}

	public void removeFirst() throws NoSuchElementException { // Tar bort det första element ur kön
		// TODO Auto-generated method stub

		if (this.queue.size() == 0) {
			throw new NoSuchElementException("The queue is empty");
		} else {
			this.queue.remove(this.queue.get(0)); // Tar bort det första elementet UTAN att flytta på resterande element
													// (Kolla ADT:n)
		}

	}

	public int size() { // Returnerar antal element i kön, dvs om storleken är 10, men endast fylld med
						// 4 element, returnerar den 4
		// TODO Auto-generated method stub
		return this.queue.size();
	}

	public boolean equals(Object f) { // Kollar om f uppfyller vissa kriterier som kön har.

		if (this.getClass() != f.getClass()) { // kollar om f är av samma typ som this (queue variabeln) annars kastar
			throw new ClassCastException("The two queues are not of the same type");
		}

		FIFO queue2 = (FIFO) f; // Kastar om f till en FIFO typ, då vi vet redan att de är av samma typ

		if (this.size() != queue2.size()) { // Om storleken på dem inte är den samma, returnerar den false
			return false;
		}

		for (int i = 0; i < this.size(); i++) { // Kollar igenom varje element

			Object queueElement = this.queue.get(i);
			Object queueElement2 = queue2.queue.get(i);

			if (queueElement == null) {
				if (queueElement2 != null) { // Om elementet ur första kön = null, men elementet med samma index från f
												// INTE är null, returnerar den false
					return false;
				}
			} else {
				if (!queueElement.equals(queueElement2)) { // Om elementet ur första kön INTE är den samma som elementet
															// med samma index ur f, returnerar den false
					return false;
				}
			}

		}
		return true; // Returnerar true om f uppfyller kriterierna ovan!

	}

	public String toString() { // Returnerar en string som börjar med "Queue: "

		/*
		 * Den av kommenterade koden nedan använder StringBuilder, vet ej om ok
		 */

//		StringBuilder queueString = new StringBuilder("Queue: "); // Skapar en stringbuilder för att kunna använda "append()"
//		for (Object elem: queue) { // Skapar en for-each loop som går igenom varje element i kön
//			queueString.append("(").append(String.valueOf(elem)).append(") "); // "Attachar" i slutet av stringbuildern
//		}
//		
//		return queueString.toString();
//		

		String str = "[";// Skapar bas-stringen, men värdet "Queue: "

		for (int i = 0; i < queue.size(); i++) { // For-each loop
			
			if(i==queue.size()-1) {
				str = str.concat(String.valueOf(((Customer) queue.get(i)).getId()));
				
			}else {
				str = str.concat(String.valueOf(((Customer) queue.get(i)).getId()) + ", "); // I str läggs det till så att stringen =
				
			}
			
			
		}
		str = str.concat("]");

		return str; // returnar str

		
	}

}
