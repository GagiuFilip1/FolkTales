package com.pesna.talentInterface;

import com.pesna.Main;

/**
 * Created by Gagiu on 1/3/2017.
 */
public class TalentData
{
   private Main reference;
   private int id;
   private String description = "";
   public TalentData(Main _reference , int _id)
   {
      id = _id;
      reference = _reference;
   }

   public void useTalent()
   {
      switch (id) {
         case 1:
         reference.player.DAMAGE += 5;
             break;
         case 2:
            reference.player.SPEED += 50;
             break;
         case 3:
            reference.player.ATTACK_SPEED -= 0.5f;
             break;
         case 4:
            reference.player.doRegen = true;
             break;
         case 5:
            reference.player.doBlock = true;
            break;
         case 6:
             reference.player.doEmpower = true;
            break;
         case 7:
            reference.player.doSlow = true;
            
            break;
         case 8:
            reference.player.doUltimate = true;
            break;
      }
   }

   public String GetDescription()
   {
      switch (id)
      {
         case 1 :
         description = " + 15% Attack Damage \n " ;
            break;
         case 2:
            description = " + 40% Speed";
            break;
         case 3:
            description = " + 25% Attack Speed";
            break;
         case 4:
            description = " Regen with 2% more \n health out of combat";
            break;
         case 5:
            description = " After you are attacked  \n by a enemy , you will \n block a attack \n every 5 seconds";
            break;
         case 6:
            description = "Press Key : 1 to \n use a spell that \n will increase your power : \n +50% Attack Damage \n +50% Attack Speed \n receive +10 more damage\n from the enemy \n Cooldown : 30 s";
            break;
         case 7:
            description = "Press Key : 1 to \n use a spell that \n will slow every enemy \n that is near to you \n by 50% for 7 seconds \n Cooldown : 12 s";
            break;
         case 8:
            description = "Press Key : 2 to \n use a spell that \n will increase your damage \n by 300% for 1 hit \n Cooldown : 60 s";
            break;
      }
      return description;
   }
}
