package com.pesna.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.pesna.Main;

/**
 * Created by Gagiu on 1/4/2017.
 */
public class SimpleTextBox implements ScreenObject {

   private String TEXT = "", toWrite = "";
   private BitmapFont font;
   private ShapeRenderer boxRenderer;
   private Vector3 getMousePosInGameWorld;
   private Color color;
   private Texture texture;
   public boolean DRAW = true;
   Main reference;

   private float x = 0, y = 0,width,height;

   public SimpleTextBox(Main _reference,float _width , float _height) {
      width = _width;
      height = _height;
      font = new BitmapFont();
      getMousePosInGameWorld = new Vector3();
      boxRenderer = _reference.shapeRenderer;
      reference = _reference;
   }

   @Override
   public void draw(Main _reference) {
      if(DRAW) {
         if(color != null) {
            boxRenderer.begin(ShapeType.Filled);
            boxRenderer.setColor(color);
            boxRenderer.rect(x, y, width, height);
            boxRenderer.end();
         }
         if (texture != null) {
            _reference.batch.begin();
            _reference.batch.draw(texture, x, y, width, height);
            _reference.batch.end();

         }
         _reference.batch.begin();
         font.draw(_reference.batch, toWrite, x + 10, y + height - 5);
         _reference.batch.end();
      }
   }

   @Override
   public void update(Main _reference) {
      toWrite = TEXT;
   }

   public void Write(String string) {
      TEXT = string;
   }

   public void Clear()
   {
      TEXT = "";
   }

   public void SetPosition(int _x, int _y) {
      x = _x;
      y = _y;
   }

   public void DrawToMouse() {
      getMousePosInGameWorld = reference.camera
          .unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
      x = getMousePosInGameWorld.x +50;
      y = getMousePosInGameWorld.y -50;
   }
   public void SetColor(Color _color)
   {
      color = _color;
   }
   public void Destroy()
   {
      Clear();
      DRAW = false;
   }
   public void SetTexture(Texture _texture)
   {
         texture = _texture;
   }
   public void SetTextColor(Color color)
   {
      font.setColor(color);
   }
}
