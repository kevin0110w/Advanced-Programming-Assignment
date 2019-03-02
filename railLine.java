import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 0808148w
 * This class is responsible for creating a rail way line object. An instance of this class will be the 
 * shared object that will be updated by the TrainCreator thread or the thread from this class.
 * @param line - an arraylist of rail section (i.e. station / track repeats)
 * @param trains - an arraylist of trains that are currently on the railway line for printing purposes
 * @param lock - an object from the lock class to let a thread manipulate the shared resource without external interference
 * @param canAddCondition : a condition to say whether a traincreator thread can add a train because existing trains have 
 * shunted along the line 
 * @param - SLEEP_TIME - a thread object will try and create a new train every second
 */
public class RailLine implements Runnable  {
	private ArrayList<RailSection> line; // a railway line made up of railway sections
	private ArrayList<Train> trains; // an arraylist of trains
	private ReentrantLock lock; 
	private Condition canAddCondition; 
	private final int SLEEP_TIME = 1000;
	
	public RailLine() {
		this.line = new ArrayList<>();
		this.trains = new ArrayList<>();
		this.lock = new ReentrantLock();
		this.canAddCondition = lock.newCondition();
	} 
	
	/**
	 * This method will return an arraylist of rail sections
	 * @return  an arraylist of rail sections
	 */
	public ArrayList<RailSection> getStops() {
		return this.line;
	}
	/**
	 * Create the rail way line as depicted in the specification
	 */
	public void createRailLine() {
		this.line.add(new Station("Glasgow", 3));
		this.line.add(new Track());
		this.line.add(new Station("Stirling", 2));
		this.line.add(new Track());
		this.line.add(new Station("Perth", 2));
		this.line.add(new Track());
		this.line.add(new Station("Inverness", 3));
	}
	 /**
	  * The threadcreator thread object will try to call this method after creating an instance of the train class.
	  * The shared object, the rail line, is locked for the TrainCreator thread to use.
	  * If it's not possible for the train to be added, the thread will  enter the waiting state and relinquish the lock object
	  * @param aTrain instance created by the createTrain() method in the TrainCreator class
	  */
	public void addTrainToFirstStation(Train aTrain) {
		lock.lock();
		while (!this.line.get(0).getCanATrainBeAddedBoolean()) { 
			try {
				canAddCondition.await();
			}
			catch (InterruptedException e) {
			} 
		}
		this.line.get(0).addTrain(aTrain); // add train to first station
		this.trains.add(aTrain); // add train to this list of trains, required for printing purposes - toString()
		Thread newTrainThread = new Thread(aTrain);  // create new thread object with instance of the train passed as argument
		newTrainThread.start(); // start the thread object (i.e. run the timer down for a train to stay in a section)
		lock.unlock(); 
	}
	
	/**
	 * A thread object that is passed an instance of the rail line class will try and call this method indefinitely
	 * This method iterate through the list of trains on the line, and try and move each one based on whether the train thread has woken,  i.e. the train has stayed at least the calculated amount of time on a section of the line. 
	 * Then it'll determine whether the train has reached the end of the line in which case, it'll be removed.
	 * Otherwise, it'll check to see if the section of the rail line that the train wishes to move into, is ready to be occupied (i.e. has capacity) by checking the flag in the rail section(i.e station/track) object. 
	 */
	public void advanceTrains() {
		lock.lock();
		for (int i = 0 ; i < this.trains.size(); i++) {
			if (this.trains.get(i).isAwake()) {  // only interested in train threads that have woken up, 
				if (this.trains.get(i).getCurrentRailSegment() == this.line.size()-1) { // check whether this train is at the last station and if so
					this.line.get(this.line.size()-1).removeTrain(this.trains.get(i)); // remove the train from the line
					this.trains.remove(this.trains.get(i)); // remove train from overall list of trains ensuring correct printing
				} else if (this.trains.get(i).getCurrentRailSegment() < this.line.size()-1 && this.line.get(this.trains.get(i).getCurrentRailSegment()+1).getCanATrainBeAddedBoolean()) { // check that the current train is anywhere between first and penultimate station and whether following section can be occupied
					this.line.get(this.trains.get(i).getCurrentRailSegment()+1).addTrain(this.trains.get(i)); // add this train to the next section
					this.line.get(this.trains.get(i).getCurrentRailSegment()).removeTrain(this.trains.get(i)); // remove this train from the current section
					this.trains.get(i).incrementCurrentRailSegment(); // increment the train objects counter to reflect the index of the rail way line it's now in
					Thread atrainthread = new Thread(this.trains.get(i)); // creating another thread of the train instance
					atrainthread.start(); // start the run method and count down the timer
				}
			}
		}
		canAddCondition.signalAll(); // signal to all threads, preferably the traincreator thread that they can complete the addTrain method 
		lock.unlock();
	}

	/**
	 * A string representation of the railway line object. 
	 */
	public String toString() {
		String s = "";
		for (RailSection e: this.line) {
			s += e.toString();
		}
		return s;
	}
	
	/**
	 * A print method that is called by a thread object of the printer instance
	 * The shared rail line object is locked ensuring that it's not accidentally manipulated resulting in incorrect printing
	 * As this method does not involve moving trains/creating space in the 2nd segment and allowing the traincreator thread
	 * from finishing it's job,, it does not require a signalAll()  method
	 */
	public void printTrack() {
		lock.lock();
		System.out.println(this);
		lock.unlock();
	}
	
	/**
	 * A thread object created from an instance of the Rail Line class will try and keep advancing trains indefinitely
	 */
	@Override
	public void run() {
		while (true) {
			advanceTrains();
			try {
				Thread.sleep(SLEEP_TIME);
			} 
			catch (InterruptedException e) {
			}
		}
	}
}