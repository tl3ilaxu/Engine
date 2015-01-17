package com.tleilaxu.graphics.images;

public class Image {
	private int w, h;
	private int[] pixels;

	public Image(int w, int h, int[] pixels) {
		this.w = w;
		this.h = h;
		this.pixels = pixels;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixel(int x, int y, int color) {
		if (x < 0 || y < 0)
			return;
		if (x + y * h > pixels.length)
			return;
		pixels[x + y * h] = color;
	}
}
