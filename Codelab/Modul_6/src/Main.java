import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.awt.Toolkit;
import java.util.Random;

public class Main extends Application {
    int angkaRahasia;
    int jumlahPercobaan;
    TextField inputTebakan;
    Label labelStatus;
    Label labelPercobaan;
    Button tombolAksi;

    @Override
    public void start(Stage primaryStage) {
        angkaRahasia = new Random().nextInt(100) + 1;
        jumlahPercobaan = 0;

        Label judul = new Label("🐲 Tebak Angka 1–100");
        judul.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 25));
        judul.setTextFill(Color.web("#9B7EBD"));

        // Label status
        labelStatus = new Label("");
        labelStatus.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        inputTebakan = new TextField();         // Input
        inputTebakan.setPromptText("Masukkan tebakkanmu!");
        inputTebakan.setMaxWidth(200);
        inputTebakan.setOnAction(e -> prosesTebakan());
        inputTebakan.setStyle("-fx-border-color: #9B7EBD; -fx-border-width: 4px;");

        tombolAksi = new Button("Coba Tebak!");         // Tombol Tebak
        tombolAksi.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        tombolAksi.setOnMouseEntered(e -> {
            tombolAksi.setStyle("-fx-background-color: #218838; -fx-text-fill: white;");
        });// Aksi ketika mouse masuk (hover)
        tombolAksi.setOnMouseExited(e -> {
            tombolAksi.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        });// Aksi ketika mouse keluar
        tombolAksi.setOnAction(e -> prosesTebakan()); // Aksi ketika tombol ditekan

        labelPercobaan = new Label("Jumlah percobaan: 0");      // Label percobaan
        labelPercobaan.setFont(Font.font("Arial", 12));

        VBox layout = new VBox(10, judul, labelStatus, inputTebakan, tombolAksi, labelPercobaan);   // Layout
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F4F8D3;");

        Scene scene = new Scene(layout, 700, 350);
        primaryStage.setTitle("Tebak Angka Dulu Boss");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.isFullScreen();
    }

    void prosesTebakan() {
        String input = inputTebakan.getText().trim();

        if (tombolAksi.getText().equals("Main Lagi")) {
            resetGame();
            return;
        }

        if (input.isEmpty() || !input.matches("\\d+")) {
            labelStatus.setTextFill(Color.ORANGE);
            labelStatus.setText("Masukkan angka yang valid!");
            return;
        }
        int tebakan = Integer.parseInt(input);
        jumlahPercobaan++;
        labelPercobaan.setText("Jumlah percobaan: " + jumlahPercobaan);

        if (tebakan > angkaRahasia) {
            labelStatus.setTextFill(Color.ORANGE);
            labelStatus.setText("😝 Terlalu besar!");
        } else if (tebakan < angkaRahasia) {
            labelStatus.setTextFill(Color.ORANGE);
            labelStatus.setText("😝 Terlalu kecil!");
        } else {
            labelStatus.setTextFill(Color.GREEN);
            labelStatus.setText("✔ Tebakan benar!");
            Toolkit.getDefaultToolkit().beep();
            tombolAksi.setText("Main Lagi");
        }
        inputTebakan.clear();
    }
    void resetGame() {
        angkaRahasia = new Random().nextInt(100) + 1;
        jumlahPercobaan = 0;
        labelStatus.setText("");
        labelPercobaan.setText("Jumlah percobaan: 0");
        tombolAksi.setText("Coba Tebak!");
        inputTebakan.clear();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
