/**
 * This class inherits from the superclass, Train.
 * Each instance of this class, a slow train object, will have a set speed of 10 ms-1.
 * @author 0808148w
 */
public class SlowTrain extends Train {
	public SlowTrain(String name) {
		super(name);
		this.setSpeed(10);
	}
	
	@Override
	public String toString() {
		return "I am the " + this.getTrainName() + " train, a slow train and my speed is " + this.getSpeed() + " !";
	}
}
