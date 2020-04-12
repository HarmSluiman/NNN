package nnn.nerve;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Axon axon = new Axon();
		System.out.println("first stimualtion");
		axon.stimulate(1);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("second stimualtion");
		axon.stimulate(0.5);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("third stimualtion");
		axon.stimulate(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("atrophy");
		axon.atrophy();
	}

}
