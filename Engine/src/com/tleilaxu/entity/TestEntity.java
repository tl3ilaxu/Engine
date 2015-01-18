package com.tleilaxu.entity;

import com.tleilaxu.filters.Filter;
import com.tleilaxu.filters.StaticDistortFilter;
import com.tleilaxu.graphics.Screen;
import com.tleilaxu.graphics.images.Image;

public class TestEntity extends Entity{
	Filter filter;
	public TestEntity(int x, int y, Image image) {
		super(x, y);
		filter = new StaticDistortFilter(0, 0, image);
	}
	public void render(Screen screen){
		filter.apply();
		screen.drawFilter(x,y, filter);
	}
	public void update(){
		filter.update();
	}

}
