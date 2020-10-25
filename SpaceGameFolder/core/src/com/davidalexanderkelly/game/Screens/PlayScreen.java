package com.davidalexanderkelly.game.Screens;



import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import com.davidalexanderkelly.game.Scenes.Hud;
import com.davidalexanderkelly.game.Sprites.Player;
import com.davidalexanderkelly.game.Tools.Assets;
import com.davidalexanderkelly.game.Tools.Box2DWorldCreator;

public class PlayScreen implements Screen {
	//Reference to Game, used to set screen
	private SpaceGamePrototype game;
	
	public SpriteBatch batch;
	public Texture img;
	private Skin skin;
	
	private static final int IDLE = 0;
	private static final int RUNNING = 1;
	
	
	//Camera and Camera manipulation variables
	private OrthographicCamera gamecam;
	private Viewport gamePort;  
	private Hud hud;
	
	//Tiled map variables
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	//Box2d variables
	private World world;
	private Box2DDebugRenderer collisionRenderer;
	
	private Assets assets;
	private Player player;
	
	public PlayScreen(SpaceGamePrototype game) {
		
		this.game = game;
		//create camera used to follow player
		gamecam = new OrthographicCamera();	
		//create a FitViewport to maintain virtual aspect ratio despite screen size
		gamePort = new FitViewport(SpaceGamePrototype.V_WIDTH / SpaceGamePrototype.PixelsPerMetre, SpaceGamePrototype.V_HEIGHT / SpaceGamePrototype.PixelsPerMetre, gamecam);
		
		hud = new Hud(game.batch);
		
		//Load the map and setup its renderer
		maploader = new TmxMapLoader();
		map = maploader.load("assets/Maps/SpaceShip.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / SpaceGamePrototype.PixelsPerMetre);
		
		//Game camera position and zoom level
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() /2,0);
		gamecam.zoom = 0.225f;
		
		//Creates the collision world
		world = new World(new Vector2(0,0), true);
		
		//allows the rendering of collision boxes
		collisionRenderer = new Box2DDebugRenderer();
		
		new Box2DWorldCreator(world,map);
		
		batch = new SpriteBatch();

		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();

		skin = new Skin();
		skin.addRegions(assets.manager.get("assets/Sprites/Spritepack.atlas", TextureAtlas.class));
		
		//creates Player in the world
		player = new Player(world, this,skin.getRegion("playerIdle"), skin.getRegion("playerRun"), assets.manager.get("assets/Sounds/stepSound.wav", Sound.class) );
		
				
	}
		
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void handleInput(float deltaTime) {
		
		//Player Movement
		boolean moveUp = (Gdx.input.isKeyPressed(Input.Keys.UP));
		boolean moveDown = (Gdx.input.isKeyPressed(Input.Keys.DOWN));
		boolean moveRight = (Gdx.input.isKeyPressed(Input.Keys.RIGHT));
		boolean moveLeft = (Gdx.input.isKeyPressed(Input.Keys.LEFT));
		
		if (moveUp && player.box2dBody.getLinearVelocity().y < 0.7f && !moveDown) {
			player.box2dBody.applyLinearImpulse(new Vector2(0f,0.15f), player.box2dBody.getWorldCenter(), true);
		} else if (moveDown && player.box2dBody.getLinearVelocity().y > -0.7f && !moveUp) {
			player.box2dBody.applyLinearImpulse(new Vector2(0f,-0.15f), player.box2dBody.getWorldCenter(), true);
		} else if (moveUp == moveDown) {
			player.box2dBody.setLinearVelocity(player.box2dBody.getLinearVelocity().x,0f);
		}
		
		if (moveRight && player.box2dBody.getLinearVelocity().x < 0.7f && !moveLeft) {
			player.box2dBody.applyLinearImpulse(new Vector2(0.15f, 0f), player.box2dBody.getWorldCenter(), true);
			if(Player.width < 0) {
				Player.width*=-1;
				Player.xOffset = 0;
			}				
		} else if (moveLeft && player.box2dBody.getLinearVelocity().x > -0.7f && !moveRight) {
			player.box2dBody.applyLinearImpulse(new Vector2(-0.15f, 0f), player.box2dBody.getWorldCenter(), true);
			if(Player.width >0) {
				Player.width*=-1;
				Player.xOffset = 70;
			}			
		} else if (moveRight == moveLeft) {
			player.box2dBody.setLinearVelocity(0f, player.box2dBody.getLinearVelocity().y);
		}
		
		if(player.box2dBody.getLinearVelocity().y == 0f && player.box2dBody.getLinearVelocity().x == 0) {
			player.setCurrentAnimation(IDLE);
		}
		else if(player.getCurrentAnimation() != RUNNING){
			player.setCurrentAnimation(RUNNING);
		}
		
	}
	
	public void update(float deltaTime) {
		//looks for player input
		handleInput(deltaTime);
		
		//updates physics 60 times per second
		world.step(1/60f,6,2);
		
		
		
		//Game camera follows the player
		gamecam.position.x = player.box2dBody.getPosition().x;
		gamecam.position.y = player.box2dBody.getPosition().y;
		gamecam.update();
		
		
		
		//Tells the renderer to only draw what the player can see
		renderer.setView(gamecam);
	}

	@Override
	public void render(float delta) {
		//separate update logic from renderer
		update(delta);
		
		//Clear game screen with Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//render the game map
		renderer.render();
		
		//renderer for the collision boxes
		collisionRenderer.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.update(delta,batch);
		game.batch.end();
	}
	

	@Override
	public void resize(int width, int height) {
		gamePort.update(width,height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		//dispose of all opened resources
		map.dispose();
		renderer.dispose();
		world.dispose();
		collisionRenderer.dispose();
		hud.dispose();
		skin.dispose();
		batch.dispose();
	
		
	}

}
