import java.awt.Image;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MathProblemPanel extends JPanel
{
	
	public static final int NUMBER_MAX = 12;
	
	private ProblemType type;
	private int baseNum;
	private Image image;
	
	private JLabel problemLabel;
	private JLabel imageLabel;
	private JLabel coverLabel;
	
	public MathProblemPanel(ProblemType type, int baseNum, Image image)
	{
		this.type = type;
		this.baseNum = baseNum;
		this.image = image;
		
		imageLabel = new JLabel(new ImageIcon(image));
		add(imageLabel);
	}
	
	public void showProblem()
	{
		
	}
	
	public void hideProblem()
	{
		
	}
	
	public void resetProblem()
	{
		
	}
	
	public void showImage()
	{
		
	}
	
}
