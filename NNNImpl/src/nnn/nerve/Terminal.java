package nnn.nerve;

import java.util.ArrayList;

import nnn.space.Location;

public class Terminal {

	private ArrayList<Bouton> boutons = new ArrayList<Bouton>();
	private Axon axon = null;
	private Location myLocation;
	

	
	public ArrayList<Bouton> getBoutons() {
		return boutons;
	}

	public void setBoutons(ArrayList<Bouton> boutons) {
		this.boutons = boutons;
	}

	public Axon getAxon() {
		return axon;
	}

	public void setAxon(Axon axon) {
		this.axon = axon;
	}

	public Location getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(Location myLocation) {
		this.myLocation = myLocation;
	}

	public Terminal(Axon owner, Location location) {
		this.setAxon(owner);
		this.setMyLocation(location);
		this.getBoutons().add(new Bouton(this, location));
	}

	/**
	 * stimulate
	 * <p>
	 * sprays the incoming stimulus to the connected Boutons
	 * 
	 * @param signal
	 */
	public void stimulate(double signal) {

		for (int j = 0; j < getBoutons().size(); j++) {

			getBoutons().get(j).stimulate(signal / boutons.size());

		}

	}
	/**
	 * atrophy
	 * <p>
	 * when a Terminal is atrophied, it is not receiving stimulation and no longer needs the Boutons
	 * 
	 */
	public void atrophy() {
		
		for (int j = 0; j < getBoutons().size(); j++) {

			getBoutons().get(j).atrophy();

		}
		
	}


}
