package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;

/**
 * Created by Gagiu on 4/21/2017.
 */
public class Infobox extends screenObject
{

  private final Main referrence;
  private BitmapFont writer;
  private String toWrite = "";
  private Texture texture;
  private Vector2 position;

  private final float width  = 320 , height = 400;

  public Infobox(Main _reference)
  {
    referrence = _reference;
    writer = new BitmapFont();
    writer.setColor(Color.FIREBRICK);
    //writer.setColor(Color.BLACK);
    position = new Vector2();
    texture = new Texture(Gdx.files.internal("core/caset2.png"));
  }

  public void putText(String S)
  {
    toWrite = S;
  }

  @Override
  public void Render()
  {
    referrence.batch.begin();
    referrence.batch.draw(texture , position.x , position.y , width,height);
    writer.draw(referrence.batch , toWrite , 1020 , 700);
    referrence.batch.end();
  }

  @Override
  public void Update()
  {

  }

  @Override
  public void Destroy()
  {

  }

  public void SetPosition(float x , float y)
  {
    position.set(x,y);
  }

}
