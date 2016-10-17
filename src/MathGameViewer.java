import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Shows the math game window, with menu, status bar, and MathGamePanel.
 * @author Bobby
 */
public class MathGameViewer extends JFrame
{
	
	MathGamePanel gamePanel;
	
	public MathGameViewer()
	{
		setTitle("Math Game");
		setSize(1920, 1080);
		
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File("image1.jpg"));
		}
		catch (IOException e)
		{
			System.out.print("File not found");
		}
		
		ArrayList<ProblemType> types = new ArrayList<ProblemType>();
		types.add(ProblemType.ADDITION);
		types.add(ProblemType.SUBTRACTION);
		types.add(ProblemType.MULTIPLICATION);
		types.add(ProblemType.DIVISION);
		
		gamePanel = new MathGamePanel(types, 2, 12, image);
		add(gamePanel);
	}
	
}
