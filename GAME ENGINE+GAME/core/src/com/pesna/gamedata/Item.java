package com.pesna.gamedata;

import java.sql.Connection;

/**
 * Created by teo on 02.12.2016.
 */
public class Item {
    private String connectionUrl = "jdbc:sqlserver://plesna.database.windows.net:1433;database=plesna;user=plesna@plesna;password={Admin123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection _connection = null;

    private int itemId;
    private int ownerId;
    private String itemName;
    private int itemHP;
    private int itemHP_REGEN;
    private int itemSPEED;
    private int itemATTACK_SPEED;
    private int itemATTACK_POWER;
    private int itemCRIT_CHANCE;

    public Item(int _itemId, int _ownerId, String _itemName, int _itemHP, int _itemHP_REGEN, int _itemSPEED, int _itemATTACK_SPEED, int _itemATTACK_POWER,int _itemCRIT_CHANCE){
        itemId = _itemId;
        ownerId = _ownerId;
        itemName = _itemName;
        itemHP = _itemHP;
        itemHP_REGEN = _itemHP_REGEN;
        itemSPEED = _itemSPEED;
        itemATTACK_SPEED = _itemATTACK_SPEED;
        itemATTACK_POWER = _itemATTACK_POWER;
        itemCRIT_CHANCE = _itemCRIT_CHANCE;
    }

    public int getItemId() {
        return itemId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemHP() {
        return itemHP;
    }

    public int getItemHP_REGEN() {
        return itemHP_REGEN;
    }

    public int getItemSPEED() {
        return itemSPEED;
    }

    public int getItemATTACK_SPEED() {
        return itemATTACK_SPEED;
    }

    public int getItemATTACK_POWER() {
        return itemATTACK_POWER;
    }

    public int getItemCRIT_CHANCE() {
        return itemCRIT_CHANCE;
    }
/*
    public int getOwnerId(int itemId) {
        return 0;
    }

    public String getItemName(int itemId) {
        return null;
    }

    public int getItemHP(int itemId) {
        return 0;
    }

    public int getItemHP_REGEN(int itemId) {
        return 0;
    }

    public int getItemSPEED(int itemId) {
        return 0;
    }
    public int getItemATTACK_SPEED(int itemId) {
        return 0;
    }

    public int getItemATTACK_POWER(int itemId) {
        return 0;
    }

    public int getItemCRIT_CHANCE(int itemId) {
        return 0;
    }
*/
}
