package com.tleilaxu.graphics.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static Image load(String path) {
		File file = new File(path);
		try {
			BufferedImage img = ImageIO.read(file);
			return new Image(img.getWidth(), img.getHeight(), img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
}