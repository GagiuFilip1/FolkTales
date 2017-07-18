package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import java.util.concurrent.Callable;

/**
 * Created by Gagiu on 3/24/2017.
 */
public class SimpleButton extends screenObject
{

  Main reference;

  public int ID = Integer.MAX_VALUE;

  private Texture texture;
  private Vector2 position;
  private float x ,y , width, height;
  private Color color;
  private BitmapFont font;
  private String toWrite = "";

  private boolean isEnabled = true , StopDraw = false;

  public SimpleButton(Main _reference)
  {
    reference = _reference;
    texture = null;
    font = new BitmapFont();
    position = new Vector2();
  }

  @Override
  public void Render()
  {

    if(!StopDraw)
    {
      reference.shapeRenderer.begin(ShapeType.Filled);
      reference.shapeRenderer.rect(x ,y , width , height);
      if(color != null)
      reference.shapeRenderer.setColor(color);
      reference.shapeRenderer.end();
    }

    if(font != null) {
      reference.batch.begin();
      font.draw(reference.batch, toWrite, x + width / 2, y + height / 2);
      reference.batch.end();
    }

    if(texture != null)
    {
      reference.batch.begin();
      reference.batch.draw(texture ,x ,y);
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

  public void setPosition(float _x , float _y)
  {
      x = _x; position.x = x;
      y = _y; position.y = y;
  }

  public void setTexture(Texture _texture)
  {
    texture = _texture;
  }

  public void setColor(Color _color)
  {
    color = _color;
  }

  public void setBounds(float _width , float _height)
  {
    width = _width;
    height =_height;
  }

  public void Disable()
  {
    isEnabled = false;
  }

  public void Enable()
  {
    isEnabled = true;
  }

  public boolean IsClicked()
  {
    if (isEnabled) {
      Vector3 getMousePosInGameWorld;
      getMousePosInGameWorld = reference.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
      float ix = getMousePosInGameWorld.x, iy = getMousePosInGameWorld.y;
      if (ix > position.x && ix < position.x + width) {
        if (iy > position.y && iy < position.y + height) {
          if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public void callFunc(Callable<Void> func)
  {
    try {
      func.call();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setID(int someID)
  {
    ID = someID;
  }

  public void addText(String text)
  {
    toWrite = text;
    font.setColor(Color.BLACK);
  }


}
