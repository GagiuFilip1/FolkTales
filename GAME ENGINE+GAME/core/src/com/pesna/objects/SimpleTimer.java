package com.pesna.objects;

import com.badlogic.gdx.Gdx;

/**
 * Created by Gagiu on 1/4/2017.
 */
public class SimpleTimer
{
   private float counter = 0.0f , time;
   public SimpleTimer(float _time)
   {
      time = _time;
   }
   public boolean TimeElapsed()
   {
      counter += Gdx.graphics.getDeltaTime();
      if(counter >= time)
      {
         counter = 0;
         return true;
      }
      return false;
   }
   public void setNewTime(float _time)
   {
      time = _time;
   }
}
