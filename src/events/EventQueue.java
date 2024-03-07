package events;
import java.util.ArrayList;
//import org.w3c.dom.events.Event;
import java.util.*;

/*
 * En klass som fungerar som mellansteg mellan eventen och simulatorn. 
 * Här finns två metoder, changeState() som levererar nästa event som ska utföras till simulatorn
 * och sortEvent() som sorterar eventen efter tid. Klassen håller därmed alltid reda
 * på i vilken tidsordning eventen ska exekveras och levererar rätt element till simulatorn.
 */
public class EventQueue {
	public ArrayList<Event> queue;
	
	public EventQueue() {
		this.queue = new ArrayList<Event>();
	}
	
	public Event nextEvent() {//Returnerar nästa event baserat på närhet i tid.
		sortEvents();
//		for (Event element:queue) {
//			System.out.println(element.getEventTime());
//		}
		Event event=queue.get(0);
		queue.remove(0);
		return event;
	}
	
	public void addEvent(Event event) {
		queue.add(event);
	}
	
	public boolean isEmpty() {
		return queue.size()==0;
	}
	
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
