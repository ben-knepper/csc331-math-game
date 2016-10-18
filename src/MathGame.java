import javax.swing.JFrame;

public class MathGame
{
	
	public static void main(String[] args)
	{
		MathGameViewer viewer = new MathGameViewer();
		viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewer.setLocationRelativeTo(null);
		viewer.setVisible(true);
	}
	
}