package com.tleilaxu.graphics.images;

public class Image {
	private int w, h;
	private int[] pixels;

	public Image(int w, int h, int[] pixels) {
		this.w = w;
		this.h = h;
		this.pixels = pixels;
	}

	public Image(int w, int h) {
		this.w = w;
		this.h = h;
		pixels = new int[w * h];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffff00ff;
		}
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

	public int getPixel(int x, int y) {
		if (x < 0 || y < 0 || x >= w || y >= h) {
			throw new IllegalArgumentException("x or y out of range");
		}
		// return -1;
		// if ((x + y * w) > pixels.length)
		// return -1;
		return pixels[x + y * w];
	}

	public void setPixel(int x, int y, int color) {
		if (x < 0 || y < 0)
			return;
		if (x >= w || y >= h)
			return;
		pixels[x + y * w] = color;
	}
}
