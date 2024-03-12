package com.example.loginapp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button lgBtn;

    @FXML
    private PasswordField passInsert;

    @FXML
    private Button regBtn;

    @FXML
    private CheckBox remlogCheckBox;

    @FXML
    private TextField usernameInsert;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private Label anchorPane2;

    @FXML
    private Label anchorPane3;

    @FXML
    void initialize() {
        defaultCommand();
        lgUser();



    }

    private void defaultCommand() {
        usernameInsert.setEditable(false);
        usernameInsert.setFocusTraversable(false);
        passInsert.setEditable(false);
        passInsert.setFocusTraversable(false);

        usernameInsert.setOnMouseClicked(event -> {
            usernameInsert.setEditable(true);
            usernameInsert.requestFocus();
        });

        passInsert.setOnMouseClicked(event -> {
            passInsert.setEditable(true);
            passInsert.requestFocus(); // Перенос фокуса на текстовое поле
        });

        anchorPane.setOnMouseClicked(event -> {
            usernameInsert.setEditable(false);
            usernameInsert.setFocusTraversable(false);
            passInsert.setEditable(false);
            passInsert.setFocusTraversable(false);
        });

        anchorPane1.setOnMouseClicked(event -> {
            usernameInsert.setEditable(false);
            usernameInsert.setFocusTraversable(false);
            passInsert.setEditable(false);
            passInsert.setFocusTraversable(false);
        });

        anchorPane2.setOnMouseClicked(event -> {
            usernameInsert.setEditable(false);
            usernameInsert.setFocusTraversable(false);
            passInsert.setEditable(false);
            passInsert.setFocusTraversable(false);
        });

        anchorPane3.setOnMouseClicked(event -> {
            usernameInsert.setEditable(false);
            usernameInsert.setFocusTraversable(false);
            passInsert.setEditable(false);
            passInsert.setFocusTraversable(false);
        });

        regBtn.setOnAction(event -> {
            regBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("singUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("ProgAuth");
            stage.show();

        });
    }

    private void lgUser() {

        lgBtn.setOnAction(event -> {

            String lgusername = usernameInsert.getText().trim();
            String lgpassword = passInsert.getText().trim();

            DatabaseHandler dbHandler = new DatabaseHandler();
            User user = new User();
            user.setUsername(lgusername);
            user.setPassword(lgpassword);
            ResultSet result = dbHandler.lgUser(user);
            int counter = 0;

            try {
                while(result.next()){
                    counter++;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            if (counter >= 1) {
                loadWindow();

                System.out.println("username: " + user.getUsername() + " is logged");

            } else {
                Shake shake = new Shake(usernameInsert);
                Shake shake1 = new Shake(passInsert);
                shake.playAnim();
                shake1.playAnim();
            }
        });
    }

    private void loadWindow() {
            lgBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddonWindow.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("ProgAuth");
            stage.show();

    }
}
