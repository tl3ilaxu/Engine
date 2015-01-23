package com.tleilaxu.entity;

import com.tleilaxu.graphics.Screen;
import com.tleilaxu.math.Vector;

public abstract class Entity {
	protected Vector pos;
	public Entity(Vector pos){
		this.pos = pos;
	}
	public abstract void render(Screen screen);
	public abstract void update();

	public double getX() {
		return pos.getValue(0);
	}
	
	public double getY() {
		return pos.getValue(1);
	}
	public void setX(double x){
		pos.setValue(0, x);
	}
	public void setY(double y){
		pos.setValue(1, y);
	}

}
