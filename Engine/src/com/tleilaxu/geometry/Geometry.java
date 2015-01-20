package com.tleilaxu.geometry;

import com.tleilaxu.entity.DrawableEntity;
import com.tleilaxu.graphics.images.Image;

public class Geometry {
	public static DrawableEntity generateLine(int x, int y, int x2, int y2,
			int color) {
		//TODO : FIX 
		int w = Math.abs(x - x2);
		int h = Math.abs(y - y2);

		int ax = x;
		if (x2 < x)
			ax = x2;
		int ay = y;
		if (y2 < y)
			ay = y2;

		int[] pixels = new int[(w + 1) * (h + 1)];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffff00ff;
		}
		int ly = 0;
		pixels[0] = 0xffffffff;
		int D = 2 * h - w;
		for (int lx = 1; lx < w; lx++) {
			if (D > 0) {
				ly++;
				pixels[lx + ly * w] = 0xffffffff;
				D = D + (2 * h - 2 * w);
			} else {
				pixels[lx + ly * w] = 0xffffffff;
				D = D + (2 * h);
			}

		}
		Image img = new Image(w, h, pixels);
		return new DrawableEntity(ax, ay, img);
	}
}
