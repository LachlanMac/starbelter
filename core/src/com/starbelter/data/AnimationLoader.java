package com.starbelter.data;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationLoader {

	public static final Map<Integer, Map<AnimationState, TextureRegion[]>> bodyAnimationMap = new HashMap<>();
	public static final Map<Integer, Map<AnimationState, TextureRegion[]>> eyeAnimationMap = new HashMap<>();
	public static final Map<Integer, Map<AnimationState, TextureRegion[]>> eyebrowAnimationMap = new HashMap<>();
	public static final Map<Integer, Map<AnimationState, TextureRegion[]>> hairAnimationMap = new HashMap<>();
	public static final Map<Integer, Map<AnimationState, TextureRegion[]>> pantsAnimationMap = new HashMap<>();
	public static final Map<Integer, Map<AnimationState, TextureRegion[]>> feetAnimationMap = new HashMap<>();
	public static final Map<Integer, Map<AnimationState, TextureRegion[]>> shirtAnimationMap = new HashMap<>();
	public static final Map<Integer, Map<AnimationState, TextureRegion[]>> mouthAnimationMap = new HashMap<>();
	
	public enum AnimationState {
		IDLE_UP, IDLE_DOWN, IDLE_LEFT, IDLE_RIGHT, WALK_LEFT, WALK_RIGHT, WALK_DOWN,
		WALK_UP
	}

	public enum SheetType {
		BODY, HAIR, EYES, EYEBROWS, PANTS, FEET, SHIRT, MOUTH;
	}

	public static void load() {
		loadSpriteSheet(0, SheetType.BODY);
		loadSpriteSheet(0, SheetType.HAIR);
		loadSpriteSheet(0, SheetType.EYES);
		loadSpriteSheet(0, SheetType.EYEBROWS);
		loadSpriteSheet(0, SheetType.PANTS);
		loadSpriteSheet(0, SheetType.FEET);
		loadSpriteSheet(0, SheetType.SHIRT);
		loadSpriteSheet(0, SheetType.MOUTH);
	}

	private static void addToMap(Map<AnimationState, TextureRegion[]> map,TextureRegion[][] region) {
		map.put(AnimationState.IDLE_UP,new TextureRegion[] { region[0][0], region[0][1] });
		map.put(AnimationState.IDLE_DOWN,new TextureRegion[] { region[1][0], region[1][1] });
		map.put(AnimationState.IDLE_LEFT,new TextureRegion[] { region[2][0], region[2][1] });
		map.put(AnimationState.IDLE_RIGHT,new TextureRegion[] { region[3][0], region[3][1] });
		map.put(AnimationState.WALK_UP, new TextureRegion[] { region[0][0], region[0][2],region[0][0], region[0][3] });
		map.put(AnimationState.WALK_DOWN, new TextureRegion[] { region[1][0],region[1][2], region[1][0], region[1][3] });
		map.put(AnimationState.WALK_RIGHT, new TextureRegion[] { region[2][0],region[2][2], region[2][0], region[2][3] });
		map.put(AnimationState.WALK_LEFT, new TextureRegion[] { region[3][0],region[3][2], region[3][0], region[3][3] });
	}

	public static void loadSpriteSheet(int id, SheetType sheetType) {

		String sheetPath = "./data/sprites/";
		switch (sheetType) {
		case BODY:
			sheetPath += "bodysheet_" + id + ".png";
			break;
		case EYES:
			sheetPath += "eyesheet_" + id + ".png";
			break;
		case EYEBROWS:
			sheetPath += "eyebrowsheet_" + id + ".png";
			break;
		case HAIR:
			sheetPath += "hairsheet_" + id + ".png";
			break;
		case PANTS:
			sheetPath += "pantssheet_" + id + ".png";
			break;
		case FEET:
			sheetPath += "feetsheet_" + id + ".png";
			break;
		case SHIRT:
			sheetPath += "shirtsheet_" + id + ".png";
			break;
		case MOUTH:
			sheetPath += "mouthsheet_" + id + ".png";
			break;
		default:
			break;
		}

		Texture texture = new Texture(sheetPath);
		TextureRegion[][] textureRegion = TextureRegion.split(texture, 64, 64);
		Map<AnimationState, TextureRegion[]> animations = new HashMap<>();
		addToMap(animations, textureRegion);

		switch (sheetType) {
		case BODY:
			bodyAnimationMap.put(id, animations);
			break;
		case EYES:
			eyeAnimationMap.put(id, animations);
			break;
		case EYEBROWS:
			eyebrowAnimationMap.put(id, animations);
			break;
		case HAIR:
			hairAnimationMap.put(id, animations);
			break;
		case PANTS:
			pantsAnimationMap.put(id, animations);
			break;
		case FEET:
			feetAnimationMap.put(id, animations);
			break;
		case SHIRT:
			shirtAnimationMap.put(id, animations);
			break;
		case MOUTH:
			mouthAnimationMap.put(id, animations);
			break;
		default:
			break;
		}
	}

	public static HashMap<AnimationState, Animation<Sprite>> get(int id, Color color,
			SheetType sheetType) {

		HashMap<AnimationState, Animation<Sprite>> map = new HashMap<AnimationState, Animation<Sprite>>();
		Map<AnimationState, TextureRegion[]> animations = null;
		switch (sheetType) {
		case BODY:
			animations = bodyAnimationMap.get(id);
			break;
		case EYES:
			animations = eyeAnimationMap.get(id);
			break;
		case EYEBROWS:
			animations = eyebrowAnimationMap.get(id);
			break;
		case HAIR:
			animations = hairAnimationMap.get(id);
			break;
		case PANTS:
			animations = pantsAnimationMap.get(id);
			break;
		case FEET:
			animations = feetAnimationMap.get(id);
			break;
		case SHIRT:
			animations = shirtAnimationMap.get(id);
			break;
		case MOUTH:
			animations = mouthAnimationMap.get(id);
			break;
		default:
			break;
		}

		TextureRegion[] idleDown = animations.get(AnimationState.IDLE_DOWN);
		TextureRegion[] idleUp = animations.get(AnimationState.IDLE_UP);
		TextureRegion[] idleLeft = animations.get(AnimationState.IDLE_LEFT);
		TextureRegion[] idleRight = animations.get(AnimationState.IDLE_RIGHT);
		TextureRegion[] walkDown = animations.get(AnimationState.WALK_DOWN);
		TextureRegion[] walkUp = animations.get(AnimationState.WALK_UP);
		TextureRegion[] walkRight = animations.get(AnimationState.WALK_RIGHT);
		TextureRegion[] walkLeft = animations.get(AnimationState.WALK_LEFT);

		Array<Sprite> walkUpArray = new Array<Sprite>(),
				walkDownArray = new Array<Sprite>(), walkRightArray = new Array<Sprite>(),
				walkLeftArray = new Array<Sprite>(), idleUpArray = new Array<Sprite>(),
				idleDownArray = new Array<Sprite>(), idleLeftArray = new Array<Sprite>(),
				idleRightArray = new Array<Sprite>();

		for (int i = 0; i < walkUp.length; i++) {
			Sprite s = new Sprite(walkUp[i]);
			s.setColor(color);
			walkUpArray.add(s);
		}
		for (int i = 0; i < walkDown.length; i++) {
			Sprite s = new Sprite(walkDown[i]);
			s.setColor(color);
			walkDownArray.add(s);
		}
		for (int i = 0; i < walkRight.length; i++) {
			Sprite s = new Sprite(walkRight[i]);
			s.setColor(color);
			walkRightArray.add(s);
		}
		for (int i = 0; i < walkLeft.length; i++) {
			Sprite s = new Sprite(walkLeft[i]);
			s.setColor(color);
			walkLeftArray.add(s);
		}
		for (int i = 0; i < idleDown.length; i++) {
			Sprite s = new Sprite(idleDown[i]);
			s.setColor(color);
			idleDownArray.add(s);
		}
		for (int i = 0; i < idleUp.length; i++) {
			Sprite s = new Sprite(idleUp[i]);
			s.setColor(color);
			idleUpArray.add(s);
		}
		for (int i = 0; i < idleLeft.length; i++) {
			Sprite s = new Sprite(idleLeft[i]);
			s.setColor(color);
			idleLeftArray.add(s);
		}
		for (int i = 0; i < idleRight.length; i++) {
			Sprite s = new Sprite(idleRight[i]);
			s.setColor(color);
			idleRightArray.add(s);
		}

		map.put(AnimationState.IDLE_UP, new Animation<Sprite>(0.5f, idleUpArray));
		map.put(AnimationState.IDLE_LEFT, new Animation<Sprite>(0.5f, idleLeftArray));
		map.put(AnimationState.IDLE_RIGHT, new Animation<Sprite>(0.5f, idleRightArray));
		map.put(AnimationState.IDLE_DOWN, new Animation<Sprite>(0.5f, idleDownArray));
		map.put(AnimationState.WALK_LEFT, new Animation<Sprite>(0.2f, walkLeftArray));
		map.put(AnimationState.WALK_RIGHT, new Animation<Sprite>(0.2f, walkRightArray));
		map.put(AnimationState.WALK_DOWN, new Animation<Sprite>(0.2f, walkDownArray));
		map.put(AnimationState.WALK_UP, new Animation<Sprite>(0.2f, walkUpArray));

		return map;
	}

}
