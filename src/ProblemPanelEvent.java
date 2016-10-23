/**
 * Holds information about a ProblemPanelListener event.
 * @author Ben
 */
public class ProblemPanelEvent
{
	
	private MathProblemPanel sender;
	private boolean isCorrect;
	private int triesTaken;
	private long nanosTaken;
	
	/**
	 * Initializes a ProblemPanelEvent.
	 * @param sender The source of the event.
	 * @param isCorrect Whether the problem was answered correctly.
	 * @param triesTaken The number of tries taken.
	 * @param nanosTaken The number of nanoseconds taken.
	 */
	public ProblemPanelEvent(MathProblemPanel sender, boolean isCorrect, int triesTaken, long nanosTaken)
	{
		this.sender = sender;
		this.isCorrect = isCorrect;
		this.nanosTaken = nanosTaken;
	}
	
	/**
	 * Gets the sender.
	 * @return The sender.
	 */
	public MathProblemPanel getSender()
	{
		return sender;
	}
	/**
	 * Gets whether the problem was answered correctly.
	 * @return Whether the problem was answered correctly.
	 */
	public boolean isCorrect()
	{
		return isCorrect;
	}
	/**
	 * Gets the number of tries taken.
	 * @return The number of tries taken.
	 */
	public int getTriesTaken()
	{
		return triesTaken;
	}
	/**
	 * Gets the nanoseconds taken.
	 * @return The nanoseconds taken.
	 */
	public long getNanosTaken()
	{
		return nanosTaken;
	}
}

