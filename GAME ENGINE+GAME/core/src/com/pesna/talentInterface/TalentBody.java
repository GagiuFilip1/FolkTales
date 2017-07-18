package com.pesna.talentInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pesna.Main;
import com.pesna.objects.ScreenObject;
import com.pesna.objects.SimpleButton;
import com.pesna.objects.SimpleTextBox;


/**
 * Created by Gagiu on 1/3/2017.
 */
public class TalentBody implements ScreenObject
{

   private Main reference;
   private TalentData thisTalentData;
   private ShapeRenderer shapeRenderer;
   public Rectangle bounds;
   private Color color;
   public SimpleButton button;
   private Texture texture;
   public boolean SELECTED = false;
   private int X ,X1 , Y, Y1;
   private SimpleTextBox infoBox;

   public TalentBody(Main _reference , int x , int y , int id,String path)
   {
      texture = new Texture(Gdx.files.internal(path));
      reference = _reference;
      shapeRenderer = reference.shapeRenderer;
      color = new Color(Color.rgb888(203,125,0));
      X1 = x;
      Y1 = y;
      bounds = new Rectangle(0,0,50,50);
      button = new SimpleButton(reference,texture,X1, Y1,50,50);
      thisTalentData = new TalentData(_reference,id);
      infoBox = new SimpleTextBox(reference,170,150);

   }

   @Override
   public void draw(Main _reference)
   {

      if(reference.player.talentsTree.StartDraw)
      {
         X = X1;
         Y = Y1;
         bounds.setPosition(X ,Y);
         button.SetPosition(X,Y);
         if(button.IsHovered()) {
            infoBox.Write(thisTalentData.GetDescription());
            infoBox.DrawToMouse();
            infoBox.SetColor(Color.BROWN);
            infoBox.draw(_reference);
            infoBox.update(_reference);
         }
      }
   }

   @Override
   public void update(Main _reference) {
      if(_reference.player.SkillPoints > 0) {
         if (button.IsClicked()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
               SELECTED = true;
               thisTalentData.useTalent();
               _reference.player.SkillPoints--;
            }
         }
      }
   }


   public void SetPosition(int aux1 , int aux2)
   {
      X1 = aux1;
      Y1 = aux2;
   }

}
