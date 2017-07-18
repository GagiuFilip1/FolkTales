package com.pesna.talentInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pesna.Main;
import com.pesna.levels.Spawner;
import com.pesna.objects.ScreenObject;
import com.pesna.objects.SimpleButton;
import com.pesna.player.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gagiu on 1/3/2017.
 */
public class TalentsTree implements ScreenObject
{

   private Main reference;
   private Player player;
   private Color color;
   private ShapeRenderer shapeRenderer ;
   private TalentBody PrimalAgresive , UltimateAgresive , FirstAgresiveLine1 , FirstAgresiveLine2 , SecondAgresiveLine1 , SecondAgresiveLine2 , LastAgresiveLine1 ,LastAgresiveLine2;
   public Rectangle mouseBounds;

   private Texture texture;
   private SimpleButton ExitButton;

   private boolean AllTalentsAdded = false;
   private int RectPositionX , RectPositionY;
   public boolean IS_Added , StartDraw;



   public TalentsTree(int id , Main _reference)
   {
      IS_Added = false;StartDraw = false;
      reference = _reference;
      player = reference.player;
      color = new Color(Color.BROWN);
      shapeRenderer = reference.shapeRenderer;
      texture =new Texture(Gdx.files.internal("ItemsSprites/talentsBack.png"));
      ExitButton = new SimpleButton(reference , new Texture(Gdx.files.internal("ItemsSprites/transparent.png")),reference.camera.position.y + 260 , RectPositionX = (int) reference.camera.position.x - 500,16,16 );
      mouseBounds = new Rectangle(0,0,5,5);
   }

   @Override
   public void draw(Main _reference)
   {
      addTalents();
      if(StartDraw)
      {
         shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         shapeRenderer.setColor(color);
         shapeRenderer.rect(reference.camera.position.x - 500, reference.camera.position.y - 100, 280, 420);
         shapeRenderer.end();
         reference.batch.begin();
         reference.batch.draw(texture,reference.camera.position.x - 500, reference.camera.position.y - 100, 290,470);
         reference.batch.end();
         ExitButton.SetPosition((int) reference.camera.position.x - 225, (int) reference.camera.position.y + 358);
         setTalents();
         drawLines();
      }

   }

   @Override
   public void update(Main _reference)
   {
      mouseBounds.setPosition(Gdx.input.getX() , Gdx.input.getY());
      if (ExitButton.IsClicked())
      {
         if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            StartDraw = false;
         }
      }

   }

   private void addTalents()
   {
      if (IS_Added && !AllTalentsAdded) {

         for (int i = 1; i <= 8; i++)
         {
            if (i == 1)
            {
               RectPositionY = (int) reference.camera.position.y + 250;
               RectPositionX = (int) reference.camera.position.x - 385;
               PrimalAgresive = new TalentBody(reference,RectPositionX,RectPositionY,1, "icons/1.png");
               reference.screenManager.gameScreen.ObjectForceAdd(PrimalAgresive);
               reference.screenManager.gameScreen.ObjectForceAdd(PrimalAgresive.button);
            }
            if(i == 2 || i == 3)
            {
               RectPositionY = (int) reference.camera.position.y + 180;
               if(i == 2) {
                  RectPositionX = (int) reference.camera.position.x - 305;
                  FirstAgresiveLine1 = new TalentBody(reference, RectPositionX, RectPositionY,2,"icons/2.png");
                  reference.screenManager.gameScreen.ObjectForceAdd(FirstAgresiveLine1);
                  reference.screenManager.gameScreen.ObjectForceAdd(FirstAgresiveLine1.button);
               }
                  if(i == 3) {
                     RectPositionX = (int) reference.camera.position.x - 465;
                     FirstAgresiveLine2 = new TalentBody(reference, RectPositionX, RectPositionY,3,"icons/3.png");
                     reference.screenManager.gameScreen.ObjectForceAdd(FirstAgresiveLine2);
                     reference.screenManager.gameScreen.ObjectForceAdd(FirstAgresiveLine2.button);
                  }
            }
            if(i == 3 || i == 4)
            {
               RectPositionY = (int) reference.camera.position.y + 90;
               if(i==3)
               {
                  RectPositionX = (int) reference.camera.position.x - 305;
                  SecondAgresiveLine1 = new TalentBody(reference, RectPositionX, RectPositionY,4,"icons/4.png");
                  reference.screenManager.gameScreen.ObjectForceAdd(SecondAgresiveLine1);
                  reference.screenManager.gameScreen.ObjectForceAdd(SecondAgresiveLine1.button);
               }
               if(i==4)
               {
                  RectPositionX = (int) reference.camera.position.x - 465;
                  SecondAgresiveLine2 = new TalentBody(reference, RectPositionX, RectPositionY,5,"icons/5.png");
                  reference.screenManager.gameScreen.ObjectForceAdd(SecondAgresiveLine2);
                  reference.screenManager.gameScreen.ObjectForceAdd(SecondAgresiveLine2.button);
               }
            }
            if(i==5  || i == 6)
            {
               RectPositionY = (int) reference.camera.position.y;
               if(i==5)
               {
                  RectPositionX = (int) reference.camera.position.x - 305;
                  LastAgresiveLine1 = new TalentBody(reference, RectPositionX, RectPositionY,6,"icons/6.png");
                  reference.screenManager.gameScreen.ObjectForceAdd(LastAgresiveLine1);
                  reference.screenManager.gameScreen.ObjectForceAdd(LastAgresiveLine1.button);
               }
               if(i==6)
               {
                  RectPositionX = (int) reference.camera.position.x - 465;
                  LastAgresiveLine2 = new TalentBody(reference, RectPositionX, RectPositionY,7,"icons/7.png");
                  reference.screenManager.gameScreen.ObjectForceAdd(LastAgresiveLine2);
                  reference.screenManager.gameScreen.ObjectForceAdd(LastAgresiveLine2.button);
               }
            }
            if (i == 8)
            {
               RectPositionY = (int) reference.camera.position.y - 80;
               RectPositionX = (int) reference.camera.position.x - 385;
               UltimateAgresive = new TalentBody(reference, RectPositionX,RectPositionY,8,"icons/8.png");
               reference.screenManager.gameScreen.ObjectForceAdd(UltimateAgresive);
               reference.screenManager.gameScreen.ObjectForceAdd(UltimateAgresive.button);
               reference.screenManager.gameScreen.ObjectForceAdd(ExitButton);
               AllTalentsAdded = true;
            }
         }
      }
   }
   private void setTalents()
   {
      PrimalAgresive.SetPosition((int) reference.camera.position.x - 385 , (int) reference.camera.position.y + 237);
      UltimateAgresive.SetPosition((int) reference.camera.position.x - 385, (int) reference.camera.position.y - 80);
      FirstAgresiveLine1.SetPosition((int) reference.camera.position.x - 305 ,(int) reference.camera.position.y + 180 );
      FirstAgresiveLine2.SetPosition((int) reference.camera.position.x - 465 ,(int) reference.camera.position.y + 180 );
      SecondAgresiveLine1.SetPosition((int) reference.camera.position.x - 305 ,(int) reference.camera.position.y + 90 );
      SecondAgresiveLine2.SetPosition((int) reference.camera.position.x - 465 ,(int) reference.camera.position.y + 90 );
      LastAgresiveLine1.SetPosition((int) reference.camera.position.x - 305 ,(int) reference.camera.position.y );
      LastAgresiveLine2.SetPosition((int) reference.camera.position.x - 465 ,(int) reference.camera.position.y );
   }
   float newY = 0.0f , newX = 0.0f , newX2,newX3;
   boolean stage1 , stage2 , stage3,stage4;
   List<SimpleButton> allButons = new LinkedList<SimpleButton>();
   private void drawLines()
   {
      allButons.add(PrimalAgresive.button);
      allButons.add(UltimateAgresive.button);
      allButons.add(FirstAgresiveLine1.button);
      allButons.add(FirstAgresiveLine2.button);
      allButons.add(SecondAgresiveLine1.button);
      allButons.add(SecondAgresiveLine2.button);
      allButons.add(LastAgresiveLine1.button);
      allButons.add(LastAgresiveLine2.button);

      for(SimpleButton s : allButons)
      {
         s.isEnabled = false;
      }
      PrimalAgresive.button.isEnabled = true;

      if(PrimalAgresive.SELECTED)
      {
         if(newY >=-50) {
            newY -= 25 * Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 250 , 3,newY );
        // reference.shapeRenderer.rect(reference.player.x,reference.player.y,200,200);
         reference.shapeRenderer.end();

         if(PrimalAgresive.button.isEnabled)
         {
            FirstAgresiveLine1.button.isEnabled = true;
            FirstAgresiveLine2.button.isEnabled = true;
         }
         stage1 =true;
         PrimalAgresive.button.isEnabled = false;
      }
      if(FirstAgresiveLine1.SELECTED)
      {

         if(newX <=55) {
            newX += 25 * Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 200 , newX,3 );
         reference.shapeRenderer.end();

         FirstAgresiveLine2.button.isEnabled = false;
         if(FirstAgresiveLine1.button.isEnabled)
         {
            SecondAgresiveLine1.button.isEnabled = true;
            SecondAgresiveLine2.button.isEnabled = true;
         }
         FirstAgresiveLine1.button.isEnabled = false;
         if(newX>=55)
         {
            if(!stage2)
            {
              // newY = 0;
            }
            stage2 = true;
         }
      }
      if(FirstAgresiveLine2.SELECTED)
      {
         if(newX >=-55) {
            newX -= 25 * Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 200 , newX,3 );
         reference.shapeRenderer.end();

         FirstAgresiveLine1.button.isEnabled = false;
         if(FirstAgresiveLine2.button.isEnabled)
         {
            SecondAgresiveLine1.button.isEnabled = true;
            SecondAgresiveLine2.button.isEnabled = true;
         }
         FirstAgresiveLine2.button.isEnabled = false;
         if(newX<= -55) {
            if(!stage2)
            {
             //  newY = 0;
            }
            stage2 = true;
         }
      }
      if(stage2)
      {

         if(newY >=-140)
         {
         newY -=  25*Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 250 , 3,newY );
         reference.shapeRenderer.end();
      }
      if(SecondAgresiveLine1.SELECTED)
      {
         if(newX2 <=55) {
            newX2 += 25 * Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 110 , newX2,3 );
         reference.shapeRenderer.end();

         SecondAgresiveLine2.button.isEnabled = false;
         if(SecondAgresiveLine1.button.isEnabled)
         {
            LastAgresiveLine1.button.isEnabled = true;
            LastAgresiveLine2.button.isEnabled = true;
         }
         SecondAgresiveLine1.button.isEnabled = false;
         if(newX2>=55)
         {
            if(!stage3)
            {
               // newY = 0;
            }
            stage3 = true;
         }
      }
      if(SecondAgresiveLine2.SELECTED)
      {
         if(newX2 >=-55) {
            newX2 -= 25 * Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 110 , newX2,3 );
         reference.shapeRenderer.end();

         SecondAgresiveLine1.button.isEnabled = false;
         if(SecondAgresiveLine2.button.isEnabled)
         {
            LastAgresiveLine1.button.isEnabled = true;
            LastAgresiveLine2.button.isEnabled = true;
         }
         SecondAgresiveLine2.button.isEnabled = false;
         if(newX2<=-55)
         {
            if(!stage3)
            {
               // newY = 0;
            }
            stage3 = true;
         }
      }
      if(stage3)
      {

         if(newY >=-220)
         {
            newY -=  25*Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 250 , 3,newY );
         reference.shapeRenderer.end();
      }
      if(LastAgresiveLine2.SELECTED)
      {
         if(newX3 >=-55) {
            newX3 -= 25 * Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 30 , newX3,3 );
         reference.shapeRenderer.end();

         LastAgresiveLine1.button.isEnabled = false;
         if(LastAgresiveLine2.button.isEnabled)
         {
            UltimateAgresive.button.isEnabled = true;
         }
         LastAgresiveLine1.button.isEnabled = false;
         if(newX3<=-55)
         {
            if(!stage3)
            {
               // newY = 0;
            }
            stage4 = true;
         }
      }
      if(LastAgresiveLine1.SELECTED)
      {
         if(newX3 <=55) {
            newX3 += 25 * Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 30 , newX3,3 );
         reference.shapeRenderer.end();

         LastAgresiveLine2.button.isEnabled = false;
         if(LastAgresiveLine1.button.isEnabled)
         {
            UltimateAgresive.button.isEnabled = true;
         }
         LastAgresiveLine1.button.isEnabled = false;
         if(newX3<=-55)
         {
            if(!stage3)
            {
               // newY = 0;
            }
            stage4 = true;
         }
      }
      if(UltimateAgresive.SELECTED)
      {
         if(newY >=-290)
         {
            newY -=  25*Gdx.graphics.getDeltaTime();
         }
         reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
         reference.shapeRenderer.setColor(Color.RED);
         reference.shapeRenderer.rect(reference.camera.position.x - 385 + PrimalAgresive.bounds.getWidth()/2 ,  reference.camera.position.y + 250 , 3,newY );
         reference.shapeRenderer.end();
         UltimateAgresive.button.isEnabled = false;
      }
   }
}
