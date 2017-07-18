package com.pesna.screens;

import com.pesna.Main;
import com.pesna.abstracts.SpellObject;
import com.pesna.entities.EnemyBot;
import com.pesna.entities.EnemyObject;
import com.pesna.gui.GuiObject;
import com.pesna.levels.Spawner;
import com.pesna.objects.LevelRenderer;
import com.pesna.objects.ScreenObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class GameScreen implements IScreen {
	public ArrayList<ScreenObject> objects= new ArrayList<ScreenObject>();
	public ArrayList<ScreenObject> spellObjects= new ArrayList<ScreenObject>();
	public ArrayList<GuiObject> guiObjects = new ArrayList<GuiObject>();
	public LinkedList<EnemyObject> enemyList = new LinkedList<EnemyObject>();
	public Spawner spawner;
	
	//TODO : modify the class so the ProjectionMatrix is set only twice per rendering cycle
	// once in the player's class update
	// and once in the draw() function of this class, set it to the camera.projection matrix.

	public ScreenObject levelRenderer, player;
	
	public GameScreen( Main _reference ) throws IOException {
		player = _reference.player;
		//PlayerSecond secondPlayer = _reference.playerSecond;
		levelRenderer = new LevelRenderer( _reference );

		objects.add(levelRenderer);
	  spawner = new Spawner(_reference , new EnemyObject(_reference,0,0));
	  objects.add(spawner);
		objects.add(player);
		//objects.add(secondPlayer);
		for (EnemyObject enemyObject : enemyList) {
			objects.add(enemyObject);
		}
		//objects.add(new Bear(_reference , 500,0));

		objects.add(new EnemyBot( _reference, 300, 0, 0, 0 ));

		//objects.add(levelRenderer.)
	}
	
	public void onAssetsLoaded()
	{
		((LevelRenderer)levelRenderer).onAssetsLoaded();
	}
	
	public void update( Main _reference )
	{

		//TODO : Which is the most optimized way to cycle in an ArrayList?
		try {

			for (ScreenObject object : objects) {
				object.update(_reference);
			}

		}
		catch (ConcurrentModificationException ignored)
		{

		}
		try {
			for (ScreenObject object : spellObjects) {
				object.update(_reference);
				if (((SpellObject) object).Destroy()) {
					spellObjects.remove(object);
				}
			}
		}
		catch (ConcurrentModificationException ignored)
		{

		}

		for ( GuiObject object : guiObjects )
		{
			object.update( _reference );
		}
		//leftSpawner.SetPosition(_reference.player.x + 1000 , 0);
	}
	
	public void draw( Main _reference )
	{	
		//Projection Matrixes are set in the "Player" class, in void update(), because the camera is watching it.
		
		//TODO : Which is the most optimized way to cycle in an ArrayList?
		//_reference.shapeRenderer.setProjectionMatrix(_reference.camera.combined);
		
		//TODO add a _reference.batch.begin() in here? so you don't have to begin() it for every object?
		//Leave those todo's to Tony
		
		
		//REAL WORLD OBJECTS GO IN HERE
		try {
			for (ScreenObject object : objects) {
				object.draw(_reference);
			}

			for (ScreenObject object : spellObjects) {
				object.draw(_reference);
			}
		}
		catch (ConcurrentModificationException ignored)
		{

		}
		//GUI GOES IN HERE
		_reference.shapeRenderer.setProjectionMatrix(_reference.camera.projection);
		_reference.shapeRenderer.setProjectionMatrix(_reference.camera.projection);
		for ( GuiObject object : guiObjects )
		{
			object.draw( _reference );
		}
	}
	public void SpellForceAdd(ScreenObject newObject)
	{
		spellObjects.add(newObject);
	}

	public void ObjectForceAdd(ScreenObject newObject)
	{
		objects.add(newObject);
	}

	@Override
	public LinkedList<EnemyObject> GetLevelEnemy() {
		return enemyList;
	}

}
