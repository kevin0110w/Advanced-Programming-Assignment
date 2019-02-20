import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Test {
	
	private static RailLine line;
	private static TrainCreator tt;
	public static void main(String[] args) {
		line = new RailLine();
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
		RailSection glasgow = new Station("Glasgow", 2);
		Track one = new Track();
		RailSection stirling = new Station("Stirling", x);
		Track two = new Track();
		RailSection perth = new Station("Perth", x);
		Track three = new Track();
		RailSection inverness = new Station("Inverness", x);
		line.add(glasgow);
		line.add(one);
		line.add(stirling);
		line.add(two);
		line.add(perth);
		line.add(three);
		line.add(inverness);
	}
}
