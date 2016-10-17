public class ProblemPanelEvent
{
	
	private MathProblemPanel sender;
	private boolean isCorrect;
	private int triesTaken;
	private long nanosTaken;

	public ProblemPanelEvent(MathProblemPanel sender, boolean isCorrect, int triesTaken, long nanosTaken)
	{
		this.sender = sender;
		this.isCorrect = isCorrect;
		this.nanosTaken = nanosTaken;
	}
	
	public MathProblemPanel getSender()
	{
		return sender;
	}
	public boolean isCorrect()
	{
		return isCorrect;
	}
	public int getTriesTaken()
	{
		return triesTaken;
	}
	public long getNanosTaken()
	{
		return nanosTaken;
	}
	
}
