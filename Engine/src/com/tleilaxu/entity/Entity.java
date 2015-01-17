package com.tleilaxu.entity;

import com.tleilaxu.graphics.Screen;

public abstract class Entity {
	protected int x,y;
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	public abstract void render(Screen screen);
	public abstract void update();

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
