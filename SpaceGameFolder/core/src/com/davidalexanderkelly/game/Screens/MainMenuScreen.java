package com.davidalexanderkelly.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.davidalexanderkelly.game.SpaceGamePrototype;



public class MainMenuScreen implements Screen {
	
	/*
	 * Initialise menu stage where widgets wil go
	 */
	private Stage stage;
	
	/*
	 * Initialise atlas which will retrieve button images
	 */
	private TextureAtlas atlas;
	
	/*
	 * Initialise skin which will map textures to widgets
	 */
	private Skin skin;
	
	/*
	 * Initialise table where widgets will be organised
	 */
	private Table table;
	
	/*
	 * Initialise menu buttons
	 */
	private Button buttonStart, buttonExit;
	
	/*
	 *  reference to game
	 */
	private SpaceGamePrototype game;
	
	public MainMenuScreen(SpaceGamePrototype game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		stage = new Stage();
		
		//look for inputs on stage
		Gdx.input.setInputProcessor(stage);
		
		//load in button assets
		atlas = new TextureAtlas("assets/UI/Menu.atlas");
		skin = new Skin(atlas);
		
		//load skin for use in table
		table = new Table(skin);
		table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		//Initialise button styler
		TextButtonStyle textButton = new TextButtonStyle();
		
		//Start button styling
		textButton.up = skin.getDrawable("StartButton");
		textButton.pressedOffsetX =  1;
		textButton.pressedOffsetX = -1;
		buttonStart = new Button(textButton);
		buttonStart.padTop(20);
		
		//Start button click event
		buttonStart.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PlayScreen(game));
			}
		});		
		buttonStart.padTop(20);
		
		//Exit button styling
		textButton = new TextButtonStyle();
		textButton.up = skin.getDrawable("ExitButton");
		textButton.pressedOffsetX =  1;
		textButton.pressedOffsetX = -1;
		buttonExit = new Button(textButton);
		
		//Exit button click event
		buttonExit.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});		
		buttonExit.padTop(20);
		
		//Add buttons to table
		table.add(buttonStart);
		table.row();
		table.add(buttonExit);
		table.debug();
		stage.addActor(table);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	
}