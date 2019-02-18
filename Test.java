import java.util.Random;

public class Test {

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
		
		
		TrainCreator tt = new TrainCreator();
		Thread t = new Thread(tt);
		t.start();
		System.out.println(t);
		
	}

}
