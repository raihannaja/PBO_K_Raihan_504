import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputan = new Scanner(System.in); /* menambahkan fitur scanner agar dapat
        menentukan poin kesehatan sesuka hati */

        // Meminta input poin kesehatan untuk Pahlawan dan Musuh
        System.out.print("Masukkan poin kesehatan untuk Super Dede: ");
        int kesehatanSupde = inputan.nextInt();

        System.out.print("Masukkan poin kesehatan untuk Bencong: ");
        int kesehatanBoti = inputan.nextInt();

        //membuat objek pahlawan dan musuh
        Pahlawan superdede = new Pahlawan("Super Dede", kesehatanSupde);
        Musuh boti = new Musuh("Bencong", kesehatanBoti);

        // Menampilkan status awal kesehatan Pahlawan dan Musuh
        System.out.println("\nStatus awal:");
        System.out.println(superdede.getNama() + " memiliki kesehatan: " + superdede.getKesehatan());
        System.out.println(boti.getNama() + " memiliki kesehatan: " + boti.getKesehatan());

        // Menjalankan method serang untuk mensimulasikan pertarungan
        superdede.serang(boti);
        boti.serang(superdede);
    }
}
