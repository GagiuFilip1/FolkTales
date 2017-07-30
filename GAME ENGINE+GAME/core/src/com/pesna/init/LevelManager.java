package com.pesna.init;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.pesna.Main;

import java.util.Objects;

public class LevelManager {
	public Texture platform,background, over;
	public String platformSeter, backgroundSeter = "";
	
	public void assignTextures( )
	{
	        if(Objects.equals(platformSeter, "") || Objects.equals(backgroundSeter, "")) {
                System.out.println("here" + platformSeter + " "  + backgroundSeter);
                platform = new Texture(Gdx.files.internal("level/platform.png"));
                background = new Texture(Gdx.files.internal("background1.png"));
                over = new Texture(Gdx.files.internal("b22.PNG"));
            }
            else
            {
                System.out.println("omfg");
                platform = new Texture(Gdx.files.internal(platformSeter.replace(" ","")));
                background = new Texture(Gdx.files.internal(backgroundSeter.replace(" ","")));
            }
	}

	public void getTextures(String a, String b)
    {
        platformSeter = "level/" + b;
        backgroundSeter = a;
        assignTextures();
    }
}
