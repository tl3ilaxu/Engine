package com.tleilaxu.geometry;

import com.tleilaxu.entity.DrawableEntity;
import com.tleilaxu.graphics.images.Image;

public class Geometry {
	public static DrawableEntity generateLine(int x, int y, int x2, int y2,
			int color) {
		int w = x2 - x;
		int h = y2 - y;
		if (w == 0)
			w = 1;
		if (h == 0)
			h = 1;
		
		int lx = 0;
		int ly = 0;
		int dx = x;
		int dy = y;
		if(h < 0){
			dy = y2;
			ly = Math.abs(h);
		}
		if(w < 0){
			dx = x2;
			lx = Math.abs(w);
		}
		Image img = new Image(Math.abs(w), Math.abs(h));
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		if (w < 0)
			dx1 = -1;
		else if (w > 0)
			dx1 = 1;
		if (h < 0)
			dy1 = -1;
		else if (h > 0)
			dy1 = 1;
		if (w < 0)
			dx2 = -1;
		else if (w > 0)
			dx2 = 1;
		int longest = Math.abs(w);
		int shortest = Math.abs(h);
		if (!(longest > shortest)) {
			longest = Math.abs(h);
			shortest = Math.abs(w);
			if (h < 0)
				dy2 = -1;
			else if (h > 0)
				dy2 = 1;
			dx2 = 0;
		}
		int numerator = longest >> 1;
		for (int i = 0; i <= longest; i++) {
			img.setPixel(lx, ly, color);
			numerator += shortest;
			if (!(numerator < longest)) {
				numerator -= longest;
				lx += dx1;
				ly += dy1;
			} else {
				lx += dx2;
				ly += dy2;
			}
		}

		return new DrawableEntity(dx, dy, img);
	}
}
