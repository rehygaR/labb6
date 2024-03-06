package view;

import java.util.Observable;
import java.util.Observer;


/*
 * Detta interface ärver observer och ska observera den generella simState interfacet.
 */
@SuppressWarnings("deprecation")
public abstract class simView implements Observer{

	
	
	/*
	 * Eftersom detta interface är en observatör, behövs en update-metod, dock tom för oss.
	 */

	@Override
	public void update(Observable o, Object arg) {
	}
}
