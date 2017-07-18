package com.pesna.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pesna.Main;
import com.pesna.entities.EnemyObject;
import com.pesna.player.Player;

import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 * Created by Gagiu on 12/29/2016.
 */
public class Analyzer
{

   private Main reference;
   private Player player;
   private EnemyObject AI;
   private float Agresivity , Defensive , TO_SAVE_DATA;

   public Analyzer(Main _reference , EnemyObject thisAI)
   {
      AI = thisAI;
      reference = _reference;
      player = reference.player;
   }

   private void GetData()
   {
      if (IS_IN_RANGE())
      {
         if (NPC_IS_RIGHT())
         {
            if(Gdx.input.isKeyPressed(Input.Keys.D))
            {
               Agresivity += 0.2f;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.A))
            {
               Defensive += 0.2f;
            }
            else
            {
               Defensive += 0.1f;
            }
         }
      }
   }

   private void SetData()
   {
      TO_SAVE_DATA = (Agresivity + Defensive)/2;
   }

   private boolean IS_IN_RANGE()
   {
      return Math.abs(player.x - AI.GetPosition(1)) < 800;
   }
   private boolean NPC_IS_RIGHT()
   {
      return (player.x - AI.GetPosition(1)) < 0;
   }
}
