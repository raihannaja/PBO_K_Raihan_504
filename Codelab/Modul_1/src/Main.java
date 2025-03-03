import java.util.Scanner;

public class Main{
    public static void main (String [] agrs){
        Scanner scanner = new Scanner(System.in);

        String name;
        int birth;
        String jenisKelamin;

        System.out.print("Masukan nama anda: ");
        name = scanner.nextLine();
        System.out.print("Masukkan Jenis Kelamin (L/P): ");
        String gender = scanner.next().toUpperCase(); // Mengubah inputan menjadi huruf besar

        // Menentukan jenis kelamin
        if (gender.equals("L")) {
            jenisKelamin = "Laki-laki";
        } else if (gender.equals("P")) {
            jenisKelamin = "Perempuan";
        } else {
            jenisKelamin = "Tidak diketahui";
        }
        System.out.print("Masukan Tahun Lahir : ");
        birth = scanner.nextInt();

        // fungsi untuk menghitung usia
        int tahunSekarang = 2025;
        int usia = tahunSekarang - birth;

        System.out.println("Data Diri :");
        System.out.println("Nama : " + name);
        System.out.println("Jenis Kelamin : " + jenisKelamin);
        System.out.println("Tahun Lahir : " + birth);
        System.out.println("Usia : " + usia);

        scanner.close(); // Menutup scanner untuk menghindari kebocoran sumber daya
    }
}
