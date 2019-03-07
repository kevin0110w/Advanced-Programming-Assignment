/**
 * This class defines the superclass, Train which both FastTrain and SlowTrain objects will inherit from.
 * The superclass will implement the Runnable interface.
 * When a thread calls the start method, (i.e. when it's advanced to the next segment on the railway line)
 * it'll sleep for as long as the time that the train object,
 * passed to the thread constructor, should stay in it's current segment as a minimum. 
 * If a thread has finished the sleep, it'll call the enter and leave methods sequentially to finalise the move.
 * @author 0808148w
 */
public abstract class Train implements Runnable {
	private String trainName; 
	private int speed;
	private int timeLimit;
	private int currentRailSegment; 
	private RailLine railwayLine;

	/**
	 * This constructor is defined and will be called by each instance in the subclasses
	 * @param name - name of a train is passed to the constructor
	 * @param - line - a reference of the created rail way line is passed to the constructor
	 * @param name - name of a train
	 * @param speed - speed of a train - each instance of the subclass will adjust this depending on whether express or slow
	 * train
	 * @param - timelimit - the minimum time that a thread of the train object should stay in the segment
	 * @param - line - a reference of the created rail way line is passed to the constructor
	 */	
	public Train(String name, RailLine line) {
		this.trainName = name;
		this.speed = 0;
		this.timeLimit = 0;
		this.currentRailSegment = 0;
		this.railwayLine = line;
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
	 * Method to get the rail way line
	 * @return the line in its current form
	 */
	public RailLine getLine() {
		return this.railwayLine;
	}

	/**
	 * When a train thread invokes the start method in the rail line class, it'll start sleeping for the calculated time by the setStopTime() in the RailSection class
	 * as a minimum. Once it awakens, it'll call the enter method in the Rail Line class to try and move to the next section. If that's not possible, it'll go into the waiting state.
	 * If it is possible, it'll call the leave method in the Rail Line class which 'finalises' the move and the line is updated in the rail line class. 
	*/
	@Override
	public void run() {
		try {
			Thread.sleep(this.getTimeLimit());
		} catch (InterruptedException e) {
		}
		this.railwayLine.enterSection(this);
		this.railwayLine.leaveSection(this);
	}
}