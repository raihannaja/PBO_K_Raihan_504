package com.praktikum.gui;

import com.praktikum.data.DataStore;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;
import com.praktikum.users.Mahasiswa;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

    public class MahasiswaDashboard {
        private  Mahasiswa mahasiswa;
        public void showDashboard(Stage stage, Mahasiswa mahasiswa) {
            this.mahasiswa = mahasiswa;

            stage.setTitle("Mahasiswa Menu");
            Label welcomeLabel = new Label("Selamat datang, " + mahasiswa.getNama());
            Label instructionLabel = new Label("Laporkan Barang Hilang/Temuan");

            TextField tfNamaBarang = new TextField();
            tfNamaBarang.setPromptText("Nama Barang");

            TextField tfDeskripsi = new TextField();
            tfDeskripsi.setPromptText("Deskripsi");

            TextField tfLokasi = new TextField();
            tfLokasi.setPromptText("Lokasi");

            Button btnLaporkan = new Button("Laporkan");

            TableView<Item> tableView = new TableView<>();
            TableColumn<Item, String> namaColom = new TableColumn<>("Nama");
            namaColom.setCellValueFactory(new PropertyValueFactory<>("itemName"));

            TableColumn<Item, String> deskColom = new TableColumn<>("Deskripsi");
            deskColom.setCellValueFactory(new PropertyValueFactory<>("description")); // pastikan getter bernama getDescription()

            TableColumn<Item, String> lokasiCol = new TableColumn<>("Lokasi");
            lokasiCol.setCellValueFactory(new PropertyValueFactory<>("location"));

            tableView.getColumns().addAll(namaColom, deskColom, lokasiCol);
            tableView.setItems(DataStore.getReportedItems());
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            btnLaporkan.setOnAction(e -> {
                String nama = tfNamaBarang.getText();
                String deskripsi = tfDeskripsi.getText();
                String lokasi = tfLokasi.getText();
                if (!nama.isEmpty() && !deskripsi.isEmpty() && !lokasi.isEmpty()) {
                    Item item = new Item(nama, deskripsi, lokasi);
                    DataStore.addItem(item);
                    tfNamaBarang.clear();
                    tfDeskripsi.clear();
                    tfLokasi.clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Semua kolom harus diisi.", ButtonType.OK);
                    alert.showAndWait();
                }
            });

            Button btnLogout = new Button("Logout");
            btnLogout.setOnAction(e -> {
                LoginPane loginPane = new LoginPane(LoginSystem.userList, user -> {
                    if (user instanceof com.praktikum.users.Admin) {
                        new AdminDashboard().show(stage);
                    } else if (user instanceof Mahasiswa mhs) {
                        new MahasiswaDashboard().showDashboard(stage, mhs);
                    }
                });
                Scene loginScene = new Scene(loginPane, 500, 350);
                stage.setScene(loginScene);
                stage.setTitle("Login Kembali");
            });


            HBox inputBox = new HBox(5, tfNamaBarang, tfDeskripsi, tfLokasi, btnLaporkan);
            VBox mainLayout = new VBox(10, welcomeLabel, instructionLabel, inputBox,
                    new Label("Daftar Laporan Anda"), tableView, btnLogout);
            mainLayout.setPadding(new Insets(15));

            Scene scene = new Scene(mainLayout, 900, 400);
            stage.setScene(scene);
            stage.show();
        }
    }
