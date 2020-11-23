package com.davidalexanderkelly.game.Entities.TileObjects;

import java.util.List;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import com.davidalexanderkelly.game.Screens.PlayScreen;
import com.davidalexanderkelly.game.Entities.Player;


public class Teleporter extends InteractiveTileObject {
	
	private float x;
	private float y;
	
    public Teleporter(PlayScreen screen, MapObject object,float x,float y){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(SpaceGamePrototype.TELEPORTER_BIT);
        this.x = x;
        this.y = y;
    }

    @Override
    public void collide(Player player) {
    	List<Teleporter> teleporters = screen.creator.getTeleporters();
    	for(int i = 0; i < teleporters.size(); i++) {
    		if(teleporters.get(i) == this) {
    			if(i % 2 == 0 )
    				player.teleport(teleporters.get(i+1).getPosition().x,teleporters.get(i+1).getPosition().y);
    			else
    				player.teleport(teleporters.get(i-1).getPosition().x,teleporters.get(i-1).getPosition().y);

    		}
    	}
    }
    
    public Vector2 getPosition() {
    	return new Vector2(x,y);
    }

}
