package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;

/**
 * Created by Gagiu on 3/25/2017.
 */
public class Vars
{
  private final Main main;

    public Vars(Main _ref)
    {
      main = _ref;
    }

    public float platformX = 0 , platformWidth = 2000 , platformHeight = 100;
    public float backgroundY = platformHeight;
    public float SmallDodasHeight = 100 , SmallDodasWidth = 80 , BigDodasHeight = 800 , BigDoodasWidth = 200;
    public float SmallDodasX , BigDoodasX;
    public float spawnerY = backgroundY;
    public String info1 = " Press 1 to add platform \n Press 2 to add background \n Press 3 to add spawner \n Press 4 to add level boss \n Press 5 to add level info \n";

    public Vector2 mousePositionInWorld()
    {
      Vector2 aux = new Vector2();
      Vector3 getMousePosInGameWorld;
      getMousePosInGameWorld = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY() , 0));
      aux.x = getMousePosInGameWorld.x;
      aux.y = getMousePosInGameWorld.y;
      return aux;
    }

}
