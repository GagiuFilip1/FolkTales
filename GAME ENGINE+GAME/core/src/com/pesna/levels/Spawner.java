package com.pesna.levels;

import com.pesna.Main;
import com.pesna.entities.EnemyObject;
import com.pesna.entities.ForestBoss;
import com.pesna.objects.ScreenObject;
import com.pesna.objects.SimpleTimer;

import java.util.ArrayList;

/**
 * Created by Gagiu on 1/18/2017.
 */
public class Spawner implements ScreenObject
{
  Main reference;
  private SimpleTimer spawnTime;
  private EnemyObject npc;
  private ForestBoss lvlBoss;
  public float x = 0 ,y = 0;
  private ArrayList<EnemyObject> mobCap = new ArrayList<>();
  private int aux = 0;
  public Spawner(Main _reference , EnemyObject _toSpawn)
  {
    spawnTime = new SimpleTimer(4.0f);
    npc = _toSpawn;
    reference = _reference;
    lvlBoss = new ForestBoss(_reference , reference.player.x + 1000 , reference.player.y);
    for(int i = 0 ;i < 60 ;i++)
    {
      mobCap.add(new EnemyObject(_reference , _reference.player.x + 1000 , _reference.player.y));
    }
  }

  @Override
  public void draw(Main _reference)
  {
    SetPosition(reference.player.x + 1000 ,0);
  }

  @Override
  public void update(Main _reference)
  {
    if(reference.player.Level <= 10) {
      if (spawnTime.TimeElapsed()
          && reference.player.animation == reference.gameRegistry.animationManager.hwalk) {
        mobCap.get(aux).x = reference.player.x + 1000;
        reference.screenManager.gameScreen.ObjectForceAdd(mobCap.get(aux));
        mobCap.remove(aux);
        aux++;
      }
    }
    else
    {
      if (spawnTime.TimeElapsed() && reference.player.animation == reference.gameRegistry.animationManager.hwalk) {
        lvlBoss = new ForestBoss(reference, (int) x, 0);
        reference.screenManager.gameScreen.ObjectForceAdd(lvlBoss);
        System.out.println("am spawnat bossu yey");
        spawnTime.setNewTime(Integer.MAX_VALUE);
      }
      }
  }

  public void SetSpawnTime(float Time)
  {
    spawnTime.setNewTime(Time);
  }

  public boolean StartSpawnMob()
  {
    return spawnTime.TimeElapsed();
  }

  public void SetPosition(int _x , int _y)
  {
    x = _x;
    y = _y;
  }
}
