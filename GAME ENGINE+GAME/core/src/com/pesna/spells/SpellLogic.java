package com.pesna.spells;

import com.badlogic.gdx.Gdx;
import com.pesna.Main;
import com.pesna.abstracts.SpellObject;

/**
 * Created by Filip on 12/3/2016.
 */
public class SpellLogic
{
    private Main reference;
    private float counter = 0;
    public SpellLogic(Main _reference)
    {
        reference = _reference;
    }

    private void LoadSpell(SpellObject spell)
    {
        reference.screenManager.gameScreen.SpellForceAdd(spell);
    }
    public void CastSpell(SpellObject spell)
    {
        if(Timer(spell.GetCooldown()))
        {
            LoadSpell(spell);
            spell.draw(reference);
            spell.update(reference);
        }
    }
    private boolean Timer(float EndTime)
    {
        counter += Gdx.graphics.getDeltaTime();
        if(counter >= EndTime)
        {
            counter =0;
            return true;
        }
        else
        {
            return false;
        }
    }
}
