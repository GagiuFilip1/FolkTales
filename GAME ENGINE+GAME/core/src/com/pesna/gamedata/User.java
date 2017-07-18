package com.pesna.gamedata;

import com.pesna.abstracts.UserStructure;

import java.sql.*;

/**
 * Created by teo on 24.11.2016.
 */

public class User extends UserStructure {

    private boolean login_isSuccessful = false;

    private int Id;
    private String _username;
    public int location;
    public int money;
    public int level;

    private String connectionUrl = "jdbc:sqlserver://plesna.database.windows.net:1433;database=plesna;user=plesna@plesna;password={Admin123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection _connection = null;

    public User(String username, String password,boolean login){
        // true-login
        if(login){
        startLogin(username,password);
        if(isSuccessful()) {
            System.out.println("Your Id is "+ getId() + ".");
            }
        }
        else register(username, password);
        _username = username;
    }
    private void register(String username, String password){
        PreparedStatement _statement = null;
        ResultSet _resultSet = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection(connectionUrl);
            String SQL = "INSERT INTO Players_Login (username,password) OUTPUT INSERTED.ID VALUES (?,?)";
            _statement = _connection.prepareStatement(SQL);
            _statement.setString(1,username);
            _statement.setString(2,password);
            _resultSet = _statement.executeQuery();
            if(_resultSet.next())
                Id = _resultSet.getInt(1);
            System.out.println("Your Id is " + Id + ".");
            if(Id != 0){
                _connection = DriverManager.getConnection(connectionUrl);
                SQL = "INSERT INTO Players_Data (Id) VALUES (?)";
                _statement = _connection.prepareStatement(SQL);
                _statement.setInt(1,Id);
                _statement.execute();
            }
        }
        catch (Exception e) {e.printStackTrace();}
        finally {
            if (_statement != null) try { _statement.close(); } catch(Exception e) {}
            if (_connection != null) try { _connection.close(); } catch(Exception e) {}
            if (_resultSet != null) try { _resultSet.close(); } catch(Exception e) {}
        }
    }
    private void startLogin(String username, String password){
        PreparedStatement _statement = null;
        ResultSet _resultSet = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection(connectionUrl);
            String SQL = "SELECT * FROM Players_Login WHERE username=? AND password=? ";
            _statement = _connection.prepareStatement(SQL);
            _statement.setString(1,username);
            _statement.setString(2,password);

            _resultSet = _statement.executeQuery();

            if (_resultSet.next()) {
                Id = _resultSet.getInt(1);
                login_isSuccessful = true;
                System.out.println("Connected!");
                location = getLocation();
                level = getLevel();
                money = getMoney();

            }
            else System.out.println("Could not login.");
        }
        catch (Exception e) {e.printStackTrace();}
        finally {
            if (_resultSet != null) try { _resultSet.close(); } catch(Exception e) {}
            if (_statement != null) try { _statement.close(); } catch(Exception e) {}
            if (_connection != null) try { _connection.close(); } catch(Exception e) {}
        }
    }

    public boolean isSuccessful(){
        return login_isSuccessful;
    }

    @Override
    public String getUsername() { return _username; }

    @Override
    public int getId() { return Id; }

    @Override
    public int getMoney() {
        return GetUserData.userMoney(Id);
    }

    @Override
    public int getLevel() {
        return GetUserData.userLevel(Id);
    }

    @Override
    public int getLocation() {
        return GetUserData.userLocation(Id);
    }

    public int updateMoney(int value){return UpdateUserData.userMoney(Id,value);}

    public int updateLevel(int value){return UpdateUserData.userLevel(Id, value);}

    public int updateLocation(int value){return UpdateUserData.userLocation(Id, value);}
}
