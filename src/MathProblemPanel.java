import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MathProblemPanel extends JPanel implements MouseListener, KeyListener
{
	
	private enum State
	{
		COVER,
		PROBLEM,
		IMAGE
	}
	
	public static final int NUMBER_MAX = 12;
	
	private ProblemType type;
	private int baseNum;
	private Image image;

	private JLabel coverLabel;
	private JLabel problemLabel;
	private JLabel imageLabel;
	
	private ArrayList<ProblemPanelListener> problemCompleteListeners
			= new ArrayList<ProblemPanelListener>();
	
	private Color coverColor;
	
	private State state;
	private String problem;
	private String answer;
	private int correctAnswer;
	
	public MathProblemPanel(ProblemType type, int baseNum, Image image)
	{
		this.type = type;
		this.baseNum = baseNum;
		this.image = image;
		
		coverColor = Color.GRAY;
		
		addMouseListener(this);
		
		state = State.COVER;
		resetProblem();
	}
	
	public void resetProblem()
	{
		problem = "test";
	}
	public void setImage(Image image)
	{
		boolean imageIsVisible = imageLabel.isVisible();
		imageLabel = new JLabel(new ImageIcon(image));
		imageLabel.setVisible(imageIsVisible);
		//add(imageLabel);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		switch (state)
		{
			case COVER:
				g2.setColor(coverColor);
				g2.fillRect(0, 0, getWidth(), getHeight());
				g2.setColor(Color.BLACK);
				g2.drawRect(0, 0, getWidth(), getHeight());
				break;
			case PROBLEM:
				g2.setFont(new Font("Arial", Font.PLAIN, 36));
				int stringWidth = g2.getFontMetrics().stringWidth(problem);
				int stringHeight = g2.getFontMetrics().getHeight();
				int x = (getWidth() - stringWidth) / 2;
				int y = (getHeight() / 2) + (stringHeight / 3);
				g2.drawString(problem, x, y);
				g2.setColor(Color.BLACK);
				g2.drawRect(0, 0, getWidth(), getHeight());
				break;
			case IMAGE:
				Image img = image.getScaledInstance(getWidth(), getHeight(),
						Image.SCALE_AREA_AVERAGING);
				g2.drawImage(img, 0, 0, (ImageObserver) this);
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		if (state == State.COVER)
			state = State.PROBLEM;
		else if (state == State.PROBLEM)
			state = State.IMAGE;
		else
			state = State.COVER;
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		coverColor = Color.DARK_GRAY;
		repaint();
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		coverColor = Color.GRAY;
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		// do nothing
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// do nothing
	}
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// do nothing
	}
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}
