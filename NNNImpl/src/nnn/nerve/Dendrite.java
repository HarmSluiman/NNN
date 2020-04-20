package nnn.nerve;

import nnn.space.Location;

public class Dendrite {

	private double signal = 0;
	private Location dendriteRootLocation;
	
	public Dendrite(Location location) {
		this.dendriteRootLocation = location;
		
	}
	public void stimulate(double input) {
		signal = input;
	}
	public double getSignal() {
		double tempSignal = signal;
		signal = 0;
		return tempSignal;
	}
}
