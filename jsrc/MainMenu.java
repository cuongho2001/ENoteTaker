import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainMenu
{
public JPanel rootPanel;
private JButton chooseSourceImageButton;
private JButton trainNeuralNetworkButton;
private JTextArea isTrained;
private JButton transcribeButton;
private JTextArea chosenImageFiles;
private JScrollPane imagePreviewsScrollWrapper;
private JTextPane outPutOfImage;
private JLabel imageLabel;

public MainMenu( )
{
	trainNeuralNetworkButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//TODO use Jython interpreter to invoke python code for training the NN's
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
					String s1 = "", sl = "";

					// File reader
					FileReader fr = new FileReader(fi);

					// Buffered reader
					BufferedReader br = new BufferedReader(fr);

					sl = br.readLine();

					while ( (s1 = br.readLine()) != null )
					{
						sl = sl + "\n" + s1;
					}

					// Set the text
					outPutOfImage.setText(sl);
				} catch ( Exception evt )
				{
					JOptionPane.showMessageDialog(rootPanel, evt.getMessage());
				}
			}
		}
	});
}

private void createUIComponents( )
{
	// TODO: place custom component creation code here
}
}
