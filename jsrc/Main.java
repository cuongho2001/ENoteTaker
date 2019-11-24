import javax.swing.*;

public class Main
{
public static final String programName = "ENoteTaker";

public static void main( String[] args )
{
	JFrame mainWindow = new JFrame(programName);
	MainMenu mainMenuObj = new MainMenu();
	mainWindow.setContentPane(mainMenuObj.getRootPanel());
	mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainWindow.pack();
	mainWindow.setVisible(true);
}
}
