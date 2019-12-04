/*import jep.Interpreter;
import jep.JepConfig;
import jep.JepException;
import jep.SubInterpreter;
import jep.python.PyCallable;
import jep.python.PyObject;
*/

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class MainMenu
{

public static final String appId = "17900373";
public static final String apiKey = "B0Hv0D5bVQKHk2yCPOHoIdhY";
public static final String secretKey = "dCox7HQhDN2253NysmfQH5v8kZqeh3qV";
//Sets up API connection
AipOcr client = new AipOcr(appId, apiKey, secretKey);

public JPanel rootPanel;

private JButton chooseSourceImageButton;
//private JButton trainNeuralNetworkButton;
//private JTextArea isTrained;
private JButton transcribeButton;
private JTextArea chosenImageFiles;
private JTextPane outPutOfImage;
private JLabel imageLabel;
private JButton saveButton;
private JTextPane textFileName;
private JTextPane error;

private String chosenImageFilePath = null;
public MainMenu( )
{
	/*isTrained.setText("Neural Network is not yet trained");

	JepConfig jConfig = new JepConfig().addIncludePaths("./CNNRNN")
									   .addIncludePaths("./CNNTXT");


	trainNeuralNetworkButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			isTrained.setText("Neural Network is now training");
			rootPanel.update(rootPanel.getGraphics());

			/*(try ( Interpreter interp = new SubInterpreter(jConfig) )
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
	});*/

	chooseSourceImageButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//learned how to use JFileChooser from https://www.geeksforgeeks.org/java-swing-jfilechooser/
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
					ImageIcon icon = new ImageIcon(image.getScaledInstance(150, 300, Image.SCALE_SMOOTH));
					//sets imageLabel to hold the image
					imageLabel.setIcon(icon);
					//gets the absolute path of the selected image and sets the text into the chosenImageFiles TextArea
					chosenImageFilePath = selectedImage.getSelectedFile()
													   .getAbsolutePath();
					chosenImageFiles.setText(chosenImageFilePath);
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
			String transcribedText = imgOcr(chosenImageFilePath);
			outPutOfImage.setText(transcribedText);
			/*try ( Interpreter interp = new SubInterpreter(jConfig) )
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


				interp.exec("import nbformat\n" +
							"from nbconvert.preprocessors import ExecutePreprocessor");

				interp.exec("ep = ExecutePreprocessor(timeout=600, kernel_name='python3')");

				final String pyCnnPredictNotebookPath = "./CNNTXT/CNN/Model Predict.ipynb";
				interp.set("notebookPath", pyCnnPredictNotebookPath);
				interp.exec("with open(notebookPath) as f:\n" +
							"    nb = nbformat.read(f, as_version=4)");
				interp.exec("ep.preprocess(nb, {'metadata': {'path': 'notebooks/'}})");


			} catch ( JepException ex )
			{
				System.err.println("Python interpreter failed while running the neural network on new input data:");
				ex.printStackTrace();
			}*/
		}
	});

	saveButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			saveButton.setToolTipText(
				"If you would like to save this file to a specific place then enter absolute path with name at the end of last \\");

			//this is the raw file name without extension that user will enter
			//User can choose to store file anyhwhere by entering the absolute path of the location and then making name of file last
			//portion of the path
			//ex: C:\User\Documents\textFile1	--> textFile1 is name of file
			String rawFileName = textFileName.getText();
			//this has the '.txt' extension added so that the computer will recognize it when user wants to open it.
			String fileName = rawFileName + ".txt";
			//This is the text that the user has edited in the GUI
			String editedText = outPutOfImage.getText();

			try
			{
				//is the user has not entered a file name to save then show error
				if ( rawFileName.equals("") )
				{
					JOptionPane.showMessageDialog(error, "Please enter a name for the file you are trying to save.",
						"File Name Error", JOptionPane.ERROR_MESSAGE);
				} else
				{
					//Creates a new file object (with name user entered) to store the file
					File newTextFile = new File(fileName);
					//learned some material from stackoverflow
					//checks to see if it does not already exist
					if ( !newTextFile.exists() )
					{

						//printWriter allows for the writing of objects to text rather than bytes
						PrintWriter savingText = new PrintWriter(newTextFile);
						//writes the text to the file
						savingText.println(editedText);
						//closes the printwriter
						savingText.close();
					} else
					{
						JOptionPane.showMessageDialog(error, "File name already exists.", "Duplicate File Names",
							JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch ( FileNotFoundException ex )
			{
				ex.printStackTrace();
			}
		}
	});
}

public String imgOcr( String imgpath )
{
	HashMap<String, String> options = new HashMap<String, String>();
	options.put("recognize_granularity", "big");

	JSONObject res = client.basicGeneral(imgpath, options);

	JSONArray wordsResult = (JSONArray) res.get("words_result");
	String ocrStr = "\n";
	for ( Object obj : wordsResult )
	{
		JSONObject jo = (JSONObject) obj;
		ocrStr += jo.getString("words") + "\n";
	}

	return ocrStr;
	// return res.toString(2);

}

private void createUIComponents( )
{
	// TODO: place custom component creation code here
}

public JPanel getRootPanel( ) { return this.rootPanel; }
}
