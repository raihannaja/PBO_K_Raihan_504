import java.util.Scanner;
import java.awt.Toolkit;

public class Mahasiswa extends User {
    Scanner inputan = new Scanner(System.in);

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public void login() {
        System.out.print("Masukkan Nama: ");
        String inputNama = inputan.nextLine();
        System.out.print("Masukkan NIM: ");
        String inputNim = inputan.nextLine();

        if (inputNama.equals(getNama()) && inputNim.equals(getNim())) {
            displayInfo();
        } else {
            System.out.println("Maaf '_' Login Mahasiswa Gagal !, Nama / Nim salah");
        }
    }

    @Override
    public void displayInfo() {
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Selamat Login Mahasiswa Berhasil");
        System.out.println("Nama: " + getNama());
        System.out.println("Nim: " + getNim());
    }
}
