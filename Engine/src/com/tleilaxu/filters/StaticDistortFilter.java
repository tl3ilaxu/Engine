package com.tleilaxu.filters;

import java.util.Random;

import com.tleilaxu.Timer;
import com.tleilaxu.graphics.images.Image;

public class StaticDistortFilter extends Filter {
	private static int strenght = 5;
	private int currentFrame = 0;
	private int backsnappyness = 8;
	private int originalFrame = 3;
	private double updateSpeed = 0.03;

	private int numFrames = 100;

	private Random r;
	private double[][] frames;
	private Image original;
	private Timer timer;

	public StaticDistortFilter(int x, int y, Image image) {
		super(x - strenght, y, image);
		original = image;
		r = new Random();
		timer = new Timer();
		
		frames = new double[numFrames][image.getHeight()];
		for (int i = 0; i < numFrames; i++) {
			if (i % originalFrame == 0) {
				for (int j = 0; j < image.getHeight(); j++) {
					frames[i][j] = 1;
				}
			} else {
				for (int j = 0; j < image.getHeight(); j++) {
					frames[i][j] = r.nextDouble() + r.nextDouble();
					if ((r.nextInt(10) + 1) < backsnappyness && j > 1) {
						frames[i][j] = frames[i][j - 1];
					}
				}
			}
		}
	}

	public void update() {
		timer.time();
		if(timer.getTime() > Timer.toMillSeconds(updateSpeed)){
			currentFrame++;
			timer.setTime(0);
		}
		if (currentFrame >= original.getHeight())
			currentFrame = 0;
	}

	public void apply() {
		int newWidth = original.getWidth() + strenght * 2;
		int[] pixels = new int[newWidth * original.getHeight()];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffff00ff;
		}
		for (int i = 0; i < original.getHeight(); i++) {
			int offset = (int) (strenght * frames[currentFrame][i]);
			for (int j = 0; j < original.getWidth(); j++) {
				pixels[j + offset + i * newWidth] = original.getPixel(j, i);
			}
		}
		image = new Image(newWidth, original.getHeight(), pixels);
	}

}
