package com.pesna.items;

public class ItemStack {
	public int count;
	public Item item;
	
	public ItemStack()
	{
		item = null;
		count = 0;
	}
	
	public ItemStack( Item _item, int _count )
	{
		item = _item;
		count = _count;
	}
}
