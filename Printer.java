/**
 * @author 0808148w
 * An instance of this class will be responsible for printing the railway every second
 * It'll call the printTrack() method in the rail Line class
 */
public class Printer implements Runnable {
	private PrintingInterface printingInterface;
	private final int SLEEP_TIME = 1000;
	
	/**
	 * Create an instance of this class 
	 * @param PrintingInterface printingInterface - an interface that describes the printing method, passed as an argument here
	 */
	public Printer(PrintingInterface printingInterface) {
		this.printingInterface = printingInterface;
	}
	
	/**
	 * A thread object created from an instance of this class will continue to print indefinitely
	 */
	@Override
	public void run() {
		while (true) {
			this.printingInterface.printTrack();
			try {
				Thread.sleep(SLEEP_TIME);
			}
			catch (InterruptedException e) {
			}
		}
	}
}
