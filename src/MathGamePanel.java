import java.awt.image.BufferedImage;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * @author Ben
 */
enum ProblemType
{
	ADDITION,
	SUBTRACTION,
	MULTIPLICATION,
	DIVISION
}

/**
 * The main panel for the math game, which has a grid of MathProblemPanels.
 * @author Ben
 */
public class MathGamePanel extends JPanel implements ProblemPanelListener
{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ProblemType> types;
	private int size;
	private int baseNum;
	private BufferedImage image;
	
	private int completeCount;
	private ArrayList<String> problems;
	private ArrayList<Boolean> results;
	private ArrayList<Long> nanoTimes;
	private ArrayList<Integer> tryCounts;
	
	private ArrayList<MathProblemPanel> problemPanels = new ArrayList<MathProblemPanel>();
	
	private ArrayList<GameCompleteListener> gameCompleteListeners
			= new ArrayList<GameCompleteListener>();
	
	/**
	 * Initializes a MathGamePanel.
	 * @param types The type(s) of problems to use.
	 * @param size The number of rows and columns to have (always the same).
	 * @param baseNum The number to use in all the calculations.
	 * @param image The image to use.
	 */
	public MathGamePanel(ArrayList<ProblemType> types, int size, int baseNum, BufferedImage image)
	{
		this.types = types;
		this.size = size;
		this.baseNum = baseNum;
		this.image = image;
		
		startNewGame();
	}
	/**
	 * Initializes a MathGamePanel with a single problem type.
	 * @param type The type of problems to use.
	 * @param size The number of rows and columns to have (always the same).
	 * @param baseNum The number to use in all the calculations.
	 * @param image The image to use.
	 */
	public MathGamePanel(ProblemType type, int size, int baseNum, BufferedImage image)
	{
		this.types = new ArrayList<ProblemType>();
		types.add(type);
		
		this.size = size;
		this.baseNum = baseNum;
		this.image = image;
		
		startNewGame();
	}
	
	/**
	 * Gets the base number for the panel problems.
	 * @return The base number.
	 */
	public int getBaseNum()
	{
		return baseNum;
	}
	/**
	 * Gets the size (number of rows and columns) of the panel.
	 * @return The size of the panel.
	 */
	public int getGridSize()
	{
		return size;
	}
	/**
	 * Gets the problem type(s) of the panel.
	 * @return The problem type(s).
	 */
	public ArrayList<ProblemType> getTypes()
	{
		return types;
	}
	/**
	 * Gets the image of the panel.
	 * @return The image;
	 */
	public BufferedImage getImage()
	{
		return image;
	}
	public int getPanelCount()
	{
		return size * size;
	}
	
	/**
	 * Starts a new game using all previous parameters.
	 */
	public void startNewGame()
	{
		for (MathProblemPanel panel : problemPanels)
		{
			remove(panel);
		}
		
		problemPanels = new ArrayList<MathProblemPanel>();
		
		setLayout(new GridLayout(size, size, 0, 0));
		
		BufferedImage[] subImages = splitImage();
		for (int i = 0; i < size * size; ++i)
		{
			MathProblemPanel problemPanel = new MathProblemPanel(
					types, baseNum, subImages[i], problemPanels);
			problemPanel.addProblemPanelListener(this);
			
			problemPanels.add(problemPanel);
			this.add(problemPanel);
		}
		
		completeCount = 0;
		problems = new ArrayList<String>();
		results = new ArrayList<Boolean>();
		nanoTimes = new ArrayList<Long>();
		tryCounts = new ArrayList<Integer>();
		
		revalidate();
	}
	/**
	 * Starts a new game using the new base number.
	 * @param baseNum The number to use in all the calculations.
	 */
	public void startNewGame(int baseNum)
	{
		this.baseNum = baseNum;
		
		startNewGame();
	}
	/**
	 * Starts a new game using the new base number and size.
	 * @param baseNum The number to use in all the calculations.
	 * @param size The number of rows and columns to have (always the same).
	 */
	public void startNewGame(int baseNum, int size)
	{
		this.baseNum = baseNum;
		this.size = size;
		
		startNewGame();
	}
	/**
	 * Starts a new game using the new base number, size, and image.
	 * @param baseNum The number to use in all the calculations.
	 * @param size The number of rows and columns to have (always the same).
	 * @param image The image to use.
	 */
	public void startNewGame(int baseNum, int size, BufferedImage image)
	{
		this.baseNum = baseNum;
		this.size = size;
		this.image = image;
		
		startNewGame();
	}
	/**
	 * Starts a new game using all new parameters.
	 * @param baseNum The number to use in all the calculations.
	 * @param size The number of rows and columns to have (always the same).
	 * @param image The image to use.
	 * @param types The type(s) of problems to use.
	 */
	public void startNewGame(int baseNum, int size, BufferedImage image, ArrayList<ProblemType> types)
	{
		this.baseNum = baseNum;
		this.size = size;
		this.image = image;
		this.types = types;
		
		startNewGame();
	}
	/**
	 * Starts a new game using all new parameters and a single problem type.
	 * @param baseNum The number to use in all the calculations.
	 * @param size The number of rows and columns to have (always the same).
	 * @param image The image to use.
	 * @param type The type of problems to use.
	 */
	public void startNewGame(int baseNum, int size, BufferedImage image, ProblemType type)
	{
		this.baseNum = baseNum;
		this.size = size;
		this.image = image;
		
		this.types = new ArrayList<ProblemType>();
		this.types.add(type);
		
		startNewGame();
	}
	
	private BufferedImage[] splitImage()
	{
		int width = image.getWidth();
		int height = image.getHeight();
		int startX = 0;
		int startY = 0;
//		if (image.getWidth() < image.getHeight())
//		{
//			width = image.getWidth();
//			height = image.getWidth();
//			startX = 0;
//			startY = (image.getHeight() - image.getWidth()) / 2;
//		}
//		else
//		{
//			width = image.getHeight();
//			height = image.getHeight();
//			startX = (image.getWidth() - image.getHeight()) / 2;
//			startY = 0;
//		}
		int subImageWidth = width / size;
		int subImageHeight = height / size;
		
		BufferedImage[] subImages = new BufferedImage[size * size];
		
		for (int i = 0; i < subImages.length; ++i)
			subImages[i] = new BufferedImage(subImageWidth, subImageHeight, BufferedImage.TYPE_3BYTE_BGR);
		
		for (int x = 0; x < width / size; ++x)
			for (int y = 0; y < height / size; ++y)
				for (int i = 0; i < subImages.length; ++i)
				{
					subImages[i].setRGB(x, y, image.getRGB(
							startX + (subImageWidth * (i % size)) + x,
							startY + (subImageHeight * (i / size)) + y));
				}
		
		return subImages;
	}
	
	public void addGameCompleteListener(GameCompleteListener listener)
	{
		gameCompleteListeners.add(listener);
	}
	public void removeGameCompleteListener(GameCompleteListener listener)
	{
		gameCompleteListeners.remove(listener);
	}
	private void gameCompleted()
	{
		for (GameCompleteListener listener : gameCompleteListeners)
			listener.gameCompleted(new GameCompleteEvent(this,
					problems, results, nanoTimes, tryCounts));
	}

	@Override
	public void problemCompleted(ProblemPanelEvent e)
	{
		++completeCount;
		problems.add(e.getSender().getProblem());
		results.add(e.isCorrect());
		nanoTimes.add(e.getNanosTaken());
		tryCounts.add(e.getTriesTaken());
		
		if (completeCount == getPanelCount())
		{
			gameCompleted();
		}
	}
	
}