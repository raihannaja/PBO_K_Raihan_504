package com.praktikum.main;

import com.praktikum.users.*;
import java.util.Scanner;

public class LoginSistim {
    public static void main(String[] args) {
    int pilihan;
    User user = null;
    Scanner scanner = new Scanner(System.in);

        System.out.print("Pilih Login: \n1.Login Sebagai Admin \n2.Login Sebagai Mahasiswa \nMasukan Pilihan: ");
        pilihan = scanner.nextInt();

        if (pilihan == 1){
            user = new Admin();
        } else if (pilihan == 2) {
            user = new Mahasiswa();
        }else {
            System.out.println("Inputan Tidak Valid");
            return;
        }
        if (user.login()){
            user.displayAppmenu();
        }
    }
}