import java.util.ArrayList;
import java.util.Iterator;


public class RailLine extends Thread {
	private ArrayList<RailSection> line;
	private ArrayList<Train> trains;
	
	public RailLine() {
		this.line = new ArrayList<>();
		this.trains = new ArrayList<>();
	}
	
	public ArrayList<RailSection> getStops() {
		return this.line;
	}

	public void add(RailSection name) {
		this.line.add(name);
	}
	
	public String toString() {
		String s = "";
		for (RailSection e: this.line) {
			s += e.toString();
		}
		return s;
	}
	
	public static void main(String[] args) throws InterruptedException {
		int x = 0;
		RailLine one = new RailLine();
		RailSection glasgow = new Station("Glasgow", 2);
		RailSection track = new Track();
		RailSection edinburgh = new Station("Edinburgh", 3);
		one.add(glasgow);
		one.add(track);
		one.add(edinburgh);
		Train slow = new SlowTrain("slooow");
		Train express = new FastTrain("express");
		glasgow.addTrain(slow);
		glasgow.addTrain(express);
		one.trains.add(express);
		one.trains.add(slow);
		glasgow.setStopTime();
		while (true) {
//			for (Train aTrain : one.trains) {
//			if (aTrain.time >= aTrain.getTimeLimit()) {
//				one.advanceTrain(aTrain);
//			}
//			aTrain.time++;
			for (int i = 0; i < one.trains.size(); i++) {
				if (one.trains.get(i).time >= one.trains.get(i).getTimeLimit()) {
					one.advanceTrain(one.trains.get(i));
				}
				one.trains.get(i).time++;
			}
			x++;
			Thread.sleep(1000);
			
			System.out.println(one + "\tx = " + x);		
			if (one.isEmpty()) {
				System.out.println("Rail Line is empty!");
				break;
			}

			if (x % 50 == 0) {
				Train anewtrain = new SlowTrain(Integer.toString(x));
				one.getStops().get(0).addTrain(anewtrain);
				one.trains.add(anewtrain);
				one.getStops().get(0).setStopTime();
				System.out.println("New train added");
			}
		}
	}
	
	public void advanceTrain(Train aTrain) {
		for (int i = 0; i < this.line.size(); i++) {
			if (i == this.line.size()-1 && this.line.get(i).containsTrain(aTrain)) {
				System.out.println(aTrain.getTrainName() + " at end of the line!");
				this.line.get(i).removeTrain(aTrain);
				this.trains.remove(aTrain);
				break;
			} else if (this.line.get(i).containsTrain(aTrain) && this.line.get(i+1).getCanATrainBeAddedBoolean()) {
				System.out.println(aTrain + " moving to next part!");
				this.line.get(i+1).addTrain(aTrain);
				this.line.get(i+1).setStopTime();
				this.line.get(i+1).getTrain(aTrain).resetTrainTime(); // null pointer exception with a new train, why?
				this.line.get(i).removeTrain(aTrain);
				break;
			}
		}
	}
	
	public boolean isEmpty() {
		int numberOfTrainsOnLine = 0;
		for (RailSection aSection : this.line ) {
			numberOfTrainsOnLine += aSection.getTrains().size();
		}
		return numberOfTrainsOnLine == 0;
	}
}

/**
while (true) {
for (Train aTrain : one.trains) {
	if (aTrain.time == aTrain.getTimeLimit()) {
//		if (one.line.get(0).getTrains().size() >= 0) {
		one.advanceTrain(aTrain);
//		}
	}
	aTrain.time++;
}
x++;
Thread.sleep(1000);
System.out.println(one + ": " + x);
if (one.isEmpty()) {
	break;
}

if (x % 50 == 0) {
	Train anewtrain = new SlowTrain(Integer.toString(x));
	glasgow.addTrain(anewtrain);
	one.trains.add(anewtrain);
	glasgow.setStopTime();
	System.out.println("new train added");
}
}
*/