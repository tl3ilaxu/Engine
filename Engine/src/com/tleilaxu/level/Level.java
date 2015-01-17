package com.tleilaxu.level;

import java.util.ArrayList;

import com.tleilaxu.entity.Entity;
import com.tleilaxu.graphics.Screen;

public class Level {
	private ArrayList<Entity> entities;
	public Level(){
		entities = new ArrayList<Entity>();
	}
	
	public void add(Entity entity){
		entities.add(entity);
	}
	public void update(){
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
	public void render(Screen screen){
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}
}
