package com.davidalexanderkelly.game.Entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.davidalexanderkelly.game.SpaceGamePrototype;

public class Teleporter {
	
	private float x;
	private float y;
	private Body box2dBody;
	
	public Teleporter(float x, float y) {
		this.x = x;
		this.y = y;
		

	}
	
	public void collide() {
		System.out.println("Teleport");
	}
	
	
	

	
}
