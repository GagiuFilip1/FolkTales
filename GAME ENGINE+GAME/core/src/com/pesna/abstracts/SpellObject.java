package com.pesna.abstracts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pesna.objects.ScreenObject;

/**
 * Created by Filip on 11/20/2016.
 */
public interface SpellObject extends ScreenObject
{
	float getX();
	float getY();
    void SetCaster(int x);
    float GetCooldown();
    void MoveTo();
    int type();
    boolean Destroy();
}
