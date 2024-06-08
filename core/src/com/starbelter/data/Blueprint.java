package com.starbelter.data;

import java.util.ArrayList;
import java.util.List;

public class Blueprint {

	private List<BlueprintLayer> layers;
	private int width, height;

	// params culture
	public Blueprint(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
		layers = new ArrayList<BlueprintLayer>();
	}

	public BlueprintLayer getLayerByID(int id) {
		return layers.get(id);
	}

	public void addLayer(BlueprintLayer layer) {
		layers.add(layer);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTileAtLayer(int x, int y, int l) {
		return layers.get(l).getTileAt(x, y);
	}

}

class BlueprintLayer {

	private int[][] tileLayer;
	private int[][] objectLayer;

	public BlueprintLayer(int[][] tileLayer, int[][] objectLayer) {
		this.tileLayer = tileLayer;
		this.objectLayer = objectLayer;
	}

	public int[][] getTiles() {
		return tileLayer;
	}

	public int[][] getObjects() {
		return objectLayer;
	}

	public int getTileAt(int x, int y) {
		return tileLayer[x][y];
	}

	public int getObjectAt(int x, int y) {
		return objectLayer[x][y];
	}

}