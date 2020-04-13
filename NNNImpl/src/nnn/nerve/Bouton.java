package nnn.nerve;

public class Bouton {

	private double potential = 0;
	private Thread decayThread = new Thread(() -> {
		decayPotential(this);
	});

	public Bouton() {
		decay();
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
		stimulate(getPotential() - 1 - getPotential());
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
		// large potential held over time indicates no synapse firing so a Dendrite will be attracted
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
		while (bouton.getPotential() >= 0) {

			while (bouton.getPotential() > .01) {

				try {
					Thread.sleep(10);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

				bouton.setPotential(bouton.getPotential() * .9);
				System.out.println("Potential is now = " + bouton.getPotential());

			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

		}

	};

}
