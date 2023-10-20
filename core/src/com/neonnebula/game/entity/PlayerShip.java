package com.neonnebula.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerShip {

    TextureRegion texture;
    Sprite sprite;

    public PlayerShip(TextureRegion textureRegion) {
        sprite = new Sprite(textureRegion);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(0f, 0f);
    }

    public void handleInput() {
        float speed = 15f; // should be stored in ...prestanda class?
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprite.translate(0, 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.translate(0, -5f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprite.translate(-5f, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprite.translate(5f, 0);
        }
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
