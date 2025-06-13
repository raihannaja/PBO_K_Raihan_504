package com.praktikum.users;
import com.praktikum.actions.AdminActions;
import com.praktikum.main.LoginSystem;
import com.praktikum.data.Item;
import java.util.Scanner;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;

    public Admin() {
        super("Muran", "504");
        this.username = "Adm504";
        this.password = "Pass504";
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    @Override
    public void displayInfo() {
        System.out.println("Informasi Admin:");
        super.displayInfo();
    }

    @Override
    public void manageItems() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n=== Kelola Barang ===");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (pilihan) {
                    case 1 -> viewAllItems();
                    case 2 -> markItemAsClaimed(scanner);
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

    private void viewAllItems() {
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        System.out.println("\n=== Daftar Semua Laporan Barang ===");
        for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
            //Iterator
            Item item = LoginSystem.reportedItems.get(i);
            System.out.println("[" + (i + 1) + "] " + item.getItemName() +
                    " - " + item.getLocation() +
                    " - Status: " + item.getStatus());
        }
    }

    private void markItemAsClaimed(Scanner scanner) {
        viewAllItems();
        if (LoginSystem.reportedItems.isEmpty()) return;

        System.out.print("\nMasukkan nomor barang yang akan ditandai sebagai diambil: ");
        try {
            int index = scanner.nextInt() - 1;
            if (index >= 0 && index < LoginSystem.reportedItems.size()) {
                //ini ArrayList
                Item item = LoginSystem.reportedItems.get(index);
                if (item.getStatus().equals("Reported")) {
                    item.setStatus("Claimed");
                    System.out.println("Barang berhasil ditandai sebagai diambil.");
                } else {
                    System.out.println("Barang sudah ditandai sebelumnya.");
                }
            } else {
                System.out.println("Nomor barang tidak valid.");
            }
        } catch (Exception e) {
            System.out.println("Input harus berupa angka yang valid!");
        }
    }

    @Override
    public void manageUsers() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n=== Kelola Mahasiswa ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (pilihan) {
                    case 1 -> addMahasiswa(scanner);
                    case 2 -> removeMahasiswa(scanner);
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

    private void addMahasiswa(Scanner scanner) {
        System.out.println("\n=== Tambah Mahasiswa ===");
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        Mahasiswa mahasiswa = new Mahasiswa();
        LoginSystem.userList.add(mahasiswa);
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    private void removeMahasiswa(Scanner scanner) {
        System.out.println("\n=== Hapus Mahasiswa ===");
        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
        String nim = scanner.nextLine();

        boolean found = false;
        //Iterator
        for (User user : LoginSystem.userList) {
            if (user instanceof Mahasiswa && user.getNim().equals(nim)) {
                LoginSystem.userList.remove(user);
                System.out.println("Mahasiswa dengan NIM " + nim + " berhasil dihapus.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Pilihan: ");

            //ini Exceptions
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (pilihan) {
                    case 1 -> manageItems();
                    case 2 -> manageUsers();
                    case 0 -> System.out.println("Logout...");
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
