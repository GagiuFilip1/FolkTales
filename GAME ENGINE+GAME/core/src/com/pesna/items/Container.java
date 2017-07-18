package com.pesna.items;

public class Container {
	public int size = 32;
	public ItemStack[] slots;
	
	public Container()
	{
		slots = new ItemStack[size];
		for ( int i = 0; i < size; i++ )
		{
			slots[i] = new ItemStack();
		}
	}
	
	public void addItemStack( ItemStack stack ){
		for ( int i = 0; i < size; i++ )
		{
			if ( slots[i].count == 0 || slots[i].item == null )
			{
				slots[i] = stack;
				break;
			}
		}
	}
}
