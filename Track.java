/**
 * @author 0808148w
 * This class defines the subclass Track, which will inherit from RailSection.
 * A track differs as it has a max capacity of 1 and a length of 1000, which are both declared in this class as constants.
 *  @param TRACK_LENGTH - a defined constant for the length of a track segment
 *  @param TRACK_CAPACITY - a defined constant that each track segment can only hold one train at a time
 */
public class Track extends RailSection {
	private final int TRACK_LENGTH = 1000;
	private final int TRACK_CAPACITY = 1;

	/**
	 * Each instance of the station object will call the constructor in the super class
	 * The length and capacity are set using the constants, trackLength and trackCapacity respectively.
	 */
	public Track() {
		super();
		this.setName("track");
		this.setLength(this.TRACK_LENGTH);
		this.setCapacity(this.TRACK_CAPACITY) ;
	}

	/**
	 * This method calculates the time a current train will be in a track section of the rail line.
	 * It'll be used to set the time a train thread should sleep (in seconds once it has moved to a new track segment on the 
	 * rail line
	 */
	@Override
	public void setStopTime() {
		int time = 0;
		Train aTrain = this.getTrains().get(0);
		time = this.getLength() / aTrain.getSpeed() * super.getConversionRate();
		aTrain.setTimeLimit(time);
	}
}