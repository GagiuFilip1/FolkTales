package com.pesna.screens;

import com.pesna.Main;
import com.pesna.entities.Bear;
import com.pesna.entities.EnemyBot;
import com.pesna.objects.ScreenObject;

import java.util.LinkedList;

public interface IScreen {
    /**
     * Called in the screen manager ( via main loop ) for rendering.
     */
	 void draw( Main _reference );
	
    /**
     * Called in the screen manager ( via main loop ) for update.
     */
	 void update( Main _reference );

     void SpellForceAdd(ScreenObject newObject);

      void ObjectForceAdd(ScreenObject newObject);

      LinkedList<Bear> GetLevelEnemy();
}
