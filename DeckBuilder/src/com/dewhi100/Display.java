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
	JTextArea analysisArea;
	
	private Deck deck;
	
	public Display() {
		deck = new Deck();
	}
		
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
				
		//display analysis
		analysisArea = new JTextArea(20, 30);
        analysisArea.setEditable(false);
		mainFrame.add(analysisArea);
		
        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // get 2/3 of the height, and 2/3 of the width
        int height = screenSize.height * 3 / 5;
        int width = height * 7 / 3;
       
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
		
		String feedbackString = "";
		
		ManaTally sourceTotals = DeckAnalyst.calculateManaSourceCards(deck);
		if(sourceTotals != null) {
			feedbackString += DeckReporter.reportManaSources(sourceTotals);
			feedbackString += "\n";
//			sourcesTextArea.setText();
		}else {
//			sourcesTextArea.setText("");
		}

		ManaTally devotion = DeckAnalyst.calculateDevotion(deck);
		if(devotion != null) {
			feedbackString += DeckReporter.reportDevotion(devotion);
			feedbackString += "\n";
//			devotionTextArea.setText();
		}else {
//			devotionTextArea.setText("");
		}
		
		ManaTally fetchLands = DeckAnalyst.calculateFetchLands(deck);
		if(devotion != null) {
			feedbackString += DeckReporter.reportFetchLands(fetchLands);
			feedbackString += "\n";
//			devotionTextArea.setText();
		}else {
//			devotionTextArea.setText("");
		}
		analysisArea.setText(feedbackString);
		
}

	 
	 
}

