package state;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import general.Event;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna klass är en First-In-First-Out kö som ska hantera kunderna i
 * simuleringen.
 */
public class FIFO {

	private ArrayList<Object> queue = new ArrayList<Object>();

	/**
	 * Lägger till ett objekt till listan
	 * 
	 * @param arg0
	 */
	public void add(Object arg0) { // Lägger till i kön genom ArrayLists interna kommando

		this.queue.add(arg0);

	}

	/**
	 * Ger det första objektet i kön
	 * 
	 * @return this.queue.get(0)
	 * @throws NoSuchElementException
	 */
	public Object first() throws NoSuchElementException { // Skickar tillbaka det fösta elementet i listan

		if (this.queue.size() == 0) { // Om kön är tom, kastar den ett undantag istället
			throw new NoSuchElementException("The size of the queue is 0");
		} else {
			return this.queue.get(0);
		}

	}

	/**
	 * Ger true eller false beroende på om kön är tom eller ej
	 * 
	 * @return true eller false
	 */
	public boolean isEmpty() { // Kollar om kön är tom

		if (this.queue.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Ger den maximala storleken av kön
	 * 
	 * @return maxSize
	 */
	public int maxSize() { // Retunerar köns max storlek, dvs ArrayListans storlek (antal element men också
							// de platser i listan som har null)

		int maxSize = 0;

		for (int i = 0; i < this.queue.toArray().length; i++) {
			maxSize = maxSize + 1;
		}

		return maxSize;
	}

	/**
	 * Tar bort det försa elementet i kön
	 * 
	 * @throws NoSuchElementException
	 */
	public void removeFirst() throws NoSuchElementException { // Tar bort det första element ur kön

		if (this.queue.size() == 0) {
			throw new NoSuchElementException("The queue is empty");
		} else {
			this.queue.remove(this.queue.get(0)); // Tar bort det första elementet UTAN att flytta på resterande element
													// (Kolla ADT:n)
		}

	}

	/**
	 * Ger storleken av kön
	 * 
	 * @return this.queue.size()
	 */
	public int size() { // Returnerar antal element i kön, dvs om storleken är 10, men endast fylld med
						// 4 element, returnerar den 4
		return this.queue.size();
	}

	/**
	 * Kollar om två objekt är av samma typ, storleke är samma och de två köerna
	 * innehåller samma element på samma platser
	 */
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

	/**
	 * En metod som gör om kön till en sträng
	 */
	public String toString() { // Returnerar en string som börjar med "Queue: "

		String str = "[";// Skapar bas-stringen, men värdet "Queue: "

		for (int i = 0; i < queue.size(); i++) { // For-each loop

			if (i == queue.size() - 1) {
				str = str.concat(String.valueOf(((Customer) queue.get(i)).getId()));

			} else {
				str = str.concat(String.valueOf(((Customer) queue.get(i)).getId()) + ", "); // I str läggs det till så
																							// att stringen =

			}

		}
		str = str.concat("]");

		return str; // returnar str

	}

}
