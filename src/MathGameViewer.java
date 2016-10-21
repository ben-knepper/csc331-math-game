import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;


/**
 * Shows the math game window, with menu, status bar, and MathGamePanel.
 * 
 * @author Bobby
 */
public class MathGameViewer extends JFrame implements ActionListener, KeyListener, GameCompleteListener {

	private static final long serialVersionUID = 1L;
	
	MathGamePanel gamePanel;
	JMenuBar menuBar;
	TextField mathText1, mathText2, mathText3, mathText4;
	JFrame frame;
	JPanel panel;
	String gridChoice;
	String imageChoice;
	String typeChoice;
	String baseChoice;
	

	public MathGameViewer() {

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("image1.jpg"));
		} catch (IOException e) {
			System.out.print("File not found");
		}

		ArrayList<ProblemType> types = new ArrayList<ProblemType>();
		types.add(ProblemType.ADDITION);
		types.add(ProblemType.SUBTRACTION);
		types.add(ProblemType.MULTIPLICATION);
		types.add(ProblemType.DIVISION);
		

		gamePanel = new MathGamePanel(types, 5, 12, image);
		add(gamePanel);

		gamePanel.addGameCompleteListener(this);

		// Creates a menu

		JMenuBar menuBar = new JMenuBar();

		// Adds sub menus to the menu. Sets Mnemonics.
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

	/**
	 * New Game Panel that includes 3 button groups of JRadio Buttons for user
	 * to give input.
	 */
	public void newGamePanel() {
		final int numButtons = 3;
		panel = new JPanel();
		
		ArrayList<ProblemType> newTypeAdd = new ArrayList<ProblemType>();
		newTypeAdd.add(ProblemType.ADDITION);
		newTypeAdd.add(ProblemType.SUBTRACTION);
		
		ArrayList<ProblemType> newTypeMult = new ArrayList<ProblemType>();
		newTypeMult.add(ProblemType.MULTIPLICATION);
		newTypeMult.add(ProblemType.DIVISION);
		
		ButtonGroup gridButton = new ButtonGroup();
		ButtonGroup imageButton = new ButtonGroup();
		ButtonGroup typeButton = new ButtonGroup();
		ButtonGroup baseButton = new ButtonGroup();
		
		String[] objectGridSizes = { "2", "3", "4" };
		String[] objectImages = { "1", "2", "3" };
		String[] objectMathType = { "ADDITION and SUBTRACTION", "MULTIPLICATION AND DIVISION" };
		String[] objectBase = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12"};
		

		JRadioButton[] gridSizeOption = new JRadioButton[numButtons];
		JRadioButton[] imageOption = new JRadioButton[numButtons];
		JRadioButton[] typeOption = new JRadioButton[2];
		JRadioButton[] baseOption = new JRadioButton[11];

		Object[] optionObjects = new Object[8];

		// Radio buttons for Grid Option / button group
		for (int i = 0; i < numButtons; i++) {
			gridSizeOption[i] = new JRadioButton(objectGridSizes[i]);
			gridButton.add(gridSizeOption[i]);
		}
		// Radio Buttons for Image option / button group
		for (int i = 0; i < numButtons; i++) {
			imageOption[i] = new JRadioButton(objectImages[i]);
			imageButton.add(imageOption[i]);
		}
		// Radio Buttons for Math Type / button group
		for (int i = 0; i < 2; i++) {
			typeOption[i] = new JRadioButton(objectMathType[i]);
			typeButton.add(typeOption[i]);
		}
		// Radio Buttons for Base number / button group
		for(int i = 0; i < 11; i++){
			baseOption[i] = new JRadioButton(objectBase[i]);
			baseButton.add(baseOption[i]);
		}
		
		// List of option objects to make a vertical option pane
		JLabel gridMessage = new JLabel("Grid Size: ");
		JLabel imageMessage = new JLabel("Image Selection: ");
		JLabel typeMessage = new JLabel("Math Type: ");
		JLabel baseMessage = new JLabel("Base number: ");

		optionObjects[0] = gridMessage;
		optionObjects[1] = gridSizeOption;
		optionObjects[2] = imageMessage;
		optionObjects[3] = imageOption;
		optionObjects[4] = typeMessage;
		optionObjects[5] = typeOption;
		optionObjects[6] = baseMessage;
		optionObjects[7] = baseOption;

		JOptionPane.showMessageDialog(panel, optionObjects);
		for(int i = 0; i < gridSizeOption.length; i++){
			if(gridSizeOption[i].isSelected()){
				gridChoice = gridSizeOption[i].getText();
			}
		}
		for(int i = 0; i < imageOption.length; i++){
			if(imageOption[i].isSelected()){
				imageChoice = imageOption[i].getText();
			}
		}
		for(int i = 0; i < typeOption.length; i++){
			if(typeOption[i].isSelected()){
				typeChoice = typeOption[i].getText();
			}
		}
		
		for(int i = 0; i < baseOption.length; i++){
			if(baseOption[i].isSelected()){
				baseChoice = baseOption[i].getText();
			}
		}
		int gridSelect = Integer.parseInt(gridChoice);
		imageChoice = "image"+imageChoice+".jpg";
		int baseSelect = Integer.parseInt(baseChoice);
		
		BufferedImage imageSelect = null;
		try {
			imageSelect = ImageIO.read(new File(imageChoice));
		} catch (IOException e) {
			System.out.print("File not found");
		}
		
		if(typeChoice.equals(objectMathType[0])){
			gamePanel.startNewGame(baseSelect, gridSelect, imageSelect, newTypeAdd);
		}
		if(typeChoice.equals(objectMathType[1])){
			gamePanel.startNewGame(baseSelect, gridSelect, imageSelect, newTypeMult);
		}
		


	}

	public class GroupButtonUtils {

		public String getSelectedButtonText(ButtonGroup buttonGroup) {
			for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
				AbstractButton button = buttons.nextElement();

				if (button.isSelected()) {
					return button.getText();
				}
			}

			return null;
		}
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

	@Override
	public void gameCompleted(GameCompleteEvent e) {
		// All the needed information for the game is in "e".
		// e.getProblems() gives each problem as a String (probably won't be
		// used)
		// e.getResults() gives the boolean result for each problem (true =
		// correct, false = incorrect)
		// (which can be tallied as needed)
		// e.getNanoTimes() gives a list of times that you can average or sum as
		// needed
		// (and the getTimeString method will convert it to a readable form)
		// e.getTryCounts() gives the number of attempts for each problem
	}

	public String getTimeString(long nanos) {
		double elapsedSeconds = nanos / 1e9;
		long elapsedMinutes = (long) (elapsedSeconds / 60);
		elapsedSeconds -= elapsedMinutes * 60;

		return String.format("%02d:%07.4f", elapsedMinutes, elapsedSeconds);
	}

}