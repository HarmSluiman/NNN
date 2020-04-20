package nnn.nerve;

import java.util.ArrayList;

import nnn.space.Location;

public class Terminal {

	private ArrayList<Bouton> boutons = new ArrayList<Bouton>();
	private Location myLocation;

	
	public Terminal(Location location) {
		this.myLocation = location;
		this.boutons.add(new Bouton(location));
	}

	/**
	 * stimulate
	 * <p>
	 * sprays the incoming stimulus to the connected Boutons
	 * 
	 * @param signal
	 */
	public void stimulate(double signal) {

		for (int j = 0; j < boutons.size(); j++) {

			boutons.get(j).stimulate(signal / boutons.size());

		}

	}
	/**
	 * atrophy
	 * <p>
	 * when a Terminal is atrophied, it is not receiving stimulation and no longer needs the Boutons
	 * 
	 */
	public void atrophy() {
		
		for (int j = 0; j < boutons.size(); j++) {

			boutons.get(j).atrophy();

		}
		
	}


}
