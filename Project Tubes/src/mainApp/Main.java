package mainApp;

import javafx.application.Application;
import javafx.stage.Stage;

import view.LoginView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginView.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
