package nnn.nerve;

import nnn.space.Location;

public class Dendrite {

	private double signal = 0;
	private Location dendriteRootLocation = null;
	private Location currentTip = null;
	private Synapse synapse = null;
	
	
	public Location getDendriteRootLocation() {
		return dendriteRootLocation;
	}
	public void setDendriteRootLocation(Location dendriteRootLocation) {
		this.dendriteRootLocation = dendriteRootLocation;
	}
	public Location getCurrentTip() {
		return currentTip;
	}
	public void setCurrentTip(Location currentTip) {
		this.currentTip = currentTip;
	}
	public Synapse getSynapse() {
		return synapse;
	}
	public void setSynapse(Synapse synapse) {
		this.synapse = synapse;
	}
	public void setSignal(double signal) {
		this.signal = signal;
	}
	
	
	public Dendrite(Location location) {
		this.setDendriteRootLocation(location);
		this.setCurrentTip(location);
		
	}
	public void stimulate(double input) {
		setSignal(getSignal() + input);
	}
	public double getSignal() {
		double tempSignal = signal;
		setSignal(0);
		return tempSignal;
	}
}
