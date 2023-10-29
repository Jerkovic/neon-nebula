package com.neonnebula.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public abstract class Entity implements Disposable {

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final Sprite sprite;
    private final Sprite shadow;


    public Entity(TextureRegion textureRegion, float x, float y) {

        this.sprite = new Sprite(textureRegion);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(x, y);

        shadow = new Sprite(sprite);
        shadow.setColor(Color.BLACK);
        shadow.setAlpha(.3f);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public float getOriginX() {
        return sprite.getX();
    }
    public float getOriginY() {
        return sprite.getY();
    }

    public void translate(float xAmount, float yAmount) {
        sprite.translate(xAmount, yAmount);
    }

    public void update(float delta) {
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void renderShadow(SpriteBatch batch) {
        shadow.setPosition(sprite.getX() + 10, sprite.getY() - 10);
        shadow.draw(batch);
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

    @Override
    public void dispose() {
        shapeRenderer.dispose();

    }
}
