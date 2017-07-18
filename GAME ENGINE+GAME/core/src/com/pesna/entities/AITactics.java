package com.pesna.entities;

import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.spells.BuildWall;
import com.pesna.spells.Rush;
import com.pesna.spells.SpellLogic;
import com.pesna.spells.ThrowRock;
import com.sun.javafx.image.BytePixelSetter;
import com.sun.xml.internal.ws.api.config.management.policy.ManagedServiceAssertion;

/**
 * Created by Filip on 12/3/2016.
 */
public class AITactics
{
    private SpellLogic spellLogic;
    private Main reference;
    private int countthis;
    private boolean shouldRun = false;
    public AITactics(Main _reference)
    {
        reference = _reference;
        spellLogic = new SpellLogic(reference);
    }
    public void SetDefensive(int index , EnemyStructure npc)
    {
        int DISTANCE;
        DISTANCE = Math.abs(reference.player.x - npc.GetPosition(1));
        switch (index)
        {
            case 1:
                if(DISTANCE > 310)
                {
                    spellLogic.CastSpell(new ThrowRock(reference, npc.GetPosition(1)));
                    npc.SetAnimation(reference.gameRegistry.animationManager.stay);
                }
                else if(DISTANCE > 300 && DISTANCE <= 305)
                {
                    spellLogic.CastSpell(new BuildWall(reference, npc.GetPosition(1)));
                    spellLogic.CastSpell(new ThrowRock(reference, npc.GetPosition(1)));
                    npc.SetAnimation(reference.gameRegistry.animationManager.stay);
                }
                else if(DISTANCE < 200)
                {
                    npc.Attack(reference.player);
                    npc.fall(reference.player);
                    npc.Follow(reference.player);
                }
                else
                {
                    spellLogic.CastSpell(new ThrowRock(reference, npc.GetPosition(1)));
                    npc.SetAnimation(reference.gameRegistry.animationManager.stay);
                }
                break;
            case 2 :
                if(!shouldRun) {
                    if (DISTANCE > 310) {
                        spellLogic.CastSpell(new ThrowRock(reference, npc.GetPosition(1)));
                        npc.SetAnimation(reference.gameRegistry.animationManager.stay);
                    } else if (DISTANCE > 300 && DISTANCE <= 305) {
                        spellLogic.CastSpell(new BuildWall(reference, npc.GetPosition(1)));
                        shouldRun = true;
                        npc.SetAnimation(reference.gameRegistry.animationManager.stay);
                    } else if (DISTANCE < 100) {
                        npc.Attack(reference.player);
                        npc.fall(reference.player);
                        npc.Follow(reference.player);
                    } else {
                        spellLogic.CastSpell(new ThrowRock(reference, npc.GetPosition(1)));
                        npc.SetAnimation(reference.gameRegistry.animationManager.stay);
                    }
                }
                else
                {

                    if(DISTANCE < 500 && DISTANCE > 280)
                    {
                        npc.Run(reference.player);
                    }
                    if(DISTANCE <= 280 || DISTANCE >= 500)
                    {
                        shouldRun = false;
                    }
                }
                break;
        }
    }

    public void SetOffensive(int index , EnemyStructure npc)
    {
        int DISTANCE;
        DISTANCE = Math.abs(reference.player.x - npc.GetPosition(1));
        switch (index) {
            case 1:
                if(DISTANCE < 800 && DISTANCE > 80) {
                    npc.Attack(reference.player);
                    npc.fall(reference.player);
                    npc.Follow(reference.player);
                }
                else
                {
                    if(DISTANCE < 700) {
                        countthis++;
                    }
                    npc.Attack(reference.player);
                    npc.fall(reference.player);
                    npc.Follow(reference.player);
                }

        }
    }
}
