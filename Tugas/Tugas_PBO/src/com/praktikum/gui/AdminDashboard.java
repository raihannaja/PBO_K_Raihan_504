package com.praktikum.gui;

import com.praktikum.data.DataStore;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;
import com.praktikum.users.Mahasiswa;

public class AdminDashboard {
    private TableView<Item> itemTable;
    private TableView<Mahasiswa> mahasiswaTable;

    public void show(Stage stage) {
        Label greeting = new Label("Halo, Administrator admin");
        greeting.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");

        // Table: Laporan Barang
        itemTable = new TableView<>(DataStore.getReportedItems());

        TableColumn<Item, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(cell -> cell.getValue().itemNameProperty());

        TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
        lokasiCol.setCellValueFactory(cell -> cell.getValue().locationProperty());

        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cell -> cell.getValue().statusProperty());

        itemTable.getColumns().addAll(namaCol, lokasiCol, statusCol);
        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button btnTandai = new Button("Tandai Claimed");
        btnTandai.setOnAction(e -> tandaiClaimed());

        VBox leftBox = new VBox(10, new Label("Laporan Barang"), itemTable, btnTandai);
        leftBox.setPadding(new Insets(10));
        leftBox.setPrefWidth(400);

        // Table: Data Mahasiswa
        ObservableList<Mahasiswa> mahasiswaData = DataStore.getMahasiswaList();
        mahasiswaTable = new TableView<>(mahasiswaData);

        TableColumn<Mahasiswa, String> namaMhsCol = new TableColumn<>("Nama");
        namaMhsCol.setCellValueFactory(cell -> cell.getValue().namaProperty());

        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(cell -> cell.getValue().nimProperty());

        mahasiswaTable.getColumns().addAll(namaMhsCol, nimCol);
        mahasiswaTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TextField namaField = new TextField();
        namaField.setPromptText("Nama Mahasiswa");

        TextField nimField = new TextField();
        nimField.setPromptText("NIM");

        Button btnTambah = new Button("Tambah");
        btnTambah.setPrefWidth(100);
        btnTambah.setOnAction(e -> {
            String nama = namaField.getText();
            String nim = nimField.getText();
            if (!nama.isEmpty() && !nim.isEmpty()) {
                Mahasiswa mhs = new Mahasiswa(nama, nim);
                DataStore.addUser(mhs);
                mahasiswaTable.getItems().add(mhs); // update tabel
                namaField.clear();
                nimField.clear();
            }
        });

        Button btnHapus = new Button("Hapus");
        btnHapus.setPrefWidth(80);
        btnHapus.setOnAction(e -> {
            Mahasiswa selected = mahasiswaTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DataStore.removeUser(selected); // Hapus dari userList
                mahasiswaTable.getItems().remove(selected); // Refresh tampilan
            }
        });

        HBox inputBox = new HBox(5, namaField, nimField, btnTambah, btnHapus);
        VBox rightBox = new VBox(10, new Label("Data Mahasiswa"), mahasiswaTable, inputBox);
        rightBox.setPadding(new Insets(10));
        rightBox.setPrefWidth(400);

        Button btnLogout = new Button("Logout");
        btnLogout.setOnAction(e -> {
            LoginPane loginPane = new LoginPane(LoginSystem.userList, user -> {
                if (user instanceof Mahasiswa mhs) {
                    new MahasiswaDashboard().showDashboard(stage, mhs);
                } else if (user instanceof com.praktikum.users.Admin) {
                    new AdminDashboard().show(stage);
                }
            });
            Scene loginScene = new Scene(loginPane, 500, 350);
            stage.setScene(loginScene);
            stage.setTitle("Login Kembali");
            stage.show();
        });

        HBox rootBox = new HBox(20, leftBox, rightBox);
        VBox layout = new VBox(10, greeting, rootBox, btnLogout);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        Scene scene = new Scene(layout, 900, 400);
        stage.setTitle("Admin Menu");
        stage.setScene(scene);
        stage.show();
    }

    private void tandaiClaimed() {
        Item selected = itemTable.getSelectionModel().getSelectedItem();
        if (selected != null && selected.getStatus().equals("Reported")) {
            selected.setStatus("Claimed");
            itemTable.refresh();
        }
    }
}
