package events;
import java.util.ArrayList;
//import org.w3c.dom.events.Event;
import java.util.*;
/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */
/*
 * En klass som fungerar som mellansteg mellan eventen och simulatorn.Metoder:
 * nextEvent() levererar nästa event i listan.
 * addEvent(Event event) lägger till event i listan.
 * sortEvents() sorterar eventen så de ligger i rätt tidsordning.
 */
public class EventQueue {
	private ArrayList<Event> queue;
	
	/*
	 * Konstruktorn. När en instans av EventQueue skapas håller den reda på eventen i en instans av ArrayList.
	 */
	public EventQueue() {
		this.queue = new ArrayList<Event>();
	}
	
	/*
	 * Sorterar eventen i tidsordning, hämtar ut det första eventet och tar därefter bort det ur listan. Returnerar det eventet som var först i listan.
	 */
	public Event nextEvent() {
		sortEvents();
		Event event=queue.get(0);
		queue.remove(0);
		return event;
	}
	
	/*
	 * Lägger till event i listan.
	 */
	public void addEvent(Event event) {
		queue.add(event);
	}
	
	/*
	 * Sorterar eventen efter tid. Går igenom listan och om ett event ska genomföras senare än det efterföljande eventet 
	 * byter dessa plats. Listan kontrolleras tills inga events behöver byta plats med varandra.
	 */
	private void sortEvents() {//Sorterar listan efter tid.
		boolean notSorted=true;
		int n=0;
		while(notSorted) {
			n=0; 
			for(int i=0;i<queue.size();i++) {
				if (i+1 == queue.size()) {
					continue;
				}
				else if(queue.get(i).getEventTime()>queue.get(i+1).getEventTime()) {
					Event movedEvent=queue.get(i+1);
					queue.set(i+1,queue.get(i));
					queue.set(i,movedEvent);
					n+=1;
				}
			}
			if(n==0) {
				notSorted=false;
			}
		}
	}
}
