import java.util.ArrayList;
import java.util.Random;

public class Test {
	private static railLine line;
	private static TrainCreator tt;
	public static void main(String[] args) {
//		Random rand = new Random();
//		int x = rand.nextInt(2)+1;
//		Station glasgow = new Station("Glasgow", x);
//		Track one = new Track();
//		Station stirling = new Station("Stirling", x);
//		Track two = new Track();
//		Station perth = new Station("Perth", x);
//		Track three = new Track();
//		Station inverness = new Station("Inverness", x);
//		String s = "";
//		System.out.print(glasgow);
//		System.out.print(one);
//		System.out.print(stirling);
//		System.out.print(two);
//		System.out.print(perth);
//		System.out.print(three);
//		System.out.print(inverness);
		line = new railLine();
		createTrack();
		Thread[] threads = new Thread[3];
		for (int i = 0; i < threads.length; i++) {
			TrainCreator tt = new TrainCreator(line);
			threads[i] = new Thread(tt);
			threads[i].start();
		}
//		TrainCreator tt = new TrainCreator(line);
//		Thread t = new Thread(tt);
//		line.getRailLines().get(0).addTrain(t);
//		t.start();
//		System.out.println(t);
		System.out.println(line);
	}
	
	public static void createTrack() {
		Random rand = new Random();
		int x = rand.nextInt(2)+1;
		Stop glasgow = new Station("Glasgow", 2);
		Track one = new Track();
		Stop stirling = new Station("Stirling", x);
		Track two = new Track();
		Stop perth = new Station("Perth", x);
		Track three = new Track();
		Stop inverness = new Station("Inverness", x);
		line.add(glasgow);
		line.add(one);
		line.add(stirling);
		line.add(two);
		line.add(perth);
		line.add(three);
		line.add(inverness);
	}
}
