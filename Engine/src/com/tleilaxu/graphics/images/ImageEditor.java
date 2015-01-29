package com.tleilaxu.graphics.images;

import java.util.ArrayList;

import com.tleilaxu.entity.DrawableEntity;

public class ImageEditor {

	public static Image resizeImage(Image image, int w2, int h2) {
		int[] temp = new int[w2 * h2];
		int w1 = image.getWidth();
		int h1 = image.getHeight();
		int x_ratio = (int) ((w1 << 16) / w2) + 1;
		int y_ratio = (int) ((h1 << 16) / h2) + 1;
		int x2, y2;
		for (int i = 0; i < h2; i++) {
			for (int j = 0; j < w2; j++) {
				x2 = ((j * x_ratio) >> 16);
				y2 = ((i * y_ratio) >> 16);
				temp[(i * w2) + j] = image.getPixels()[(y2 * w1) + x2];
			}
		}
		return new Image(w2, h2, temp);
	}

	public static Image blendImages(Image... images) {
		DrawableEntity[] imgs = new DrawableEntity[images.length];
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = new DrawableEntity(0, 0,images[i]);
		}
		return blendImages(imgs);
	}

	public static Image blendImages(DrawableEntity... images) {
		int w = -1;
		int h = -1;
		for (int i = 0; i < images.length; i++) {
			if (w < images[i].getImage().getWidth() + images[i].getX())
				w = (int) (images[i].getImage().getWidth() + images[i].getX());
			if (h < images[i].getImage().getHeight() + images[i].getY())
				h = (int) (images[i].getImage().getHeight() + images[i].getY());
		}
		int[] pixels = new int[w * h];
		ArrayList<Integer> pixelValues = new ArrayList<Integer>();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				for (int i = 0; i < images.length; i++) {
					int value;
					int lx = (int) (x - images[i].getX());
					int ly = (int) (y - images[i].getY());
					if (lx < 0 || ly < 0 || lx >= images[i].getImage().getWidth() || ly >= images[i].getImage().getHeight())
						continue;
					value = images[i].getImage().getPixel(lx, ly);

					if (value != 0xffff00ff) {
						pixelValues.add(value);
					}
				}
				if (pixelValues.isEmpty()) {
					pixels[x + y * w] = 0xffff00ff;
				} else {
					for (int i = 0; i < pixelValues.size(); i++) {
						pixels[x + y * w] += pixelValues.get(i) / (1.0 / pixelValues.size());
					}
				}
				pixelValues.clear();
			}

		}
		return new Image(w, h, pixels);
	}

	public static Image getRedChannel(Image image) {
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		for (int i = 0; i < pixels.length; i++) {
			if (image.getPixels()[i] == 0xffff00ff) {
				pixels[i] = 0xffff00ff;
			} else {
				pixels[i] = image.getPixels()[i] & 0xffff0000;
				if (pixels[i] == 0) {
					pixels[i] = 0xffff00ff;
				}
			}
		}
		return new Image(image.getWidth(), image.getHeight(), pixels);
	}

	public static Image getGreenChannel(Image image) {
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		for (int i = 0; i < image.getPixels().length; i++) {
			if (image.getPixels()[i] == 0xffff00ff) {
				pixels[i] = 0xffff00ff;
			} else {
				pixels[i] = image.getPixels()[i] & 0xff00ff00;
				if (pixels[i] == 0) {
					pixels[i] = 0xffff00ff;
				}
			}
		}
		return new Image(image.getWidth(), image.getHeight(), pixels);
	}

	public static Image getBlueChannel(Image image) {
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		for (int i = 0; i < image.getPixels().length; i++) {
			if (image.getPixels()[i] == 0xffff00ff) {
				pixels[i] = 0xffff00ff;
			} else {
				pixels[i] = image.getPixels()[i] & 0xff0000ff;
				if (pixels[i] == 0) {
					pixels[i] = 0xffff00ff;
				}
			}
		}
		return new Image(image.getWidth(), image.getHeight(), pixels);
	}
}
