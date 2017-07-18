package com.pesna.gamedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by teo on 24.11.2016.
 */
public class GetUserData {
    private static String connectionUrl = "jdbc:sqlserver://plesna.database.windows.net:1433;database=plesna;user=plesna@plesna;password={Admin123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static Connection _connection = null;
    private static int model(int userId, int type){
        PreparedStatement _statement = null;
        ResultSet _resultSet = null;
        int result = 0;
        try {
            String toFind;
            switch ( type){
                case 1:toFind = "Location_Id";break;
                case 2:toFind = "Level";break;
                case 3:toFind = "Money";break;
                default: toFind = "";break;
            }
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT "+toFind+" FROM Players_Data WHERE Id=? ";
            _statement = _connection.prepareStatement(SQL);
            _statement.setInt(1,userId);
            _resultSet = _statement.executeQuery();
            if (_resultSet.next()) {
                result = _resultSet.getInt(1);
            }
        }
        catch (Exception e) {e.printStackTrace();}
        finally {
            if (_resultSet != null) try { _resultSet.close(); } catch(Exception e) {}
            if (_statement != null) try { _statement.close(); } catch(Exception e) {}
            if (_connection != null) try { _connection.close(); } catch(Exception e) {}
        }
        return result;
    }
    public static int userLocation(int userId){
        return model(userId,1);
    }
    public static int userLevel(int userId){
        return model(userId,2);
    }
    public static int userMoney(int userId){
        return model(userId,3);
    }

    public static int userId(String userUsername){
        int Id = 0;
        PreparedStatement _statement = null;
        ResultSet _resultSet = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM Players_Login WHERE username=?";
            _statement = _connection.prepareStatement(SQL);
            _statement.setString(1,userUsername);
            _resultSet = _statement.executeQuery();
            if (_resultSet.next()) Id = _resultSet.getInt(1);
        }
        catch (Exception e) {e.printStackTrace();}
        finally {
            if (_resultSet != null) try { _resultSet.close(); } catch(Exception e) {}
            if (_statement != null) try { _statement.close(); } catch(Exception e) {}
            if (_connection != null) try { _connection.close(); } catch(Exception e) {}
        }
        return Id;
    }
}
