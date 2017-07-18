package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;

/**
 * Created by Gagiu on 3/24/2017.
 */
public class Square extends screenObject
{

  private Main reference;
  public Texture texture;
  public float x = 500 , y = 0 , width = 300 ,height = 300 , point = 0;

  public Square(Main _reference)
  {
    reference = _reference;
    texture = null;
  }

  @Override
  public void Render()
  {


    if (texture == null) {
      System.out.println("null");
    }
    if(texture != null)
    {
      reference.batch.begin();
      reference.batch.draw(texture,x,y,width,height);
      reference.batch.end();
    }
  }

  @Override
  public void Update()
  {

  }

  @Override
  public void Destroy()
  {

  }

  private void movement()
  {
    if(x > point)
    {
      x-= Gdx.graphics.getDeltaTime() * 50;
    }
    if (x  <  point)
    {
      x+= Gdx.graphics.getDeltaTime() * 50;
    }
  }

  public void SetTexture(Texture _texture)
  {
    texture = _texture;
  }
}
