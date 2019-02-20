import java.util.ArrayList;

/**
 * This class defines the superclass which both Track and Station classes will inherit from
 * @author Freckles
 */
public abstract class RailSection {
	private ArrayList<Train> trains; // list of Trains that can be added at any time
	private int l; // length of a stop/'segment'(m)
	private int capacity; // 1 train on any track on any one time
	private String name;
//	public Train[] trainCapacity;

	public RailSection() {
		this.trains = new ArrayList<>();
		this.name = null;
	}

	public RailSection(String name, int capacity) {
		this.trains = new ArrayList<Train>();
		this.name = name;
		this.capacity = capacity;
//		this.trainCapacity = new Train[capacity];
	}
	
	/**
	 * Return the capacity (max number of allowable trains on this section of rail)
	 * @return integer
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * This method sets the capacity for a section of rail (max allowable number of trains per section)
	 * @param capacity 
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * This method returns the name of a section of rail 
	 * @return String - name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method will set the name of a sectiokn of rail
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method will add a train to a section of rail providing there is room
	 * @param train object
	 */
	public void addTrain(Train train) {
		if (this.capacity > this.trains.size()) {
			this.trains.add(train);
			this.setStopTime();
		}
	}
	
	/**
	 * Abstract method to remove train which can differ depending on whether rail section is type station or track
	 */
	public  void removeTrain(Train trainName)  {
		this.getTrains().remove(trainName);
	}
//	
//	public void advanceTrain(Train trainName) {
//	}
	/**
	 * This method will set the list of trains with another list of trains
	 * @param trainList - a list of train objects
	 */
	public void setTrains(ArrayList<Train> trainList) {
		this.trains = trainList;
	}
	
	/**
	 * This method will return the list of trains that are 'on' the stop
	 * @return list of train objects
	 */
	public ArrayList<Train> getTrains() {
		return this.trains;
	}
	
	/**
	 * This method will count the length of time a train will be stuck in a section of rail
	 * Each subclass will implement it uniquely
	 */
	public abstract void setStopTime();
	
	/**
	 * This method will return the time a train is at this section of rail
	 * @return int - seconds

	public int getTime() {
		return this.time;
	}
	
	/**
	 * This method will set the time a train will be at a section of rail, which will be defined by the subclass

	public abstract void setTime();
	
	/**
	 * This method will return the length of a section of rail
	 * @return int - length
	 */
	public int getLength() {
		return this.l;
	}
	
	/**
	 * this method will set the length of a section of rail
	 * @param length 
	 */
	public void setLength(int length) {
		this.l = length;
	}
	
	/**
	 * This method will return a string representation of the section of rail, including it's name and any trains that are
	 * 'on' it
	 */
	public String toString() {
		String s = "";
		for (Train trains : this.trains) {
			s += trains.getTrainName() + ", ";
		}
		return "|----" + this.name + "--" + s + "----|";
	}
	
	public boolean containsTrain(Train anotherTrain) {
		return this.trains.contains(anotherTrain);
	}
	/**
	public static void main(String[] args) {
		RailSection one = new Track();
		RailSection two = new Station("Glasgow", 2);
//		Track one = new Track();
		Train s = new SlowTrain("Billy");
		Train p = new FastTrain("Joe");
		Train t = new FastTrain("Ted");
		Train x = new SlowTrain("Paddy");
		System.out.println(one.getCapacity());
		one.addTrain(s);
//		one.removeTrain(one.getTrains().size());
		two.addTrain(t);
		one.addTrain(p);
		one.addTrain(t);
		one.addTrain(x);
		System.out.println(one);
		System.out.println(two);
		two.addTrain(x);
		System.out.println(two);
		two.addTrain(s);
		System.out.println(two);
	}
	*/
	public void addTrain(Thread t) {
		t.start();
	}
	public Train getTrain(Train trainName) {
		Train aTrain = null;
		for (int i = 0; i < this.trains.size(); i++) {
			if (this.trains.get(i).equals(trainName)) {
				aTrain =  this.trains.get(i);
			}
		}
		return aTrain;
	}
}