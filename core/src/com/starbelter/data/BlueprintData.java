package com.starbelter.data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;

public class BlueprintData {

	public enum TileType {
		FLOOR, DOOR, BRIDGE, WALL, EMPTY, ENGINEERING
	};

	private static Color WALL_1 = new Color(255, 0, 0);
	private static Color ENGINEERING_FLOOR_1 = new Color(255, 0, 255);
	private static Color BRIDGE_FLOOR_1 = new Color(255, 255, 0);
	private static Color EMPTY = new Color(0, 0, 0);
	private static Color FLOOR_1 = new Color(255, 255, 0);

	public static ArrayList<Blueprint> ships;

	public BlueprintData() {
		ships = new ArrayList<Blueprint>();
	}

	public static void load() {
		loadShips();
	}

	private static int n(TileType type) {

		switch (type) {
		case BRIDGE:
			return 1;
		case DOOR:
			return 0;
		case EMPTY:
			return 0;
		case FLOOR:
			return 1;
		case WALL:
			return 2;
		case ENGINEERING:
			return 1;
		default:
			return 0;
		}
	}

	private static void loadShips() {
		ships = new ArrayList<Blueprint>();
		List<FileHandle> files = new ArrayList<>();
		FileHandle directory = Gdx.files.local("./data/blueprints/ships");
		FileHandle[] directoryFiles = directory.list();

		if (directoryFiles != null) {
			for (FileHandle file : directoryFiles) {
				if (file.extension().equalsIgnoreCase("png")) {
					files.add(file);
				}
			}
		}

		for (FileHandle f : files) {
			try {

				BufferedImage image = ImageIO.read(f.file());
				int width = image.getWidth();
				int height = image.getHeight();

				BlueprintLayer layer = new BlueprintLayer(new int[width][height],
						new int[width][height]);

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						Color color = new Color(image.getRGB(x, y));
						if (color.equals(EMPTY)) {
							layer.getTiles()[x][y] = 3;
						} else if (color.equals(WALL_1)) {
							layer.getTiles()[x][y] = 2;
						} else if (color.equals(FLOOR_1)) {
							layer.getTiles()[x][y] = 1;
						} else {
							layer.getTiles()[x][y] = 1;
						}
					}
				}

				Blueprint b = new Blueprint(width, height);
				b.addLayer(layer);
				ships.add(b);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
