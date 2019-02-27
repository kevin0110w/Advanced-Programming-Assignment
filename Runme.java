/**
 * @author 0808148w
 * This main method will create a traincreator_2 object to start the endless looping of trains moving along a railway line
 */
public class Runme {
	public static void main(String[] args) {
		RailLine aRailWayLine = new RailLine();
		aRailWayLine.createRailLine();
		TrainCreator theTrainCreator = new TrainCreator(aRailWayLine);
		Thread theTrainCreatorThread = new Thread(theTrainCreator);
		Thread railLineThread = new Thread(aRailWayLine);
		Printer printer = new Printer(aRailWayLine);
		Thread printerThread = new Thread(printer);
		theTrainCreatorThread.start();
		railLineThread.start();
		printerThread.start();
	}
}


