/**
 * This class defines the superclass, Train which both FastTrain and SlowTrain objects will inherit from
 *  * @author 0808148w
 */
public abstract class Train {
	private String trainName;  // name of a train 
	private int speed; // a train's speed
	public int time; // this will be the time a train is on a particular section of a rail. This will be reset if a train moves to next section
	private int timeLimit; //  a train's inner clock (i.e. max time it'll hypothetically spend on a section of rail, providing next section has extra capacity
//	private RailSection railsection;  // the section of rail that a train will be on (is this required?)
	
	public Train(String name) {
		this.trainName = name;
		this.speed = 0;
		this.timeLimit = 0;
//		this.railsection = null;
		this.time = 0;
	}
	
	/**
	 * This method sets the section of rail that a train is currently occupying
	 * @param section of rail, whether track or station
	 */
//	public void setRailSection(RailSection section) {
//		this.railsection = section;
//	}
	
	/**
	 * This method returns the current section of rail that a train object is currently occupying
	 * @return
	 */
//	public RailSection getRailSection() {
//		return this.railsection;
//	}
	
	/**
	 * This method will set the name of a train
	 * @param name
	 */
	public void setTrainName(String name) {
		this.trainName = name;
	}
	
	/**
	 * This method will return the name of a train instance
	 * @return
	 */
	public String getTrainName() {
		return this.trainName;
	}
	
	/**
	 * This method will set the speed of a particular train
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * This method will return a train's speed
	 * @return
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * This method will return a train's inner clock (i.e. the length of time it'll stay within a section of the rail line)
	 * @return
	 */
	public int getTimeLimit() {
		return this.timeLimit;
	}
	/**
	 * This method will increase a train's clock counter by the amount of time it's in a section of the rail line
	 * @param time
	*/
	public void setTimeLimit(int time) {
//		this.timeLimit += time;
		this.timeLimit = time;
	}

	/**
	 * A string representation of the train
	 */
	public String toString() {
		return "I am the " + this.trainName + " train and my speed is " + this.speed + " !";
	}
	
	public void resetTrainTime() {
		this.time = 0;
	}
}



