package com.starbelter.data;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileData {
	public static Texture terrainSpriteSheet;
	public static TextureRegion[][] terrainTiles;

	public static ArrayList<TextureRegion> tiles;

	public static void load() {
		terrainSpriteSheet = new Texture(
				Gdx.files.local("data/sprites/TextureSheet.png"));
		terrainTiles = TextureRegion.split(terrainSpriteSheet, 16, 16);

		tiles = new ArrayList<TextureRegion>();

		for (int y = 0; y < terrainTiles[0].length; y++) {
			for (int x = 0; x < terrainTiles.length; x++) {
				tiles.add(terrainTiles[x][y]);
			}
		}
	}

	public static TextureRegion getTile(int i) {
		return tiles.get(i - 1);
	}

}
