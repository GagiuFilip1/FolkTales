package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 * Created by Gagiu on 4/25/2017.
 */
public class KeyboardInput
{
  private boolean upper, shift;

  public KeyboardInput() {
    shift = false;
    upper = false;
  }

  public String getChar() {
    shift = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);
    if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK))
      upper = !upper;
    String typed = "";

    if (Gdx.input.isKeyJustPressed(Keys.A)) {
      typed = "a";
    } else if (Gdx.input.isKeyJustPressed(Keys.A)) {
      typed = "a";
    } else if (Gdx.input.isKeyJustPressed(Keys.B)) {
      typed = "b";
    } else if (Gdx.input.isKeyJustPressed(Keys.C)) {
      typed = "c";
    } else if (Gdx.input.isKeyJustPressed(Keys.D)) {
      typed = "d";
    } else if (Gdx.input.isKeyJustPressed(Keys.E)) {
      typed = "e";
    } else if (Gdx.input.isKeyJustPressed(Keys.F)) {
      typed = "f";
    } else if (Gdx.input.isKeyJustPressed(Keys.G)) {
      typed = "g";
    } else if (Gdx.input.isKeyJustPressed(Keys.H)) {
      typed = "h";
    } else if (Gdx.input.isKeyJustPressed(Keys.I)) {
      typed = "i";
    } else if (Gdx.input.isKeyJustPressed(Keys.J)) {
      typed = "j";
    } else if (Gdx.input.isKeyJustPressed(Keys.K)) {
      typed = "k";
    } else if (Gdx.input.isKeyJustPressed(Keys.L)) {
      typed = "l";
    } else if (Gdx.input.isKeyJustPressed(Keys.M)) {
      typed = "m";
    } else if (Gdx.input.isKeyJustPressed(Keys.N)) {
      typed = "n";
    } else if (Gdx.input.isKeyJustPressed(Keys.O)) {
      typed = "o";
    } else if (Gdx.input.isKeyJustPressed(Keys.P)) {
      typed = "p";
    } else if (Gdx.input.isKeyJustPressed(Keys.Q)) {
      typed = "q";
    } else if (Gdx.input.isKeyJustPressed(Keys.R)) {
      typed = "r";
    } else if (Gdx.input.isKeyJustPressed(Keys.S)) {
      typed = "s";
    } else if (Gdx.input.isKeyJustPressed(Keys.T)) {
      typed = "t";
    } else if (Gdx.input.isKeyJustPressed(Keys.V)) {
      typed = "v";
    } else if (Gdx.input.isKeyJustPressed(Keys.U)) {
      typed = "u";
    } else if (Gdx.input.isKeyJustPressed(Keys.W)) {
      typed = "w";
    } else if (Gdx.input.isKeyJustPressed(Keys.Q)) {
      typed = "q";
    } else if (Gdx.input.isKeyJustPressed(Keys.Y)) {
      typed = "y";
    } else if (Gdx.input.isKeyJustPressed(Keys.Z)) {
      typed = "z";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
      if (!shift)
        typed = "1";
      else
        typed = "!";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
      if (!shift)
        typed = "2";
      else
        typed = "@";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
      if (!shift)
        typed = "3";
      else
        typed = "#";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
      if (!shift)
        typed = "4";
      else
        typed = "$";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
      if (!shift)
        typed = "5";
      else
        typed = "%";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_6)) {
      if (!shift)
        typed = "6";
      else
        typed = "^";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_7)) {
      if (!shift)
        typed = "7";
      else
        typed = "&";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_8)) {
      if (!shift)
        typed = "8";
      else
        typed = "&";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_9)) {
      if (!shift)
        typed = "9";
      else
        typed = "(";
    } else if (Gdx.input.isKeyJustPressed(Keys.NUM_0)) {
      if (!shift)
        typed = "0";
      else
        typed = ")";
    } else if (Gdx.input.isKeyJustPressed(Keys.COMMA)) {
      typed = ",";
    } else if (Gdx.input.isKeyJustPressed(Keys.PERIOD)) {
      typed = ".";
    } else if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      typed = " ";
    } else if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
      typed = "\n";
    }
    if (upper) {
      try {
        typed = typed.toUpperCase();
      } catch (Exception ignored) {
      }
    }
    return typed;
  }
}
