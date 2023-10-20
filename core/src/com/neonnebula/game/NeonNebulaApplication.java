package com.neonnebula.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class NeonNebulaApplication extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

    OrthographicCamera camera;
    Viewport viewport;

    BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("graphics/splash2.jpg");

        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 1024, camera);

        font = new BitmapFont();
        font.getData().setScale(2, 2);
	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    @Override
	public void render () {
        // game logic
        // render
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
        batch.draw(img, 100, 100, 100, 100);
        font.draw(batch, "Press any key", (camera.viewportWidth / 2f)-80, camera.viewportHeight / 2f);
		batch.end();
	}

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
