package general;

import java.util.ArrayList;
//import org.w3c.dom.events.Event;
import java.util.*;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/**
 * Denna klass fungerar som mellansteg mellan händelserna och simulatorn. Syftet
 * är att sortera händelserna efter tid och leverera nästa händelse till
 * simulatorn. Klassen innehåller metoder för att hämta ut nästa händelse, lägga
 * till händelser till händelsekön och sortera händelserna efter tid.
 */
public class EventQueue {
	private ArrayList<Event> queue;

	/**
	 * Konstruktorn. Skapar en tom kö som händelserna kan läggas till i.
	 */
	public EventQueue() {
		this.queue = new ArrayList<Event>();
	}

	/**
	 * Sorterar händelserna i tidsordning, hämtar ut den första händelsen och tar
	 * bort det ur listan.
	 * 
	 * @return event
	 */
	public Event nextEvent() {
		sortEvents();
		Event event = queue.get(0);
		queue.remove(0);
		return event;
	}

	/**
	 * Lägger till händelser i listan.
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		queue.add(event);
	}

	/**
	 * Sorterar händelserna efter tid.
	 */
	private void sortEvents() {
		boolean notSorted = true;
		int n = 0;
		while (notSorted) {// Går igenom listan
			n = 0; // Räknare för antal händelser som byter plats under en genomgång av listan.
			for (int i = 0; i < queue.size(); i++) {
				if (i + 1 == queue.size()) { // Om den är på sista händelsen har den inget att jämföra och går bara
												// vidare.
					continue;
				} else if (queue.get(i).getEventTime() > queue.get(i + 1).getEventTime()) {// Om två händelser är
																							// felsorterade byter dessa
																							// plats.
					Event movedEvent = queue.get(i + 1);
					queue.set(i + 1, queue.get(i));
					queue.set(i, movedEvent);
					n += 1; // Lägger till att två händelser bytt plats.
				}
			}
			if (n == 0) {// Om inga händelser bytt plats är listan sorterad och klar, bryter loopen.
				notSorted = false;
			}
		}
	}
}
