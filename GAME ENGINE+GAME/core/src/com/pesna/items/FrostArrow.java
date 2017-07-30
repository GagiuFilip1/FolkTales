package com.pesna.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.pesna.Main;
import com.pesna.entities.Bear;
import com.pesna.objects.ScreenObject;
import com.pesna.screens.GameScreen;
import com.pesna.screens.Screen;

import java.util.LinkedList;

/**
 * Created by Gagiu Filip on 7/28/2017.
 */
public class FrostArrow implements ScreenObject
{

    private Texture arrowTexture = new Texture(Gdx.files.internal("items/frostArrow.png"));
    private Sprite sprite = new Sprite(arrowTexture);
    private final Main ref;
    Vector2 pos = new Vector2();
    public FrostArrow(Main _ref)
    {      pos.x = _ref.player.x;
        pos.y = _ref.player.y + 280;
        ref =_ref;
    }

    @Override
    public void draw(Main _reference) {

        ref.batch.begin();
        ref.batch.draw(sprite,pos.x,pos.y,90,20);
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

    private boolean destroyFrost = false;
    private void Destroy(float Destination , Main _ref)
    {
        if(pos.x >= Destination && pos.x <= Destination + 20) {
            arrowTexture.dispose();
            ((GameScreen)ref.screenManager.gameScreen).RemoveObj(this);
            DealDMG(_ref);
        }
        for(Bear b : ((GameScreen)(ref.screenManager.gameScreen)).enemyList)
        {
            for(FireBall fireBall : b.FIREBALLS)
            {
                if(Math.abs(fireBall.position.x - pos.x) < 20 && !destroyFrost)
                {
                    sprite = new Sprite(new Texture(Gdx.files.internal("items/arrow.png")));
                    if(ref.player.x - pos.x > 0)sprite.flip(true,false);
                    fireBall.FORCE_DESTROY(true);
                    arrowTexture.dispose();
                    destroyFrost = true;
                }
            }
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
