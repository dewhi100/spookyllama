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

public class Display implements ActionListener {

	JFrame frame;
	JTextField textInput;
	JTextArea cmc;
	JTextArea desc;
	JButton butt;
	
	private static final float GOLD = (float) ((1 + Math.sqrt(5)) / 2);
	
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

	        
	        
	        // get the screen size as a java dimension
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	        // get 2/3 of the height, and 2/3 of the width
	        int height = screenSize.height * 1 / 4;
	        int width = (int) (height * GOLD);

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
		
		MagicCardClient.getCardByName(name);
	
		System.out.println("Card cmc is: " + MagicCardClient.getCMC());
		cmc.setText(MagicCardClient.getCMC());
		System.out.println("Card text is: " + MagicCardClient.getText());
		desc.setText(MagicCardClient.getText());
		
	}

	 
	 
}

