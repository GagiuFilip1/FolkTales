package com.pesna.abstracts;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.pesna.objects.ScreenObject;
import com.pesna.player.Player;

/**
 * Created by Filip on 11/23/2016.
 */
public abstract class EnemyStructure implements ScreenObject
{
	public float x,y;
    public abstract int GetPosition(int x);
    public abstract void Attack(Player player);
    public abstract void Follow(Player player);
    public abstract void Run(Player player);
    public abstract void fall(Player player);
    public abstract void SetAnimation(Animation animation);
    public abstract void SetBuff(String statName , float AddValue);
    public abstract float GetAtkSpeed();
    public abstract boolean IS_Dead();
}
