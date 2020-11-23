package com.davidalexanderkelly.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import com.davidalexanderkelly.game.Entities.Player;
import com.davidalexanderkelly.game.Entities.TileObjects.InteractiveTileObject;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        
        
        
        switch (cDef){
            case SpaceGamePrototype.PLAYER_BIT | SpaceGamePrototype.TELEPORTER_BIT:
            	System.out.println(fixB.getUserData().getClass().getName());
                System.out.println(fixA.getUserData().getClass().getName());
                if(fixA.getFilterData().categoryBits == SpaceGamePrototype.PLAYER_BIT)
                    ((InteractiveTileObject)fixB.getUserData()).collide((Player) fixA.getUserData());
                else
                    ((InteractiveTileObject)fixA.getUserData()).collide((Player) fixB.getUserData());
                break;
            
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}