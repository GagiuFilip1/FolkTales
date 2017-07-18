package com.pesna.gamedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by teo on 24.11.2016.
 */
public class UpdateUserData {
    private static String connectionUrl = "jdbc:sqlserver://plesna.database.windows.net:1433;database=plesna;user=plesna@plesna;password={Admin123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static Connection _connection = null;

    private static int model(int userId, int type, int value){
        PreparedStatement _statement = null;
        int result = 0;
        try {
            String toUpdate;
            switch ( type){
                case 1:toUpdate = "Location_Id";break;
                case 2:toUpdate = "Level";break;
                case 3:toUpdate = "Money";break;
                default: toUpdate = "";break;
            }
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection(connectionUrl);
            String SQL = "UPDATE Players_Data SET "+toUpdate+"=? WHERE Id=? ";
            _statement = _connection.prepareStatement(SQL);
            _statement.setInt(1,value);
            _statement.setInt(2,userId);
            _statement.execute();
        }
        catch (Exception e) {e.printStackTrace();}
        finally {
            if (_statement != null) try { _statement.close(); } catch(Exception e) {}
            if (_connection != null) try { _connection.close(); } catch(Exception e) {}
        }
        return result;
    }
    public static int userLocation(int userId,int value){
        return model(userId,1,value);
    }
    public static int userLevel(int userId, int value){
        return model(userId,2,value);
    }
    public static int userMoney(int userId, int value){
        return model(userId,3,value);
    }

}
