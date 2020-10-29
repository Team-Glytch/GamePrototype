package com.davidalexanderkelly.game.Entities;

import java.lang.Thread.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.davidalexanderkelly.game.SpaceGamePrototype;
import com.davidalexanderkelly.game.Screens.PlayScreen;

public class Player extends Sprite{
	public static int width = 60;
	public static int height = 80;
	public static int xOffset = 10;
	private int currentAnimation;
	
	private float stateTime = 0;
	
	private Animation<TextureRegion> idleAnimation;
	private Animation<TextureRegion> runningAnimation;

	private TextureRegion[] idleFrames;
	private TextureRegion[] runningFrames;

	private TextureRegion currentFrame;
	private Animation [] animations;
	
	private int stepIndex = 0;
	private Sound stepSound;
	
	public World world;
	public Body box2dBody;

	
	public Player(World world, PlayScreen screen,TextureRegion textureRegionIdle,TextureRegion textureRegionRunning, Sound stepSound) {
		this.stepSound = stepSound;
		this.world = world;
		int frameCollumns = 0;
		int frameRows = 0;
		
		// Idle Animation
		frameCollumns = 4;
		frameRows = 1;
        TextureRegion[][] tmp = textureRegionIdle.split(textureRegionIdle.getRegionWidth() / frameCollumns,
                textureRegionIdle.getRegionHeight() / frameRows);
        idleFrames = new TextureRegion[frameCollumns * frameRows];
        int index = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCollumns; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }
        idleAnimation = new Animation<TextureRegion>(0.125f, idleFrames);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        
        // Running Animation
        frameCollumns = 6;
        frameRows = 1;
        tmp = textureRegionRunning.split(textureRegionRunning.getRegionWidth() / frameCollumns,
                (textureRegionRunning.getRegionHeight() / frameRows));
        runningFrames = new TextureRegion[frameCollumns * frameRows];
        index = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCollumns; j++) {
                runningFrames[index++] = tmp[i][j];
            }
        }
        runningAnimation = new Animation<TextureRegion>(0.125f, runningFrames);
        runningAnimation.setPlayMode(Animation.PlayMode.LOOP);
        
        // Array of Animations
        animations = new Animation[2];
        animations[0] = idleAnimation;
        animations[1] = runningAnimation;

        // Initial currentAnimation
        setCurrentAnimation(0);
		
		definePlayer();
				
	}
	
	public void update(float deltaTime, SpriteBatch batch) {
		
		stateTime += Gdx.graphics.getDeltaTime();				
		currentFrame = (TextureRegion) animations[currentAnimation].getKeyFrame(stateTime, true);

        batch.begin();
        
        batch.draw(currentFrame,box2dBody.getPosition().x + 280 + xOffset,
                box2dBody.getPosition().y + 215,width,height);
        batch.end();
        
        
       
	}
	
	public void definePlayer() {
		BodyDef bodyDefinition = new BodyDef();
		bodyDefinition.position.set(100 / SpaceGamePrototype.PixelsPerMetre,100 / SpaceGamePrototype.PixelsPerMetre);
		bodyDefinition.type = BodyDef.BodyType.DynamicBody;
		box2dBody = world.createBody(bodyDefinition);
		
		FixtureDef fixtureDefinition = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5 / SpaceGamePrototype.PixelsPerMetre);
		
		fixtureDefinition.shape = shape;
		box2dBody.createFixture(fixtureDefinition);
	}
	
	public void setCurrentAnimation(int currentAnimation) {
		if(getCurrentAnimation() != currentAnimation) {
			this.currentAnimation = currentAnimation;
			stateTime = 0;
	        stepIndex = 0;
		}
			
			
		
	}
	
	public int getCurrentAnimation() {
		return currentAnimation;
	}
	
	 
}
