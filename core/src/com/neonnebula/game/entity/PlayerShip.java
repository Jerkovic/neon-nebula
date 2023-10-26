package com.neonnebula.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;


public class PlayerShip {

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final Sprite sprite;
    private final Sprite shadow;
    private float shipSpeed = 200f;
    private Sound laser;

    float shotCoolDown = .45f; // Shooting - Cool down time in seconds
    float timeSinceLastShot = 0.0f; // Time elapsed since the last shot
    boolean canShoot = true; // shooting is allowed or not

    float angle=0, radius=8;


    public PlayerShip(TextureRegion textureRegion) {
        sprite = new Sprite(textureRegion);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(200f, 200f);

        shadow = new Sprite(sprite);
        shadow.setColor(Color.BLACK);
        shadow.setAlpha(.3f);
    }

    public void setLaserSound(Sound laser) {
        this.laser = laser;
    }

    private float generateRandomPitchValue() {
        float minPitch = 1.0f;  // Lower bound
        float maxPitch = 1.1f;  // Upper bound
        return MathUtils.random(minPitch, maxPitch);
    }

    public void update(float delta) {
        handleInput(delta);

        updateShipMovement(delta);

        timeSinceLastShot += delta;
        if (!canShoot && timeSinceLastShot >= shotCoolDown) {
            canShoot = true;
        }
    }

    private void updateShipMovement(float delta) {
        // Calculate the new position of the sprite
        float x = sprite.getX() + radius * MathUtils.cosDeg(angle);
        float y = sprite.getY() + radius * MathUtils.sinDeg(angle);

        // Update the sprite's position
        sprite.setPosition(x, y);
        shadow.setPosition(sprite.getX() + 10, sprite.getY() - 10);

        // Increment the angle to make the sprite move along the circular path
        angle += shipSpeed * delta;

    }

    private void handleInput(float delta) {
        float speed = shipSpeed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprite.translate(0, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.translate(0, -speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprite.translate(-speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprite.translate(speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (canShoot) {
                if (timeSinceLastShot >= shotCoolDown) {
                    // Perform the shooting logic here
                    laser.play(1f, generateRandomPitchValue(), 0f);
                    timeSinceLastShot = 0.0f; // Reset timer
                    canShoot = false; // Shooting is not allowed until the cool down is over
                }
            }
        }

        shadow.setPosition(sprite.getX() + 10, sprite.getY() - 10);
    }

    public void render(SpriteBatch batch) {

        shadow.draw(batch);
        sprite.draw(batch);
    }

    public void renderDebug() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Color prevColor = shapeRenderer.getColor();
        shapeRenderer.setColor(Color.RED);

        float spriteX = sprite.getX();
        float spriteY = sprite.getY();
        float spriteWidth = sprite.getWidth();
        float spriteHeight = sprite.getHeight();

        shapeRenderer.rect(spriteX, spriteY, spriteWidth, spriteHeight);
        shapeRenderer.setColor(prevColor); // reset the color
        shapeRenderer.end();
    }

    public void dispose() {
        laser.dispose();
        shapeRenderer.dispose();
    }
}
