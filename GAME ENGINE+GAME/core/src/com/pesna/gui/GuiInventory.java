package com.pesna.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.pesna.Main;
import com.pesna.items.Container;
import com.pesna.items.ItemStack;

public class GuiInventory extends GuiObject implements InputProcessor{
	public Texture texture;
	public float x, y;
	public Container container;
	
	public boolean visible = false;
	
	public ItemStack cursorStack;
	
	private int spacing=4,columns=8,slotSize=64,border=4;
	
	public GuiInventory( Main _reference )
	{
		texture = _reference.assetManager.get("items/inventory.png",Texture.class);
		
		cursorStack = new ItemStack();
		
		x = -texture.getWidth()/2;
		y = -texture.getHeight()/2;
		container = new Container();
		
		//LOAD THE ITEMS HERE - FROM FILE
		for ( int i = 0; i < 12; i++ )
			container.slots[i] = new ItemStack(_reference.gameRegistry.itemManager.martisor, 1);
		container.slots[21] = new ItemStack(_reference.gameRegistry.itemManager.martisor, 1);
		
		//add self to the multiplexer
		//_reference.multiplexer.addProcessor(this);
	}
	
	public void draw( Main _reference )
	{
		if ( visible )
		{
			_reference.batch.begin();
			//Draw frame/background
			_reference.batch.draw(texture,x,y);
			//Draw Items
			for ( int i = 0; i < container.size; i++ )
			{
				if ( container.slots[i].item != null )
					_reference.batch.draw(container.slots[i].item.texture,x+itox(i),y-itoy(i));
			}
			//Draw hand stack
			if ( cursorStack.item != null )
				_reference.batch.draw( cursorStack.item.texture,
						Gdx.input.getX()-Gdx.graphics.getWidth()/2-slotSize/2,
						-Gdx.input.getY()+Gdx.graphics.getHeight()/2-slotSize/2 );
			
			_reference.batch.end();
		}
	}
	public void update( Main _reference )
	{
		
	}
	
	public void toggle()
	{
		//Put the cursorItemStack back in the inventory upon closing
		if ( visible )
		{
			for ( int i = 0; i < container.size; i++ )
			{
				if ( container.slots[i].item == null )
				{
					ItemStack aux;
					aux = container.slots[i];
					container.slots[i] = cursorStack;
					cursorStack = aux;
					break;
				}
			}
		}
		
		visible = !visible;
	}
	
	public float itox(int i){
		if ( i%columns != 0 )
			return border+(i%columns)*slotSize+(i%columns)*spacing-1;
		else
			return border;
	}
	public float itoy(int i){
		return border+(i/columns)*(slotSize+spacing)-texture.getHeight()+slotSize;
	}
	
	public boolean touchDown (int screenX, int screenY, int pointer, int button)
	{
		screenX -= Gdx.graphics.getWidth()/2 - texture.getWidth()/2;
		screenY -= Gdx.graphics.getHeight()/2 - texture.getHeight()/2;
		
		if ( screenX > border && screenY > border && screenX < texture.getWidth()-border && screenY < texture.getHeight()-border  )
		{
			int rownumber = screenY/(spacing+slotSize);
			int columnnumber = screenX/(spacing+slotSize);
			
			ItemStack aux;
			aux = container.slots[columnnumber+rownumber*columns];
			container.slots[columnnumber+rownumber*columns] = cursorStack;
			cursorStack = aux;
			
			//System.out.println(rownumber + " " + columnnumber + " " + (columnnumber+rownumber*columns) );
			return true;
		}
		
		
		return false;
	}
	public boolean touchUp (int screenX, int screenY, int pointer, int button){return false;}
	public boolean keyDown (int keycode){
		if ( keycode == Keys.I )
		{
			toggle();
			return true;
		}
		return false;
	}
	public boolean keyUp (int keycode){return false;}
	public boolean keyTyped (char character){return false;}
	public boolean touchDragged (int screenX, int screenY, int pointer){return false;}
	public boolean mouseMoved (int screenX, int screenY){return false;}
	public boolean scrolled (int amount){return false;}
}
