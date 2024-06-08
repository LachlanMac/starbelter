package com.starbelter.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.starbelter.camera.CustomCamera;
import com.starbelter.data.AnimationLoader;
import com.starbelter.data.DataLoader;
import com.starbelter.data.AnimationLoader.AnimationState;
import com.starbelter.data.AnimationLoader.SheetType;
import com.starbelter.ecs.AnimationComponent;
import com.starbelter.ecs.ECSManager;
import com.starbelter.ecs.TransformComponent;
import com.starbelter.generation.Galaxy;

public class StarbelterGame extends ApplicationAdapter {

	private ECSManager ecsManager;
	CustomCamera camera;
	ExtendViewport viewport;
	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new CustomCamera();
		// camera.setToOrtho(false, (w / h) * 320, 320);
		viewport = new ExtendViewport(w, h, camera);
		
		AnimationLoader.load();
		
		ecsManager = ECSManager.getInstance(); 
		
		createEntities();

		DataLoader.load();
		
		Galaxy g = new Galaxy();
		
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.4f, 0.4f, 0.4f, 1f);
		camera.update();
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		ecsManager.update(deltaTime);
	}

	private void createEntities() {
		for(int i =0; i < 10; i++) {
			
			Entity entity = ecsManager.getEngine().createEntity();
			AnimationComponent animComp = new AnimationComponent();

			animComp.bodyAnimation = AnimationLoader.get(0,new Color(1.0f, 0.8f, 0.67f, 1.0f), SheetType.BODY);
			animComp.hairAnimation = AnimationLoader.get(0,new Color(236f/255f, 234f/255f, 70f/255f, 1.0f), SheetType.HAIR);
			animComp.eyeAnimation = AnimationLoader.get(0,new Color(1f, 1f, 1f, 1.0f), SheetType.EYES);
			animComp.eyebrowAnimation = AnimationLoader.get(0,new Color(0.2f, 0.2f, 0.47f, 1.0f), SheetType.EYEBROWS);
			animComp.pantsAnimation = AnimationLoader.get(0,new Color(0.05f, 0.05f, 0.05f, 1.0f), SheetType.PANTS);
			animComp.feetAnimation = AnimationLoader.get(0,new Color(0.51f, 0.25f, 0.2f, 1.0f), SheetType.FEET);
			animComp.shirtAnimation = AnimationLoader.get(0,new Color(0.81f, 0.25f, 0.2f, 1.0f), SheetType.SHIRT);
			animComp.mouthAnimation = AnimationLoader.get(0,new Color(0.81f, 0.15f, 0.15f, 1.0f), SheetType.MOUTH);
			TransformComponent transComp = new TransformComponent();

			transComp.position.set(100 * i, 100f, 5f); // Set position
			transComp.scale = 3.0f;
			transComp.animationState = AnimationState.values()[i % 8];
			
			entity.add(animComp);
			entity.add(transComp);
			ecsManager.getEngine().addEntity(entity);
		}
		
	
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		ecsManager.getBatch().setProjectionMatrix(camera.combined);
	}

	@Override
	public void pause() {
		// Handle pause if necessary
	}

	@Override
	public void resume() {
		// Handle resume if necessary
	}

	@Override
	public void dispose() {
		// Clean up resources
		ecsManager.getEngine().clearPools(); // For good measure
	}
}
