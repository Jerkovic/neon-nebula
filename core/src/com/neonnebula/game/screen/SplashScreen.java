package com.neonnebula.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neonnebula.game.NeonNebulaGame;

public class SplashScreen implements Screen {
    NeonNebulaGame game;

    SpriteBatch batch;
    Texture img;

    OrthographicCamera camera;
    Viewport viewport;

    BitmapFont font;

    public SplashScreen(NeonNebulaGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("graphics/splash2.jpg");

        camera = new OrthographicCamera();
        camera.lookAt(0,0,0);
        camera.update();

        viewport = new FitViewport(1024, 1024, camera);

        font = new BitmapFont();
        font.getData().setScale(2, 2);
    }

    public void update() {
        // handleInputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GamePlayScreen(game));
        }
        // other logic
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(img, 0, 0);
        font.draw(batch, "Press any key", (camera.viewportWidth / 2f) - 80, camera.viewportHeight / 2f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        font.dispose();
        img.dispose();
    }
}
