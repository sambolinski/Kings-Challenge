/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.DatabaseControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sam
 */
public class DBConnect {
    private String host = "jdbc:derby://localhost:1527/Scores";
    private String username;
    private String password;
    private Connection con;
    public DBConnect(String username, String password){
        this.username = username;
        this.password = password;
    }
    public void connect(){
        try{
            con = DriverManager.getConnection(host, username, password);
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }        
    }
    public void fixConnection(){
        try{
            if(con == null || con.isClosed()){
                connect();
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public void insert(String id, String name, String time){
        String SQL = "INSERT INTO PERSON (id, name, score) values(?,?,?)";
        fixConnection();
        try{
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, time);
            statement.execute();
            con.close();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public int retrieveDatabaseSize(){
        fixConnection();
        String SQL = "SELECT COUNT(id) AS number FROM PERSON";
        int count = 0;
        try{
            Statement statement = con.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL);
            if (rs.next()) {
                count = rs.getInt("number");
            }
            con.close();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return count;
    }
}
