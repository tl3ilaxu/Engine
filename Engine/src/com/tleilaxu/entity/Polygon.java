package com.tleilaxu.entity;

import java.util.ArrayList;

import com.tleilaxu.geometry.Geometry;
import com.tleilaxu.graphics.images.Image;
import com.tleilaxu.graphics.images.ImageEditor;
import com.tleilaxu.math.Vector;

public class Polygon extends DrawableEntity {

	public Polygon(TRSMatrix pos, Vector[] points) {
		super(pos, new Image(0, 0));
		init(points);
		image = Geometry.generatePolygon(points);
	}

	public Polygon(TRSMatrix pos, int edges, double size) {
		super(pos, new Image(0, 0));
		if (edges > 30)
			throw new IllegalArgumentException("Edges exceed maximum number of edges");
		Vector[] points = new Vector[edges];
		int i0 = 0;
		for (double i = 0; i < 360; i += 360.0 / (double) (edges)) {
			points[i0] = new Vector(Math.cos(Math.toRadians(i)), Math.sin(Math.toRadians(i)), 1);
			i0++;
		}
		init(points);
		image = Geometry.generatePolygon(points);
	}
	public void init(Vector[] points) {
		//calculate smallest width and height and translate every point over it
		double sw = 0;
		double sh = 0;
		for (int i = 0; i < points.length; i++) {
			points[i].multiplyByMatrix(pos.getRSMatrix());
		}
		for (int i = 0; i < points.length; i++) {
			double xv = points[i].getValue(0);
			double yv = points[i].getValue(1);
			if (xv < sw)
				sw = xv;
			if (yv < sh)
				sh = yv;

		}
		for (int i = 0; i < points.length; i++) {
			points[i].addVec(new Vector(-sw, -sh, 1));
		}
	}
	public void rebuild(){
		
	}
	public void update() {
		super.update();
		pos.rotate(Math.toRadians(1));
		pos.rebuild();
//		System.out.println(pos.getMatrix());

	}

}
