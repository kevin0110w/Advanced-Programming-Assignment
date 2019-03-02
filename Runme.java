/**
 * @author 0808148w
 * This main method will create a traincreator, railline and printer object.
 * Threads for each instance of these classes will be created and will run indefinitely.
 */
public class Runme {
	public static void main(String[] args) {
		RailLine aRailWayLine = new RailLine();
		aRailWayLine.createRailLine();
		TrainCreator theTrainCreator = new TrainCreator(aRailWayLine);
		Printer printer = new Printer(aRailWayLine);
		Thread theTrainCreatorThread = new Thread(theTrainCreator);
		Thread railLineThread = new Thread(aRailWayLine);
		Thread printerThread = new Thread(printer);
		theTrainCreatorThread.start();
		railLineThread.start();
		printerThread.start();
	}
}


