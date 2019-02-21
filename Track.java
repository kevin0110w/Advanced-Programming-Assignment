import java.util.ArrayList;

/**
 * This class defines the subclass Track, which will inherit from RailSection.
 * A track differs  as it has a max capacity of 1 and a length of 1000
 * @author 0808148w
 */
public class Track extends RailSection {

	public Track() {
		super();
		this.setName("track");
		this.setLength(1000);
		this.setCapacity(1) ;
		//		this.trains = new ArrayList<>();
		//		this.trainCapacity = new Train[1];

	}
	/**
	public static void main(String[] args) {
		Track one = new Track();
		Train s = new SlowTrain("Billy");
		Train p = new FastTrain("Joe");
		Train t = new FastTrain("Ted");
		Train x = new SlowTrain("Paddy");
		one.addTrain(s);
		one.addTrain(p);
		one.addTrain(t);
		one.addTrain(x);
		System.out.println(one);
	}
	 */
	
	/**
	 * This method calculate the time a current train will be in a track section of the rail line.
	 */
	@Override
	public void setStopTime() {
		int time = 0;
		Train aTrain = this.getTrains().get(0);
		time = this.getLength() / aTrain.getSpeed();
		aTrain.setTimeLimit(time);
	}
}

/**
public class Track extends RailSection {
	//	private ArrayList<Train> trains; // list of Trains that can be on at any one time
	//	public final int l; // length (m)
	//	public final int capacity; // 1 train on any track on any one time
	//	public final String name = "track";
	//	public Train[] trainCapacity;
//	public void addTrain(Train train) {
//		if (this.capacity > this.trains.size()) {
//			this.trains.add(train);
//		}
//	}
//	
//	public void removeTrain() {
//		this.trains.clear();
//	}

//	public String toString() {
//		String s = "";
//		for (Train trains : this.trains) {
//			s += trains.getTrainName() + ", ";
//		}
//		return "|----" + this.name + "--" + s + "----|";
//	}

*/