import java.util.ArrayList;

/**
 * @author 0808148w
 * This class defines an abstract superclass which both Track and Station classes will inherit from. Each subclass will inherit the abstract setStopTime() method that will determine how long a train will stay in a section of the railway line
 * @param trainCanBeAddedToSection - a flag to check whether a train can be added to this section of the rail line
 * @param trains - a list of trains that are on this section
 * @param length - length of this 'section' (metres), each instance of either subclass will set the specific length in their constructor
 * @param capacity - the number of trains that can occupy this section
 * @param name - the name of this section
 * @param SECS_TO_MS - a constant that both subclasses will invoke to convert the stop/sleep time into milliseconds
 */
public abstract class RailSection {
	private boolean trainCanBeAddedToSection;
	private ArrayList<Train> trains; 
	private int length; 
	private int capacity;
	private String name; 
	private final int SECS_TO_MS = 1000;

	/**
	 * This constructor is defined for the use of each instance in the track class
	 * @param trains - a list of trains on this section
	 * @param name - name of this rail section
	 * @param trainCanBeAddedToSection - a flag determining whether a train can be added to this rail section
	 */
	public RailSection() {
		this.trains = new ArrayList<Train>();
		this.name = null;
		this.trainCanBeAddedToSection = true;
	}

	/**
	 * This constructor is defined for the use of each instance in the station class
	 * @param trains - a list of trains on this section
	 * @param name - name of this rail section 
	 * @param capacity - number of trains that can occupy this rail section
	 * @param trainCanBeAddedToSection - a flag determining whether a train can be added to this rail section
	 */
	public RailSection(String name, int capacity) {
		this.trains = new ArrayList<Train>();
		this.name = name;
		this.capacity = capacity;
		this.trainCanBeAddedToSection = true;
	}

	/**
	 * Return the capacity 
	 * @return number of maximum allowable trains on this section of the rail way line
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * This method sets the capacity for a section of rail 
	 * @param capacity - max allowable number of trains for a section of the rail way line
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * This method returns the name of a section of rail 
	 * @return String - name of a section
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method will set the name of a section of the rail
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method will add/advance a train to the next section
	 * of a rail way line providing there is room and the train thread has completed slept long enough
	 * It'll advance a train if possible and then set the time limit the train can occupy the next section of the rail line
	 * If a train is added and the number of trains at that section reaches the capacity of that section
	 * , the trainCanBeAddedToSection flag will be set to false. This is used by an instance of the Rail
	 * Line Class to determine if the next train can be added to this section
	 * @param train object 
	 */
	public void addTrain(Train train) {
		if (this.capacity > this.trains.size()) {
			this.trains.add(train);
			this.setStopTime();
		} 
		if (this.capacity == this.trains.size()) {
			this.trainCanBeAddedToSection= false;
		}
	}

	/**
	 * Method to remove a train, called by an instance of the Rail Line class
	 * Once a train as been removed, capacity would be greater than the number 
	 * of trains in that section. The trainCanBeAddedToSection flag is reset, allowing another train to be 
	 * added in the future
	 */
	public void removeTrain(Train atrain)  {
		this.getTrains().remove(atrain);
		this.trainCanBeAddedToSection = true;
	}
	
	/**
	 * This method will return the list of trains that are 'on' a section of the rail way line
	 * @return list of train objects
	 */
	public ArrayList<Train> getTrains() {
		return this.trains;
	}

	/**
	 * This method will calculate and set the amount of time a train will be on a section of rail way line
	 * Each subclass will implement it uniquely
	 */
	public abstract void setStopTime();

	/**
	 * This method will return the length of a section of rail
	 * @return int - length of a section of the railway line
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * this method will set the length of a section of rail
	 * @param length  of a section of the railway line
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * This method will return a string representation of the sections of rail, including the name of each section and any trains that are
	 * 'on' it. This method will be called by an instance of the rail line class
	 */
	public String toString() {
		String s = "";
		for (Train trains : this.trains) {
			s += trains.getName() + ", ";
		}
		return "|----" + this.name + "--" + s + "----|";
	}

	/**
	 * This method checks whether a section of the rail line has enough capacity to be added on the next run
	 * @return boolean - whether a train can be added
	 */
	public boolean getCanATrainBeAddedBoolean() {
		return this.trainCanBeAddedToSection;
	}
	
	/**
	 * This method returns the constant SECS_TO_MS. It is defined here so that the subclass can call the setStopTime method ensuring the calculated time is correctly converted to ms.
	 * @return int - seconds to milliseconds constant
	 */
	public int getConversionRate() {
		return this.SECS_TO_MS;
	}
}