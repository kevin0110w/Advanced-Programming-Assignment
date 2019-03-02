/**
 * @author 0808148w
 * An instance of this class will be responsible for printing the railway every second
 * It'll call the printTrack() method in the rail Line class
 */
public class Printer implements Runnable {
	private RailLine line;
	private final int SLEEP_TIME = 1000;
	
	/**
	 * Create an instance of this class 
	 * @param aline - a rail line object
	 */
	public Printer(RailLine aline) {
		this.line = aline;
	}
	
	/**
	 * A thread object created from an instance of this class will continue to print indefinitely
	 */
	@Override
	public void run() {
		while (true) {
			this.line.printTrack();
			try {
				Thread.sleep(SLEEP_TIME);
			}
			catch (InterruptedException e) {
			}
		}
	}
}
