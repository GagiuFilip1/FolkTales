package com.mygdx.game.editorCore;

import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;

import java.util.LinkedList;

/**
 * Created by Gagiu on 3/24/2017.
 */
public class Editor
{
  private final Main reference;
  public LinkedList<screenObject> toDraw = new LinkedList<>();
  public boolean forceStop = false;
public EditorFunctions editorFunctions;


  public Editor(Main _reference)
  {
    reference = _reference;
    editorFunctions = new EditorFunctions(this , reference);
  }

  public void addToRuntime(screenObject obj , int id)
  {
    switch (id)
    {
      case 1:
        break;
      case 2:
        break;
      case 3:
        break;
    }
    toDraw.add(obj);
  }

  public void removeFromRuntime(screenObject obj)
  {
    toDraw.remove(obj);

  }

  public void startRuntime()
  {
    try {
      if (!forceStop)
        for (screenObject obj : toDraw) {
          try {
            obj.Render();
            obj.Update();
            obj.Destroy();
          } catch (NullPointerException ex) {
            //_______________________\\
          }
        }
    }
    catch(Exception ex)
    {

    }
  }


}
