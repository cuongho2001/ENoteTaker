import javax.imageio.ImageIO;
import jep.Interpreter;
import jep.JepConfig;
import jep.JepException;
import jep.SubInterpreter;
import jep.python.PyCallable;
import jep.python.PyObject;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu
{
public JPanel rootPanel;
private final String programName = "EnoteTaker";
private JPanel rootPanel;
private JButton chooseSourceImageButton;
private JButton trainNeuralNetworkButton;
private JTextArea isTrained;
private JButton transcribeButton;
private JTextArea chosenImageFiles;
private JScrollPane imagePreviewsScrollWrapper;
private JTextPane outPutOfImage;
private JLabel imageLabel;
private JButton tesseractButton;
private JButton saveButton;

public MainMenu( )
{
	isTrained.setText("Neural Network is not yet trained");

	JepConfig jConfig = new JepConfig().addIncludePaths("./CNNRNN")
									   .addIncludePaths("./CNNTXT");


	trainNeuralNetworkButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			isTrained.setText("Neural Network is now training");
			rootPanel.update(rootPanel.getGraphics());

			try ( Interpreter interp = new SubInterpreter(jConfig) )
			{
				PyObject pySysObj = interp.getValue("sys", PyObject.class);

				List<String> pyArgv = new ArrayList<>();
				pyArgv.add(programName);
				pySysObj.setAttr("argv", pyArgv);

				interp.exec("import train");
				interp.exec("train.main()");

				isTrained.setText("Neural Network is trained");
			} catch ( JepException ex )
			{
				System.err.println("Python interpreter failed while training the neural network:");
				ex.printStackTrace();
				isTrained.setText("Neural Network training failed: neural network is not yet trained");
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

	chooseSourceImageButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//this is a fileChooser used to store the selected file
			JFileChooser selectedImage = new JFileChooser(FileSystemView.getFileSystemView());

			//shows open dialog box and stores the option selected by the user
			int r = selectedImage.showOpenDialog(null);

			//if user chose to open a file
			if ( r == JFileChooser.APPROVE_OPTION )
			{
				//holds the file selected by user
				File imageFile = new File(selectedImage.getSelectedFile()
									.getAbsolutePath());
				BufferedImage image = null;

				try
				{
					//declares the buffered image to be the image that is selected by the user
					image = ImageIO.read(imageFile);
					//"converts" the image into an icon and scales it to match the size of the jlabel
					ImageIcon icon = new ImageIcon(image.getScaledInstance(150, 300, image.SCALE_FAST));
					//sets imageLabel to hold the image
					imageLabel.setIcon(icon);
					//gets the absolute path of the selected image and sets the text into the chosenImageFiles TextArea
					chosenImageFiles.setText(selectedImage.getSelectedFile()
														  .getAbsolutePath());
				} catch ( IOException ex )
				{
					ex.printStackTrace();
				}
			}
		}
	});
	transcribeButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//TODO use Jython Interpreter to invoke python code to use the current NN's on the loaded image

			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());

			//shows open dialog box
			int r = j.showOpenDialog(null);

			// If the user selects a file
			if ( r == JFileChooser.APPROVE_OPTION )
			{
				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile()
									.getAbsolutePath());

				try
				{
					// String
					String string1 = "", string2 = "";

					// File reader
					FileReader fr = new FileReader(fi);

					// Buffered reader
					BufferedReader br = new BufferedReader(fr);

					string2 = br.readLine();

					while ( (string1 = br.readLine()) != null )
					{
						string2 = string2 + "\n" + string1;
					}

					// Set the text
					outPutOfImage.setText(string2);
				} catch ( Exception evt )
				{
					JOptionPane.showMessageDialog(rootPanel, evt.getMessage());
				}
			}
		}
	});

	saveButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//gets the text that has been transcribed
			String editedText = outPutOfImage.getText();
			//needs logic to be saved to a file


			try ( Interpreter interp = new SubInterpreter(jConfig) )
			{
				PyObject pySysObj = interp.getValue("sys", PyObject.class);

				List<String> pyArgv = new ArrayList<>();
				pyArgv.add(programName);
				pySysObj.setAttr("argv", pyArgv);

				String pyPosDataFilePath = "./CNNRNN/data/rt-polaritydata/rt-polarity.pos";
				String pyNegDataFilePath = "./CNNRNN/data/rt-polaritydata/rt-polarity.neg";

				interp.exec("import data_helpers");
				interp.exec("from data_helpers import set_tf_flag");
				PyCallable pyDefineStrFlag = interp.getValue("set_tf_flag", PyCallable.class);
				pyDefineStrFlag.call("positive_input_data_file", pyPosDataFilePath,
					"path of file with positive input data");
				pyDefineStrFlag.call("negative_input_data_file", pyNegDataFilePath,
					"path of file with negative input data");

				interp.exec("import eval");

				interp.exec("eval");

				/*
				interp.exec("import nbformat\n" +
							"from nbconvert.preprocessors import ExecutePreprocessor");

				interp.exec("ep = ExecutePreprocessor(timeout=600, kernel_name='python3')");

				final String pyCnnPredictNotebookPath = "./CNNTXT/CNN/Model Predict.ipynb";
				interp.set("notebookPath", pyCnnPredictNotebookPath);
				interp.exec("with open(notebookPath) as f:\n" +
							"    nb = nbformat.read(f, as_version=4)");
				interp.exec("ep.preprocess(nb, {'metadata': {'path': 'notebooks/'}})");
				 */

			} catch ( JepException ex )
			{
				System.err.println("Python interpreter failed while running the neural network on new input data:");
				ex.printStackTrace();
			}
		}
	});
}
}
