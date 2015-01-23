package com.tleilaxu.graphics;

import com.tleilaxu.entity.DrawableEntity;
import com.tleilaxu.filters.Filter;
import com.tleilaxu.graphics.images.Image;

public class Screen {
	private int w,h;
	public int[] pixels;
	public Screen(int w, int h){
		this.w = w;
		this.h = h;
		pixels = new int[w * h];
	}
	public void drawImage(double x, double y, Image image) {
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				setPixels(x + j, y + i, image.getPixels()[j + i * image.getWidth()]);
			}
		}
	}
	public void drawFilter(double x, double y, Filter filter){
		filter.apply();
		drawImage(x + filter.getX(), y + filter.getY(), filter.getImage());
	}
	public void drawEntity(DrawableEntity e){
		drawImage(e.getX(), e.getY(), e.getImage());
	}
	public void setPixels(double x, double y, int color){
		if (x < 0 || y < 0 || x >= w || y >= h)return;
		//if(color == 0xffff00ff)return;
		pixels[(int) (x + y * w)] = color;
	}
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
		
	}

}
