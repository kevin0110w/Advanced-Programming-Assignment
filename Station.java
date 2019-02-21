import java.util.ArrayList;

/**
 * This class defines the subclass Station, which will inherit from the class, RailSection.
 * A station differs as it has a max capacity > 1 and a length of 100
 * @author 0808148w
 */
public class Station extends RailSection {
//		private ArrayList<Train> trains;
//		private String name;
//		private int capacity;
//		private Train[] trainCapacity;
//		private final int l; // length

	public Station(String name, int capacity) {
		super(name, capacity);
		this.setLength(100);
//		this.setName(name);
//		this.capacity = capacity;
//		this.trainCapacity = new Train[capacity];
//		trains = new ArrayList<>();
	}


	/**
	 * This method will set each train's time to stop at a station on the line 
	 */
	@Override
	public void setStopTime() {
		int time = 0;
		for (Train aTrain : this.getTrains()) {
			time = (this.getLength() / aTrain.getSpeed()) + 5;
			aTrain.setTimeLimit(time);
		}
	}
	
	/**
	public static void main(String[] args) {
		Station glasgow = new Station("Glasgow", 3);
		Train s = new SlowTrain("Billy");
		Train p = new FastTrain("Joe");
		Train t = new FastTrain("Ted");
		Train x = new SlowTrain("Paddy");
		glasgow.addTrain(s);
		glasgow.addTrain(p);
		glasgow.addTrain(t);
		glasgow.addTrain(x);
		System.out.println(glasgow);
		Station EK = new Station("East Kilbride", 1);
		System.out.println(EK);
	}
	*/
}

/**
//public void addTrain(Train train) {
//	if (this.capacity > this.trains.size()) {
//		this.trains.add(train);
//	}
//}
//
//
//
//public String toString() {
//	String s = "";
//	for (Train trains : this.trains) {
//		s += trains.getTrainName() + ", ";
//	}
//	return "|----" + this.name + "--" + s + "----|";
//}
//
//@Override
//public void setName(String name) {
//	this.name = name;
//}
*/
