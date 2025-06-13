package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.main.LoginSystem;
import com.praktikum.data.Item;
import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa() {
        super("Muran", "504");
    }

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim) {
        return getNama().equals(inputNama) && getNim().equals(inputNim);
    }

    @Override
    public void displayInfo() {
        System.out.println("Informasi Mahasiswa:");
        super.displayInfo();
    }

    @Override
    public void reportItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Laporan Barang ===");
        System.out.print("Nama Barang: ");
        String namaBarang = scanner.nextLine();
        System.out.print("Deskripsi Barang: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Lokasi Terakhir/Ditemukan: ");
        String lokasi = scanner.nextLine();

        Item newItem = new Item(namaBarang, deskripsi, lokasi);
        LoginSystem.reportedItems.add(newItem);
        System.out.println(">> Laporan telah diterima. Terima kasih! <<");
    }

    @Override
    public void viewReportedItems() {
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        System.out.println("\n=== Daftar Laporan Barang ===");
        for (Item item : LoginSystem.reportedItems) {
            if (item.getStatus().equals("Reported")) {
                System.out.println("Nama Barang: " + item.getItemName());
                System.out.println("Deskripsi: " + item.getDescription());
                System.out.println("Lokasi: " + item.getLocation());
                System.out.println("-----------------------------");
            }
        }
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n=== Menu Mahasiswa ===");
            System.out.println("1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (pilihan) {
                    case 1 -> reportItem();
                    case 2 -> viewReportedItems();
                    case 0 -> System.out.println("Kembali ke menu utama...");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine(); // Clear invalid input
                pilihan = -1;
            }
        } while (pilihan != 0);
    }
}
