/**
 * @author 0808148w
 * This interface describes the print method which will be implemented by the Rail Line class
 * A variable of this interface will be declared in the printer class. This reduces stamp coupling as the printer object will only have access to this method rather than the accepting rail line object as a variable and therefore
 * having full access to the methods.
 */
public interface PrintingInterface {
	public abstract void printTrack();
}
