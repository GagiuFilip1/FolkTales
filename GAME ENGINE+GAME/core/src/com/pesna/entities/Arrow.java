package com.pesna.entities;

import com.badlogic.gdx.graphics.Texture;
import com.pesna.Main;
import com.pesna.objects.ScreenObject;

/**
 * Created by Zenibryum on 4/6/2017.
 */
public class Arrow implements ScreenObject {

    float x,y;
    float hvelocity;
    static final float gravity = 0.2f;
    private Texture arrowTexture;

    public Arrow( Main _reference, float x, float y, float hvelocity )
    {
        this.x = x;
        this.y = y;
        this.hvelocity = hvelocity;
        arrowTexture = _reference.assetManager.get( "items/arrow.png", Texture.class );
    }

    @Override
    public void draw(Main _reference) {
        _reference.batch.begin();

        _reference.batch.draw( arrowTexture, x, y );

        _reference.batch.end();
    }

    @Override
    public void update(Main _reference) {
        x += hvelocity;
        y -= gravity;
    }
}
