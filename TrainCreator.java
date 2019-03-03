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
//	private RailLine line;
	private TrainCreationInterface trainCreationInterface;
	private Random rand = new Random();
	private  int trainNumber = 0; 
	private  int SLEEP_TIME;
	
	public TrainCreator(TrainCreationInterface trainCreator) {
//		this.line = aline;
		this.trainCreationInterface = trainCreator;
		this.SLEEP_TIME = 0;
		
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
			aNewTrain = new SlowTrain(this.trainNumber +"");
		}
		else {
			aNewTrain = new ExpressTrain(this.trainNumber + "");
		}
//		this.line.addTrainToFirstStation(aNewTrain);
		this.trainCreationInterface.addTrainToFirstStation(aNewTrain);
	}
	
	/**
	 * Instructions for train creator thread to keep creating a new train and sleep for a random number of milliseconds between 1000 and 5000
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

