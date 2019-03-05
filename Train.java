/**
 * This class defines the superclass, Train which both FastTrain and SlowTrain objects will inherit from.
 * The superclass will implement the Runnable interface.
 * When a thread calls the start method, (i.e. when it's advanced to the next segment on the railway line)
 * it'll sleep for as long as the time that the train object,
 * passed to the thread constructor, should stay in it's current segment as a minimum. 
 * If a thread has finished the sleep and the boolean "awake" is set to true,  this is the basis of when the train can advance
 * @author 0808148w
 */
public abstract class Train implements Runnable {
	private String trainName; 
	private int speed;
	private int timeLimit;
	private int currentRailSegment; 
	private boolean awake; 
	private RailLine railline;
	private boolean trainAdvanced = false;

	/**
	 * This constructor is defined and will be called by each instance in the subclasses
	 * @param name - name of a train
	 * @param speed - speed of a train - each instance of the subclass will adjust this depending on whether express or slow
	 * train
	 * @param - timelimit - the minimum time that a thread of the train object should stay in the segment
	 * @param - awake - a flag that is used by an instance of the rail line class, 
	 * to determine if a thread has complete the sleep method and can advance
	 */
	public Train(String name) {
		this.trainName = name;
		this.speed = 0;
		this.timeLimit = 0;
		this.currentRailSegment = 0;
		this.awake = false;
		this.trainAdvanced = false;
	}
	
	public Train(String name, RailLine line) {
		this.trainName = name;
		this.speed = 0;
		this.timeLimit = 0;
		this.currentRailSegment = 0;
		this.awake = false;
		this.railline = line;
		this.trainAdvanced = false;
	}
	
	public boolean getTrainAdvanced() {
		return this.trainAdvanced;
	}
	
	public void trainAdvanced() {
		this.trainAdvanced = true;
	}
	
	public void resetTrainAdvanced() {
		this.trainAdvanced = false;
	}
	

	/**
	 * This method will set the name of a train
	 * @param name of a train
	 */
	public void setTrainName(String name) {
		this.trainName = name;
	}

	/**
	 * This method will return the name of a train instance
	 * @return the name of a train
	 */
	public String getName() {
		return this.trainName;
	}

	/**
	 * This method will set the speed of a particular train
	 * @param speed of a train
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * This method will return a train's speed
	 * @return the speed of a train
		 */
	public int getSpeed() {
		return this.speed;
	}


	/**
	 * This method will return the time an instance of the subclass should stay on a section of the rail line
	 * @return time in seconds
	 */
	public int getTimeLimit() {
		return this.timeLimit;
	}
	
	/**
	 * This method will set the time a train's should stay at a segment on the rail line. This is called whenever an instance of the
	 * subclass has moved into a new segment
	 * @param time in seconds, calculated once the train has moved onto a new segment
	 */
	public void setTimeLimit(int time) {
		this.timeLimit = time;
	}
	
	/**
	 * This method will return the index of the rail way line list that this tain is currently occupying
	 * @return the index of the rail way line
	 */
	public int getCurrentRailSegment() {
		return this.currentRailSegment;
	}
	
	/**
	 * This method will increment the index of the rail way line list that this train is currently occupying (i.e. each time it 
	 * advances)
	 */
	public void incrementCurrentRailSegment() {
		this.currentRailSegment++;
	}
	
	/**
	 * This method is used to determine whether a train object has woken (i.e. the thread has finished it's run method)
	 * @return boolean - true if complete
	 */
	public boolean isAwake() {
		return this.awake;
	}
	
	/**
	 * This method is called once a thread awakens to set the flag used by the instance of the rail line class to determine if
	 * a train can advance to the next step of the rail way line
	 */
	public void setAwake() {
		this.awake = true;
	}
	
	/**
	 * This method is called by an instance of the rail section class after a train has been added
	 * to the next section and resets the flag.
	 */
	public void setNotAwake() {
		this.awake= false;
	}
	
	public RailLine getLine() {
		return this.railline;
	}
	
	public void setLine(RailLine aline) {
		this.railline = aline;
	}

	/**
	 * When a train thread invokes the start method, it'll sleep for the time it should remain in a segment of the railway line
	 * as a minimum. Once it awakens, it'll change the awake flag, which an instance of the Rail Line class will use to determine
	 * if a train can advance to the next stage.
	 
	@Override
	public void run() {
		try {
			Thread.sleep(this.getTimeLimit());
		} catch (InterruptedException e) {
		}
		setAwake();
	}
	*/
	
	@Override
	public void run() {
		try {
			Thread.sleep(this.getTimeLimit());
		} catch (InterruptedException e) {
		}
		this.railline.enter(this);
		this.railline.leave(this);
	}
}