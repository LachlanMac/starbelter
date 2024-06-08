package com.starbelter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	private Texture texture;
	private Vector2 pos;

	public GameObject(Vector2 pos, Texture texture) {
		this.pos = pos;
		this.texture = texture;
	}

	public void update() {

	}

	public void render(Batch batch) {
		batch.draw(texture, pos.x, pos.y);
	}

}
