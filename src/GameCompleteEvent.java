import java.util.ArrayList;

public class GameCompleteEvent
{
	private MathGamePanel sender;
	private ArrayList<String> problems;
	private ArrayList<Boolean> results;
	private ArrayList<Long> nanoTimes;
	private ArrayList<Integer> tryCounts;
	
	public GameCompleteEvent(MathGamePanel sender, ArrayList<String> problems,
			ArrayList<Boolean> results, ArrayList<Long> nanoTimes, ArrayList<Integer> tryCounts)
	{
		this.sender = sender;
		this.problems = problems;
		this.results = results;
		this.nanoTimes = nanoTimes;
		this.tryCounts = tryCounts;
	}
	
	public MathGamePanel getSender()
	{
		return sender;
	}
	public ArrayList<String> getProblems()
	{
		return problems;
	}
	public ArrayList<Boolean> getResults()
	{
		return results;
	}
	public ArrayList<Long> getNanoTimes()
	{
		return nanoTimes;
	}
	public ArrayList<Integer> getTryCounts()
	{
		return tryCounts;
	}
}