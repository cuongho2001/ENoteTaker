import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu
{
public JPanel rootPanel;
private JButton chooseSourceImageButton;
private JButton trainNeuralNetworkButton;
private JTextArea isTrained;
private JButton transcribeButton;
private JTextArea chosenImageFiles;
private JScrollPane imagePreviewsScrollWrapper;
private JTextPane textPane1;

public MainMenu( )
{
	trainNeuralNetworkButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//TODO use Jython interpreter to invoke python code for training the NN's
		}
	});

	chooseSourceImageButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//TODO create a JFileChooser to select the image file
			// also set the chosenImageFiles text area to that file name
			//TODO it might also be relatively easy to have an image preview pane which could show any number of images inside itself

			//TESTING A COMMIT TO NEWLY CREATED BRANCH


		}
	});
	transcribeButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//TODO use Jython Interpreter to invoke python code to use the current NN's on the loaded image
		}
	});
}
}
