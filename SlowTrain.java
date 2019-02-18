
public class SlowTrain extends Train {
	private final int speed;
	private int clockcounter;

	public SlowTrain(String name) {
		super();
		this.trainName = name;
//		this.setName(name);
		this.speed = 10;
		this.clockcounter = 0;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	@Override
	public String toString() {
		return "I am " + this.trainName + ", the slow train and my speed is " + this.getSpeed() + " !";
	}
}
