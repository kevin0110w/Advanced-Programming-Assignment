import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 0808148w
 * This class is responsible for creating a rail way line object. An instance of this class will be the 
 * shared object that will be updated by the TrainCreator thread or the thread from this class.
 * @param line - an list of rail sections that make up the railway line (i.e. station / track repeats)
 * @param lock, lock2 - an object from the lock class to let a thread manipulate the shared resource without external interference
 * @param canAddCondition : a condition to say whether a traincreator thread can add a train because existing trains have been
 * shunted along the line 
 * @param - SLEEP_TIME - a thread object will try and create a new train every second
 */
public class RailLine implements PrintingInterface  {
	private ArrayList<RailSection> line; // a railway line made up of railway sections
	private ReentrantLock lock; 
	private Condition canAdvanceCondition; 
	private final int SLEEP_TIME = 1000;

	public RailLine() {
		this.line = new ArrayList<>();
		this.lock = new ReentrantLock();
		this.canAdvanceCondition = lock.newCondition();
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
	 * The traincreator thread object will try to call this method after creating an instance of the train class, to add a train to the first station before starting a thread object of that train instance
	 * The shared object, the rail line, is locked for the TrainCreator thread to use.
	 * If it's not possible for the train to be added, the thread will  enter the waiting state and relinquish the lock object.
	 * It'll wait until the flag is true, i.e. there is space, before awaking and completing rest of this method
	 * @param aTrain instance created by the createTrain() method in the TrainCreator class
	 */
	public void addTrainToFirstStation(Train aTrain) {
		lock.lock();
		while (!this.line.get(0).getCanATrainBeAddedBoolean()) { 
			try {
				canAdvanceCondition.await();
			}
			catch (InterruptedException e) {
			} 
		}
		this.line.get(0).addTrain(aTrain); 
		Thread newTrainThread = new Thread(aTrain);  
		newTrainThread.start();
		canAdvanceCondition.signalAll(); 
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
	 * A print method that is called by a thread object of the printer class. It needs to be locked while printing to minimise the risk of java.concurrentModification exception
	 */
	@Override
	public void printTrack() {
		lock.lock();
		System.out.println(this);
		canAdvanceCondition.signalAll(); // signal other threads
		lock.unlock();
	}

	/**
	 * A thread of train object type will call this method to move it to the next section on the rail line. If there is not enough room in the next section, determined by the flag, the thread will go into the waiting state.
	 * If the train has reached the end of this line, it'll exit this method immediately, otherwise the train will be added to the next section
	 * This will allow other threads of train object type to call the leave method and make room in a section
	 * @param atrain - a reference of the current train that has 'awoken' and called this method
	 */
	public void enterSection(Train atrain) {
		if (atrain.getCurrentRailSegment() == this.line.size()-1) { 
			return;
		}
		lock.lock(); 
		while (!atrain.getLine().getStops().get(atrain.getCurrentRailSegment()+1).getCanATrainBeAddedBoolean()) {
			try {
				canAdvanceCondition.await();
			} catch (InterruptedException e) {
			}
		}
		this.line.get(atrain.getCurrentRailSegment()+1).addTrain(atrain);
		lock.unlock();
	}

	/**
	 * A thread of train object type will call this method to leave the section that it was on.
	 * If the train has reached the end of this line, the train will be removed from the last station, signal other threads to enter next section and exit the method call
	 * Otherwise, the train will be removed from the section it's currently on and then it's station counter is incremented and a thread of this train will start again, i.e. start the timer on the current section.
	 * @param atrain - a reference of the current train that has 'awoken' and called this method
	 */
	public void leaveSection(Train atrain) {
		lock.lock();
		if (atrain.getCurrentRailSegment() == this.line.size() -1) {
			this.line.get(this.line.size()-1).removeTrain(atrain);
		} else { 
			this.line.get(atrain.getCurrentRailSegment()).removeTrain(atrain);
			atrain.incrementCurrentRailSegment(); 
			Thread atrainthread = new Thread(atrain); 
			atrainthread.start();  
		}
		canAdvanceCondition.signalAll();
		lock.unlock(); 
	}
}
