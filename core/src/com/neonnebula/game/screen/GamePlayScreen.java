package com.neonnebula.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.neonnebula.game.NeonNebulaGame;

public class GamePlayScreen implements Screen {
    NeonNebulaGame game;

    TextureRegion background;

    private SpriteBatch batch;

    public GamePlayScreen(NeonNebulaGame game) {
        this.game = game;
        // game.getAssetManager().get('')
        batch = new SpriteBatch();

        TextureAtlas atlas = game.getAssetManager().get("texturePack.txt");
        background = atlas.findRegion("background");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0);
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
