package com.pesna.spells;

import com.pesna.Main;
import com.pesna.abstracts.SpellObject;
import com.pesna.player.Player;

/**
 * Created by Gagiu on 12/29/2016.
 */
public class ShieldUp implements SpellObject
{
   private Main reference;
   private Player player;
   public ShieldUp(Main _reference)
   {
      reference = _reference;
      player = reference.player;
   }

   @Override
   public void draw(Main _reference)
   {

   }

   @Override
   public void update(Main _reference)
   {

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
      return false;
   }
}
