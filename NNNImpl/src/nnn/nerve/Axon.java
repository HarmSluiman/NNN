package nnn.nerve;

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

	private Terminal[] terminals = {new Terminal()};

	public Axon() {
		//terminals[0] = new Terminal();

	}

	/**
	 * stimulate
	 * <p>
	 * sprays the incoming stimulus to the connected Terminals
	 * 
	 * @param signal
	 */
	public void stimulate(double signal) {

		for (int j = 0; j < terminals.length; j++) {

			terminals[j].stimulate(signal / terminals.length);

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

		for (int j = 0; j < terminals.length; j++) {

			terminals[j].atrophy();

		}
	}
}
