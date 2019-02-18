import java.util.ArrayList;

public class railLine {
	private ArrayList<Stop> railLines;
	private ArrayList<Train> trains;
	
	public railLine() {
		this.railLines = new ArrayList<>();
		this.trains = new ArrayList<>();
	}
	public ArrayList<Stop> getRailLines() {
		return this.railLines;
	}
	public Object getObject(int index) {
		return this.railLines.get(index);
	}


	
	public void add(Stop name) {
		this.railLines.add(name);
	}
	
	public String toString() {
		String s = "";
		for (Stop e: this.railLines) {
			s += e.toString();
		}
		return s;
	}
}
