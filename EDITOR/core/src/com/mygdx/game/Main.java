package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.editorCore.*;
import com.mygdx.game.infoManager.outputManager;
import com.mygdx.game.infoManager.reader;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.ScreenManager;
import com.mygdx.game.utils.Infobox;
import com.mygdx.game.utils.SimpleButton;
import com.mygdx.game.utils.Vars;

public class Main extends ApplicationAdapter {

  public SpriteBatch batch;
  public ShapeRenderer shapeRenderer;
  public OrthographicCamera camera;
  public Editor editor;
  public EditorGUI editorGUI;
  public EditorFunctions editorFunctions;
  public outputManager mapCreator;
  public Infobox infobox;
  public Vars vars = new Vars(this);
  public InfoViewer infoViewer;
  public ColaborationModule colaborationModule;
  public ScreenManager screenManager;

  @Override
  public void create() {
    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();
    camera = new OrthographicCamera(1280, 720);
    camera.setToOrtho(false, 1280, 720);
    screenManager = new ScreenManager();
    //--------------------SINGLE------------------//
    editor = new Editor(this);
    editorGUI = new EditorGUI(this);
    editorFunctions = new EditorFunctions(editor, this);
    mapCreator = new outputManager();
    infobox = new Infobox(this);
    infobox.putText(vars.info1);
    infobox.SetPosition(990, 320);
    infoViewer = new InfoViewer(this);
    //------------------ONLINE--------------------//
    colaborationModule = new ColaborationModule(this);
    screenManager.setScreen(new MenuScreen(this));
    //-----------------------------------------//
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    screenManager.updateScreen();
  }

  @Override
  public void dispose() {
    batch.dispose();
  }
}
