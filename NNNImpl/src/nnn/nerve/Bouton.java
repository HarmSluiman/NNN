package nnn.nerve;

import nnn.space.Location;

public class Bouton {

	private double potential = 0;
	private Location myLocation;
	private boolean dead = false;
	private Thread decayThread = new Thread(() -> {
		decayPotential(this);
	});

	public Bouton(Location location) {
		this.myLocation = location;
		this.decay();
	}

	public void stimulate(double signal) {
		setPotential(getPotential() + signal);

	};

	private void setPotential(double value) {
		potential = value;
	};

	private double getPotential() {
		return potential;
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
		// be attracted
		// it will take time for a dendrite to grow

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

			while (bouton.getPotential() > .1) {

				try {
					Thread.sleep(2);
				} catch (InterruptedException ex) {
					System.out.println(ex.getStackTrace());
					Thread.currentThread().interrupt();
				}

				bouton.setPotential(bouton.getPotential() * .9);
				System.out.println("Potential is now = " + bouton.getPotential());

			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException ex) {
				System.out.println(ex.getStackTrace());
				Thread.currentThread().interrupt();
			}

		}

	};

}
