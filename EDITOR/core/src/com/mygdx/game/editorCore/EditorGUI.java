package com.mygdx.game.editorCore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import com.mygdx.game.utils.SimpleButton;
import java.util.LinkedList;

/**
 * Created by Gagiu on 3/24/2017.
 */
public class EditorGUI extends screenObject
{
  private Main reference;
  private  LinkedList<screenObject> runTime;
  private Viewer viewer;
  public EditorGUI(Main _reference)
  {
    reference = _reference;
    runTime = new LinkedList<>();
  }


  @Override
  public void Render()
  {

  }

  @Override
  public void Update() {
    updateRuntime();
    if(!reference.infoViewer.ShouldDraw) {
      if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
        viewer = new Viewer(reference);
        viewer.selector.files.directedPath = "platforms";
        viewer.selector.files.start();

        viewer.ID = 1;
      }
      if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
        viewer = new Viewer(reference);
        viewer.selector.files.directedPath = "backgrounds";
        viewer.selector.files.start();
        viewer.ID = 2;
      }
      if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
        viewer = new Viewer(reference);
        viewer.selector.files.directedPath = "mobs";
        viewer.selector.files.start();
        viewer.ID = 3;
      }
      if (Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
        viewer = new Viewer(reference);
        viewer.selector.files.directedPath = "mobs";
        viewer.selector.files.start();
        viewer.ID = 4;
      }
      if (Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
        reference.infoViewer.ShouldDraw = true;
      }
    }

    if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE))
    {
      reference.editor.editorFunctions.RemoveLast();
    }

    if(Gdx.input.isKeyJustPressed(Keys.S))
    {
      reference.mapCreator.SaveData();
    }

    if (!reference.editor.toDraw.contains(viewer))
    {
      reference.editor.addToRuntime(viewer , 1);
    }
  }

  @Override
  public void Destroy() {

  }

  private void updateRuntime()
  {
    runTime =  reference.editor.toDraw;
  }
}
