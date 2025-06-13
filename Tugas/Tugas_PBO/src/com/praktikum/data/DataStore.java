package com.praktikum.data;

import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import com.praktikum.data.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {

    // ObservableList untuk daftar item yang dilaporkan
    private static final ObservableList<Item> reportedItems = FXCollections.observableArrayList();

    // ObservableList untuk daftar user (termasuk Mahasiswa/Admin)
    private static final ObservableList<User> userList = FXCollections.observableArrayList();

    static {
        // Tambah data awal
        Mahasiswa m1 = new Mahasiswa("Haji", "969");
        Mahasiswa m2 = new Mahasiswa("Panjul", "555");
        userList.addAll(m1, m2);
    }

    // Mendapatkan daftar semua item yang dilaporkan
    public static ObservableList<Item> getReportedItems() {
        return reportedItems;
    }

    // Mendapatkan daftar semua user
    public static ObservableList<User> getUserList() {
        return userList;
    }

    // Mendapatkan daftar khusus mahasiswa
    public static ObservableList<Mahasiswa> getMahasiswaList() {
        ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList();
        for (User user : userList) {
            if (user instanceof Mahasiswa mahasiswa) {
                mahasiswaList.add(mahasiswa);
            }
        }
        return mahasiswaList;
    }

    // Tambah user ke list
    public static void addUser(User user) {
        userList.add(user);
    }

    // Tambah item ke list
    public static void addItem(Item item) {
        reportedItems.add(item);
    }

    // Hapus user dari list
    public static void removeUser(User user) {
        userList.remove(user);
    }

    // Hapus item dari list
    public static void removeItem(Item item) {
        reportedItems.remove(item);
    }
}
