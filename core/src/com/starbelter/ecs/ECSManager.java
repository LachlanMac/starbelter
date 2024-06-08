package com.starbelter.ecs;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.starbelter.ecs.systems.RenderingSystem;

public class ECSManager {

	private static ECSManager instance;

	private SpriteBatch batch;
	private PooledEngine engine;

	public static ECSManager getInstance() {
		if (instance == null) {
			instance = new ECSManager();
		}
		return instance;
	}

	public ECSManager() {
		batch = new SpriteBatch();
		engine = new PooledEngine();
		engine.addSystem(new RenderingSystem(batch));
	}

	public PooledEngine getEngine() {
		return engine;
	}

	public void update(float deltaTime) {
		engine.update(deltaTime); // This will call update on all systems, including
									// RenderingSystem
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
