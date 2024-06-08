package com.starbelter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Mob {

	public Player(Vector2 pos, Texture texture) {
		super(pos, texture);

	}

	@Override
	public void update() {

	}

	private void checkInput() {

		if (Gdx.input.isKeyPressed(Keys.W)) {
			
		}

	}

}
