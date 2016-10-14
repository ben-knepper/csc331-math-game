/**
 * Interface to listen for when a problem in a MathProblemPanel is completed.
 * @author Ben
 */
public interface ProblemPanelListener
{
	/**
	 * Called when a problem is completed.
	 */
	public void problemCompleted();
	/**
	 * Called when a problem is focused.
	 */
	public void problemFocused();
}
