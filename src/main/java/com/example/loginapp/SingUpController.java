package com.example.loginapp;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SingUpController {

    @FXML
    public static Text errorText;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField regPass;

    @FXML
    private PasswordField regPassConfirm;

    @FXML
    private TextField regUsername;

    @FXML
    private Button singUpBtn;
    @FXML
    private Button returnAuth;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane rootPane1;

    @FXML
    private Label rootPane2;

    @FXML
    private Text rootPane3;

    @FXML
    void initialize() {
        defaultCommand();
        singUpNewUser();
        returnBack();

    }

    private void returnBack() {
        returnAuth.setOnAction(event -> {
            returnAuth.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AuthWindow.fxml"));

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

    private void singUpNewUser() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        singUpBtn.setOnAction(event -> {
            String reggUsername = regUsername.getText().trim();
            String reggPass = regPass.getText().trim();
            String reggPassConf = regPassConfirm.getText().trim();


            if (!reggUsername.equals("") && !reggPass.equals("") && reggPassConf.equals(reggPass)) {

                DatabaseHandler dbbHandler = new DatabaseHandler();

                User user = new User(reggUsername, reggPass);

                ResultSet resultexist = dbHandler.singUpNewUser(user);

                int counter = 0;

                try {
                    while(resultexist.next()){
                        counter++;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if(counter == 1) {
                    Shake shake = new Shake(regUsername);
                    shake.playAnim();
                }

                else {
                    dbHandler.regUser(user);
                    singUpBtn.getScene().getWindow().hide();
                    loadScene();
                }

            } else {
                if (reggUsername.equals("") || reggPass.equals("") || reggPassConf.equals(""))
                    System.out.println("login/password/confirm password is empty");
                else if (!reggPassConf.equals(reggPass))
                    System.out.println("Password confirmation does not match the password");
                    Shake shake2 = new Shake(regPassConfirm);
                    shake2.playAnim();
            }

        });

    }

    private void loadScene() {
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

    private void defaultCommand() {
        regUsername.setEditable(false);
        regUsername.setFocusTraversable(false);
        regPass.setEditable(false);
        regPass.setFocusTraversable(false);
        regPassConfirm.setEditable(false);
        regPassConfirm.setFocusTraversable(false);


        regUsername.setOnMouseClicked(event -> {
            regUsername.setEditable(true);
            regUsername.requestFocus();
        });

        regPass.setOnMouseClicked(event -> {
            regPass.setEditable(true);
            regPass.requestFocus();
        });

        regPassConfirm.setOnMouseClicked(event -> {
            regPassConfirm.setEditable(true);
            regPassConfirm.requestFocus();
        });

        rootPane.setOnMouseClicked(event -> {
            regUsername.setEditable(false);
            regUsername.setFocusTraversable(false);
            regPass.setEditable(false);
            regPass.setFocusTraversable(false);
            regPassConfirm.setEditable(false);
            regPassConfirm.setFocusTraversable(false);
        });

        rootPane1.setOnMouseClicked(event -> {
            regUsername.setEditable(false);
            regUsername.setFocusTraversable(false);
            regPass.setEditable(false);
            regPass.setFocusTraversable(false);
            regPassConfirm.setEditable(false);
            regPassConfirm.setFocusTraversable(false);
        });

        rootPane2.setOnMouseClicked(event -> {
            regUsername.setEditable(false);
            regUsername.setFocusTraversable(false);
            regPass.setEditable(false);
            regPass.setFocusTraversable(false);
            regPassConfirm.setEditable(false);
            regPassConfirm.setFocusTraversable(false);
        });

        rootPane3.setOnMouseClicked(event -> {
            regUsername.setEditable(false);
            regUsername.setFocusTraversable(false);
            regPass.setEditable(false);
            regPass.setFocusTraversable(false);
            regPassConfirm.setEditable(false);
            regPassConfirm.setFocusTraversable(false);
        });
    }
}