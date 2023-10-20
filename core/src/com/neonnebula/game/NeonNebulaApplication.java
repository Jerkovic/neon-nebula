package com.neonnebula.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.ScreenUtils;
import com.neonnebula.game.screen.SplashScreen;

public class NeonNebulaApplication extends ApplicationAdapter {
	private NeonNebulaGame game;

	@Override
	public void create () {
        game = new NeonNebulaGame(new AssetManager());
        game.create();
        game.setScreen(new SplashScreen(game));
	}

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
	public void render () {
        // render
		ScreenUtils.clear(0, 0, 0, 1);
        game.render();
	}

    @Override
    public void pause() {
        game.pause();
    }

    @Override
    public void resume() {
        game.resume();
    }

    @Override
	public void dispose () {
		game.dispose();
	}
}
