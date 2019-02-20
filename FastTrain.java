/**
 * This class inherits from the superclass, Train.
 * Each instance of this class, a fast train object, will have a set speed of 500 ms-1.
 * @author 0808148w
 */
public class FastTrain extends Train {
	public FastTrain(String name) {
		super(name);
		this.setSpeed(500);
	}

	@Override
	public String toString() {
		return "I am  " + this.getTrainName() + ", the fast train and my speed is " + this.getSpeed() + " !";
	}
}