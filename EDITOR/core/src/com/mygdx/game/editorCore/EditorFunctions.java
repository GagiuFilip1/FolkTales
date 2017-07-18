package com.mygdx.game.editorCore;

import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import com.mygdx.game.objects.Square;
import com.mygdx.game.utils.Vars;

/**
 * Created by Gagiu on 3/24/2017.
 */
public class EditorFunctions {

  private Editor editor;
  private Main reference;
  public Vars vars;

  public EditorFunctions(Editor _editor, Main _reference) {
    editor = _editor;
    reference = _reference;
    vars = new Vars(reference);
  }

  public screenObject AddPLatform(Square sq) {
    sq.width = vars.platformWidth;
    sq.height = vars.platformHeight;
    sq.x = vars.platformX;
    return sq;
  }

  public void AddPlayer() {

  }

  public void AddSpawner() {

  }

  public void AddDialogue() {

  }

  public void AddBoss() {

  }

  public screenObject AddBackground(Square sq) {
    sq.width = 1366;
    sq.height = 768 - vars.platformHeight;
    sq.x = 0;
    sq.y = vars.platformHeight;
    return sq;
  }

  public void RemoveLast() {
    try {
      reference.editor.removeFromRuntime(reference.editor.toDraw.getLast());
    } catch (Exception ex) {//_______________\\ Unhandled Exception
    }
  }

}
