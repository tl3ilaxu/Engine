package com.tleilaxu;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Engine extends Canvas{
	private JFrame frame;
	private BufferedImage image ;
	
	public static void main(String [] args){
		new Engine();
	}
	public Engine(){
		frame = new JFrame("0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.getGraphics().drawImage(image, image.getWidth(), image.getHeight(), null);
		frame.add(this);
	}
}
