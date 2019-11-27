import javax.swing.*;

public class Main
{
public static final String programName = "ENoteTaker";

public static void main( String[] args )
{
	//Learned how to make frame for GUI when program is run from :https://www.youtube.com/watch?v=5vSyylPPEko
	JFrame frame = new JFrame("MainMenu");
	frame.setContentPane(new MainMenu().rootPanel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	frame.pack();
}
}
