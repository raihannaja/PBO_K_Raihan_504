package view;

import controller.TaskController;
import javafx.scene.Scene;
import model.Task;
import model.User;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskManagerView extends VBox {
    private final TableView<Task> table;
    private final TaskController controller = new TaskController();
    private final Stage stage;
    private final User user;

    public TaskManagerView(Stage primaryStage, User user) {
        this.stage = primaryStage;
        this.user = user;
        setPadding(new Insets(10));
        setSpacing(10);
        table = new TableView<>();

        // Kolom-kolom
        TableColumn<Task, String> nameCol = new TableColumn<>("Tugas");
        nameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));

        TableColumn<Task, String> dateCol = new TableColumn<>("Tanggal Diberikan");
        dateCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getGivenDate()));

        TableColumn<Task, String> deadlineCol = new TableColumn<>("Batas Waktu");
        deadlineCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getDeadline()));

        TableColumn<Task, String> descCol = new TableColumn<>("Keterangan");
        descCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getDescription()));

        TableColumn<Task, String> statusCol = new TableColumn<>("Sudah/Belum");
        statusCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getStatusText()));

        table.getColumns().addAll(nameCol, dateCol, deadlineCol, descCol, statusCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Tombol
        Button addBtn = new Button("Tambah");
        addBtn.setOnAction(e -> {
            Task task = new Task("", "", "", "", false);
            if (showEditDialog(task, false)) {
                if (task.isValid()) {
                    controller.saveTask(task);
                    loadTasks();
                } else {
                    showError("Data tugas tidak valid. Pastikan semua kolom terisi.");
                }
            }
        });


        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            Task selected = table.getSelectionModel().getSelectedItem();
            if (selected != null && showEditDialog(selected, true)) {
                if (selected.isValid()) {
                    controller.saveAllTasks(table.getItems());
                    loadTasks();
                } else {
                    showError("Data tugas yang diedit tidak valid.");
                }
            }
        });


        Button deleteBtn = new Button("Hapus");
        deleteBtn.setOnAction(e -> {
            Task selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                table.getItems().remove(selected);
                controller.saveAllTasks(table.getItems());
                loadTasks();
            }
        });

        Button backBtn = new Button("Kembali");
        backBtn.setOnAction(e -> stage.setScene(view.MainMenuView.createMainMenuScene(stage, user)));

        HBox buttonBox = new HBox(10, addBtn, editBtn, deleteBtn, backBtn);
        getChildren().addAll(table, buttonBox);

        loadTasks();
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validasi Gagal");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void loadTasks() {
        ObservableList<Task> tasks = controller.loadTasks();
        table.setItems(tasks);
    }

    private boolean showEditDialog(Task task, boolean isEdit) {
        Stage dialog = new Stage();
        dialog.setTitle(isEdit ? "EDIT TUGAS" : "TAMBAH TUGAS");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField(task.getName());
        TextField descField = new TextField(task.getDescription());

        // Tanggal Diberikan
        DatePicker givenDatePicker = new DatePicker();
        Spinner<Integer> givenHour = new Spinner<>(0, 23, 12);
        Spinner<Integer> givenMinute = new Spinner<>(0, 59, 0);

        // Deadline
        DatePicker deadlinePicker = new DatePicker();
        Spinner<Integer> deadlineHour = new Spinner<>(0, 23, 12);
        Spinner<Integer> deadlineMinute = new Spinner<>(0, 59, 0);

        // Prefill jika edit
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            if (!task.getGivenDate().isEmpty()) {
                LocalDateTime given = LocalDateTime.parse(task.getGivenDate(), formatter);
                givenDatePicker.setValue(given.toLocalDate());
                givenHour.getValueFactory().setValue(given.getHour());
                givenMinute.getValueFactory().setValue(given.getMinute());
            }
            if (!task.getDeadline().isEmpty()) {
                LocalDateTime deadline = LocalDateTime.parse(task.getDeadline(), formatter);
                deadlinePicker.setValue(deadline.toLocalDate());
                deadlineHour.getValueFactory().setValue(deadline.getHour());
                deadlineMinute.getValueFactory().setValue(deadline.getMinute());
            }
        } catch (Exception ignored) {}

        RadioButton sudahBtn = new RadioButton("Sudah");
        RadioButton belumBtn = new RadioButton("Belum");
        ToggleGroup statusGroup = new ToggleGroup();
        sudahBtn.setToggleGroup(statusGroup);
        belumBtn.setToggleGroup(statusGroup);
        if (task.isDone()) {
            sudahBtn.setSelected(true);
        } else {
            belumBtn.setSelected(true);
        }

        grid.addRow(0, new Label("Nama Tugas"), nameField);

        HBox givenTimeBox = new HBox(5, givenHour, new Label("jam"), givenMinute, new Label("menit"));
        HBox deadlineTimeBox = new HBox(5, deadlineHour, new Label("jam"), deadlineMinute, new Label("menit"));

        grid.addRow(1, new Label("Tanggal Diberikan"), new HBox(5, givenDatePicker, givenTimeBox));
        grid.addRow(2, new Label("Batas Waktu"), new HBox(5, deadlinePicker, deadlineTimeBox));
        grid.addRow(3, new Label("Keterangan"), descField);
        grid.addRow(4, new Label("Status"), new HBox(10, sudahBtn, belumBtn));

        final boolean[] updated = {false};

        Button actionBtn = new Button(isEdit ? "Update Data" : "Tambah Data");
        actionBtn.setOnAction(e -> {
            try {
                LocalDateTime given = givenDatePicker.getValue().atTime(givenHour.getValue(), givenMinute.getValue());
                LocalDateTime deadline = deadlinePicker.getValue().atTime(deadlineHour.getValue(), deadlineMinute.getValue());
                task.setName(nameField.getText());
                task.setGivenDate(given.format(formatter));
                task.setDeadline(deadline.format(formatter));
                task.setDescription(descField.getText());
                String status = sudahBtn.isSelected() ? "Sudah" : "Belum";
                task.setDone(status);
                updated[0] = true;
                dialog.close();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Data tugas tidak valid. Pastikan semua kolom terisi dengan benar.");
                alert.showAndWait();
            }
        });

        Button closeBtn = new Button("Tutup");
        closeBtn.setOnAction(e -> dialog.close());

        VBox dialogBox = new VBox(10, grid, new HBox(10, actionBtn, closeBtn));
        dialogBox.setPadding(new Insets(10));

        dialog.setScene(new Scene(dialogBox, 700, 400));
        dialog.showAndWait();

        return updated[0];
    }
}