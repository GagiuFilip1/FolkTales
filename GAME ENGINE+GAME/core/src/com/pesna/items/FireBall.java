package com.pesna.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.brashmonkey.spriter.Player;
import com.pesna.Main;
import com.pesna.entities.Bear;
import com.pesna.objects.ScreenObject;
import com.pesna.screens.GameScreen;

/**
 * Created by Gagiu Filip on 7/28/2017.
 */
public class FireBall implements ScreenObject
{

    private final Main ref;
    private Player player;
    public Vector2 position = new Vector2();
    private float starPos;
    public FireBall(Main _ref, Bear caster)
    {
        ref = _ref;
        player = new Player(_ref.gameRegistry.fireballData.getEntity(0)).setScale(0.6f);
        player.setAngle(90);
        Init(caster);
    }

    @Override
    public void draw(Main _reference) {
       if(!stopDraw) {
           player.setPosition(position.x,position.y);
           _reference.batch.begin();
           _reference.fireballDrawer.draw(player);
           _reference.batch.end();
       }
    }


    private int counter , getCounter = 0;
    private boolean fliped = false , setFlip = false;
    @Override
    public void update(Main _reference) {
        if(!stopDraw) {
            if(!stopMove)
            MoveTo();
            if(counter >= getCounter) {
                player.update();
                counter = 0;
            }
            else counter++;
            Explode();
        }
        if(fliped && !setFlip) {
            player.setAngle(270);
            setFlip = true;
        }

    }

    private boolean stopMove = false;
    private void MoveTo()
    {
        if(position.x < ref.player.x && starPos < ref.player.x) position.x += Gdx.graphics.getDeltaTime() * 600;
        else {
         if(starPos > ref.player.x) {
             position.x -= Gdx.graphics.getDeltaTime() * 600;
             fliped = true;
         }
        }
        if(position.x > ref.player.x && starPos <ref.player.x) position.x += Gdx.graphics.getDeltaTime() * 600;
        if(position.x < ref.player.x && starPos > ref.player.x) position.x -= Gdx.graphics.getDeltaTime() *600;
    }

    private boolean stopDraw = false, preExplode = false;
    private void Explode()
    {
        System.out.println(Math.abs(position.y - ref.player.y));
        if(position.y > 0 && Math.abs(position.y - ref.player.y)  >= 255)
        if((position.x < ref.player.x + 10 && position.x > ref.player.x - 10)|| position.x > ref.player.x + 1366 || position.x < ref.player.y - 1366)
        {

            if(!preExplode)  player = new Player(ref.gameRegistry.fireballData.getEntity(1));
            preExplode = true;
            getCounter = 3;
            player.setAngle(270);
            if(setFlip)player.setAngle(90);
        }
        if(preExplode)
            if(player.getTime() >= player.getAnimation().length /2) {
                stopDraw = true;
                ref.player.TakeDamage(10);
                ((GameScreen) (ref.screenManager.gameScreen)).RemoveObj(this);
            }
    }

    private void Init(Bear Caster)
    {
        position.set(Caster.x + 50,Caster.y + 160);
        starPos = Caster.x;
    }

    public void FORCE_DESTROY(boolean frostTouched)
    {
        if(frostTouched)
        {
            stopMove = true;
            if(!preExplode)  player = new Player(ref.gameRegistry.fireballData.getEntity(1));
            preExplode = true;
            getCounter = 3;
            player.setAngle(90);
            if(setFlip)player.setAngle(270);
            if(preExplode)
                if(player.getTime() >= player.getAnimation().length /2) {
                    stopDraw = true;
                    ((GameScreen) (ref.screenManager.gameScreen)).RemoveObj(this);
                    for(Bear b : ((GameScreen)(ref.screenManager.gameScreen)).enemyList)
                    {
                        try{
                            b.FIREBALLS.remove(this);
                        }
                        catch (Exception ignore)
                        {
                            //IGNORE FOR OTHER RUNTIMES
                        }
                    }
                }
        }
    }
}
