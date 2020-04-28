package nnn.nerve;

import java.util.ArrayList;
import nnn.space.Space;

import nnn.space.Location;
/**
 * 
 * @author harmsluiman
 *         <p>
 *         Axon provides a connection from the core of the Nerve body to one or
 *         more terminals
 *         <p>
 *         If the stimulus is too large it will eventually trigger additional
 *         terminals to be generated
 */

public class Axon {

	private ArrayList<Terminal> terminals = new ArrayList<Terminal>();
	private Nerve nerve = null;
	private Location terminalRoot = null;
	
	public Nerve getNerve() {
		return nerve;
	}

	public void setNerve(Nerve nerve) {
		this.nerve = nerve;
	}

	public Location getTerminalRoot() {
		return terminalRoot;
	}

	public void setTerminalRoot(Location terminalRoot) {
		this.terminalRoot = terminalRoot;
	}

	public ArrayList<Terminal> getTerminals() {
		return terminals;
	}

	public void setTerminals(ArrayList<Terminal> terminals) {
		this.terminals = terminals;
	}

	

	public Axon(Nerve owner, Location location) {
		this.setNerve(owner);
		this.setTerminalRoot(location);
		
		this.getTerminals().add( new Terminal(this, terminalRoot));
		this.getTerminalRoot().setOccupant(this.getTerminals().get(0));
	}

	/**
	 * stimulate
	 * <p>
	 * sprays the incoming stimulus to the connected Terminals
	 * 
	 * @param signal
	 */
	public void stimulate(double signal) {
		// wait for the impact of the axon length and then propagate
		// TODO add length delay and some degradation
		for (int j = 0; j < getTerminals().size(); j++) {

			getTerminals().get(j).stimulate(signal / terminals.size());

		}

	}

	/**
	 * atrophy
	 * <p>
	 * when a Axon is atrophied, it is not receiving stimulation and no longer needs
	 * the Terminals
	 * 
	 * 
	 */
	public void atrophy() {

		for (int j = 0; j < getTerminals().size(); j++) {

			getTerminals().get(j).atrophy();

		}
	}
}
