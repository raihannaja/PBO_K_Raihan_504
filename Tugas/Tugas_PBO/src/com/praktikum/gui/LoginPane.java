package com.praktikum.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.praktikum.users.*;
import java.util.List;
import java.awt.Toolkit;

public class LoginPane extends VBox {
    private final Label messageLabel;
    private final ComboBox<String> roleCombo;
    private final TextField usernameField;
    private final PasswordField passwordField;

    public interface LoginCallback {
        void onLoginSuccess(User user);
    }

    public LoginPane(List<User> userList, LoginCallback callback) {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setStyle("-fx-font-family: Times New Roman;");

        Label title = new Label("Login Sistem Pelaporan Data");

        roleCombo = new ComboBox<>();
        roleCombo.getItems().addAll("Admin", "Mahasiswa");
        roleCombo.setValue("Tentukan Pilihan");

        usernameField = new TextField();
        usernameField.setPromptText("Nama / Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("NIM / Password");

        Button loginButton = new Button("Login");
        messageLabel = new Label();
        loginButton.setOnAction(e -> handleLogin(userList, callback));

        Button exitButt = new Button("EXIT");
        exitButt.setOnAction(e -> {
            System.exit(0);
        } );
        HBox inputBox = new HBox(5, loginButton,exitButt);
        inputBox.setAlignment(Pos.CENTER);
        getChildren().addAll(title, roleCombo, usernameField, passwordField, inputBox, messageLabel);
    }

    private void handleLogin(List<User> userList, LoginCallback callback) {
        String role = roleCombo.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (role.equals("Admin")) {
            Admin admin = new Admin();
            if (admin.login(username, password)) {
                Toolkit.getDefaultToolkit().beep();
                messageLabel.setText("Login berhasil sebagai Admin.");
                callback.onLoginSuccess(admin);
            } else {
                messageLabel.setText("Login gagal, periksa kredensial.");
            }
        } else {
            for (User user : userList) {
                if (user instanceof Mahasiswa mhs &&
                        mhs.getNama().equals(username) &&
                        mhs.getNim().equals(password)) {
                    Toolkit.getDefaultToolkit().beep();
                    messageLabel.setText("Login berhasil sebagai Mahasiswa.");
                    callback.onLoginSuccess(mhs);
                    return;
                }
            }
            messageLabel.setText("Login gagal, periksa nama / nim.");
        }
    }
}

