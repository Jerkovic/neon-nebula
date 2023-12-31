package com.neonnebula.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.neonnebula.game.NeonNebulaGame;
import com.neonnebula.game.entity.PlayerShip;

public class GamePlayScreen implements Screen {
    NeonNebulaGame game;

    TextureRegion background;

    PlayerShip playerShip;
    Sound laserSound;

    Music music;

    TextureAtlas atlas;

    private SpriteBatch batch;

    public GamePlayScreen(NeonNebulaGame game) {
        this.game = game;
        batch = new SpriteBatch();

        // get the spriteSheet (atlas) from the asset manager
        TextureAtlas atlas = game.getAssetManager().get("texturePack.txt");

        music = game.getAssetManager().get("music/jeroen.mp3");
        background = atlas.findRegion("background");
        playerShip = new PlayerShip(atlas.findRegion("PlayerShip"));
        playerShip.setLaserSound((Sound) game.getAssetManager().get("sfx/laser.wav"));
    }

    @Override
    public void show() {
        music.setLooping(true);
        music.setVolume(1.0f);
        music.play(); // start music
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setSplashScreen();
        }
    }

    public void update(float delta) {
        handleInput(delta);
        playerShip.update(delta);
    }

    @Override
    public void render(float delta) {
        // all logic for our game play screen in update
        update(delta);

        batch.begin();
        batch.draw(background, 0, 0);
        playerShip.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    @Override
    public void hide() {
        music.stop();
    }

    @Override
    public void dispose() {
        atlas.dispose();
        batch.dispose();
    }
}
