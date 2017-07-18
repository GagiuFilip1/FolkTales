package com.mygdx.game.editorCore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import com.mygdx.game.objects.Square;
import com.mygdx.game.utils.SimpleButton;

import java.util.LinkedList;

/**
 * Created by Gagiu on 3/24/2017.
 */
public class Viewer extends screenObject {

  private final Main reference;
  private final float x, y, width, height;
  private Texture texture;
  public boxOfInfo selector;
  private SimpleButton prev, next;
  public boolean ShouldDraw = true;
  int ID = 0;

  public Viewer(Main _reference) {
    reference = _reference;
    texture = new Texture(Gdx.files.internal("core/caset1.png"));
    selector = new boxOfInfo(reference);
    x = 300;
    y = 0;
    width = 800;
    height =100;
  }

  @Override
  public void Render() {
    if (ShouldDraw) {
      buildBody();
    }
  }

  private boolean WasClicked = false;
  private String Data;
  @Override
  public void Update() {
    if (ShouldDraw)
      if (selector.IsCliked()) {
        screenObject sq = new Square(reference);
        switch (ID) {
          case 1:
            sq = reference.editorFunctions.AddPLatform(((Square) sq));
            ((Square) sq).SetTexture(selector.textures.get(selector.textureID));
            reference.editor.addToRuntime(sq, 1);
            Data = "platform " + selector.textureID;
            reference.mapCreator.putData(Data);
            String path = ((FileTextureData)selector.textures.get(selector.textureID).getTextureData()).getFileHandle().path();
            reference.colaborationModule.onlineBuilder.sendPlatform(String.valueOf(path));
            WasClicked = true;
            break;

          case 2:
            sq = reference.editorFunctions.AddBackground(((Square) sq));
            ((Square) sq).SetTexture(selector.textures.get(selector.textureID));
            reference.editor.addToRuntime(sq, 1);
            Data = "background " + String.valueOf(selector.textures.indexOf(selector.textures.get(selector.textureID)));
            reference.mapCreator.putData(Data);
            path = ((FileTextureData) selector.textures.get(selector.textureID).getTextureData()).getFileHandle().path();
            reference.colaborationModule.onlineBuilder.sendBackground(String.valueOf(path));
            WasClicked = true;
            break;

          case 3 :
            Data = "spawner " + String.valueOf(selector.textures.indexOf(selector.textures.get(selector.textureID)));
            reference.mapCreator.putData(Data);
            WasClicked = true;
            break;

          case 4:
            Data = "boss " + String.valueOf(selector.textures.indexOf(selector.textures.get(selector.textureID)));
            reference.mapCreator.putData(Data);
            WasClicked = true;
            break;
        }
      }
  }

  @Override
  public void Destroy() {
    if (WasClicked) {
      ShouldDraw = false;
      reference.editor.removeFromRuntime(this);
    }
  }

  private void buildBody() {
    reference.batch.begin();
    reference.batch.draw(texture,x,y,width,height);
    reference.batch.end();
    selector.buildInfo(x, y);
  }
}

class boxOfInfo {

  public GetFiles files;
  LinkedList<Texture> textures = new LinkedList<>();
  int textureID;
  private BitmapFont font;
  private LinkedList<Rectangle> hitBoxes = new LinkedList<>();
  private Main main;

  boxOfInfo(Main _ref) {
    files = new GetFiles();
    main = _ref;
    font = new BitmapFont();
  }

  private int index = 0;

  public void buildInfo(float x, float y) {

    addTextures();
    for (String s : files.recover()) {
      x += 100;
      main.batch.begin();
      font.draw(main.batch, s, x, y);
      main.batch.draw(textures.get(index), x, y + 20, 40, 40);
      if (index < textures.size() - 1)
        index++;
      else
        index = 0;
      main.batch.end();
    }
    AddHit(260 ,20);
  }

  private boolean Added = false;
  private int auxiliarIndex = 0;

  private void addTextures() {
    if(!Added) {
      for (String s : files.recover()) {
        if(auxiliarIndex >= files.recover().size())
        {

          Added = true;
        }
        try {
          textures.add(new Texture(Gdx.files.internal(files.directedPath + "/" + s)));
          auxiliarIndex ++;
        }catch (Exception ex)
        {
          ex.printStackTrace();
        }
      }
    }
  }

  void AddHit(float x, float y) {
    for (String ignored : files.recover()) {
      x += 100;
      hitBoxes.add(new Rectangle(x, y, 40, 40));
    }
  }

  public boolean IsCliked() {
    Vector3 getMousePosInGameWorld;
    getMousePosInGameWorld = main.camera
        .unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    float ix = getMousePosInGameWorld.x, iy = getMousePosInGameWorld.y;
    for (Rectangle position : hitBoxes) {
      if (ix > position.x && ix < position.x + position.getWidth()) {
        if (iy > position.y && iy < position.y + position.getHeight()) {
          if (Gdx.input.isKeyJustPressed(Keys.ALT_LEFT)) {
            textureID = hitBoxes.indexOf(position);
            return true;
          }
        }
      }
    }
    return false;
  }

}
