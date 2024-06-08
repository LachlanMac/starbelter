package com.starbelter.ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.starbelter.data.AnimationLoader.AnimationState;

public class AnimationComponent implements Component, Poolable {

	public HashMap<AnimationState, Animation<Sprite>> bodyAnimation;
	public HashMap<AnimationState, Animation<Sprite>> hairAnimation;
	public HashMap<AnimationState, Animation<Sprite>> eyeAnimation;
	public HashMap<AnimationState, Animation<Sprite>> eyebrowAnimation;
	public HashMap<AnimationState, Animation<Sprite>> pantsAnimation;
	public HashMap<AnimationState, Animation<Sprite>> feetAnimation;
	public HashMap<AnimationState, Animation<Sprite>> shirtAnimation;
	public HashMap<AnimationState, Animation<Sprite>> mouthAnimation;
	public float stateTime = 0.0f;
	public boolean looping = true;
	
	
	@Override
	public void reset() {
		bodyAnimation = null;
		stateTime = 0.0f;
		looping = true;
	}
}
