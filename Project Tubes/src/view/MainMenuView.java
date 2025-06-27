package view;

import controller.TaskController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Task;
import model.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MainMenuView extends VBox {
    public static Scene createMainMenuScene(Stage primaryStage, User user) {
        // Header (setengah lingkaran hijau)
        StackPane header = new StackPane();
        header.setPrefHeight(150);
        header.setStyle("-fx-background-color: #00c853; -fx-background-radius: 0 0 200 200;");

        VBox headerText = new VBox(5);
        Text welcome = new Text(user.displayWelcome());
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        welcome.setFill(Color.WHITE);

        Text title = new Text("Selamat Datang Di Brain");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setFill(Color.WHITE);

        Text subtitle = new Text("silahkan pilih menu");
        subtitle.setFont(Font.font("Arial", 18));
        subtitle.setFill(Color.WHITE);

        headerText.getChildren().addAll(welcome, title, subtitle);
        headerText.setAlignment(Pos.CENTER);
        header.getChildren().add(headerText);

        // Tombol
        Button manageTaskBtn = new Button("Manage Tugas");
        Button manageScheduleBtn = new Button("Manage Jadwal");
        Button logoutBtn = new Button("Logout");

        // Notifikasi Icon
        Button notifIcon = new Button("🔔");
        notifIcon.setFont(Font.font(20));
        notifIcon.setStyle("-fx-background-color: transparent;");
        Tooltip tooltip = new Tooltip("Klik untuk melihat tugas belum selesai");
        Tooltip.install(notifIcon, tooltip);

        notifIcon.setOnAction(e -> {
            List<Task> pendingTasks = new TaskController().loadTasks()
                    .stream()
                    .filter(t -> !t.isDone())
                    .collect(Collectors.toList());

            if (pendingTasks.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notifikasi Tugas");
                alert.setHeaderText(null);
                alert.setContentText("Semua tugas sudah diselesaikan!");
                alert.showAndWait();
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime now = LocalDateTime.now();
            StringBuilder message = new StringBuilder("Anda memiliki tugas belum selesai:\n\n");

            int i = 0;
            do {
                Task task = pendingTasks.get(i);
                try {
                    LocalDateTime deadline = LocalDateTime.parse(task.getDeadline(), formatter);
                    Duration diff = Duration.between(now, deadline);
                    String waktu;

                    if (diff.isNegative()) {
                        long days = Math.abs(diff.toDays());
                        long hours = Math.abs(diff.toHours()) % 24;
                        waktu = days > 0
                                ? String.format("(lewat %d hari %d jam)", days, hours)
                                : String.format("(lewat %d jam)", Math.abs(diff.toHours()));
                    } else {
                        long days = diff.toDays();
                        long hours = diff.toHours() % 24;
                        if (days > 0) {
                            waktu = String.format("(tinggal %d hari %d jam)", days, hours);
                        } else {
                            long minutes = diff.toMinutes() % 60;
                            waktu = String.format("(tinggal %d jam %d menit)", diff.toHours(), minutes);
                        }
                    }
                    message.append("- ").append(task.getName()).append(" ").append(waktu).append("\n");

                } catch (Exception ex) {
                    message.append("- ").append(task.getName()).append(" (format deadline salah)\n");
                }
                i++;
            } while (i < pendingTasks.size());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tugas Belum Selesai");
            alert.setHeaderText("Berikut daftar tugas:");
            alert.setContentText(message.toString());
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        });

        // Style tombol
        Button[] buttons = {manageTaskBtn, manageScheduleBtn, logoutBtn};
        for (Button btn : buttons) {
            btn.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 16));
            btn.setStyle("-fx-background-color: #00c853; -fx-text-fill: white; -fx-background-radius: 10;");
            btn.setPrefSize(150, 80);
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #009624; -fx-text-fill: white; -fx-background-radius: 10;"));
            btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #00c853; -fx-text-fill: white; -fx-background-radius: 10;"));
        }
        logoutBtn.setPrefSize(120, 40);

        // Aksi tombol
        manageTaskBtn.setOnAction(e -> {
            TaskManagerView taskManager = new TaskManagerView(primaryStage, user);
            Scene taskScene = new Scene(taskManager, 700, 400);
            primaryStage.setScene(taskScene);
        });

        manageScheduleBtn.setOnAction(e -> {
            ScheduleManagerView scheduleManager = new ScheduleManagerView(primaryStage, user);
            Scene scheduleScene = new Scene(scheduleManager, 700, 400);
            primaryStage.setScene(scheduleScene);
        });

        logoutBtn.setOnAction(e -> LoginView.show(primaryStage));

        // Layout tombol dan notifikasi
        HBox notifBox = new HBox(notifIcon);
        notifBox.setAlignment(Pos.TOP_RIGHT);
        notifBox.setPadding(new Insets(10, 20, 0, 0));

        HBox mainButtons = new HBox(40, manageTaskBtn, manageScheduleBtn);
        mainButtons.setAlignment(Pos.CENTER);

        VBox buttonLayout = new VBox(10, notifBox, mainButtons, logoutBtn);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(20, 0, 0, 0));

        VBox mainLayout = new VBox(header, buttonLayout);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: white;");

        return new Scene(mainLayout, 700, 400);
    }
}
