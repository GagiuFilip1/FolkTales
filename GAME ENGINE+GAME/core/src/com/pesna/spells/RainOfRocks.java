package com.pesna.spells;

import com.badlogic.gdx.Gdx;
import com.brashmonkey.spriter.Player;
import com.pesna.Main;
import com.pesna.objects.ScreenObject;
import com.pesna.objects.SimpleTimer;

import java.util.Random;

/**
 * Created by Gagiu Filip on 7/26/2017.
 */
public class RainOfRocks implements ScreenObject
{

    private SimpleTimer fallTimer = new SimpleTimer(0.8f);
    public SimpleTimer stopTimer = new SimpleTimer(6.0f);
    private int X = 0;
    public boolean stop =  true;
    @Override
    public void draw(Main _reference) {

    }

    @Override
    public void update(Main _reference) {
        if(X==0)X = _reference.player.x;
        if (!stop) {
            if (fallTimer.TimeElapsed()) {
                Fireball fireBall = new Fireball(_reference,0,100,false);
                fireBall.SetLoc(setX(_reference.player.x),800);
                _reference.screenManager.gameScreen.ObjectForceAdd(fireBall);
            }
            if (stopTimer.TimeElapsed()) {
                X = 0;
                stop = true;
            }
        }
    }

    private int setX(int posX)
    {
        Random r = new Random();
        int Low = posX - 200;
        int High = posX + 100;
        int Result = r.nextInt(High-Low) + Low;
        return Result;
    }

}


final class Fireball implements ScreenObject
{


    public float x, y;
    private Player player;

    private float trgX,trgY;
    private boolean tox;

    protected Fireball(Main _ref, float PosX, float PosY, boolean ToX)
    {
        y =800;
        player = new Player(_ref.gameRegistry.fireballData.getEntity(0)).setScale(0.5f);
        player.scale(0.5f);
        trgX = PosX;
        trgY= PosY;
        tox =ToX;
    }

    private int counter = 0 , seted = 0, aux =2;
    @Override
    public void draw(Main _reference) {
        player.setPosition(x,y);
        if(!destroy) {
            counter++;
            if (counter == aux) {
                player.update();
                counter = 0;
            }
            _reference.batch.begin();
            _reference.fireballDrawer.draw(player);
            _reference.batch.end();
        }
    }

    @Override
    public void update(Main _reference)
    {
        if(!destroy)
            MoveTo(trgX,trgY,tox,_reference);
    }

    private void MoveTo(float ToX, float ToY, boolean toX, Main ref)
    {

        if(y < ToY) y+=Gdx.graphics.getDeltaTime() * 800;
        else y -= Gdx.graphics.getDeltaTime() *500;

        Destroy(ToX,ToY,toX,ref);
    }

    private boolean destroy = false, predestroy = false, playerHited = false;
    private  void Destroy(float TargetX, float TargetY, boolean toX , Main ref)
    {
        if(toX) {
            if (x > TargetX && x < TargetX + 20)
                if (y < TargetY + 250 && TargetY > 0)
                    predestroy = true;
        }
        else
        if(y < TargetY) predestroy = true;
        if(y < ref.player.y + 260 && x < ref.player.x + 40 && x > ref.player.x - 40){ predestroy = true;playerHited = true;}
        if(predestroy)
        {
            if(seted == 0) {
                player = new Player(ref.gameRegistry.fireballData.getEntity(1));
                aux = 5;
                player.setScale(0.4f);
            }
            seted =1;
            if(player.getTime() >= player.getAnimation().length / 3)
            {
                if(playerHited){
                    ref.player.TakeDamage(5);
                    playerHited = false;
                }
                destroy =true;
            }
        }
    }

    private boolean set = false;
    public void SetLoc(float _x, float _y)
    {
        if(!set) {
            x = _x;
            y = _y;
        }
        set = true;
    }

}
