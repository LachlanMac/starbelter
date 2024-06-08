package com.starbelter.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.starbelter.data.AnimationLoader.AnimationState;

public class TransformComponent implements Component, Poolable {

	public int layer = 0;
	public final Vector3 position = new Vector3();
    public float scale = 1.0f;
    public float rotation = 0.0f;
    public boolean isHidden = false;
    public AnimationState animationState = AnimationState.IDLE_DOWN;

	@Override
	public void reset() {
		this.rotation = 0.0f;
		this.scale = 0.0f;
		this.position.x = 0.0f;
		this.position.y = 0.0f;
		this.position.z = 0.0f;
	}
}
