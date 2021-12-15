import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * StoryViewer.java
 * @author Daniel Hannon (19484286)
 */
public class StoryViewer extends JFrame {
	private String[] testText = {"String1","String2","String3"};
	protected String[] imagePaths;
	private JLabel myImageHolder;
	private JTextArea imageCaption;
	private JComboBox storiesComboBox;
	private JButton addStoryButton;
	private ButtonGroup radioButtonGroup;
	private JPanel buttonPanel;
	protected ArrayList<JRadioButton> buttonsGroup;
	private JPanel storyPanel;
	private ArrayList<Story> stories;

	/**
	 * Constructor
	 * Has A LOT of boilerplate
	 */
	public StoryViewer() {
		super("My Stories");
		this.stories = new ArrayList<>();
		Container container = this.getContentPane();
		//testText exists solely to have placeholders when there's no stories :))))
		this.storiesComboBox = new JComboBox(testText);

		storiesComboBox.addItemListener((event) -> {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				if(storiesComboBox.getSelectedIndex() < this.stories.size()) {
					//Here's where the Magic Happens
					this.imagePaths = new String[this.stories.get(storiesComboBox.getSelectedIndex()).getImages().size()];
					int i = 0;
					for(String str:this.stories.get(storiesComboBox.getSelectedIndex()).getImages()) {
						this.imagePaths[i++] = str;
					}
					setImageAndScale(this.imagePaths[0]);
					imageCaption.setText(this.stories.get(storiesComboBox.getSelectedIndex()).getCaption());
				}

				//Deal with the radio buttons as they come, create/delete as needed
				if (buttonsGroup.size() < this.imagePaths.length) {
					JRadioButton temp;
					while (buttonsGroup.size() < this.imagePaths.length) {
						temp = new JRadioButton();
						temp.addItemListener(new RadioButtonHandler());
						buttonsGroup.add(temp);
						radioButtonGroup.add(temp);
					}
					radioButtonSetup();
				} else if (buttonsGroup.size() > this.imagePaths.length) {
					while(buttonsGroup.size() > this.imagePaths.length) {
						JRadioButton temp = buttonsGroup.remove(buttonsGroup.size()-1);
						radioButtonGroup.remove(temp);
					}
					radioButtonSetup();
				}
				buttonsGroup.get(0).setSelected(true);
			}
		});

		this.addStoryButton = new JButton("Add Story");
		addStoryButton.addActionListener((event)->{
			Story temp = createStory();
			if (temp != null) {
				this.stories.add(temp);
				//This is done because there's test text initially.
				this.storiesComboBox.removeAllItems();
				for(Story story: stories) {
					storiesComboBox.addItem(story.getTitle());
				}

			}
		});

		this.storyPanel = new JPanel(new GridBagLayout());
		myImageHolder = new JLabel();
		setImageAndScale("placeholder.png");
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 1;
		constraints.insets=new Insets(5,0,0,0);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,1));
		JRadioButton index = new JRadioButton();
		index.addItemListener(new RadioButtonHandler());
		buttonPanel.add(index);
		this.buttonsGroup = new ArrayList<>();
		this.buttonsGroup.add(index);
		radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(index);
		imageCaption = new JTextArea("Story Text goes here");
		imageCaption.setRows(6);
		imageCaption.setColumns(50);
		imageCaption.setEditable(false);
		storyPanel.add(myImageHolder);
		storyPanel.add(buttonPanel,constraints);
		constraints.gridy =2;
		storyPanel.add(imageCaption,constraints);

		//storyPanel.add(myImageHolder);
		storiesComboBox.setSize(800,50);
		addStoryButton.setSize(800,50);
		container.add(storyPanel,BorderLayout.CENTER);
		container.add(storiesComboBox,BorderLayout.NORTH);
		container.add(addStoryButton,BorderLayout.SOUTH);
		setSize(800,900);

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Image Scaler
	 * @param imagePath a string which stores the path of the image, if no image is found the image is simply not updated :)
	 */
	private void setImageAndScale(String imagePath) {
		//Made an image scaler that maintains Aspect Ratio by getting the larger side and comparing it
		// against the maximum window size (which I arbitrarily set to 650px)
		float scaleFactor = 1;
		try {
			BufferedImage img = ImageIO.read(new File(imagePath));
			if(img.getWidth() > img.getHeight()) {
				scaleFactor = 650f/((float)img.getWidth() );
			} else {
				scaleFactor = 650f/((float)img.getHeight());
			}
			System.out.println(scaleFactor);
			myImageHolder.setIcon(new ImageIcon(img.getScaledInstance((int)(img.getWidth()*scaleFactor),(int)(img.getHeight()*scaleFactor),BufferedImage.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This was written as it has to be invoked in two situations
	 * 1. The Story has more than the previous
	 * 2. It has less
	 */
	private void radioButtonSetup() {
		buttonPanel.removeAll();
		buttonPanel.setLayout(new GridLayout(1,buttonsGroup.size()));
		int i=0;
		for(JRadioButton rb : buttonsGroup) {
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = i++;
			constraints.insets = new Insets(0,5,0,0);
			buttonPanel.add(rb);
		}
	}

	/**
	 * createStory - This method runs you through an entire wizard to setup a story
	 * @return A story for the list
	 */
	public Story createStory() {
		/*
		* I Figured it'd be nice if I made a UI to add stories as it'd make stuff more consistent
		* Than one where you use a command line, also writing paths to files is tedious and annoying
		* And I do not want to do that while testing this
		*/
		int dialog = JOptionPane.showConfirmDialog(this,"Would You Like to create a story?","Create Story?",JOptionPane.INFORMATION_MESSAGE);
		if(dialog == JOptionPane.YES_OPTION) {
			while(true) {
				//Forever loop for images
				//the one downside is that JFileChooser does not have image previews by default,
				//I feel the pros outweigh the cons in this specific case.
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images, Hold Ctrl and click for multiple images :)", "jpg", "png", "jpeg", "gif");
				chooser.setFileFilter(filter);
				chooser.setMultiSelectionEnabled(true);

				int returnVal = chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File[] files = chooser.getSelectedFiles();
					String output = "You Have Selected:\n";
					for (File file : files) {
						output += file.getName() + "\n";
					}
					output += "Is this Correct?";
					dialog = JOptionPane.showConfirmDialog(this, output, "Confirm", JOptionPane.INFORMATION_MESSAGE);
					if(dialog == JOptionPane.YES_OPTION) {
						JTextArea prompt = new JTextArea(6,50);
						JOptionPane pane = new JOptionPane(new Object[] {
							"What would you like to caption your story?",
							new JScrollPane(prompt)
						}, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
						pane.setWantsInput(false);
						JDialog dialogBox = pane.createDialog(this,"Caption");
						dialogBox.pack();
						dialogBox.setVisible(true);
						Integer val = (Integer) pane.getValue();
						if(val != null && val.intValue() == JOptionPane.YES_OPTION) {
							String caption = prompt.getText();
							while(true) {
								String title = JOptionPane.showInputDialog(this, "What title would you like?");
								if(title != null && title.length() != 0) {
									ArrayList<String> imagePaths = new ArrayList<>();
									for(File file:files) {
										imagePaths.add(file.getAbsolutePath());
									}
									if(imagePaths.size() == 0) {
										return null;
									}
									return new Story(imagePaths,caption,title);
								}
							}
						}
						break;
					}
				} else if(returnVal == JFileChooser.CANCEL_OPTION) {
					return null;
				}
			}
		}
		return null;
	}

	private class RadioButtonHandler implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if(imagePaths == null) return;
			int index = buttonsGroup.indexOf(event.getItem());
			System.out.println(index);
			setImageAndScale(imagePaths[buttonsGroup.indexOf(event.getItem())]);
		}
	}

	public static void main(String[] args) {
		StoryViewer viewer = new StoryViewer();
		viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
