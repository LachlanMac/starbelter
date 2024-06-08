package com.starbelter.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.starbelter.ecs.AnimationComponent;
import com.starbelter.ecs.TransformComponent;

public class RenderingSystem extends IteratingSystem {
	private SpriteBatch batch;
	private ComponentMapper<AnimationComponent> lam = ComponentMapper
			.getFor(AnimationComponent.class);
	private ComponentMapper<TransformComponent> tm = ComponentMapper
			.getFor(TransformComponent.class);

	public RenderingSystem(SpriteBatch batch) {
		super(Family.all(AnimationComponent.class, TransformComponent.class).get());
		this.batch = batch;
	}

	@Override
	public void update(float deltaTime) {
		batch.begin();
		super.update(deltaTime);
		batch.end();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		AnimationComponent anim = lam.get(entity);
		TransformComponent transform = tm.get(entity);

		anim.stateTime += deltaTime;

		Sprite bodyFrame = anim.bodyAnimation.get(transform.animationState)
				.getKeyFrame(anim.stateTime, anim.looping);
		Sprite hairFrame = anim.hairAnimation.get(transform.animationState)
				.getKeyFrame(anim.stateTime, anim.looping);
		Sprite eyeFrame = anim.eyeAnimation.get(transform.animationState)
				.getKeyFrame(anim.stateTime, anim.looping);
		Sprite eyebrowFrame = anim.eyebrowAnimation.get(transform.animationState)
				.getKeyFrame(anim.stateTime, anim.looping);
		Sprite pantsFrame = anim.pantsAnimation.get(transform.animationState)
				.getKeyFrame(anim.stateTime, anim.looping);
		Sprite feetFrame = anim.feetAnimation.get(transform.animationState)
				.getKeyFrame(anim.stateTime, anim.looping);
		Sprite shirtFrame = anim.shirtAnimation.get(transform.animationState)
				.getKeyFrame(anim.stateTime, anim.looping);
		Sprite mouthFrame = anim.mouthAnimation.get(transform.animationState)
				.getKeyFrame(anim.stateTime, anim.looping);

		setSpriteTransform(bodyFrame, transform);
		setSpriteTransform(eyeFrame, transform);
		setSpriteTransform(eyebrowFrame, transform);
		setSpriteTransform(hairFrame, transform);
		setSpriteTransform(pantsFrame, transform);
		setSpriteTransform(feetFrame, transform);
		setSpriteTransform(shirtFrame, transform);
		setSpriteTransform(mouthFrame, transform);
		
		
		bodyFrame.draw(batch);
		// eyeFrame.draw(batch);
		// mouthFrame.draw(batch);
		// eyebrowFrame.draw(batch);
		// hairFrame.draw(batch);
		// pantsFrame.draw(batch);
		// shirtFrame.draw(batch);
		// feetFrame.draw(batch);

	}

	private void setSpriteTransform(Sprite sprite, TransformComponent transform) {
		sprite.setPosition(transform.position.x, transform.position.y);
		sprite.setRotation(transform.rotation); // Assuming TransformComponent includes
		sprite.setScale(transform.scale); // Assuming TransformComponent includes scale
	}
}