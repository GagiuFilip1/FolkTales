package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * Created by Gagiu on 4/25/2017.
 */
public class TextBox extends screenObject {

  private final Main ref;
  private final KeyboardInput keyboardInput;

  private BitmapFont writer;
  private Vector2 position;
  private float width , height;

  public boolean ShouldDraw = true;
  public String textData = "";

  public TextBox(Main _ref) {
    ref = _ref;
    keyboardInput = new KeyboardInput();
    writer = new BitmapFont();
    writer.setColor(Color.BLACK);
    position = new Vector2();
  }


  @Override
  public void Render() {
    if(ShouldDraw) {
      ref.shapeRenderer.begin(ShapeType.Filled);
      ref.shapeRenderer.setColor(Color.WHITE);
      ref.shapeRenderer.rect(position.x, position.y, width, height);
      ref.shapeRenderer.end();
      ref.batch.begin();
      writer.draw(ref.batch, textData, position.x + 10, position.y + height/2);
      ref.batch.end();
    }
  }

  @Override
  public void Update() {
    if(ShouldDraw) {
      if (isClicked) {
          if (textData.length() < CHAR_LIMIT) {
              addText();
          }
          if (Gdx.input.isButtonPressed(Buttons.LEFT))
              isClicked = false;
      }
      CheckClick();
    }
  }

  @Override
  public void Destroy() {

  }

  public void SetPosition(float x , float y)
  {
   position.set(x ,y);
  }

  private void addBox() {
    /*
    to draw box need sprite
    */
  }

  private void addText() {
    textData += keyboardInput.getChar();
    if (Gdx.input.isKeyPressed(Keys.BACKSPACE) && textData.length() >= 0)
      textData = textData.substring(0, textData.length() - 1);

  }


  private int CHAR_LIMIT;
  public void SetCharLimit(int x)
  {
    CHAR_LIMIT =x;
  }

  public boolean isClicked;
  public void CheckClick()
  {
    Vector3 getMousePosInGameWorld;
    getMousePosInGameWorld = ref.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    float ix = getMousePosInGameWorld.x, iy = getMousePosInGameWorld.y;
    if (ix > position.x && ix < position.x + width) {
      if (iy > position.y && iy < position.y + height) {
        if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
          isClicked = true;
        }
      }
    }
  }

  public void SetBounds(float _wid , float _hei)
  {
    width = _wid;
    height = _hei;
  }

}
