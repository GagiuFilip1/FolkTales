package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;

/**
 * Created by Gagiu on 2/24/2017.
 */
public class PolygonC
{
  private Main reference;
  private Vector3 getMousePosInGameWorld;
  private float X ,Y;
  private float[] vertices = new float[200];
  private float[] finalVertices;
  Vector2[] nodes = new Vector2[100];

  PolygonSprite poly;
  PolygonSpriteBatch polyBatch = new PolygonSpriteBatch(); // To assign at the beginning
  Texture textureSolid;


  public PolygonC(Main _refence)
  {
    reference = _refence;
    getMousePosInGameWorld = new Vector3();
    Pixmap pix = new Pixmap(1000, 1000, Pixmap.Format.RGBA8888);
    pix.setColor(0xDEADBEFF); // DE is red, AD is green and BE is blue.
    pix.fill();
    textureSolid = new Texture(pix);
  }

  private int i = 0;
  private void getVerticles()
  {
    getMousePosInGameWorld = reference.camera
        .unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    X = getMousePosInGameWorld.x;
    Y = getMousePosInGameWorld.y;
    nodes[i] = new Vector2();
    nodes[i].set(X,Y);
    i++;
  }

  public void tap()
  {
    if(Gdx.input.isKeyJustPressed(Keys.J))
    {
      getVerticles();
    }
  }
  int aux = 0 , j = 0 , finali;
  boolean checker = false , draw = false;
  public void Create() {
     if(!draw) {
       try {
         if (j <= 100) {
           if (nodes[aux].x != 0 && nodes[aux].y != 0) {
             vertices[j] = nodes[aux].x;
             vertices[j + 1] = nodes[aux].y;
             finali++;
           }
           aux += 1;
           j += 2;

         }
       } catch (NullPointerException ex) {
         // ex.printStackTrace();
       }
       finalVertices = new float[finali * 2];
       for (int x = 0; x < finali * 2; x++) {
         finalVertices[x] = vertices[x];
         x++;
         finalVertices[x] = vertices[x];
       }

       if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
         checker = true;
       }
       if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
         draw = true;
       }
     }
  }
  public void Draw() {
   /*
    if(draw) {

      reference.shapeRenderer.begin(ShapeType.Line);
      reference.shapeRenderer.setColor(Color.BLACK);
      reference.shapeRenderer.polygon(finalVertices);
      reference.shapeRenderer.end();
    }
*/

    if (draw) {
      PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
          finalVertices,  new short[] {
          0, 1, 2,
          0, 2, 3
      }
      );
      poly = new PolygonSprite(polyReg);
      poly.setOrigin(0, 0);
      polyBatch = new PolygonSpriteBatch();
      polyBatch.begin();
      poly.draw(polyBatch);
      polyBatch.end();
    }
  }
}
