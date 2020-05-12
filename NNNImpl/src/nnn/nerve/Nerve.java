package nnn.nerve;

import nnn.utility.*;

import java.util.ArrayList;

import nnn.space.Location;
import nnn.space.Space;

public class Nerve {

	private boolean dead = false;
	private double currentPotential = 0;
	private Thread signalThread = new Thread(() -> {
		propagateSignal(this);
	});

	private ArrayList<Dendrite> dendrites = new ArrayList<Dendrite>();
	private Axon axon;
	private Location dendriteRootLocation;
	private Location terminalRootLocation;

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public double getCurrentPotential() {
		return currentPotential;
	}

	public void setCurrentPotential(double currentPotential) {
		this.currentPotential = currentPotential;
	}

	public Thread getSignalThread() {
		return signalThread;
	}

	public ArrayList<Dendrite> getDendrites() {
		return dendrites;
	}

	public void setDendrites(ArrayList<Dendrite> dendrites) {
		this.dendrites = dendrites;
	}

	public Axon getAxon() {
		return axon;
	}

	public void setAxon(Axon axon) {
		this.axon = axon;
	}

	public Location getDendriteRootLocation() {
		return dendriteRootLocation;
	}

	public void setDendriteRootLocation(Location dendriteRootLocation) {
		this.dendriteRootLocation = dendriteRootLocation;
	}

	public Location getTerminalRootLocation() {
		return terminalRootLocation;
	}

	public void setTerminalRootLocation(Location terminalRootLocation) {
		this.terminalRootLocation = terminalRootLocation;
	}

	public Nerve(Location dendriteRoot, Location terminalRoot, Space dSpace, Space tSpace) {
		Dendrite dendrite = new Dendrite(dendriteRoot);
		this.getDendrites().add(dendrite);

		this.setAxon(new Axon(this, terminalRoot));
		// Terminal terminal = new Terminal(this.getAxon(), terminalRoot);

		this.setDendriteRootLocation(dendriteRoot);
		this.setTerminalRootLocation(terminalRoot);

		this.getDendriteRootLocation().setOccupant(dendrite);
		// this.getTerminalRootLocation().setOccupant(terminal);

		dSpace.addLocation(dendriteRoot);
		tSpace.addLocation(terminalRoot);
		this.propagate();

	}

	private void propagate() {
		if (!signalThread.isAlive()) {
			signalThread.start();
		} else {
			signalThread.interrupt();
			signalThread = new Thread(() -> {
				propagateSignal(this);
			});
		}

	};

	private void propagateSignal(Nerve nerve) {
		// accumulate the input stimulations

		while (nerve.isDead() == false) {
			try {
				Thread.sleep(Configuration.nervePropagationDelay);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			// collect the signals
			for (int j = 0; j < nerve.dendrites.size(); j++) {

				setCurrentPotential(getCurrentPotential() + nerve.dendrites.get(j).getSignal());

			}
			// Logger.log(Thread.currentThread() + " " + this + " Accumulated Signal is = " + currentPotential);
			// propagate the signal via the Axon
			if (getCurrentPotential() > 0)
				nerve.axon.stimulate(currentPotential);
			setCurrentPotential(0);
		}

	}

	public void atrophy() {

		getAxon().atrophy();
		setDead(true);
	}

}
