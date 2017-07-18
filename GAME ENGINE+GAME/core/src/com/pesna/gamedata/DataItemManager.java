package com.pesna.gamedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by teo on 02.12.2016.
 */
//get items from database
public class DataItemManager {
    private static List<Item> _list = new LinkedList<Item>();
    public static void AddItem(int ownerId, String itemName, int itemHP, int itemHP_REGEN, int itemSPEED, int itemATTACK_SPEED, int itemATTACK_POWER, int itemCRIT_CHANCE)
    {
        DatabaseNewItem newItem = new DatabaseNewItem(ownerId,itemName,itemHP,itemHP_REGEN,itemSPEED,itemATTACK_SPEED,itemATTACK_POWER,itemCRIT_CHANCE);

        _list.add(new Item(newItem.getItemId(),ownerId,itemName,itemHP,itemHP_REGEN,itemSPEED,itemATTACK_SPEED,itemATTACK_POWER,itemCRIT_CHANCE));
    }

    public static LinkedList<Item> RetreiveItems(){
        return (LinkedList<Item>) _list;
    }

    public static void GetItemsFromDB(int ownerId){
        String connectionUrl = "jdbc:sqlserver://plesna.database.windows.net:1433;database=plesna;user=plesna@plesna;password={Admin123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection _connection = null;
        PreparedStatement _statement = null;
        ResultSet _resultSet = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM Players_Inventory WHERE Owner =? ";
            _statement = _connection.prepareStatement(SQL);
            _statement.setInt(1,ownerId);
            _resultSet = _statement.executeQuery();

            while(_resultSet.next()){
            _list.add(new Item(_resultSet.getInt(1),_resultSet.getInt(2),_resultSet.getString(3).trim(),_resultSet.getInt(4),_resultSet.getInt(5),_resultSet.getInt(6),_resultSet.getInt(7),_resultSet.getInt(8),_resultSet.getInt(9)));

            }
        }
        catch (Exception e) {e.printStackTrace();}
        finally {
            if (_statement != null) try { _statement.close(); } catch(Exception e) {}
            if (_connection != null) try { _connection.close(); } catch(Exception e) {}
            if (_resultSet != null) try { _resultSet.close(); } catch(Exception e) {}
        }
    }
}
