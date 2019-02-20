import  java.util.Random;

/**
 * This class is responsible for generating a thread which will create a train object
 * @author Freckles
 *
 */
public class TrainCreator implements Runnable {
	private RailLine line;
	public int x;
	
	public TrainCreator(RailLine rails) {
		this.line = rails;
		this.x = 0;
	}
	public SlowTrain createSlowTrain() {
		this.x++;
		return new SlowTrain(Integer.toString(this.x));

	}

	public FastTrain createFastTrain() {
		this.x++;
		return new FastTrain(Integer.toString(this.x));
	}
	
	public static void main(String[] args) {
		Random rand = new Random();;
		int i = 0;
		while (i < 10) {
			int x = rand.nextInt(2) + 1;
			Train train = new SlowTrain(Integer.toString(x));
			Train train2 = new FastTrain(Integer.toString(x*10));
			System.out.println(train);
			System.out.println(train2);
			i++;
		}
	}
	
	
	/**
	 * Method for thread to either create an express or slow train depending on random number generated
	 */
	@Override
	public void run() {
		Random rand = new Random();
		Train train = null;
		int x = rand.nextInt(2) + 1;
		if (x % 2 == 0) {
			 train = createSlowTrain();
//			System.out.println(train);
		} else {
			 train = createFastTrain();
//			System.out.println(train);
		}
		line.getStops().get(0).addTrain(train);
//		System.out.println(line.getRailLines().get(0).toString());
	}

}

