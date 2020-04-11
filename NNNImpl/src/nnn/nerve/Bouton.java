package nnn.nerve;



public class Bouton {

	private double potential = 0;
	private Thread decayThread = new Thread(() -> {decayPotential(this);});

	public void stimulate(double signal)
	{
		setPotential(getPotential()+signal);
		decay();
	};

	private void setPotential(double value)
	{
		potential = value;
	};

	private double getPotential()
	{
		return potential;
	};
	public void atrophy() {
		stimulate(getPotential()- 1 - getPotential());
	}
	/*
	 * decay 
	 * 
	 * the decay method
	 */
	private void decay()
	{
		if (!decayThread.isAlive() ) {
			decayThread.start();		
		} else {
			decayThread.interrupt();
			decayThread = new Thread(() -> {decayPotential(this);});
		}


	};

	private void decayPotential(Bouton bouton)
	{
		while (bouton.getPotential()>0){

			while (bouton.getPotential() > .1) {

				try
				{
					Thread.sleep(10);
				}
				catch(InterruptedException ex)
				{
					Thread.currentThread().interrupt();
				}

				bouton.setPotential(bouton.getPotential() * .9);
				System.out.println("Potential is now = " +bouton.getPotential());

			}
			try
			{
				Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}

		}


	};


}
