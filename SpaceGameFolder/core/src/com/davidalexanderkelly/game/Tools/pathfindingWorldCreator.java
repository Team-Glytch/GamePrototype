package com.davidalexanderkelly.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import java.util.ArrayList;



public class pathfindingWorldCreator {
	
	private ArrayList<ArrayList<Float>> locations;

	public pathfindingWorldCreator(TiledMap map) {
		
		this.locations = new ArrayList<ArrayList<Float>>();

		for(MapObject object : map.getLayers().get("Ai Pathing").getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			ArrayList<Float> position = new ArrayList<Float>();
			position.add((rect.getX() + rect.getWidth() / 2) / SpaceGamePrototype.PixelsPerMetre);
			position.add((rect.getY() + rect.getHeight() / 2) / SpaceGamePrototype.PixelsPerMetre);
			this.locations.add(position);
			

		};
		System.out.println(locations);
	}
}
