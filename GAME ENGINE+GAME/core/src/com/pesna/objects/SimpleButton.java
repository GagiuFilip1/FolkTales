package com.pesna.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pesna.Main;

/**
 * Created by Gagiu on 1/3/2017.
 */
public class SimpleButton implements ScreenObject
{

   private Sprite skin;
   private Main reference;

   public boolean isEnabled = true;

   public SimpleButton(Main _reference,Texture texture, float x, float y, float width, float height)
   {
      reference = _reference;
      skin = new Sprite(texture); // your image
      skin.setPosition(x, y);
      skin.setSize(width, height);
   }

   public boolean IsClicked () {
      if (isEnabled) {
         Vector3 getMousePosInGameWorld;
         getMousePosInGameWorld = reference.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

         float ix = getMousePosInGameWorld.x, iy = getMousePosInGameWorld.y;
         if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
               return true;
            }
         }
      }
      return false;
   }

   public boolean IsHovered()
   {
         Vector3 getMousePosInGameWorld;
         getMousePosInGameWorld = reference.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
         float ix = getMousePosInGameWorld.x, iy = getMousePosInGameWorld.y;
         if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
               return true;
            }
         }
      return false;
   }

   public void SetPosition(int X , int Y)
   {
      skin.setPosition(X,Y);
   }

   public boolean StopDraw(boolean x)
   {
      return !x;
   }

   @Override
   public void draw(Main _reference)
   {

      if(!StopDraw(reference.player.talentsTree.StartDraw)) {
         reference.batch.begin();
         skin.draw(reference.batch);
         reference.batch.end();
      }
   }

   @Override
   public void update(Main _reference)
   {

   }
}