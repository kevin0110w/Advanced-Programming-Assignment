import  java.util.Random;

/**
 * @author 0808148w
 * This class is responsible for generating a thread that will create trains endlessly
 * @param line - a rail way line - an object of this class will be updated on any changes to the line
 * rather than all methods in the rail line class had an instance from that class was passed
 * @param rand - random object to generate a random number to determine to form slow or express train
 * @param - trainNumber - an integer used to name each train
 * @param - SLEEP_TIME - a thread object will try and create a new train between 1 and 5 seconds
 */
public class TrainCreator implements Runnable {
	private Random rand = new Random();
	private int trainNumber = 0; 
	private int SLEEP_TIME;
	private RailLine line;
	
	/**
	 * The constructor creates an instance of this class.
	 * @param aline - a reference to a rail line object
	 */
	public TrainCreator(RailLine aline) {
		this.line = aline;
		this.SLEEP_TIME = 0;
	}
	
	/**
	 * Method of creating and adding a new train to the first station, called by a thread object created in the TrainCreator class 
	 * The choice of train is randomly determined between express and slow trains. 
	 */
	public void createTrain() {
		this.trainNumber++; 
		Train aNewTrain = null;
		int random = rand.nextInt();
		if (random % 2 == 0) {
			aNewTrain = new SlowTrain(this.trainNumber +"S", this.line);
		}
		else {
			aNewTrain = new ExpressTrain(this.trainNumber + "E", this.line);
		}
		this.line.addTrainToFirstStation(aNewTrain);
	}
	
	/**
	 * Instructions for train creator thread to keep creating a new train and sleep between 1000 and 5000 milliseconds.
	 */
	@Override
	public void run() {
		while (true) {
			this.SLEEP_TIME = rand.nextInt(5000) + 1000;
			this.createTrain();
			try {
				Thread.sleep(SLEEP_TIME);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

