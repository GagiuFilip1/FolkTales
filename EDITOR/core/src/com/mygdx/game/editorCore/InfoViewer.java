package com.mygdx.game.editorCore;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import com.mygdx.game.infoManager.outputManager;
import com.mygdx.game.utils.SimpleButton;
import com.mygdx.game.utils.TextBox;

/**
 * Created by Gagiu on 4/26/2017.
 */
public class InfoViewer extends screenObject
{
    private final Main ref;
    private TextBox levelBox , dialogBox;
    private SimpleButton SetButton;
    private Vector2 position;
    private final float width = 800 , height =800;
    private final ShapeRenderer shapeRenderer;
    private outputManager output;
    public boolean ShouldDraw = false;
    public InfoViewer(Main _ref)
    {
      output = new outputManager();
      shapeRenderer = new ShapeRenderer();
      ref = _ref;
      levelBox = new TextBox(ref);
      levelBox.SetCharLimit(2);
      levelBox.SetBounds(80,40);
      levelBox.SetPosition(100, 600);

      dialogBox = new TextBox(ref);
      dialogBox.SetCharLimit(300);
      dialogBox.SetBounds(300,300);
      dialogBox.SetPosition(100 , 200);

      SetButton = new SimpleButton(ref);
      SetButton.setPosition(100 , 100);
      SetButton.addText("SAVE");
      SetButton.setBounds(100 , 50);
      SetButton.setColor(Color.FOREST);

      position = new Vector2();
    }

  @Override
  public void Render() {
      if(ShouldDraw) {
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(position.x, position.y, width, height);
        shapeRenderer.end();
        SetButton.Render();
        dialogBox.Render();
        levelBox.Render();
      }
  }

  @Override
  public void Update() {
    if (ShouldDraw) {
      SetButton.Update();
      dialogBox.Update();
      levelBox.Update();
      saveAction();
    }
  }

  @Override
  public void Destroy() {
      if(ShouldDraw) {
        SetButton.Destroy();
        dialogBox.Destroy();
        levelBox.Destroy();
      }
  }

  private void saveAction()
  {
    if(SetButton.IsClicked())
    {
      ref.mapCreator.putData("level " +  levelBox.textData);
      ref.mapCreator.putData("story " + dialogBox.textData);
        ref.colaborationModule.onlineBuilder.sendStory(String.valueOf(dialogBox.textData));
        ref.colaborationModule.onlineBuilder.sendLevel(String.valueOf(levelBox.textData));
      ShouldDraw = false;
    }
  }
}
