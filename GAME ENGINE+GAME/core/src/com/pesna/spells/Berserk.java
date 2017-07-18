package com.pesna.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.abstracts.SpellObject;
import sun.security.krb5.internal.crypto.Des;

/**
 * Created by Gagiu on 12/29/2016.
 */
public class Berserk implements SpellObject
{
   Main reference;

   private boolean FINISHED , Destroyed , USED;
   private EnemyStructure thisAI;
   private float IN_SCENE_TIME = 10.0f;
   private Animation empowerAnimation;
   private float timer = 0;

   public Berserk(Main _reference , EnemyStructure npc)
   {
      USED = false;
      thisAI = npc;
      reference = _reference;
      empowerAnimation = reference.gameRegistry.animationManager.stay;
   }
   @Override
   public void draw(Main _reference)
   {

   }
   @Override
   public void update(Main _reference)
   {
      if (!USED)
      {
         thisAI.SetBuff("Attack", 15);
         USED = true;
      }
      if(USED)
      {
         LocalTimer(IN_SCENE_TIME);
         if(FINISHED == true)
         {
            thisAI.SetBuff("Attack" , -15);
            Destroyed = true;
         }
      }
   }

   @Override
   public float getX()
   {
      return 0;
   }

   @Override
   public float getY()
   {
      return 0;
   }

   @Override
   public void SetCaster(int x)
   {

   }

   @Override
   public float GetCooldown()
   {
      return 0;
   }

   @Override
   public void MoveTo()
   {

   }

   @Override
   public int type()
   {
      return 0;
   }

   @Override
   public boolean Destroy()
   {
      return Destroyed;
   }
   private void LocalTimer(Float Point)
   {
      timer += Gdx.graphics.getDeltaTime();
      if(timer >= Point)
      {
         FINISHED = true;
      }
   }
}
