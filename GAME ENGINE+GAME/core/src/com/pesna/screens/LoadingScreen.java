package com.pesna.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.pesna.Main;
import com.pesna.entities.EnemyObject;
import com.pesna.objects.ScreenObject;
import com.pesna.objects.SimpleLabel;

import java.io.IOException;
import java.util.LinkedList;

public class LoadingScreen implements IScreen {
	
	private final int lheight = 10; // 10 pixels for the line height
	private final float lborder = 0.1f; // the border will take 0.1f of the screen's width
	private SimpleLabel infoLabel;

	public void update( Main _reference )
	{
		if ( _reference.assetManager.update() )//If loading finishes
		{
			onLoaded( _reference );
		}

	}

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

	public void draw( Main _reference )
	{
		if(infoLabel == null)
		{
			infoLabel = new SimpleLabel(_reference);
			infoLabel.SetColor(Color.YELLOW);
			infoLabel.SetSize(2.0f);
		}
		_reference.batch.setProjectionMatrix(_reference.camera.combined);
		_reference.shapeRenderer.setProjectionMatrix(_reference.camera.combined);
		
		float progress = _reference.assetManager.getProgress();
		int gwidth = Gdx.graphics.getWidth();
		int gheight = Gdx.graphics.getHeight();

		infoLabel.SetPosition((lborder*gwidth) + 600, (gheight-lheight)/2 - 60);

		_reference.shapeRenderer.begin(ShapeType.Filled);
		_reference.shapeRenderer.setColor(35/255f, 200/255f, 0f, 1f);
		_reference.shapeRenderer.rect( (lborder*gwidth) + 100, (gheight-lheight)/2, progress*gwidth*(1f-2f*lborder), lheight );
		//System.out.println(progress);
		_reference.shapeRenderer.end();
		if(progress < 0.9f)
		{
			infoLabel.Draw("LOADING MAP");
		}
		else
		{
			infoLabel.Draw("LOADING ENEMIES");
		}
	}
	
	/**
	 * Describes what should be done upon loading everything
	 * @param _reference Main Class Reference
	 */
	private void onLoaded( Main _reference )
	{
		try {
			_reference.gameRegistry.onAssetsLoaded( _reference );
		} catch (IOException e) {
			e.printStackTrace();
		}
		//move to the next screen
		_reference.screenManager.queueScreen( _reference.screenManager.menuScreen );
	}
}
