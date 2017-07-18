package com.pesna.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.abstracts.SpellObject;
import sun.security.krb5.internal.crypto.Des;

import javax.crypto.spec.DESKeySpec;
import javax.jws.soap.SOAPBinding;

/**
 * Created by Gagiu on 12/7/2016.
 */
public class Rush implements SpellObject
{
   Main reference;
   private Animation rushAnimation;
   private boolean destroyed;
   private EnemyStructure thisAI;
   private float TotalAdded;
   float cd = 0.8f;

   public Rush(Main _reference , EnemyStructure AI)
   {
      reference = _reference;
      thisAI = AI;
      rushAnimation = reference.gameRegistry.animationManager.walk;
   }

   @Override
   public void draw(Main _reference)
   {
      thisAI.SetAnimation(rushAnimation);
     // thisAI.draw(_reference);
   }

   @Override
   public void update(Main _reference)
   {
      //thisAI.update(_reference);
      if(!Destroy() && !destroyed)
      {
         thisAI.SetBuff("Speed" , 150 * Gdx.graphics.getDeltaTime());
         TotalAdded += 150 *Gdx.graphics.getDeltaTime();

         if(IS_CLOSE())
         {
            thisAI.SetBuff("Speed", -TotalAdded);

         }
      }
      if(Destroy())
      {

         thisAI.SetBuff("Speed" , -TotalAdded);
         destroyed = true;
      }
      MoveTo();
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
      return cd;
   }

   @Override
   public void MoveTo()
   {
      thisAI.Follow(reference.player);
   }

   @Override
   public int type()
   {
      return 0;
   }

   @Override
   public boolean Destroy()
   {
      return IS_CLOSE();
   }
   private boolean IS_LEFT()
   {
      return (reference.player.x - thisAI.x) < 0;
   }
   private boolean IS_RIGHT()
   {
      return (reference.player.x - thisAI.x) > 0;
   }
   private boolean IS_CLOSE()
   {
      return Math.abs((reference.player.x - thisAI.GetPosition(1))) < 35;
   }
}
