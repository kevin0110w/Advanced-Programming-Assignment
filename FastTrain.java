
public class FastTrain extends Train {
	private final int speed;
	
	public FastTrain(String name) {
		super();
		this.trainName = name;
//		this.setName(name);
		this.speed = 500;
		this.clockcounter=0;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	@Override
	public String toString() {
		return "I am  " + this.trainName + ", the fast train and my speed is " + this.getSpeed() + " !";
	}
}
