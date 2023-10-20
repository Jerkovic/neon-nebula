package com.neonnebula.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class NeonNebulaGame extends Game {

    private AssetManager assetManager;

    public NeonNebulaGame(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    private void loadAssets() {
        assetManager.load("texturePack.txt", TextureAtlas.class);
        assetManager.finishLoading();
    }

    @Override
    public void create() {
        loadAssets();
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
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    @Override
    public Screen getScreen() {
        return super.getScreen();
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }
}
