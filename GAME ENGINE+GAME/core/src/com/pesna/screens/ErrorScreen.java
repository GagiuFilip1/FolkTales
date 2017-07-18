package com.pesna.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.pesna.Main;
import com.pesna.entities.EnemyObject;
import com.pesna.objects.ScreenObject;
import java.util.LinkedList;

public class ErrorScreen implements IScreen {

	
	public void draw( Main _reference )
	{
		_reference.batch.setProjectionMatrix(_reference.camera.combined);
		_reference.shapeRenderer.setProjectionMatrix(_reference.camera.combined);
		
		int gwidth = Gdx.graphics.getWidth();
		int gheight = Gdx.graphics.getHeight();

		_reference.shapeRenderer.begin( ShapeType.Filled );
		_reference.shapeRenderer.setColor( 1, 0, 0, 1 );
		_reference.shapeRenderer.circle( gwidth/2, gheight/2, 50, 30 );
		_reference.shapeRenderer.setColor( 0.5f, 0.5f, 0.5f , 1 );
		_reference.shapeRenderer.circle( gwidth/2, gheight/2-30, 8 );
		_reference.shapeRenderer.ellipse( gwidth/2-8, gheight/2-15, 16, 50 );
		_reference.shapeRenderer.end();
	}
	
    /**
     * Called in the screen manager ( via main loop ) for update.
     */
	public void update( Main _reference ){}

	@Override
	public void SpellForceAdd(ScreenObject newObject) {

	}

	@Override
	public void ObjectForceAdd(ScreenObject newObject) {

	}

	@Override
	public LinkedList<EnemyObject> GetLevelEnemy() {
		return null;
	}
}
