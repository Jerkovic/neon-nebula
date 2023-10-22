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
    Texture splashImage, logoImage;

    OrthographicCamera camera;
    Viewport viewport;

    BitmapFont font;

    public SplashScreen(NeonNebulaGame game) {
        this.game = game;
        batch = new SpriteBatch();

        splashImage = new Texture("graphics/splash2.jpg");
        logoImage = new Texture("graphics/logo/neon-nebula.png");

        camera = new OrthographicCamera();
        camera.lookAt(0,0,0);
        camera.update();

        viewport = new FitViewport(1024, 1024, camera);
        viewport.apply(true);

        font = new BitmapFont();
    }

    @Override
    public void show() {
    }

    public void update(float delta) {
        // handleInputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setGamePlayScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.quit();
        }
    }

    @Override
    public void render(float delta) {
        // logic
        update(delta);
        camera.update();

        // render
        batch.begin();
        batch.draw(splashImage, 0, 0);
        batch.draw(logoImage, 110, 250);
        font.draw(batch, "PRESS SPACE TO START GAME", (camera.viewportWidth / 2f) - 114, 140);
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
        splashImage.dispose();
        logoImage.dispose();
    }
}
