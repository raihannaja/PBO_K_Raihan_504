import java.util.Scanner;
import java.awt.Toolkit;

public class Admin extends User {
    private String username;
    private String password;
    Scanner inputan = new Scanner(System.in);

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public void login() {
        System.out.print("Masukkan Username: ");
        String inputUser = inputan.nextLine();
        System.out.print("Masukkan Password: ");
        String inputPass = inputan.nextLine();
        if (inputUser.equals(this.username) && inputPass.equals(this.password)) {
            displayInfo();
        } else {
            System.out.println("Maaf '_' Login Admin Gagal ! Username / Password salah");
        }
    }

    @Override
    public void displayInfo() {
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Selamat Login Admin Berhasil");
    }
}