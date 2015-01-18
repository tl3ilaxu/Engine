package com.tleilaxu.entity;

import com.tleilaxu.graphics.Screen;
import com.tleilaxu.graphics.images.Image;


public class DrawableEntity extends Entity{
	protected Image image;
	public DrawableEntity(int x, int y, Image image) {
		super(x, y);
		this.image = image;
	}

	public void render(Screen screen) {
		screen.drawImage(x,y, image);
		
	}
	public void update() {}

	public Image getImage() {
		return image;
	}

}
