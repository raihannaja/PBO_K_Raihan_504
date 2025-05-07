package com.praktikum.users;
import java.util.Scanner;
import com.praktikum.actions.AdminActions;

public class Admin extends User implements AdminActions {
    Scanner scanner = new Scanner(System.in);
    private String username;
    private String password;

    @Override
    public void manageItems() {
        System.out.println(">> Fitur Kelola Barang Belum Tersedia <<");
    }

    @Override
    public void manageUsers() {
        System.out.println(">> Fitur Kelola MAhasiswa Belum Tersedia <<");
    }

    @Override
    public boolean login() {
        System.out.print("Masukan Username: ");
        username = scanner.nextLine();
        System.out.print("Masukan Password: ");
        password = scanner.nextLine();

        if (username.equals("adm504") && password.equals("pass504")){
            System.out.println("Login sebagai admin berhasil");
            displayAppmenu();
        }else {
            System.out.println("Username atau Password salah");
        }
        return false;
    }
    @Override
    public void displayAppmenu() {
        int pilihan;
        do {
            System.out.println("Pilih Menu ( 1 - 3 ) : ");
            System.out.print(" 1.Kelola Laporan Barang \n 2.Kelola Data Mahasiswa \n 3.Keluar\nMasukan Pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan){
                case 1:manageItems();break;
                case 2:manageUsers();break;
                case 3:
                    System.out.println("Keluar....");break;
            }
        }while(pilihan != 3 );
    }
}