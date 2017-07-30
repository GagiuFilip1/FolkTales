package com.pesna.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.brashmonkey.spriter.Spriter;
import com.pesna.Main;
import com.pesna.entities.Bear;
import com.pesna.objects.ScreenObject;
import com.pesna.screens.GameScreen;

/**
 * Created by Gagiu Filip on 7/28/2017.
 */
public class Arrow implements ScreenObject
{

    private Texture arrowTexture = new Texture(Gdx.files.internal("items/arrow.png"));
    private Sprite sprite = new Sprite(arrowTexture);
    private final Main ref;
    Vector2 pos = new Vector2();
    public Arrow(Main _ref)
    {      pos.x = _ref.player.x;
        pos.y = _ref.player.y + 280;
        ref =_ref;
    }

    @Override
    public void draw(Main _reference) {

        ref.batch.begin();
        ref.batch.draw(sprite,pos.x,pos.y,80,10);
        ref.batch.end();
    }

    @Override
    public void update(Main _reference) {
        for(Bear b : ((GameScreen)_reference.screenManager.gameScreen).enemyList)
        {
            Move(b.x);
        }
    }
    private boolean flipOnce = false;
    private void Move(float Tox)
    {
        if(pos.x < Tox) {
            pos.x += Gdx.graphics.getDeltaTime() * 1400;
            if (ref.player.pesnaPlayer.flippedX() == -1)ref.player.pesnaPlayer.flipX();
        }
        else {
            pos.x -= Gdx.graphics.getDeltaTime() * 1400;
            if(!flipOnce)
            sprite.flip(true,false);
            flipOnce = true;
            if (ref.player.pesnaPlayer.flippedX() == 1)ref.player.pesnaPlayer.flipX();
        }
        Destroy(Tox, ref);
    }

    private void Destroy(float Destination , Main _ref)
    {
        if(pos.x >= Destination && pos.x <= Destination + 20) {
            arrowTexture.dispose();
            ((GameScreen)ref.screenManager.gameScreen).RemoveObj(this);
            DealDMG(_ref);
        }
    }

    public void DealDMG(Main _ref)
    {
        for(Bear b : ((GameScreen)_ref.screenManager.gameScreen).enemyList)
        {
            b.TakeDamage(3);
        }
    }
}
