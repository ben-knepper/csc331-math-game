import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MathProblemPanel extends JPanel implements MouseListener, KeyListener
{

	private enum PanelState
	{
		COVER,
		PROBLEM,
		IMAGE
	}
	private enum ProblemState
	{
		NORMAL,
		CORRECT,
		INCORRECT,
		INCORRECT_FINAL
	}
	
	private static final long serialVersionUID = 1L;
	
	public static final int NUMBER_MAX = 12;
	public static final Color CORRECT_COLOR = new Color(32, 176, 32);
	public static final Color INCORRECT_COLOR = Color.RED;
	
	private static Random rng = new Random();
	private Timer timer = new Timer();
	
	private ArrayList<ProblemType> types;
	private int baseNum;
	private Image image;
	
	private ArrayList<MathProblemPanel> allPanels;
	
	private ArrayList<ProblemPanelListener> problemPanelListeners
			= new ArrayList<ProblemPanelListener>();
	
	private Color coverColor;
	
	private PanelState panelState;
	private ProblemState problemState;
	private String problem;
	private String answer;
	private int correctAnswer;
	private int tries;
	
	private long totalNanos;
	private long startNanos;
	
	public MathProblemPanel(ArrayList<ProblemType> types, int baseNum, Image image,
			ArrayList<MathProblemPanel> allPanels)
	{
		this.types = types;
		this.baseNum = baseNum;
		this.image = image;
		
		this.allPanels = allPanels;
		
		coverColor = Color.GRAY;
		
		addMouseListener(this);
		addKeyListener(this);
		
		panelState = PanelState.COVER;
		resetProblem();
	}
	
	public String getProblem()
	{
		return problem;
	}
	
	public void resetProblem()
	{
		ProblemType type = types.get(rng.nextInt(types.size()));
		
		int firstNum = -1;
		int secondNum = -1;
		String sign = null;
		
		int rngNum;
		
		switch (type)
		{
			case ADDITION:
				rngNum = rng.nextInt(NUMBER_MAX + 1);
				
				sign = "+";
				firstNum = rngNum;
				secondNum = baseNum;
				
				correctAnswer = firstNum + secondNum;
				break;
			case SUBTRACTION:
				rngNum = rng.nextInt(NUMBER_MAX + 1);
				
				sign = "-";
				if (rngNum > baseNum)
				{
					firstNum = rngNum;
					secondNum = baseNum;
				}
				else
				{
					firstNum = baseNum;
					secondNum = rngNum;
				}
				
				correctAnswer = firstNum - secondNum;
				break;
			case MULTIPLICATION:
				rngNum = rng.nextInt(NUMBER_MAX + 1);
				
				sign = "×";
				firstNum = rngNum;
				secondNum = baseNum;
				
				correctAnswer = firstNum * secondNum;
				break;
			case DIVISION:
				rngNum = rng.nextInt(NUMBER_MAX) + 1; // can't be 0
				
				sign = "÷";
				if (baseNum == 0)
				{
					firstNum = 0;
					secondNum = rngNum;
				}
				else
				{
					firstNum = baseNum * rngNum;
					secondNum = baseNum;
				}
				
				correctAnswer = firstNum / secondNum;
				break;
		}
		
		problem = String.format("%d %s %d = ", firstNum, sign, secondNum);
		answer = "0";
		tries = 0;
		problemState = ProblemState.NORMAL;
		
		totalNanos = 0;
		startNanos = System.nanoTime();
		
		repaint();
	}
	public void setImage(Image image)
	{
		this.image = image;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		switch (panelState)
		{
			case COVER:
				g2.setColor(coverColor);
				g2.fillRect(0, 0, getWidth(), getHeight());
				g2.setColor(Color.BLACK);
				g2.drawRect(0, 0, getWidth(), getHeight());
				break;
			case PROBLEM:
				
				g2.setFont(new Font("Arial", Font.PLAIN, 36));
				
				int problemWidth = g2.getFontMetrics().stringWidth(problem);
				int stringHeight = g2.getFontMetrics().getHeight();
				int problemX = (getWidth() - problemWidth) / 2;
				int problemY = (getHeight() / 2) - (stringHeight * 2 / 3);
				int answerY = problemY + stringHeight;
				
				g2.drawString(problem, problemX, problemY);
				
				int answerWidth = g2.getFontMetrics().stringWidth(answer);
				int answerX = (getWidth() - answerWidth) / 2;
				
				if (problemState == ProblemState.INCORRECT_FINAL)
				{
					g2.setColor(INCORRECT_COLOR);
				}
				
				g2.drawString(answer, answerX, answerY);

				if (problemState != ProblemState.NORMAL)
				{
					String lastLine = null;
					switch (problemState)
					{
						case CORRECT:
							lastLine = "Correct!";
							g2.setColor(CORRECT_COLOR);
							break;
						case INCORRECT:
							lastLine = "Incorrect";
							g2.setColor(INCORRECT_COLOR);
							break;
						case INCORRECT_FINAL:
							lastLine = "(" + correctAnswer + ")";
							g2.setColor(CORRECT_COLOR);
							break;
						default:
							// do nothing
							break;
					}
					
					int lastLineWidth = g2.getFontMetrics().stringWidth(lastLine);
					int lastLineX = (getWidth() - lastLineWidth) / 2;
					int lastLineY = answerY + stringHeight;
					
					g2.drawString(lastLine, lastLineX, lastLineY);
				}
				
//				//////
//				// TESTING
//				double elapsedSeconds = totalNanos / 1e9;
//				long elapsedMinutes = (long)(elapsedSeconds / 60);
//				elapsedSeconds -= elapsedMinutes * 60;
//				
//				String time = String.format("%02d:%05.2f", elapsedMinutes, elapsedSeconds);
//				
//				g2.setColor(Color.BLACK);
//				g2.drawString(time, 0, 20);
//				//////
				
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
		// do nothing
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
		if (panelState == PanelState.COVER)
		{
			for (MathProblemPanel panel : allPanels)
			{
				if (panel.panelState == PanelState.PROBLEM)
				{
					long currentNanos = System.nanoTime();
					long elapsedNanos = currentNanos - panel.startNanos;
					panel.totalNanos += elapsedNanos;
					
					panel.panelState = PanelState.COVER;
					panel.repaint();
				}
			}
			
			panelState = PanelState.PROBLEM;
			
			repaint();
			
			startNanos = System.nanoTime();
		}

		grabFocus();
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if (panelState == PanelState.PROBLEM)
		{
			// if the key was backspace and the answer is not 0
			if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE && !answer.equals("0"))
			{
				// remove the first digit
				answer = answer.substring(0, answer.length() - 1);
				
				if (answer.length() == 0)
					answer = "0";
			}
			else if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
			{
				// make all panels unresponsive to input
				for (MathProblemPanel panel : allPanels)
				{
					panel.removeMouseListener(panel);
					panel.removeKeyListener(panel);
				}
				
				// increment tries
				++tries;
				
				int myAnswer = Integer.parseInt(answer);
				if (myAnswer == correctAnswer)
				{
					totalNanos += System.nanoTime() - startNanos;
					
					problemState = ProblemState.CORRECT;
					
					timer.schedule(new TimerTask()
					{
						@Override
						public void run()
						{
							panelState = PanelState.IMAGE;
							repaint();
							
							// restore input
							for (MathProblemPanel panel : allPanels)
							{
								panel.addMouseListener(panel);
								panel.addKeyListener(panel);
							}
						}
					}, 2000);
					
					for (ProblemPanelListener listener : problemPanelListeners)
						listener.problemCompleted(new ProblemPanelEvent(this, true, tries, totalNanos));
				}
				else
				{
					totalNanos += System.nanoTime() - startNanos;
					
					if (tries == 3)
					{
						problemState = ProblemState.INCORRECT_FINAL;
						
						timer.schedule(new TimerTask()
						{
							@Override
							public void run()
							{
								panelState = PanelState.IMAGE;
								repaint();
								
								// restore input
								for (MathProblemPanel panel : allPanels)
								{
									panel.addMouseListener(panel);
									panel.addKeyListener(panel);
								}
							}
						}, 2000);
						
						for (ProblemPanelListener listener : problemPanelListeners)
							listener.problemCompleted(new ProblemPanelEvent(this, false, tries, totalNanos));
					}
					else
					{
						totalNanos += System.nanoTime() - startNanos;
						
						problemState = ProblemState.INCORRECT;
						
						timer.schedule(new TimerTask()
							{
								@Override
								public void run()
								{
									panelState = PanelState.PROBLEM;
									problemState = ProblemState.NORMAL;
									answer = "0";
									repaint();
									
									// restore input
									for (MathProblemPanel panel : allPanels)
									{
										panel.addMouseListener(panel);
										panel.addKeyListener(panel);
									}
									
									// start the clock again
									startNanos = System.nanoTime();
								}
							}, 1000);
					}
				}
			}
			else
			{
				char key = arg0.getKeyChar();
				
				// if the key is a digit and the answer is not already 3 digits
				if (Character.isDigit(key) && answer.length() < 3)
				{
					if (answer.equals("0"))
					{
						if (key == '0')
							return; // don't do anything
						else
							answer = key + "";
					}
					else
					{
						answer += key;
					}
				}
			}
			
			repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// do nothing
	}
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// do nothing
	}
	
	public void addProblemPanelListener(ProblemPanelListener listener)
	{
		problemPanelListeners.add(listener);
	}
	public void removeProblemPanelListener(ProblemPanelListener listener)
	{
		problemPanelListeners.remove(listener);
	}
	
}
