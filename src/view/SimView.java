package view;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Vilma Axling, David Strömmer, Jonatan Fredriksson
 */

/*
 * Denna abstrakta klassen ärver observer och ska observera den generella
 * simState interfacet.
 */
@SuppressWarnings("deprecation")
public abstract class SimView implements Observer {

	/*
	 * Eftersom denna klass är en observatör, behövs en update-metod, dock tom för
	 * oss.
	 */

	/**
	 * Generell metod för att printa startParametrar för vilken typ av simulation
	 * som helst
	 */
	public void printStart() {

	}

	/**
	 * Generell metod för att printa events för vilken simulator som helst
	 */
	public void printEvent() {

	}

	/**
	 * Generell metod för att printa resultat för vilken simulator som helst
	 */
	public void printResult() {

	}

	/**
	 * Metod från Observer interfacet
	 */
	@Override
	public void update(Observable o, Object arg) {

	}
}
