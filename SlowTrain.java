/**
 * This class inherits from the superclass, Train.
 * Each instance of this class, a slow train object, will have a set speed of 10 ms-1, which is declared in the superclass
 * @author 0808148w
 */
public class SlowTrain extends Train {
	private final int SLOW_TRAIN_SPEED = 10; 
	
	/**
	 * Each instance of this class will call the constructor in the superclass
	 * The speed is set using the constant, SLOW, defined as a class variable for this subclass
	 * @param name of a train
	 */
	public SlowTrain(String name) {
		super(name);
		this.setSpeed(this.SLOW_TRAIN_SPEED);
	}
}
