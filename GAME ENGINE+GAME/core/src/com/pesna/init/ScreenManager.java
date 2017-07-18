package com.pesna.init;

import com.pesna.Main;
import com.pesna.screens.ErrorScreen;
import com.pesna.screens.GameScreen;
import com.pesna.screens.IScreen;
import com.pesna.screens.LoadingScreen;
import com.pesna.screens.MenuScreen;

import java.io.IOException;

public class ScreenManager {
	private IScreen currentScreen;
	private IScreen queuedScreen;//This variable ensures correct screen-changing
	private final Main reference;
	
	public IScreen loadingScreen, errorScreen, gameScreen, menuScreen;
	
    
	public ScreenManager( Main _reference )
	{
		reference = _reference;
		loadingScreen = new LoadingScreen();
		currentScreen = loadingScreen; // You start with the loading screen
		queuedScreen = currentScreen;
	}
	
	
	public void draw(){currentScreen.draw(reference); currentScreen = queuedScreen;}
	public void update(){currentScreen.update(reference);}
	
	public void onAssetsLoaded( Main _reference ) throws IOException {
		menuScreen = new MenuScreen( _reference );
		errorScreen = new ErrorScreen();
		gameScreen = new GameScreen(_reference);
		
		((GameScreen)gameScreen).onAssetsLoaded();
	}
	
	public void queueScreen( IScreen newScreen ){queuedScreen = newScreen;}
}
