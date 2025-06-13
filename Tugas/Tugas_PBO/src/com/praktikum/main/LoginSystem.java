package com.praktikum.main;

import com.praktikum.data.DataStore;
import com.praktikum.users.*;
import com.praktikum.data.Item;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSystem {
    public static ObservableList<User> userList = DataStore.getUserList();
    public static List<Item> reportedItems = new ArrayList<>();


    public static void main(String[] args) {
        userList.add(new Mahasiswa("Muran", "504"));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistem Login ===");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    Admin admin = new Admin();
                    System.out.print("Username: ");
                    String user = scanner.nextLine();
                    System.out.print("Password: ");
                    String pass = scanner.nextLine();
                    if (admin.login(user, pass)) {
                        System.out.println("Login berhasil sebagai Admin.");
                        admin.displayInfo();
                        admin.displayAppMenu(); // Setelah selesai, kembali ke menu login
                    } else {
                        System.out.println("Login gagal!");
                    }
                }
                case "2" -> {
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();

                    boolean found = false;
                    for (User user : userList) {
                        if (user instanceof Mahasiswa mhs && mhs.getNama().equals(nama) && mhs.getNim().equals(nim)) {
                            System.out.println("Login berhasil sebagai Mahasiswa.");
                            mhs.displayAppMenu(); // Setelah selesai, kembali ke menu login
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Mahasiswa tidak ditemukan!");
                    }
                }
                case "0" -> {
                    System.out.println("Aplikasi ditutup. Terima kasih!");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }
}
