package nnn.nerve;



public class Bouton {

	private double potential = 0;
	private Thread decayThread = new Thread(() -> {decayPotential(this);});
	
	public void stimulate(double signal)
	{
		potential = potential + signal;
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
	
	/*
	 * decay 
	 * 
	 * the decay method
	 */
	private void decay()
	{
		decayThread.start();
	};
	
	private void decayPotential(Bouton bouton)
	{
		while (bouton.getPotential() > .001) {
			
			try
			{
			    Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			
			bouton.setPotential(bouton.getPotential() * .9);
		}
		
		
	};
	
	
}
