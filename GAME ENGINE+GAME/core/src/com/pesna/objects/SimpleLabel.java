package com.pesna.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.pesna.Main;

/**
 * Created by Gagiu on 2/14/2017.
 */
public class SimpleLabel
{
  private BitmapFont font;
  private float X ,Y;
  private String toWrite = "";
  private Main reference;
  private Color color;

  public SimpleLabel(Main _reference)
  {
    font = new BitmapFont();
    reference = _reference;
    color = new Color(Color.BLACK);
  }

  public void SetPosition(float x , float y)
  {
    X =x;
    Y =y;
  }

  public void Draw(String text)
  {
    toWrite = text;
    font.setColor(color);
    reference.batch.begin();
    font.draw(reference.batch, toWrite, X, Y);
    reference.batch.end();
  }

  public void SetColor(Color _color)
  {
    color = _color;
  }

  public void SetSize(float size)
  {
    font.getData().setScale(size);
  }
}
