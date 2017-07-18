package com.pesna.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pesna.Main;
import com.pesna.abstracts.SpellObject;

/**
 * Created by Filip on 12/3/2016.
 */
public class BuildWall implements SpellObject
{
    private int x ,y = 0;
    private float Cooldown = 0.1f , InSceneTime = 6.0f , counter = 0;
    private boolean blockPosition = false;
    private Texture wallTexture;
    private Sprite wallSprite;
    private Main reference;

    public BuildWall(Main _reference , int casterPosX)
    {
        reference = _reference;
        x = casterPosX;
        wallTexture = reference.assetManager.get("ItemsSprites/wall.png");
        wallSprite = new Sprite(wallTexture , 50,100);
        if (IS_ONLEFT()) {
           x = reference.player.x +40;
        }
        if(IS_ONRIGHT()) {
            x = reference.player.x -40;
        }
        wallSprite.setPosition(x,y);
    }

    @Override
    public void draw(Main _reference)
    {
        _reference.batch.begin();
        wallSprite.draw(_reference.batch);
        if(Cooldown == 0)
        {
            Cooldown = 10.0f;
        }
        _reference.batch.end();
    }

    @Override
    public void update(Main _reference)
    {
        CheckColision();
        Destroy();
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

    }

    @Override
    public float GetCooldown()
    {
        return Cooldown;
    }

    @Override
    public void MoveTo()
    {

    }

    @Override
    public int type()
    {
        return 0;
    }

    @Override
    public boolean Destroy()
    {
        return Timer(InSceneTime);
    }
    private boolean Timer(float EndTime)
    {
        counter += Gdx.graphics.getDeltaTime();
        return counter > EndTime;
    }
    private void CheckColision()
    {
        if(IS_CLOSE())
        {
            if(reference.player.y < 40) {
                if (IS_ONLEFT())
                    reference.player.x -= reference.player.SPEED * Gdx.graphics.getDeltaTime();
                if (IS_ONRIGHT())
                    reference.player.x += (reference.player.SPEED + 5) * Gdx.graphics.getDeltaTime();
            }
        }
    }
    private boolean IS_CLOSE()
    {
        return Math.abs(reference.player.x - x) < 30;
    }
    private boolean IS_ONLEFT()
    {
        return reference.player.x - x < 0;
    }
    private boolean IS_ONRIGHT()
    {
        return reference.player.x - x > 0;
    }
}
