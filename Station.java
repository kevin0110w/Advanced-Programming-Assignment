/**
 * * @author 0808148w
 * This class defines the subclass Station, which will inherit from the class, RailSection.
 * A station differs as it has a max capacity > 1, which will be passed to the constructor.
 *  @param stationLength - a defined constant for a station length
 *  @param additionalStationTime - a constant representing the extra time that a train would stay in a station
 *  segment of the railway line.
 *  @param conversionFromMilliSecondsToSeconds - to help the calc in converting seconds to milliseconds ensuring correct
 *  time is used for thread sleep in the train class.
 */
public class Station extends RailSection {
	private final int STATION_LENGTH = 100;
	private final int ADDITIONAL_STATION_WAITING_TIME = 5; 

	/**
	 * Each instance of the station object will call the constructor in the super class
	 * The length is set using the constant, stationLength;
	 * @param name of a station
	 * @param capacity - maximum number of trains that can occupy a station at any time
	 */
	public Station(String name, int capacity) {
		super(name, capacity);
		this.setLength(this.STATION_LENGTH);
	}

	/**
	 * This method calculates the time a current train will be in a station section of the rail line.
	 * It'll be used to set the time a train thread should sleep (in seconds once it has moved to a new station segment on the 
	 * rail line
	 */
	@Override
	public void setStopTime() {
		int time = 0;
		for (Train aTrain : this.getTrains()) {
			time = ((this.getLength() / aTrain.getSpeed()) + this.ADDITIONAL_STATION_WAITING_TIME) * super.getConversionRate();
			aTrain.setTimeLimit(time);
		}
	}
}