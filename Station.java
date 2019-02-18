import java.util.ArrayList;

public class Station {
	private ArrayList<Train> trains;
	private String name;
	private int capacity;
	private final int l; // length
	
	public Station(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.l = 100;
		trains = new ArrayList<>();
	}
	
	public void addTrain(Train train) {
		if (this.capacity > this.trains.size()) {
			this.trains.add(train);
		}
	}
	
	public String toString() {
		String s = "";
		for (Train trains : this.trains) {
			s += trains.getTrainName() + ", ";
		}
		return "|----" + this.name + "--" + s + "----|";
	}
	
	
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

}
