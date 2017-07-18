package com.pesna.gamedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by teo on 02.12.2016.
 */
public class DatabaseNewItem {
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

    public DatabaseNewItem(int _ownerId, String _itemName, int _itemHP, int _itemHP_REGEN, int _itemSPEED, int _itemATTACK_SPEED, int _itemATTACK_POWER, int _itemCRIT_CHANCE) {
        ownerId = _ownerId;
        itemName = _itemName;
        itemHP = _itemHP;
        itemHP_REGEN = _itemHP_REGEN;
        itemSPEED = _itemSPEED;
        itemATTACK_SPEED = _itemATTACK_SPEED;
        itemATTACK_POWER = _itemATTACK_POWER;
        itemCRIT_CHANCE = _itemCRIT_CHANCE;

        itemId = addInDB();
    }

    private int addInDB(){
        int itemId=0;
        PreparedStatement _statement = null;
        ResultSet _resultSet = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection(connectionUrl);
            String SQL = "INSERT INTO Players_Inventory (Owner,Name, HP, HP_REGEN, SPEED, ATTACK_SPEED, ATTACK_POWER, CRIT_CHANCE) OUTPUT INSERTED.ID VALUES (?,?,?,?,?,?,?,?) ";
            _statement = _connection.prepareStatement(SQL);
            _statement.setInt(1,ownerId);
            _statement.setString(2,itemName);
            _statement.setInt(3,itemHP);
            _statement.setInt(4,itemHP_REGEN);
            _statement.setInt(5,itemSPEED);
            _statement.setInt(6,itemATTACK_SPEED);
            _statement.setInt(7,itemATTACK_POWER);
            _statement.setInt(8,itemCRIT_CHANCE);
            _resultSet = _statement.executeQuery();
            if(_resultSet.next())
            itemId = _resultSet.getInt(1);
        }
        catch (Exception e) {e.printStackTrace();}
        finally {
            if (_statement != null) try { _statement.close(); } catch(Exception e) {}
            if (_connection != null) try { _connection.close(); } catch(Exception e) {}
            if (_resultSet != null) try { _resultSet.close(); } catch(Exception e) {}
        }

        return itemId;
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
}
