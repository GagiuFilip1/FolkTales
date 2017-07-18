package com.mygdx.game.editorCore;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import java.util.LinkedList;

/**
 * Created by Gagiu on 3/24/2017.
 */
public class GetFiles
{

  public String directedPath;

  private LinkedList<String> names = new LinkedList<>();

  public void start()
  {
    FileHandle dirHandle;
    System.out.println(directedPath);
    if (Gdx.app.getType() == ApplicationType.Android)
    {
      dirHandle = Gdx.files.internal(directedPath);
    }
    else
    {
      dirHandle = Gdx.files.internal(directedPath);
    }
    Array<FileHandle> handles = new Array<FileHandle>();
    getHandles(dirHandle, handles);
  }
  private void getHandles(FileHandle begin, Array<FileHandle> handles)
  {
    FileHandle[] newHandles = begin.list();
    for (FileHandle f : newHandles)
    {
      if (f.isDirectory())
      {
        getHandles(f, handles);
      }
      else
      {
        handles.add(f);
        names.add(f.name());
        System.out.println(f.name());
      }
    }
  }

  protected final LinkedList<String> recover()
  {
    return names;
  }
}
