package com.praktikum.users;
import java.util.Scanner;
import java.awt.Toolkit;
import com.praktikum.actions.MahasiswaActions;

public class Mahasiswa extends User implements MahasiswaActions {
    private String name;
    private String nim;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void reportItem() {
        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Deskripsi Barang: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Lokasi Ditemukan: ");
        String lokasi = scanner.nextLine();
        System.out.println(">> Laporan Berhasil Disimpan <<");
    }

    @Override
    public void viewReportedItems() {
        System.out.println(">> Fitur Lihat Laporan Belum Tersedia <<");
    }

    @Override
    public boolean login() {
        System.out.print("Masukan Nama: ");
        name = scanner.nextLine();
        System.out.print("Masukan Nim: ");
        nim = scanner.nextLine();

        if (name.equals("Muran") && nim.equals("504")) {
            System.out.println("Login sebagai mahasiswa berhasil");
            displayAppmenu();
        } else {
            System.out.println("Nama atau Nim salah");
        }
        return false;
    }

    @Override
    public void displayAppmenu() {
        int pilihan;
        do {

            System.out.print("Pilihan Menu(1-3): \n 1.Laporkan Barang Temuan / Hilang \n 2.Lihat Daftar Laporan \n 3.Keluar \n Masukan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    reportItem();
                    break;
                case 2:
                    viewReportedItems();
                    break;
                case 3:
                    System.out.println("Keluar....");
                    break;
            }
        }while (pilihan != 3) ;
    }
}