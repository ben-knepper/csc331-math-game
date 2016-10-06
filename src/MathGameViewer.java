import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
		setSize(500, 500);
		
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File("image1.jpg"));
		}
		catch (IOException e)
		{
			System.out.print("File not found");
		}
		
		gamePanel = new MathGamePanel(ProblemType.ADDITION, 5, 1, image);
		add(gamePanel);
	}
	
}
