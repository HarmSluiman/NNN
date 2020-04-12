package nnn.nerve;

public class Terminal {

	private Bouton[] boutons = {new Bouton()};

	public Terminal() {
		//boutons[0] = new Bouton();
	}

	/**
	 * stimulate
	 * <p>
	 * sprays the incoming stimulus to the connected Boutons
	 * 
	 * @param signal
	 */
	public void stimulate(double signal) {

		for (int j = 0; j < boutons.length; j++) {

			boutons[j].stimulate(signal / boutons.length);

		}

	}
	/**
	 * atrophy
	 * <p>
	 * when a Terminal is atrophied, it is not receiving stimulation and no longer needs the Boutons
	 * 
	 */
	public void atrophy() {
		
		for (int j = 0; j < boutons.length; j++) {

			boutons[j].atrophy();

		}
		
	}


}
