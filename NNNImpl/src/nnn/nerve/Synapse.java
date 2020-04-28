package nnn.nerve;

import nnn.utility.Logger;

public class Synapse {
	private Dendrite dendrite = null;
	private Bouton bouton = null;
	
	
	public Dendrite getDendrite() {
		return dendrite;
	}


	public void setDendrite(Dendrite dendrite) {
		this.dendrite = dendrite;
	}


	public Bouton getBouton() {
		return bouton;
	}


	public void setBouton(Bouton bouton) {
		this.bouton = bouton;
	}

	public Synapse(Bouton source, Dendrite target ) {
		
		this.setBouton(bouton);
		this.setDendrite(target);
	}
	

	public void propagate(double signal) {
		Logger.log("  " + this + " propagating");
		getDendrite().stimulate(signal);
		
	}

}
