package com.example.loginapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;




    public Connection getdbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public void regUser(User user) {
        String insert = "INSERT INTO " + Const.USERDATA_TABLE + "(" + Const.USERDATA_USERNAME + ", " + Const.USERDATA_PASS + ")" + "VALUES(?, ?)";

        try {

            PreparedStatement prSu = getdbConnection().prepareStatement(insert);
            prSu.setString(1, user.getUsername());
            prSu.setString(2, user.getPassword());

            prSu.executeUpdate();

        } catch (SQLException e) {
            String errorExist = "This username exist";
            System.out.println("This username exist");
            SingUpController error = new SingUpController();
            error.errorText.setVisible(true);
            throw new RuntimeException(e + errorExist);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public ResultSet lgUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USERDATA_TABLE + " WHERE " +
                Const.USERDATA_USERNAME + "=? AND " + Const.USERDATA_PASS + "=?";
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

    public ResultSet singUpNewUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USERDATA_TABLE + " WHERE " + Const.USERDATA_USERNAME + "=? ";

        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setString(1, user.getUsername());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }



}
