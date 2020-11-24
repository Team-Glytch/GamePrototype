package com.davidalexanderkelly.game.Tools;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import com.davidalexanderkelly.game.Entities.Behaviors.Node;
import com.davidalexanderkelly.game.Screens.PlayScreen;

public class InteractiveWorldCreator {
	
	private ArrayList<Node> locations;	
	private TiledMap map;
	
	protected Fixture fixture;

public InteractiveWorldCreator(PlayScreen screen) {		
	World world = screen.getWorld();
	TiledMap map  = screen.getMap();
	
	BodyDef bodyDefinition = new BodyDef();
	PolygonShape shape = new PolygonShape();
	FixtureDef fixtureDefinition = new FixtureDef();
	Body body;
	
	bodyDefinition.type = BodyDef.BodyType.StaticBody;
	}

	public void setCategoryFilter(short filterBit) {
		Filter filter = new Filter();
		filter.categoryBits = filterBit;
		fixture.setFilterData(filter);
	}
	
	

}