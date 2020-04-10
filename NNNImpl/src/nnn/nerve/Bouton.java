package nnn.nerve;

public class Bouton {

	private float potential = 0;
	
	public void stimulate(float signal)
	{
		potential = potential + signal;
	};
	
}
