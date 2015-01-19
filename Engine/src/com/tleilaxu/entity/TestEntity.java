package com.tleilaxu.entity;

import com.tleilaxu.Timer;
import com.tleilaxu.filters.Filter;
import com.tleilaxu.filters.StaticDistortFilter;
import com.tleilaxu.graphics.Screen;
import com.tleilaxu.graphics.images.Image;
import com.tleilaxu.graphics.images.ImageEditor;

public class TestEntity extends Entity{
	private Filter filter;
	private Image image, red ,green , blue, pureB, pureG, pureR;
	public TestEntity(int x, int y, Image image) {
		super(x, y);
		this.image = image;
		red = ImageEditor.getRedChannel(image);
		green = ImageEditor.getGreenChannel(image);
		blue = ImageEditor.getBlueChannel(image);
		filter = new StaticDistortFilter(0, 0, image);
		int w = 100;
		int h = 100;
		int[][] pixels = new int[3][w*h];
		for (int i = 0; i < pixels[0].length; i++) {
			pixels[0][i] = 0xffff0000;
		}
		pureR = ImageEditor.resizeImage(new Image(w, h, pixels[0]),50,50);
		for (int i = 0; i < pixels[0].length; i++) {
			pixels[1][i] = 0xff00ff00;
		}
		pureG = new Image(w, h, pixels[1]);
		for (int i = 0; i < pixels[0].length; i++) {
			pixels[2][i] = 0xff0000ff;
		}
		pureB = ImageEditor.resizeImage(new Image(w, h, pixels[2]),200,200);
		
	}
	public void render(Screen screen){
		filter.apply();
		screen.drawImage(x, y, ImageEditor.getRedChannel(red));
		screen.drawImage(x+300, y, ImageEditor.getGreenChannel(green));
		screen.drawImage(x+600, y, ImageEditor.getBlueChannel(blue));
		screen.drawImage(x, y+100, image);
		screen.drawImage(x+300, y+150, ImageEditor.blendImages(blue, green));
		
//		screen.drawFilter(x,y, filter);
	}
	public void update(){
		filter.update();
	}

}
