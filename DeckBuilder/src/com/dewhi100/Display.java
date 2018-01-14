package com.dewhi100;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Display implements ActionListener {

	JFrame mainFrame;
	JTextArea deckListTextArea;
	JButton saveButton;
	
	private Deck deck;
	
	public Display() {
		deck = new Deck();
	}
	
	private static final float GOLD = (float) ((1 + Math.sqrt(5)) / 2);
	
	public void populateMainFrame() {
		
		mainFrame = new JFrame("Deck Buddy");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//display cards in the deck.
		deckListTextArea = new JTextArea(20, 20);
		mainFrame.add(deckListTextArea);
		
		//regenerate deck based on deckListTextArea.
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		mainFrame.add(saveButton);
		
        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // get 2/3 of the height, and 2/3 of the width
        int height = screenSize.height * 3 / 5;
        int width = (int) (height / GOLD);

        // set the jframe height and width
        mainFrame.setPreferredSize(new Dimension(width, height));
        
        //Display the window.
        mainFrame.pack();
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		deck = new Deck(deckListTextArea.getText());
		
		deckListTextArea.setText(deck.toString());
		
		/*
		String name = textInput.getText();
		
		MagicCardClient.getCardByName(name);
	
		System.out.println("Card cmc is: " + MagicCardClient.getCMC());
		cmc.setText(MagicCardClient.getCMC());
		System.out.println("Card text is: " + MagicCardClient.getText());
		desc.setText(MagicCardClient.getText());
		*/
	}

	 
	 
}

