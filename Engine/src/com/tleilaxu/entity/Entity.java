package com.tleilaxu.entity;

import com.tleilaxu.graphics.Screen;
import com.tleilaxu.math.Vector;

public abstract class Entity {
	// protected Vector pos;
	public TRSMatrix pos;
	public Entity(Vector pos) {
		this.pos = new TRSMatrix(pos, new Vector(1, 1), 0);
	}
	public Entity(TRSMatrix pos) {
		this.pos = pos;
	}
	
	public abstract void render(Screen screen);
	public abstract void update();

	public double getX() {
		return pos.getTranslation().getValue(0);
	}

	public double getY() {
		return pos.getTranslation().getValue(1);
	}
	public void setX(double x) {
		pos.setTranslation(new Vector(x, getY()));
	}
	public void setY(double y) {
		pos.setTranslation(new Vector(getX(), y));
	}

}
