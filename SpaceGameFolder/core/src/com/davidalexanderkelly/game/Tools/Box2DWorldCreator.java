package com.davidalexanderkelly.game.Tools;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import com.davidalexanderkelly.game.Screens.PlayScreen;
import com.davidalexanderkelly.game.Entities.Enemy;
import com.davidalexanderkelly.game.Entities.TileObjects.Task;
import com.davidalexanderkelly.game.Entities.TileObjects.Teleporter;



public class Box2DWorldCreator {
    private ArrayList<Enemy> enemies;
    private ArrayList<Teleporter> teleporters;
    private ArrayList<Task> tasks;

    public Box2DWorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create collision bodies/fixtures
        for(MapObject object : map.getLayers().get("Collision").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / SpaceGamePrototype.PixelsPerMetre, (rect.getY() + rect.getHeight() / 2) / SpaceGamePrototype.PixelsPerMetre);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / SpaceGamePrototype.PixelsPerMetre, rect.getHeight() / 2 / SpaceGamePrototype.PixelsPerMetre);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create Teleporter bodies/fixtures
        teleporters = new ArrayList<Teleporter>();
        for(MapObject object : map.getLayers().get("Teleporters").getObjects().getByType(RectangleMapObject.class)){
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
            teleporters.add(new Teleporter(screen, object,(rect.getX() / SpaceGamePrototype.PixelsPerMetre) - 0.08f,(rect.getY() / SpaceGamePrototype.PixelsPerMetre) - 0.08f));
        }
        
        //create Task bodies/fixtures
        tasks = new ArrayList<Task>();
        for(MapObject object : map.getLayers().get("Tasks").getObjects().getByType(RectangleMapObject.class)){			
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
            tasks.add(new Task(screen, object,(rect.getX() / SpaceGamePrototype.PixelsPerMetre) - 0.08f,(rect.getY() / SpaceGamePrototype.PixelsPerMetre) - 0.08f));
            
		}
        
        //create all imposters
        enemies = new ArrayList<Enemy>();
        for(MapObject object : map.getLayers().get("Imposters").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            enemies.add(new Enemy(world,screen,(rect.getX() / SpaceGamePrototype.PixelsPerMetre) - 0.08f,(rect.getY() / SpaceGamePrototype.PixelsPerMetre) - 0.08f));
            
        }
        

    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
      
    public ArrayList<Teleporter> getTeleporters(){
    	return teleporters;
    }
    
    public ArrayList<Task> getTasks(){
    	return tasks;
    }
}
