package view;

import java.util.Observable;
import java.util.Observer;


/*
 * Detta interface ärver observer och ska observera den generella simState interfacet.
 */
@SuppressWarnings("deprecation")
public abstract class SimView implements Observer{

	
	
	/*
	 * Eftersom detta interface är en observatör, behövs en update-metod, dock tom för oss.
	 */
	
	
	public void printStart() {
		
	}
	
	public void printEvent() {
		
	}
	
	public void printResult() {
		
	}
	

	@Override
	public void update(Observable o, Object arg) {
		
		
	}
}
