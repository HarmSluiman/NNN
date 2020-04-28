package nnn.nerve;

import java.util.ArrayList;

import nnn.space.Location;
import nnn.space.Space;
import nnn.utility.Configuration;
import nnn.utility.Logger;

public class Bouton {

	private double potential = 0;
	private Location myLocation;
	private Terminal terminal = null;
	private Synapse synapse = null;
	private boolean dead = false;
	private Thread decayThread = new Thread(() -> {
		decayPotential(this);
	});
	private void setPotential(double value) {
		potential = value;
		if (potential < 0)
			potential = 0;
	};

	private double getPotential() {
		return potential;
	};
	
	
	

	public Bouton(Terminal owner, Location location) {
		this.terminal = owner;
		this.myLocation = location;
		this.decay();
	}

	public void stimulate(double signal) {
		setPotential(getPotential() + signal);
		if (!(synapse == null)) {
						
			if (getPotential() <= Configuration.boutonPropagateAmount) {			
				synapse.propagate(getPotential());
				setPotential(0);
			} else {				
				synapse.propagate(Configuration.boutonPropagateAmount);
				setPotential(getPotential() - Configuration.boutonPropagateAmount);
			}
				
				
		}
		

	};


	/**
	 * atrophy
	 * <p>
	 * when a Bouton is atrophied, it receiving stimulation and no longer needs to
	 * decay it's potential.
	 * 
	 * By setting the potential to > zero the tread ends.
	 */
	public void atrophy() {
		dead = true;
	}

	/**
	 * decay
	 * <p>
	 * the decay method begins the decaying of any potential once it arrives
	 */
	private void decay() {
		if (!decayThread.isAlive()) {
			decayThread.start();
		} else {
			decayThread.interrupt();
			decayThread = new Thread(() -> {
				decayPotential(this);
			});
		}

	};

	private void attractDendrites() {
		// large potential held over time indicates no synapse firing so a Dendrite will
		// be connected with a Synapse or attracted
		// it will take time for a dendrite to grow
		ArrayList<Dendrite> dendriteNeighbors =  new ArrayList<Dendrite>() ;
	
		
		dendriteNeighbors = Space.findNeighborDendrites(myLocation);
		if (!(dendriteNeighbors.isEmpty())){
			// insert a Synapse if not recursive
				// check all the neighbors
				for(int dn = 0; dn< dendriteNeighbors.size();dn++) {
					
					// for each neighbor ensure the dendrite is not part of this nerve cell
					for(int d = 0 ; d < this.terminal.getAxon().getTerminals().size(); d++  ) {
						// ignore if it is already connected somewhere
						if(dendriteNeighbors.get(dn).getSynapse()== null) {
							
							if(dendriteNeighbors.get(dn).equals(this.terminal.getAxon().getNerve().getDendrites().get(d))) {
								// it was recursive
								Logger.log(" found recurrsive Dendrite");
							} else {
								// only add one synapse 
								synapse = new Synapse(this, dendriteNeighbors.get(dn));
								dendriteNeighbors.get(0).setSynapse(synapse);
								Logger.log(this + " Synapse "+ synapse +" created");
								dn = dendriteNeighbors.size();
								d = this.terminal.getAxon().getTerminals().size();
							}	
						} else {
							Logger.log(" Dendrite has a Synapse");
						}
						
						
					}
				}
			
			
			
		} else {
			// find dendrites to grow
		}

	}

	/**
	 * decayPotential - decay the signal pushed on the Synapse. Runs on a thread
	 * dedicated to this Bouton
	 * <p>
	 * Reduces the current potential by 10% on equal interval continuously to
	 * simulate general signal deterioration. However is the potential is increased
	 * with more stimulation the new value will continue to be reduced.
	 * <p>
	 * A regular maximum potential is expected to be 1 however over stimulation will
	 * just take longer to deteriorate.
	 * <p>
	 * Once deterioration has reach a low a rest is taken before deterioration of
	 * new stimulation takes place.
	 */

	private void decayPotential(Bouton bouton) {
		while (bouton.dead == false) {

			while (bouton.getPotential() > Configuration.boutonMinimumPropagation) {

				try {
					Thread.sleep(Configuration.boutonDecayCycle);
				} catch (InterruptedException ex) {
					System.out.println(ex.getStackTrace());
					Thread.currentThread().interrupt();
				}

				bouton.setPotential(bouton.getPotential() * Configuration.boutonDecayfactor);
				Logger.log(this + "  Potential is now = " + bouton.getPotential());
				if (bouton.getPotential() > Configuration.boutonDendriteAttractionThreshold) {
					attractDendrites();
				}

			}
			try {
				Thread.sleep(Configuration.boutonRefreshCycle);
			} catch (InterruptedException ex) {
				System.out.println(ex.getStackTrace());
				Thread.currentThread().interrupt();
			}

		}

	};

}
