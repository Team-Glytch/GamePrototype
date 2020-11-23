package com.davidalexanderkelly.game.Entities.TileObjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import com.davidalexanderkelly.game.Screens.PlayScreen;
import com.davidalexanderkelly.game.Entities.Player;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected PlayScreen screen;
    protected MapObject object;

    protected Fixture fixture;

    public InteractiveTileObject(PlayScreen screen, MapObject object){
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / SpaceGamePrototype.PixelsPerMetre, (bounds.getY() + bounds.getHeight() / 2) / SpaceGamePrototype.PixelsPerMetre);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / SpaceGamePrototype.PixelsPerMetre, bounds.getHeight() / 2 / SpaceGamePrototype.PixelsPerMetre);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);

    }

    public abstract void collide(Player player);
    
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }


}
