package com.dewhi100;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import io.magicthegathering.javasdk.resource.Card;

public class Display implements ActionListener {

	JFrame frame;
	JTextField textInput;
	JTextArea cmc;
	JTextArea desc;
	JButton butt;
	JTextArea manaSourceInformation;
	JTextArea manaCostBreakdown;
		
	 public void createAndShowGUI() {
	        //Create and set up the window.
	        frame = new JFrame("HelloWorldCardMonger");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //input field. specify card name
	        textInput = new JTextField(20);
	        frame.getContentPane().add(textInput);
	        
	        //submission button
	        butt = new JButton("Submit");
	        butt.addActionListener(this);
	        frame.getContentPane().add(butt);
	        
	        //cmc field. Showing converted mana cost
	        cmc = new JTextArea("Mana here.");
	        cmc.setLineWrap(true);
	        cmc.setEditable(false);
	        frame.getContentPane().add(cmc);	        
	        
	        //output field. Showing card text
	        desc = new JTextArea("Description here.");
	        desc.setLineWrap(true);
	        desc.setEditable(false);
	        frame.getContentPane().add(desc);

	        //output field. Showing card text
	        manaSourceInformation = new JTextArea(10, 20);
	        manaSourceInformation.setLineWrap(true);
	        manaSourceInformation.setEditable(false);
	        frame.getContentPane().add(manaSourceInformation);

	        //output field. Showing card text
	        manaCostBreakdown = new JTextArea(10, 20);
	        manaCostBreakdown.setLineWrap(true);
	        manaCostBreakdown.setEditable(false);
	        frame.getContentPane().add(manaCostBreakdown);
	        
	        
	        // get the screen size as a java dimension
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	        // get 2/3 of the height, and 2/3 of the width
	        int height = screenSize.height * 2 / 3;
	        int width = screenSize.width / 2;

	        // set the jframe height and width
	        frame.setPreferredSize(new Dimension(width, height));
	        
	        //Display the window.
	        frame.pack();
	        frame.setLayout(new FlowLayout());
	        frame.setVisible(true);
	    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String name = textInput.getText();
		
		Card c = MagicCardClient.getCardByName(name);
		ManaSource ms = new ManaSource(c);
		ManaCost mc = new ManaCost(c);
		
		manaSourceInformation.setText(ms.toString());
		manaCostBreakdown.setText(mc.toString());
		
		//System.out.println("Card cmc is: " + MagicCardClient.getCMC());
		cmc.setText(MagicCardClient.getCMC());
		//System.out.println("Card text is: " + MagicCardClient.getText());
		desc.setText(MagicCardClient.getText());
		
	}

	 
	 
}
