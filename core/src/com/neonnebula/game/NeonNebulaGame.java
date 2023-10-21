package com.neonnebula.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.neonnebula.game.screen.GamePlayScreen;
import com.neonnebula.game.screen.SplashScreen;

public class NeonNebulaGame extends Game {

    private AssetManager assetManager;
    private Screen splashScreen, gamePlayScreen;

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
        assetManager.load("music/jeroen.mp3", Music.class);
        assetManager.load("sfx/laser.wav", Sound.class);
        assetManager.finishLoading();
    }

    @Override
    public void create() {
        loadAssets();
        splashScreen = new SplashScreen(this);
        gamePlayScreen = new GamePlayScreen(this);
    }

    public void setSplashScreen() {
        setScreen(splashScreen);
    }

    public void setGamePlayScreen() {
        setScreen(gamePlayScreen);
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
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }

    public void quit() {
        Gdx.app.exit();
    }
}
