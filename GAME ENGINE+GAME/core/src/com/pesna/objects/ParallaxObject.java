package com.pesna.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ParallaxObject{
	int x,y,width,items;
	float factor;
	Texture texture;
	
	public ParallaxObject( int _x, int _y, float _factor, Texture _texture )
	{
		x = _x;
		y = _y;
		factor = _factor;
		texture = _texture;
		width = _texture.getWidth();
	}
	
	public void draw( Vector3 camerapos, SpriteBatch batch )
	{
		batch.draw( texture, x + (int)(camerapos.x*factor) + width, y );
	}
}
