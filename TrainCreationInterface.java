/**
 * @author 0808148w
 * This interface describes the add train to first station method which will be implemented by the Rail Line class
 * An instance of this will be passed to the thread creator constructor, to reduce stamp coupling as the traincreator object will only have access to this method rather than the rail line object
 */
public interface TrainCreationInterface {
	public void addTrainToFirstStation(Train newTrain);
}
