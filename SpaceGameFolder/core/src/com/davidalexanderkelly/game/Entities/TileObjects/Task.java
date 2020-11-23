package com.davidalexanderkelly.game.Entities.TileObjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import com.davidalexanderkelly.game.Screens.PlayScreen;
import com.davidalexanderkelly.game.Entities.Player;


public class Task extends InteractiveTileObject {
	
	private float x;
	private float y;
	
    public Task(PlayScreen screen, MapObject object, float x, float y){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(SpaceGamePrototype.TASK_BIT);
        this.x = x;
        this.y = y;
    }

    @Override
    public void collide(Player player) {
    	System.out.println("Ok");
    }
    
    public Vector2 getPosition() {
    	return new Vector2(x,y);
    }

}