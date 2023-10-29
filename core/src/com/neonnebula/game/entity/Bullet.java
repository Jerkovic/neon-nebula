package com.neonnebula.game.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class Bullet extends Entity implements Pool.Poolable {

    private float bulletSpeed = 800f;
    private boolean alive = false;
    public Bullet(TextureRegion textureRegion, float x, float y) {

        super(textureRegion, x, y);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void update(float delta) {
        if (!alive) return;

        if (getOriginY() > 1000) {
            alive = false;
        }
        float speed = bulletSpeed * delta;
        super.translate(0, speed);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void reset() {
        alive = false;
    }
}
