package com.tleilaxu.filters;

import com.tleilaxu.entity.DrawableEntity;
import com.tleilaxu.graphics.images.Image;

public abstract class Filter extends DrawableEntity{

	public Filter(int x, int y, Image image) {
		super(x, y, image);
	}
	public abstract void apply();

}
