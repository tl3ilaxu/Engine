package com.tleilaxu;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.swing.JFrame;

import com.tleilaxu.entity.Polygon;
import com.tleilaxu.entity.TRSMatrix;
import com.tleilaxu.geometry.Geometry;
import com.tleilaxu.graphics.Screen;
import com.tleilaxu.level.Level;
import com.tleilaxu.math.Matrix;
import com.tleilaxu.math.Vector;

public class Engine extends Canvas implements Runnable{
	public static final String TITLE = "Engine";
	private boolean running;
	private JFrame frame;
	private Screen screen;
	private Level level;
	
	private BufferedImage image;
	private int[] pixels;
	
	public static void main(String [] args){
		new Engine().start();
	}
	public Engine(){
		//setup frame
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.add(this);
		frame.setVisible(true);
		//setup canvas
		image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		screen = new Screen(frame.getWidth(), frame.getHeight());
		level = new Level();
		level.add(new Polygon(new Vector(50,50), 3,50));

	}
	//starts the main thread for the main loop
	public void start() {
		running = true;
		Thread t = new Thread(this);
		t.start();
	}
	public void run() {
		//fps counter and update limiter
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double ns2 = 1000000000.0 / 30.0;
		double delta = 0;
		//double delta2 = 0;
		int frames = 0;
		int ups = 0;
		//main loop
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			//delta2 += (now - lastTime) / ns2;
			lastTime = now;
			if (delta >= 1) {
				update();
				ups++;
				delta--;
			}
			//if (delta2 >= 1) {
			render();
			frames++;
				
				//delta2--;
			//}
			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				frame.setTitle(TITLE + "  |  " + ups + " ups, " + frames + " fps");
				frames = 0;
				ups = 0;
			}
		}
		System.exit(0);
	}
	public void update(){
		level.update();
	}
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		level.render(screen);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		bs.show();
		g.dispose();
	}
}
