package com.praktikum.gui;

import com.praktikum.main.LoginSystem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.praktikum.users.*;

    public class MainApp extends Application {
        AdminDashboard admDash = new AdminDashboard();
        MahasiswaDashboard mhsDash = new MahasiswaDashboard();


        @Override
        public void start(Stage primaryStage) {
            LoginSystem.userList.add(new Mahasiswa("Muran", "504"));

            LoginPane loginPane = new LoginPane(LoginSystem.userList, user -> {
                if (user instanceof Admin) {
                    admDash.show(primaryStage);
                } else if (user instanceof Mahasiswa mhs) {
                    mhsDash.showDashboard(primaryStage, mhs);
                }
            });

            Scene scene = new Scene(loginPane, 500, 350);
            primaryStage.setTitle("Data Kehilangan dan Penemuan");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

