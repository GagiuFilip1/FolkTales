package com.pesna;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.pesna.init.GameRegistry;
import com.pesna.init.ScreenManager;
import com.pesna.player.Player;
import com.pesna.spriter.LibGdx.LibGdxDrawer;

import java.io.IOException;


public class Main extends ApplicationAdapter {

	public SpriteBatch batch;
	public AssetManager assetManager;
	public OrthographicCamera camera;
	public ShapeRenderer shapeRenderer;

	public Player player;
	//public PlayerSecond playerSecond;
	public ScreenManager screenManager;
	public GameRegistry gameRegistry;

	public LibGdxDrawer werewolfDrawer, pesnaIdleDrawer, pesnaMoveDrawer;

	public BitmapFont font;
	//public Viewport viewport;

	public boolean orthoscale = false;

	@Override
	public void create () {
		batch = new  SpriteBatch();
		camera = new OrthographicCamera(1920,1080);
		//viewport = new FitViewport(1920,1080, camera);
		camera.setToOrtho(false,1920,1080);
		//camera.zoom = 2.0f;
		assetManager = new AssetManager();
		shapeRenderer = new ShapeRenderer();
		gameRegistry = new GameRegistry(this);
		try {
			player = new Player( this );
			//playerSecond = new PlayerSecond( this );
		} catch (IOException e) {
			e.printStackTrace();
		}

		screenManager = new ScreenManager(this);

		font = new BitmapFont();

	}

	@Override
	public void resize (int width, int height) {
		//camera.setToOrtho(false,width,height);
		//viewport.update(width, height);

		if ( orthoscale )
			camera.setToOrtho(false,width,height);
	}

	@Override
	public void dispose ()
	{
		shapeRenderer.dispose();
		assetManager.dispose();
		batch.dispose();
	}

	@Override
	public void render () {
		if ( Gdx.input.isKeyJustPressed(Keys.ESCAPE) )
		{
			Gdx.app.exit();
		}

		if ( Gdx.input.isKeyJustPressed(Keys.F12) )
		{
			byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);

			Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
			BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
			String filename = Long.toString( TimeUtils.millis() ) + ".png";
			System.out.println(filename);
			PixmapIO.writePNG(Gdx.files.external(filename), pixmap);
			pixmap.dispose();
			System.out.println("SCREENSHOT TAKEN");
		}

		Gdx.gl.glClearColor( 0.5f, 0.5f, 0.5f , 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		screenManager.update();
		screenManager.draw();
	}
}
