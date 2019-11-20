import jep.Interpreter;
import jep.JepConfig;
import jep.JepException;
import jep.SubInterpreter;
import jep.python.PyCallable;
import jep.python.PyObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenu
{
private final String programName = "EnoteTaker";
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
	transcribeButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//TODO use Jython Interpreter to invoke python code to use the current NN's on the loaded image

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

public JPanel getRootPanel( ) { return this.rootPanel; }
}
