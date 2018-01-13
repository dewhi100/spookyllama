package com.dewhi100;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Display implements ActionListener {

	JFrame mainFrame;
	JComboBox<String> cardComboBox;
	JButton addButton;
	JButton deleteButton;
	
	JFrame pickerFrame;
	JTextField textInput;
	JTextArea cmc;
	JTextArea desc;
	JButton butt;
	
	private Deck deck;
	
	public Display() {
		deck = new Deck();
	}
	
	private static final float GOLD = (float) ((1 + Math.sqrt(5)) / 2);
	
	public void populateMainFrame() {
		
		mainFrame = new JFrame("Deck Buddy");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//display cards in the deck.
		cardComboBox = new JComboBox<String>();
		mainFrame.add(cardComboBox);
		
		//brings up the Shitty Gatherer, allowing you to add cards
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		mainFrame.add(addButton);
		
		//remove cards from the list.
		deleteButton = new JButton("Add");
		deleteButton.addActionListener(this);
		mainFrame.add(deleteButton);
		
	}
	
	 public void createAndShowGUI() {
	        //Create and set up the window.
	        pickerFrame = new JFrame("HelloWorldCardMonger");
	        pickerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //input field. specify card name
	        textInput = new JTextField(20);
	        pickerFrame.getContentPane().add(textInput);
	        
	        //submission button
	        butt = new JButton("Submit");
	        butt.addActionListener(this);
	        pickerFrame.getContentPane().add(butt);
	        
	        //cmc field. Showing converted mana cost
	        cmc = new JTextArea("Mana here.");
	        cmc.setLineWrap(true);
	        cmc.setEditable(false);
	        pickerFrame.getContentPane().add(cmc);	        
	        
	        //output field. Showing card text
	        desc = new JTextArea("Description here.");
	        desc.setLineWrap(true);
	        desc.setEditable(false);
	        pickerFrame.getContentPane().add(desc);

	        
	        
	        // get the screen size as a java dimension
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	        // get 2/3 of the height, and 2/3 of the width
	        int height = screenSize.height * 1 / 4;
	        int width = (int) (height * GOLD);

	        // set the jframe height and width
	        pickerFrame.setPreferredSize(new Dimension(width, height));
	        
	        //Display the window.
	        pickerFrame.pack();
	        pickerFrame.setLayout(new FlowLayout());
	        pickerFrame.setVisible(true);
	    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String name = textInput.getText();
		
		MagicCardClient.getCardByName(name);
	
		System.out.println("Card cmc is: " + MagicCardClient.getCMC());
		cmc.setText(MagicCardClient.getCMC());
		System.out.println("Card text is: " + MagicCardClient.getText());
		desc.setText(MagicCardClient.getText());
		
	}

	 
	 
}

