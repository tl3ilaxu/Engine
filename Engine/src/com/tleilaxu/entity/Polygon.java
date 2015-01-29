package com.tleilaxu.entity;

import java.util.ArrayList;

import com.tleilaxu.geometry.Geometry;
import com.tleilaxu.graphics.images.Image;
import com.tleilaxu.graphics.images.ImageEditor;
import com.tleilaxu.math.Vector;

public class Polygon extends DrawableEntity {
	ArrayList<Entity> list = new ArrayList<Entity>();

	public Polygon(Vector pos, Vector[] points) {
		super(pos, new Image(0, 0));
		init(points);
	}

	public Polygon(Vector pos, int edges, double size) {
		super(pos, new Image(0, 0));
		if (edges > 30)
			throw new IllegalArgumentException("Edges exceed maximum number of edges");
		Vector[] points = new Vector[edges];
		int i0 = 0;
		for (double i = 0; i < 360; i += 360.0 / (double) (edges)) {
			points[i0] = new Vector(Math.cos(Math.toRadians(i)) * size + pos.getValue(0)+size, Math.sin(Math.toRadians(i)) * size
					+ pos.getValue(1)+size);
			i0++;
		}
		init(points);
	}

	private void init(Vector[] points) {
		int w = 0;
		int h = 0;
		for (int i = 0; i < points.length; i++) {
			if (points[0].getValue(0) > w)
				w = (int) points[0].getValue(0);
			if (points[1].getValue(1) > h)
				h = (int) points[1].getValue(1);
		}
		image = new Image(w, h);
		for (int i = 0; i < points.length - 1; i++) {
			DrawableEntity e = Geometry.generateLine(points[i], points[i + 1], 0xffffffff);
			e.setX(e.getX() - getX());
			e.setY(e.getY() - getY());
			image = ImageEditor.blendImages(new DrawableEntity(0, 0, image), e);
		}
		DrawableEntity e = Geometry.generateLine(points[points.length - 1], points[0], 0xffffffff);
		e.setX(e.getX() - getX());
		e.setY(e.getY() - getY());
		image = ImageEditor.blendImages(new DrawableEntity(0, 0, image), e);
	}

}
