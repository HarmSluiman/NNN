package nnn.nerve;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bouton bouton = new Bouton();
		System.out.println("first stimualtion");
		bouton.stimulate(10);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("second stimualtion");
		bouton.stimulate(10);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("third stimualtion");
		bouton.stimulate(10);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("atrophy");
		bouton.atrophy();
	}

}
