package com.tleilaxu.graphics.images;

import java.util.ArrayList;

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
		int w = -1;
		int h = -1;
		for (int i = 0; i < images.length; i++) {
			if (w < images[i].getWidth())
				w = images[i].getWidth();
			if (h < images[i].getHeight())
				h = images[i].getHeight();
		}
		int[] pixels = new int[w * h];
		ArrayList<Integer> pixelValues = new ArrayList<Integer>();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				for (int i = 0; i < images.length; i++) {
					int value;
					try {
						value = images[i].getPixel(x, y);
					} catch (Exception e) {
						value = 0xffff00ff;
					}
					
					if (value != 0xffff00ff) {
						pixelValues.add(value);
					}
					// if (value == 0xffff00ff) {
					// blendPixels--;
					// } else {
					// if (blendPixels <= 0) {
					// pixels[x + y * w] = 0xffff00ff;
					// } else {
					// pixels[x + y * w] += value / (1.0 / blendPixels);
					// }
					// }
				}
				if (pixelValues.isEmpty()) {
					pixels[x + y * w] = 0xffff00ff;
				} else {
					for (int i = 0; i < pixelValues.size(); i++) {
						pixels[x + y * w] += pixelValues.get(i)
								/ (1.0 / pixelValues.size());
					}
				}
				pixelValues.clear();
			}

		}
		return new Image(w, h, pixels);
		// TODO: fix it
	}

	public static Image getRedChannel(Image image) {
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		for (int i = 0; i < image.getPixels().length; i++) {
			if (image.getPixels()[i] == 0xffff00ff) {
				pixels[i] = 0xffff00ff;
			} else {
//				pixels[i] = ((image.getPixels()[i] >> 16) & 0x0ff) << 16;
				pixels[i] = image.getPixels()[i] & 0xffff0000;
				if (pixels[i] == 0 && image.getPixels()[i] != 0) {
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
//				pixels[i] = ((image.getPixels()[i] >> 8) & 0x0ff) << 8;
				pixels[i] = image.getPixels()[i] & 0xff00ff00;
				if (pixels[i] == 0 && image.getPixels()[i] != 0) {
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
//				pixels[i] = (image.getPixels()[i]) & 0x0ff;
				pixels[i] = image.getPixels()[i] & 0xff0000ff;
				if (pixels[i] == 0 && image.getPixels()[i] != 0) {
					pixels[i] = 0xffff00ff;
				}
			}
		}
		return new Image(image.getWidth(), image.getHeight(), pixels);
	}
}
