package com.starbelter.game;

import com.badlogic.gdx.ApplicationAdapter;


public class LineageGame extends ApplicationAdapter {
	
	/*
	SpriteBatch batch;
	Texture img;
	Engine eng;
	LineageCamera camera;
	ExtendViewport viewport;

	Galaxy galaxy;

	private AssetManager assetManager;

	private BitmapFont font;

	private BaseMap m;
	
	
	Texture background;

	@Override
	public void create() {

		DataLoader.load();
		TileData.load();
		BlueprintData.load();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		font = new BitmapFont();
		batch = new SpriteBatch();
		camera = new LineageCamera();
		// camera.setToOrtho(false, (w / h) * 320, 320);
		viewport = new ExtendViewport(800, 600, camera);
		galaxy = new Galaxy();
		background = new Texture(Gdx.files.local("/data/sprites/background.png"));
		
	
		
		m = new BaseMap(BlueprintData.ships.get(0));

	}

	public void update() {
		
	}
	
	@Override
	public void render() {
		update();
		ScreenUtils.clear(100f / 255f, 100f / 255f, 250f / 255f, 1f);
		camera.update();
		m.getRenderer().setView(camera);
		
		batch.begin();
		batch.draw(background, 0, 0);
		
		m.getRenderer().render();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
		batch.end();
		m.getRenderer().render();

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		batch.setProjectionMatrix(camera.combined);
	}
	*/
}
