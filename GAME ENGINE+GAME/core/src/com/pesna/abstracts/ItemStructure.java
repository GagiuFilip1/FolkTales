package com.pesna.abstracts;

/**
 * Created by teo on 02.12.2016.
 */

public abstract class ItemStructure {
    public abstract int getItemId();
    public abstract int getOwnerId();
    public abstract String getItemName();
    public abstract int getItemHP();
    public abstract int getItemHP_REGEN();
    public abstract int getItemSPEED();
    public abstract int getItemATTACK_SPEED();
    public abstract int getItemATTACK_POWER();
    public abstract int getItemCRIT_CHANCE();
}