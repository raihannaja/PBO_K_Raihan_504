package view;

import controller.LoginController;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.User;
import model.AppInfo;

public class LoginView {
    public static void show(Stage primaryStage) {
        // Title
        Label titleLabel = new Label("Sign in");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titleLabel.setAlignment(Pos.CENTER);

        // Login form
        TextField loginUsername = new TextField();
        loginUsername.setPromptText("Username");

        PasswordField loginPass = new PasswordField();
        loginPass.setPromptText("Password");

        Label loginError = new Label();
        loginError.setTextFill(Color.RED);

        Button loginBtn = new Button("SIGN IN");
        loginBtn.setStyle("-fx-background-color: #00c9a7; -fx-text-fill: white; -fx-background-radius: 40;");
        loginBtn.setOnAction(e -> {
            User user = LoginController.signIn(loginUsername.getText(), loginPass.getText());
            if (user != null) {
                primaryStage.setScene(MainMenuView.createMainMenuScene(primaryStage, user));
            } else {
                loginError.setText("❌ Username atau password salah!");
                loginUsername.clear(); 
                loginPass.clear();
            }
        });

        Label appInfoLabel = new Label(AppInfo.getFullInfo());
        appInfoLabel.setTextFill(Color.GRAY);
        appInfoLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 8));
        appInfoLabel.setAlignment(Pos.CENTER);

        Label devInfoLabel = new Label(AppInfo.getFullInfo(true));
        devInfoLabel.setTextFill(Color.DARKGRAY);
        devInfoLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 7));
        devInfoLabel.setAlignment(Pos.CENTER);

        VBox loginFields = new VBox(10, loginUsername, loginPass, loginBtn, loginError);
        loginFields.setAlignment(Pos.CENTER_LEFT);

        VBox loginContent = new VBox(20, titleLabel, loginFields);
        loginContent.setAlignment(Pos.CENTER);

        VBox singInBox = new VBox();
        singInBox.setSpacing(10);
        singInBox.setPadding(new Insets(20));
        singInBox.setPrefWidth(420);
        singInBox.setMinWidth(420);
        singInBox.setMaxWidth(420);
        singInBox.setAlignment(Pos.TOP_CENTER);

// Gunakan grow agar loginContent tetap di tengah
        VBox.setVgrow(loginContent, Priority.ALWAYS);
        singInBox.getChildren().addAll(loginContent, appInfoLabel,devInfoLabel);


        // Signup form
        Label signupTitle = new Label("Halo, Rek!");
        signupTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        signupTitle.setTextFill(Color.WHITE);

        Label signupDesc = new Label("Belum punya akun ?? Daftarkan diri anda dan mulai gunakan Aplikasi kami segera");
        signupDesc.setWrapText(true);
        signupDesc.setMaxWidth(250);
        signupDesc.setTextFill(Color.WHITE);

        TextField signupUsername = new TextField();
        signupUsername.setPromptText("Username Baru");

        PasswordField signupPass = new PasswordField();
        signupPass.setPromptText("Password Baru");

        Label signupInfo = new Label();
        signupInfo.setTextFill(Color.WHITE);

        Button signupBtn = new Button("SIGN UP");
        signupBtn.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-text-fill: white;");
        signupBtn.setOnAction(e -> {
            String msg = LoginController.signUp(signupUsername.getText(), signupPass.getText());
            signupInfo.setText(msg);
            if (msg.startsWith("✅")) {
                signupUsername.clear();
                signupPass.clear();
            }
        });

        VBox signupBox = new VBox(10, signupTitle, signupDesc, signupUsername, signupPass, signupBtn, signupInfo);
        signupBox.setAlignment(Pos.CENTER);
        signupBox.setPadding(new Insets(20));
        signupBox.setStyle("-fx-background-color: linear-gradient(to bottom right, #00c9a7, #00b289);");
        signupBox.setPrefWidth(280);
        signupBox.setMinWidth(280);
        signupBox.setMaxWidth(280);

        // kombinasi login and signup
        HBox mainContent = new HBox(singInBox, signupBox);
        mainContent.setPrefSize(700, 400);
        mainContent.setMaxSize(700, 400);
        mainContent.setMinSize(700, 400);
        mainContent.setStyle("-fx-background-color: #f6f6f6;");
        mainContent.setEffect(new DropShadow());

        // Set scene and stage
        Scene scene = new Scene(mainContent, 700, 400);
        primaryStage.setTitle("Brain Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Supaya ukuran tidak bisa diubah
        primaryStage.show();
    }
}
