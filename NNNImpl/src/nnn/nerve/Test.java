package nnn.nerve;

import nnn.space.Location;
import nnn.space.Space;

public class Test {

	public static void main(String[] args) {
		System.out.println("starting");
		Space dSpace = new Space();
		Space tSpace = dSpace;
		
		// fake a 200 by 200 by 200 nerve space for test viewing
		@SuppressWarnings("unused")
		Nerve nerve1 = new Nerve (new Location(100,100,100), new Location(100,100,-100), dSpace, tSpace);
		@SuppressWarnings("unused")
		Nerve nerve2 = new Nerve (new Location(-100,100,100), new Location(-100,100,-100), dSpace, tSpace);
		@SuppressWarnings("unused")
		Nerve nerve3 = new Nerve (new Location(100,-100,100), new Location(100,-100,-100), dSpace, tSpace);
		@SuppressWarnings("unused")
		Nerve nerve4 = new Nerve (new Location(100,-100,100), new Location(-100,-100,-100), dSpace, tSpace);
		
		
		
		
		Nerve nerve01 = new Nerve (new Location(10,10,30), new Location(10,10,25), dSpace, tSpace);
		Nerve nerve02 = new Nerve (new Location(10,10,24), new Location(10,10,20), dSpace, tSpace);
		Nerve nerve03 = new Nerve (new Location(10,10,19), new Location(10,10,15), dSpace, tSpace);
		Nerve nerve04 = new Nerve (new Location(10,10,14), new Location(10,10,10), dSpace ,tSpace);
		Nerve nerve05 = new Nerve (new Location(10,10,9), new Location(10,50,10), dSpace, tSpace);
		Nerve nerve06 = new Nerve (new Location(10,50,11), new Location(10,30,15), dSpace, tSpace);
		Nerve nerve07 = new Nerve (new Location(10,30,14), new Location(10,-10,10), dSpace, tSpace);
		Nerve nerve08 = new Nerve (new Location(10,-10,9), new Location(10,-20,10), dSpace, tSpace);
		
		System.out.println("first stimulation 1.0");
		nerve01.getDendrites().get(0).stimulate(1);

		try {
			Thread.sleep(10);
			System.out.println("sleeping 10");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("second stimulation 0.9");
		nerve01.getDendrites().get(0).stimulate(0.9);
	
		try {
			Thread.sleep(5);
			System.out.println("sleeping 5");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("third stimulation 1.0");
		nerve01.getDendrites().get(0).stimulate(1);
		
		try {
			Thread.sleep(5);
			System.out.println("sleeping 5");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("fourth stimulation 1.0");
		nerve01.getDendrites().get(0).stimulate(1);
		
		try {
			Thread.sleep(5);
			System.out.println("sleeping 5");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("fifth stimulation 1.0");
		nerve01.getDendrites().get(0).stimulate(1);
	
		try {
			Thread.sleep(15);
			System.out.println("sleeping 15");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("sixth stimulation 3.0");
		nerve01.getDendrites().get(0).stimulate(3);
	
		try {
			Thread.sleep(15);
			System.out.println("sleeping 15");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		System.out.println("atrophy");
		nerve01.atrophy();
		nerve02.atrophy();
		nerve03.atrophy();
		nerve04.atrophy();
		nerve05.atrophy();
		nerve06.atrophy();
		nerve07.atrophy();
		nerve08.atrophy();
		

		//Space.dumpLocations();
	}

}
