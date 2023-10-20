package com.neonnebula.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.neonnebula.game.NeonNebulaGame;
import com.neonnebula.game.entity.PlayerShip;

public class GamePlayScreen implements Screen {
    NeonNebulaGame game;

    TextureRegion background;

    PlayerShip playerShip;

    private SpriteBatch batch;

    public GamePlayScreen(NeonNebulaGame game) {
        this.game = game;
        batch = new SpriteBatch();

        TextureAtlas atlas = game.getAssetManager().get("texturePack.txt");
        Music music = game.getAssetManager().get("music/jeroen.mp3");
        music.play();

        background = atlas.findRegion("background");

        playerShip = new PlayerShip(atlas.findRegion("PlayerShip"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // move this somewhere
        playerShip.handleInput();

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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
