import java.util.Scanner;
class LoginSimpel {
    public static void main(String[] args) {
        Scanner inputan = new Scanner(System.in);
        // membuat objek
        Admin admin = new Admin("Muran", "504", "Adm504", "Pass504");
        Mahasiswa mhs = new Mahasiswa("Muran", "504");

        int pilihan;
        do { // Perulangan untuk memilih jenis login
            System.out.print("Pilih Login: \n1. Admin \n2. Mahasiswa \n3. Exit\nMasukan Pilihan: ");
            pilihan = inputan.nextInt();
            inputan.nextLine();

            if (pilihan == 1){
                admin.login();
            } else if (pilihan == 2){
                mhs.login();
            } else if (pilihan == 3) { // Pilihan untuk keluar
                System.out.println("Terima kasih! Anda telah keluar.");
            } else {
                System.out.println("Pilihan Anda Tidak Valid !, Pilih 1 atau 2 !");
            }
        } while (pilihan != 3); // Loop akan terus berjalan sampai pilihan 3
        inputan.close();
    }
}