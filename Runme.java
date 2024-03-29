/**
 * @author 0808148w
 * This main method will create a traincreator, railline object and printer object. 
 * Threads for traincreator and printer classes will be created and will run indefinitely.
 * The railway line should look like:  
 * | -- Glasgow (3 trains allowable)-- | | -- -track (1 train allowable) -- || -- Stirling (2 trains allowable)-- | | -- -track (1 train allowable) -- || -- Perth (2 trains allowable)-- | | -- -track (1 train allowable) -- || -- Edinburgh (3 trains allowable)-- | 
 */
public class Runme {
	public static void main(String[] args) {
		RailLine aRailWayLine = new RailLine();
		aRailWayLine.createRailLine(); 
		TrainCreator theTrainCreator = new TrainCreator(aRailWayLine);
		Printer printer = new Printer(aRailWayLine);
		Thread theTrainCreatorThread = new Thread(theTrainCreator);
		Thread printerThread = new Thread(printer);
		theTrainCreatorThread.start();
		printerThread.start();
	}
}


