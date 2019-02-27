import  java.util.Random;

/**
 * @author 0808148w
 * This class is responsible for generating a thread which will create trains endlessly, providing there is space in the
 * first segment
 * @param line - rail line object
 * @param rand - to generate a random number to determine to form slow or express train
 * @param - x - an integer used to name each train
 * @param - SLEEP_TIME - a thread object will try and create a new train every second
 */
public class TrainCreator implements Runnable {
	private RailLine line;
	private Random rand = new Random();
	private  int trainNumber = 0; 
	private  final int SLEEP_TIME = 1000;
	
	public TrainCreator(RailLine aline) {
		this.line = aline;
	}
	
	/**
	 * Method of creating a new train, called by a threadcreator_2 thread object when there is space in the first station, 
	 * a new instance of the rail line class is returned to update the rail line class variable 
	 *  so that each system.out.print provides an updated snapshot of the line (i.e. with the new train added on)
	 */
	public void createTrain() {
		this.trainNumber++; 
		Train aNewTrain = null;
		int random = rand.nextInt();
		if (random % 2 == 0) {
			aNewTrain = new SlowTrain(this.trainNumber + "s");
		}
		else {
			aNewTrain = new ExpressTrain(this.trainNumber + "e");
		}
		this.line.addTrainToFirstStation(aNewTrain); 
	}
	
	/**
	 * Instructions for train creator thread to keep creating a new train and sleep for 1 second
	 */
	@Override
	public void run() {
		try {
			while (true) {
				this.createTrain();
				Thread.sleep(SLEEP_TIME);
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

