package com.davidalexanderkelly.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.davidalexanderkelly.game.Entities.Teleporter;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		if("player".equals(fixtureA.getUserData()) || "player".equals(fixtureB.getUserData())) {
			Fixture player = "player".equals(fixtureA.getUserData()) ? fixtureA : fixtureB;
			Fixture object = player == fixtureA ? fixtureB : fixtureA;
			
			
			if(object.getUserData() != null) {
				((Teleporter) object.getUserData()).collide();
			}
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
