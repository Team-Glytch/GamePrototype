package com.davidalexanderkelly.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidalexanderkelly.game.Screens.PlayScreen;

public class SpaceGamePrototype extends Game {
	
	//Virtual Screen size and Box2D Scale(Pixels Per Meter)
	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 720;
	public static final float PixelsPerMetre = 100;
	
	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT =2;
	public static final short TELEPORTER_BIT = 4;
	public static final short TASK_BIT = 8;
	
	public SpriteBatch batch;
	
	
	@Override
	public void create () {
		// Sets level
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void dispose() {
		//Clean up
		super.dispose();
		batch.dispose();
	}
	
	@Override
	public void render () {
		super.render();
		
	}
	
}
