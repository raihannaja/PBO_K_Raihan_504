package view;

import controller.ScheduleController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Schedule;
import model.User;

public class ScheduleManagerView extends VBox {
    private final TableView<Schedule> table = new TableView<>();
    private final Stage stage;
    private final User user;

    public ScheduleManagerView(Stage stage, User user) {
        this.stage = stage;
        this.user = user;

        setPadding(new Insets(10));
        setSpacing(10);

        TableColumn<Schedule, String> matkulCol = new TableColumn<>("Matkul");
        matkulCol.setCellValueFactory(cell -> cell.getValue().matkulProperty());

        TableColumn<Schedule, String> hariCol = new TableColumn<>("Hari");
        hariCol.setCellValueFactory(cell -> cell.getValue().hariProperty());

        TableColumn<Schedule, String> jamCol = new TableColumn<>("Jam");
        jamCol.setCellValueFactory(cell -> cell.getValue().jamProperty());

        TableColumn<Schedule, String> ruanganCol = new TableColumn<>("Ruangan");
        ruanganCol.setCellValueFactory(cell -> cell.getValue().ruanganProperty());

        table.getColumns().addAll(matkulCol, hariCol, jamCol, ruanganCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Tombol
        Button addBtn = new Button("Tambah");
        addBtn.setOnAction(e -> {
            Schedule s = new Schedule("", "", "", "");
            if (showForm(s, false)) {
                if (s.isValid()) {
                    table.getItems().add(s);
                    save();
                } else {
                    showError("Data jadwal tidak valid. Pastikan kolom tidak kosong.");
                }
            }
        });

        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> {
            Schedule selected = table.getSelectionModel().getSelectedItem();
            if (selected != null && showForm(selected, true)) {
                if (selected.isValid()) {
                    save();
                } else {
                    showError("Data jadwal yang diedit tidak valid.");
                }
            }
        });

        Button deleteBtn = new Button("Hapus");
        deleteBtn.setOnAction(e -> {
            Schedule selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                table.getItems().remove(selected);
                save();
            }
        });

        Button backBtn = new Button("Kembali");
        backBtn.setOnAction(e -> stage.setScene(MainMenuView.createMainMenuScene(stage, user)));

        HBox buttonBox = new HBox(10, addBtn, editBtn, deleteBtn, backBtn);
        getChildren().addAll(table, buttonBox);

        load();
    }

    private void load() {
        ObservableList<Schedule> schedules = table.getItems();
        schedules.setAll(ScheduleController.loadSchedules());
    }

    private void save() {
        ScheduleController.saveSchedules(table.getItems());
    }

    private boolean showForm(Schedule s, boolean isEdit) {
        Stage dialog = new Stage();
        dialog.setTitle(isEdit ? "Edit Jadwal" : "Tambah Jadwal");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField matkulField = new TextField(s.getMatkul());
        TextField hariField = new TextField(s.getHari());
        TextField jamField = new TextField(s.getJam());
        TextField ruangField = new TextField(s.getRuangan());

        grid.addRow(0, new Label("Matkul"), matkulField);
        grid.addRow(1, new Label("Hari"), hariField);
        grid.addRow(2, new Label("Jam"), jamField);
        grid.addRow(3, new Label("Ruangan"), ruangField);

        Button simpan = new Button(isEdit ? "Simpan" : "Tambah");
        Button batal = new Button("Batal");

        final boolean[] updated = {false};

        simpan.setOnAction(e -> {
            s.setMatkul(matkulField.getText());
            s.setHari(hariField.getText());
            s.setJam(jamField.getText());
            s.setRuangan(ruangField.getText());
            updated[0] = true;
            dialog.close();
        });

        batal.setOnAction(e -> dialog.close());

        VBox dialogLayout = new VBox(10, grid, new HBox(10, simpan, batal));
        dialogLayout.setPadding(new Insets(10));

        dialog.setScene(new Scene(dialogLayout));
        dialog.showAndWait();
        return updated[0];
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validasi Gagal");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
