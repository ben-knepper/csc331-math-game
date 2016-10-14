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
public class MathGamePanel extends JPanel
{
	
	private ProblemType type;
	private int size;
	private int baseNum;
	private BufferedImage image;
	
	private ArrayList<MathProblemPanel> problemPanels = new ArrayList<MathProblemPanel>();
	
	private ArrayList<GameCompleteListener> gameCompleteListeners
			= new ArrayList<GameCompleteListener>();
	
	/**
	 * Initializes a MathGamePanel.
	 * @param type The type(s) of problems to use.
	 * @param size The number of rows and columns to have (always the same).
	 * @param baseNum The number to use in all the calculations.
	 * @param image The image to use.
	 */
	public MathGamePanel(ProblemType type, int size, int baseNum, BufferedImage image)
	{
		this.type = type;
		this.size = size;
		this.baseNum = baseNum;
		this.image = image;
		
		startNewGame(1);
	}
	
	/**
	 * Starts a new game using all previous parameters.
	 */
	public void startNewGame()
	{
		problemPanels = new ArrayList<MathProblemPanel>();
		
		setLayout(new GridLayout(size, size, 0, 0));
		
		BufferedImage[] subImages = splitImage();
		for (int i = 0; i < size * size; ++i)
		{
			MathProblemPanel problemPanel = new MathProblemPanel(type, baseNum, subImages[i]);
			problemPanels.add(problemPanel);
			this.add(problemPanel);
		}
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
	 * @param type The type(s) of problems to use.
	 */
	public void startNewGame(int baseNum, int size, BufferedImage image, ProblemType type)
	{
		this.baseNum = baseNum;
		this.size = size;
		this.image = image;
		this.type = type;
		
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
	private void gameCompleted()
	{
		for (GameCompleteListener listener : gameCompleteListeners)
			listener.gameCompleted();
	}
	
}
