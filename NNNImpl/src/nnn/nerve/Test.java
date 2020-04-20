package nnn.nerve;

import nnn.space.Location;
import nnn.space.Space;

public class Test {

	public static void main(String[] args) {
		System.out.println("starting");
		Nerve nerve = new Nerve (new Location(10,10,10), new Location(20,20,20));
		System.out.println("first stimulation");
		nerve.getDendrites().get(0).stimulate(1);

		try {
			Thread.sleep(10);
			System.out.println("sleeping 10");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("second stimulation");
		nerve.getDendrites().get(0).stimulate(0.5);
	
		try {
			Thread.sleep(5);
			System.out.println("sleeping 5");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("third stimulation");
		nerve.getDendrites().get(0).stimulate(1);
		
		try {
			Thread.sleep(15);
			System.out.println("sleeping 5");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("atrophy");
		nerve.atrophy();
		Space.dumpLocations();
	}

}
