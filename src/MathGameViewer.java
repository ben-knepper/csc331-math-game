
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;

/**
 * Shows the math game window, with menu, status bar, and MathGamePanel.
 * 
 * @author Bobby
 */
public class MathGameViewer extends JFrame implements ActionListener, KeyListener {

	MathGamePanel gamePanel;
	JMenuBar menuBar;
	TextField mathText1, mathText2, mathText3, mathText4;
	JFrame frame;
	JPanel panel;

	public MathGameViewer() {

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("image1.jpg"));
		} catch (IOException e) {
			System.out.print("File not found");
		}

		MathGamePanel gamePanel = new MathGamePanel(ProblemType.ADDITION, 2, 1, image);

		add(gamePanel);

		// Creates a menu

		JMenuBar menuBar = new JMenuBar();

		// Adds submenus to the menu. Sets Mnemonics.
		JMenu newGameMenu = new JMenu("New");

		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setAccelerator(KeyStroke.getKeyStroke("N"));
		newGameMenu.add(newGame);

		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');

		menuBar.add(newGameMenu);
		menuBar.add(editMenu);

		setJMenuBar(menuBar);

		JMenuItem quitAction = new JMenuItem("Quit");

		
		// Action Listener / Performer for options
		
		quitAction.setAccelerator(KeyStroke.getKeyStroke("Q"));
		editMenu.add(quitAction);

		

		quitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGamePanel();
			}
		});

		// Creates a layer to display Math problems on

		setTitle("Math Game");
		setSize(500, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void newGamePanel() {
		final int numButtons = 3;
		String[] objectGridSizes = { "2x2", "3x3", "4x4" };
		String[] objectImages = { "1", "2", "3" };
		String[] objectMathType = { "ADDITION and SUBTRACTION", "MULTIPLICATION AND DIVISION" };
		JRadioButton[] gridSizeOption = new JRadioButton[numButtons];
		JRadioButton[] imagesOption = new JRadioButton[numButtons];
		
		for(int i = 0; i < numButtons; i++){
			gridSizeOption[i] = new JRadioButton(objectGridSizes[i]);
		}
		
		
		//this.panel.add(new JLabel("Grid Size: "));
		//this.panel.add(new JLabel("Image choice: "));
		//this.panel.add(new JLabel("Math Type: "));
		
		int n = JOptionPane.showOptionDialog(panel,"New Game", "New Game Settings",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, gridSizeOption,
				gridSizeOption[2]);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
