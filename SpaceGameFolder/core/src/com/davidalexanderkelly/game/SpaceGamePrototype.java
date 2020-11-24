package com.davidalexanderkelly.game;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidalexanderkelly.game.Screens.MainMenuScreen;
import com.davidalexanderkelly.game.Screens.PlayScreen;
import com.davidalexanderkelly.game.Tools.MenuCamera;

public class SpaceGamePrototype extends Game {
	
	//Virtual Screen size and Box2D Scale(Pixels Per Meter)
	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 720;
	public static final float PixelsPerMetre = 100;
	
	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT =2;
	public static final short TELEPORTER_BIT = 4;
	
	public SpriteBatch batch;
	public MenuCamera cam;
	
	@Override
	public void create () {
		// Sets level
		batch = new SpriteBatch();
		cam = new MenuCamera(V_WIDTH, V_HEIGHT);
		
		setScreen(new MainMenuScreen(this));
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
