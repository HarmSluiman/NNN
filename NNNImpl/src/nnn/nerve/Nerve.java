package nnn.nerve;

import java.util.ArrayList;

import nnn.space.Location;
import nnn.space.Space;

public class Nerve {

	private boolean dead = false;
	private double currentPotential = 0;
	private Thread signalThread = new Thread(() -> {
		propagateSignal(this);
	});

	private ArrayList<Dendrite> dendrites =  new ArrayList<Dendrite>() ;
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



	public Nerve(Location dendriteRoot, Location terminalRoot) {
		this.dendriteRootLocation = dendriteRoot;
		this.terminalRootLocation = terminalRoot;
			
			
		Space.addLocation(dendriteRoot, dendrites);
		this.dendrites.add(new Dendrite(dendriteRoot));
		this.axon = new Axon(terminalRoot);
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
				Thread.sleep(5);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			// collect the signals
			for (int j = 0; j < nerve.dendrites.size(); j++) {

				currentPotential = currentPotential + nerve.dendrites.get(j).getSignal();
				

			}
			System.out.println("Accumulated Signal is = " + currentPotential);
			// propagate the signal via the Axon
			nerve.axon.stimulate(currentPotential);
			currentPotential = 0;
		}

	}

	public void atrophy() {
		
		getAxon().atrophy();
		setDead(true);
	}

}
