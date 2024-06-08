package com.starbelter.game.maps;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.starbelter.data.Blueprint;
import com.starbelter.data.TileData;


public class BaseMap {

	// dimensions
	private int width, height;
	// the texture for the sprite sheet
	private TiledMapRenderer renderer;
	private TiledMap map;
	private TextureRegion[][] region;

	public BaseMap(int width, int height) {
		this.width = width;
		this.height = height;
		makeRandom();
	}

	private Blueprint b;

	public BaseMap(Blueprint b) {
		this.b = b;
		this.width = b.getWidth();
		this.height = b.getHeight();

		map = new TiledMap();
		MapLayers layers = map.getLayers();

		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, 16, 16);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				Cell cell = new Cell();
				cell.setTile(new StaticTiledMapTile(
						TileData.getTile(b.getTileAtLayer(x, y, 0))));
				layer.setCell(x, y, cell);
			}
		}
		layers.add(layer);
		renderer = new OrthogonalTiledMapRenderer(map);

	}

	private void makeRandom() {
		map = new TiledMap();
		MapLayers layers = map.getLayers();

		for (int l = 0; l < 20; l++) {
			TiledMapTileLayer layer = new TiledMapTileLayer(width, height, 16, 16);
			for (int x = 0; x < 150; x++) {
				for (int y = 0; y < 100; y++) {
					int ty = (int) (Math.random() * region.length);
					int tx = (int) (Math.random() * region[ty].length);
					Cell cell = new Cell();
					cell.setTile(new StaticTiledMapTile(region[ty][tx]));
					layer.setCell(x, y, cell);
				}
			}
			layers.add(layer);
		}
		renderer = new OrthogonalTiledMapRenderer(map);
	}

	public TiledMapRenderer getRenderer() {
		return renderer;
	}

}
