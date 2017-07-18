package com.pesna.init;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.pesna.Main;
import com.pesna.items.Item;
import com.pesna.items.ItemMartisor;

public class ItemManager {
	public ArrayList<Item> items;
	
	public Item martisor = new ItemMartisor( "items/martisor.png", "martisor" );
	
	public ItemManager(Main mainClass)
	{
		items = new ArrayList<Item>();
		this.init();
		this.registerTextures( mainClass.assetManager );
	}
	
	private void init()
	{
		items.add( martisor );
	}
	
	private void registerTextures( AssetManager assetManager )
	{
		for ( Item item : items )
		{
			assetManager.load( item.textureFilename , Texture.class );
		}
	}
	
	public void assignTextures ( AssetManager assetManager )
	{
		for ( Item item : items )
		{
			item.setTexture( assetManager.get( item.textureFilename, Texture.class ) );
		}
	}
	
}
