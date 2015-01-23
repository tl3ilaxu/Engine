package com.tleilaxu.entity;

import com.tleilaxu.graphics.Screen;
import com.tleilaxu.graphics.images.Image;
import com.tleilaxu.math.Vector;

public class DrawableEntity extends Entity {
	protected Image image;

	public DrawableEntity(Vector pos, Image image) {
		super(pos);
		this.image = image;
	}

	public DrawableEntity(int x, int y, Image image) {
		super(new Vector(x,y));
		this.image = image;
	}

	public void render(Screen screen) {
		screen.drawImage(getX(), getY(), image);

	}

	public void update() {
	}

	public Image getImage() {
		return image;
	}

}
