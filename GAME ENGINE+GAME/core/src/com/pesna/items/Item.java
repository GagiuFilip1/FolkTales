package com.pesna.items;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item { //ID + Textura + dropchance...
	public String textureFilename;
	public String name;
	public int ID;
	public Texture texture;
	
	public Item ( String texfilename, String _name )
	{
		textureFilename = texfilename;
		name = _name;
	}
	// ItemDrop...
	
	public void setTexture( Texture tex )
	{
		texture = tex;
	}
}


// Item = filename, name, id, textura, enchant chance...

// ItemStack = item_type, count, max_count;

// ItemDropped = item_type, x, y