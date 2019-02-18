import  java.util.Random;


public class TrainCreator implements Runnable {
	private railLine line;
	public int x;
	
	public TrainCreator(railLine rails) {
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
			System.out.println(x);
			i++;
		}
	}
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
		line.getRailLines().get(0).addTrain(train);
//		System.out.println(line.getRailLines().get(0).toString());
	}

}

