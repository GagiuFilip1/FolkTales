package com.pesna.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pesna.Main;
import com.pesna.abstracts.SpellObject;

/**
 * Created by Filip on 12/3/2016.
 */
public class ThrowRock implements SpellObject
{
    private float x ,y , travelDistance ,TargetX = -1;
    private int casterPosX;
    private boolean blockPosition = false , stop= false;
    private Texture rockTexture;
    private Sprite rockSprite;
    private Main reference;
    private float rotate =0;
    private float Cooldown = 2.0f;

    public ThrowRock(Main _reference , int _casterPosX)
    {
        reference = _reference;
        travelDistance = 0;
        rockTexture = reference.assetManager.get("ItemsSprites/rock.png");
        rockSprite = new Sprite(rockTexture ,70,80);
        x = _casterPosX;
        casterPosX = (int)x;
        SetCaster(_casterPosX);
    }

    @Override
    public void draw(Main _reference)
    {
        if(!Destroy() && IS_IN_RANGE())
        {
            _reference.batch.begin();
            rockSprite.setPosition(x ,y);
            setRotation();
            rockSprite.draw(_reference.batch);
            _reference.batch.end();
        }
    }
    @Override
    public void update(Main _reference)
    {
        if(IS_IN_RANGE() && !stop)
        {
            MoveTo();
            Destroy();
        }

    }
    @Override
    public float getX()
    {
        return x;
    }
    @Override
    public float getY()
    {
        return y;
    }

    @Override
    public void SetCaster(int x)
    {
    casterPosX = x;
    }

    @Override
    public float GetCooldown() {
        return Cooldown;
    }

    @Override
    public void MoveTo()
    {
        if(TargetX == -1)
        {
            TargetX = reference.player.x;
        }
        if(IS_IN_RANGE())
        {
            if(IS_LEFT())
            {
                blockPosition = true;
                    x -= 400 * Gdx.graphics.getDeltaTime();
                    travelDistance += 200 * Gdx.graphics.getDeltaTime();
                    if (travelDistance < Math.abs(TargetX - x) / 2)
                    {
                        y += 200 * Gdx.graphics.getDeltaTime();
                    }
                    else
                    {
                        y -= 85 * Gdx.graphics.getDeltaTime();
                    }
                    rockSprite.setPosition(x ,y);
            }
            if(IS_RIGHT())
            {
                blockPosition = true;
                x += 400 * Gdx.graphics.getDeltaTime();
                travelDistance += 200 * Gdx.graphics.getDeltaTime();
                if(travelDistance < Math.abs(TargetX -x) /2)
                {
                    y += 200 * Gdx.graphics.getDeltaTime();
                }
                else
                {
                    y -= 85 * Gdx.graphics.getDeltaTime();
                }
                rockSprite.setPosition(x ,y);
            }
        }
    }
    @Override
    public int type() {
        return 1;
    }

    @Override
    public boolean Destroy()
    {
        if(IS_CLOSE())
        {
            reference.player.TakeDamage(20);
            stop = true;
        }
        if(y <= -20)
        {
            stop = true;
        }
        return IS_CLOSE() || y <= -20;
    }
    private boolean IS_CLOSE()
    {
        return Math.abs(reference.player.x - x) < 5 && y < 120;
    }
    private boolean IS_IN_RANGE()
    {
        return Math.abs(reference.player.x -x) < reference.player.range;
    }
    boolean aux1 = false , aux2 = false;
    private boolean IS_LEFT() {
       if(!blockPosition && ! aux2)
       {
           aux1 = reference.player.x - x < 0;
       }
        return  aux1;
    }
    private boolean IS_RIGHT()
    {
        if(!blockPosition && !aux1)
        {
            aux2 = reference.player.x - x > 0;
        }
        return  aux2;
    }
    private void setRotation()
    {
        rotate += (rockSprite.getRotation() - 40) * Gdx.graphics.getDeltaTime();
        if(Math.abs(rotate) > 10) // change the number to set the rotation power cap
        {
            rotate = -10;
        }
        rockSprite.rotate(rotate);
    }
}
