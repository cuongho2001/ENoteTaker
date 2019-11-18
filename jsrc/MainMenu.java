import jep.Interpreter;
import jep.JepConfig;
import jep.JepException;
import jep.SharedInterpreter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu
{
private JPanel rootPanel;
private JButton chooseSourceImageButton;
private JButton trainNeuralNetworkButton;
private JTextArea isTrained;
private JButton transcribeButton;
private JScrollPane transcriptionOutput;
private JTextArea chosenImageFiles;
private JList imagePreviews;
private JScrollPane imagePreviewsScrollWrapper;

public MainMenu( )
{
	trainNeuralNetworkButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//TODO use Jython interpreter to invoke python code for training the NN's
			JepConfig jConfig = new JepConfig().addIncludePaths("./CNNRNN");
			try
			{
				SharedInterpreter.setConfig(jConfig);
			} catch ( JepException ex )
			{
				System.err.println("Failed to configure the Python interpreter");
				ex.printStackTrace();
			}
			try ( Interpreter interp = new SharedInterpreter() )
			{
				interp.eval("import train");

				interp.eval("train.main()");
			} catch ( JepException ex )
			{
				System.err.println("Python interpreter failed while training the neural network:");
				ex.printStackTrace();
			}
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

public JPanel getRootPanel( ) { return this.rootPanel; }
}
