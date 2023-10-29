package com.neonnebula.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class PlayerShip extends Entity {

    private final Array<Bullet> activeBullets = new Array<Bullet>();

    // bullet pool.
    private final Pool<Bullet> bulletPool;

    private float shipSpeed = 200f;
    private Sound laser;

    float shotCoolDown = .15f; // Shooting - Cool down - time in seconds
    float timeSinceLastShot = 0.0f; // Time elapsed since the last shot
    boolean canShoot = true; // shooting is allowed or not

    float angle=0, radius=8;


    public PlayerShip(final TextureRegion textureRegion) {

        super(textureRegion, 200, 200);
        bulletPool = new Pool<Bullet>() {
            @Override
            protected Bullet newObject() {
                // we reuse the same textureRegion for now
                return new Bullet(textureRegion, 0,0);
            }
        };
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
        timeSinceLastShot += delta;
        if (!canShoot && timeSinceLastShot >= shotCoolDown) {
            canShoot = true;
        }

        // free
        freeDeadBullets();

        // update bullets
        Bullet tempBullet;
        for (int i = activeBullets.size; --i >= 0;) {
            tempBullet = activeBullets.get(i);
            tempBullet.update(delta);
        }
        super.update(delta);
    }

    /**
    private void updateShipMovement(float delta) {
        // Calculate the new position of the sprite
        float x = sprite.getX() + radius * MathUtils.cosDeg(angle);
        float y = sprite.getY() + radius * MathUtils.sinDeg(angle);

        // Update the sprite's position
        sprite.setPosition(x, y);
        shadow.setPosition(sprite.getX() + 10, sprite.getY() - 10);

        // Increment the angle to make the sprite move along the circular path
        angle += shipSpeed * delta;

    } **/

    private void handleInput(float delta) {
        float speed = shipSpeed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            super.translate(0, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            super.translate(0, -speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            super.translate(-speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            super.translate(speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (canShoot) {
                if (timeSinceLastShot >= shotCoolDown) {
                    spawnBullet();
                }
            }
        }
    }

    private void spawnBullet() {
        laser.play(1f, generateRandomPitchValue(), 0f);
        timeSinceLastShot = 0.0f;
        canShoot = false;

        Bullet bullet = bulletPool.obtain();
        bullet.setPosition(getOriginX(), getOriginY());
        bullet.setAlive(true);
        activeBullets.add(bullet);
    }

    public void render(SpriteBatch batch) {
        renderBullets(batch);
        super.render(batch);
    }

    public void renderShadow(SpriteBatch batch) {
        renderBulletsShadows(batch);
        super.renderShadow(batch);
    }

    public void freeDeadBullets () {
        Bullet bullet;
        int len = activeBullets.size;
        for (int i = len; --i >= 0;) {
            bullet = activeBullets.get(i);
            if (bullet.isAlive() == false) {
                activeBullets.removeIndex(i);
                bulletPool.free(bullet);
                // System.out.println("Removed bullet from pool: " + activeBullets.size);
            }
        }
    }

    public void renderBullets(SpriteBatch batch) {
        for (int i = activeBullets.size; --i >= 0;) {
            Bullet tempBullet = activeBullets.get(i);
            tempBullet.render(batch);
        }
    }

    public void renderBulletsShadows(SpriteBatch batch) {
        for (int i = activeBullets.size; --i >= 0;) {
            activeBullets.get(i).renderShadow(batch);
        }
    }

    public void dispose() {
        super.dispose();
        laser.dispose();
    }
}
