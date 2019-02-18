
public abstract class Train {
	protected String trainName;
	private int speed;
	protected int clockcounter;
	
//	public Train(String trainName) {
//		this.trainName = trainName;
//	}

	public String getTrainName() {
		return this.trainName;
	}

	public void setName(String name) {
		this.trainName = name;
	}
	
	public abstract int getSpeed();
	

	
	public String toString() {
		return "I am the " + this.trainName + " train and my speed is " + this.speed + " !";
	}

}
