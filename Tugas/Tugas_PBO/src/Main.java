import java.util.Scanner;
class LoginSimpel {
    public static void main(String[] args) {
        Scanner inputan = new Scanner(System.in);

        // membuat objek
        Admin admin = new Admin();
        Mahasiswa mhs = new Mahasiswa();

        //bagian memilih jenis login
        System.out.print("Pilih Login: \n1.Admin \n2.Mahasiswa\nMasukan Pilihan: ");
        int pilihan = inputan.nextInt();
        inputan.nextLine();

        //Login untuk admin
        if (pilihan == 1) {
            System.out.print("Masukan Username: ");
            String username = inputan.nextLine();
            System.out.print("Masukan Password: ");
            String password = inputan.nextLine();

        // pengecekan kondisi pilihan 1
            if (admin.login(username, password)){
                admin.displayInfo();
            }else {
                System.out.println("Maaf '_' Login Gagal ! Username / Password salah");
            }
        } else if (pilihan == 2) { //Login untuk mahasiswa
            System.out.print("Masukan Nama: ");
            String nama = inputan.nextLine();
            System.out.print("Masukan Nim: ");
            long nim = inputan.nextLong();
            inputan.nextLine();
            // pengecekan kondisi pilihan 2
            if (mhs.login(nama ,nim)){
                mhs.displayInfo(nama,nim);
            } else {
                System.out.println("Maaf '_' Login Gagal ! Nama / Nim salah");
            }
        }else {
            System.out.println("Pilihan Anda Tidak Valid !");
            }
        inputan.close();
        }
}