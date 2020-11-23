package com.davidalexanderkelly.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.davidalexanderkelly.game.SpaceGamePrototype;

public class Hud implements Disposable{
	public Stage stage;
	private Viewport viewport;
	
	private Integer health;
	
	Label healthLabel;
	Label healthWordLabel;
	
	public Hud(SpriteBatch sb) {
		health = 100;
		
		viewport = new ExtendViewport(SpaceGamePrototype.V_WIDTH,SpaceGamePrototype.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		healthLabel = new Label(String.format("%3d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healthWordLabel = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(healthWordLabel).expandX().padTop(10);
		table.add(healthLabel).expandX().padTop(10);
				
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
	
	
}
