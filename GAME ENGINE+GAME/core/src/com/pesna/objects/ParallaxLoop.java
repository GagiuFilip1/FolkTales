package com.pesna.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ParallaxLoop{
	int x,y,width,items;
	float factor;
	Texture texture;
	
	public ParallaxLoop( int _x, int _y, float _factor, Texture _texture )
	{
		x = _x;
		y = _y;
		factor = _factor;
		texture = _texture;
		width = _texture.getWidth();
		
		//TODO ... modify things upon resize
		items = 1920 / width + 1;
		if (1920%width > 0 )
			items++;
	}
	
	public void draw( Vector3 camerapos, SpriteBatch batch )
	{
		if ( x + (int)(camerapos.x*factor) + width < camerapos.x-Gdx.graphics.getWidth()/2 )
			x += width;
		if ( x + (int)(camerapos.x*factor) > camerapos.x-Gdx.graphics.getWidth()/2 )
			x -= width;
		
		for ( int i = 0; i < items; i++ )
		{
			batch.draw( texture, x + (int)(camerapos.x*factor) + width*i, y );
		}
	}
}
