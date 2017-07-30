package com.pesna.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.brashmonkey.spriter.Player;
import com.pesna.Main;
import com.pesna.entities.Bear;
import com.pesna.objects.ScreenObject;
import com.pesna.screens.GameScreen;

/**
 * Created by Gagiu Filip on 7/29/2017.
 */
public class PilarOfFlames implements ScreenObject
{

    private final Main reference;
    private Player player;
    private Vector2 position = new Vector2();
    private boolean stopDraw;
    private float startPos;

    public PilarOfFlames(Main ref, Bear caster)
    {
        reference = ref;
        player = new Player(ref.gameRegistry.fireballData.getEntity(1)).setScale(0.8f);
        Init(caster);
        startPos = position.x;
    }

    private int counter, getCounter = 3;
    @Override
    public void draw(Main _reference) {
        if(!stopDraw) {
            player.setPosition(position.x,position.y);
            _reference.batch.begin();
            if(counter >= getCounter) {
                player.update();
                counter = 0;
            }
            else counter++;
            _reference.fireballDrawer.draw(player);
            _reference.batch.end();
        }
    }

    @Override
    public void update(Main _reference) {
        if(!stopDraw) {
            Move();
            Destroy();
        }
    }

    private void Init(Bear caster)
    {
            position.set(caster.x,caster.y - 20);
    }

    private void Move()
    {
        if(startPos  < reference.player.x)
        position.x += Gdx.graphics.getDeltaTime() * 500;
        else  position.x -= Gdx.graphics.getDeltaTime() * 500;
        DealDamange();
    }

    private void Destroy()
    {
        if(Math.abs(position.x - startPos) >= 900)
        {
            player.setScale(2f);
            getCounter = 4;
            if(Math.abs(position.x - startPos) >= 1100) {
                stopDraw = true;
                ((GameScreen) (reference.screenManager.gameScreen)).RemoveObj(this);
            }
        }
    }

    private boolean hitedPlayer = false;
    private void DealDamange()
    {
        if(reference.player.y - position.y > 100)
        {
           if(position.x < reference.player.x + 80 && position.x > reference.player.y - 80)
           {
               if(!hitedPlayer)
               reference.player.TakeDamage(20);
               hitedPlayer =true;
           }
        }
    }
}
