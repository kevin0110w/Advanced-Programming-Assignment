import java.util.ArrayList;

public class Track {
	private ArrayList<Train> trains; // list of Trains that can be on at any one time
	public final int l; // length (m)
	public final int capacity; // 1 train on any track on any one time
	public final String name;
	
	public Track() {
		this.trains = new ArrayList<>();
		this.l = 1000;
		this.capacity = 1;
		this.name = "track";
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
}
