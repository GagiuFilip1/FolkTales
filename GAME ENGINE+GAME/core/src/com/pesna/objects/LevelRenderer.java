package com.pesna.objects;

import com.pesna.Main;
import com.pesna.init.LevelManager;
import com.pesna.levels.ForestLevel;

public class LevelRenderer implements ScreenObject {
	public LevelManager levelManager;
	ParallaxLoop platformLoop,backgroundLoop, overlapPlatform;
	public ForestLevel forestLevel;
	
	public LevelRenderer( Main _reference )
	{
		//forestLevel = new ForestLevel(_reference);
		levelManager = _reference.gameRegistry.levelManager;
	}
	
	public void onAssetsLoaded()
	{
		platformLoop = new ParallaxLoop(0,-levelManager.platform.getHeight(),0f,levelManager.platform);
		backgroundLoop = new ParallaxLoop(500, -92,0.3f,levelManager.background);
		overlapPlatform = new ParallaxLoop(500, -92,-0.5f,levelManager.over);
	}
	
	public void draw( Main _reference )
	{
		_reference.batch.begin();
		platformLoop.draw(_reference.camera.position, _reference.batch);
		backgroundLoop.draw(_reference.camera.position, _reference.batch);
		overlapPlatform.draw(_reference.camera.position, _reference.batch);
		//forestLevel.draw(_reference);
		
		
		_reference.batch.end();
	}
	public void update( Main _reference )
	{
		
	}
}
