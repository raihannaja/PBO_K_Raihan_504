import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;


        while (isRunning) {
            System.out.println("\n===== Menu Manajemen Stok =====");
            System.out.println("1. Tambah Barang Baru");
            System.out.println("2. Tampilkan Semua Barang");
            System.out.println("3. Kurangi Stok Barang");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");

            try {
                int opsi = scanner.nextInt();
                scanner.nextLine();

                switch (opsi) {
                    case 1:
                        System.out.print("Masukkan nama barang: ");
                        String nama = scanner.nextLine();

                        System.out.print("Masukkan stok awal: ");
                        try {
                            int stok = scanner.nextInt();
                            scanner.nextLine();

                            daftarBarang.add(new Barang(nama, stok));
                            System.out.println("Barang '" + nama + "' berhasil ditambahkan.");
                        } catch (InputMismatchException e) {
                            System.out.println("Input stok harus berupa angka!");
                            scanner.nextLine();
                        }
                        break;

                    case 2:
                        if (daftarBarang.isEmpty()) {
                            System.out.println("Stok barang kosong.");
                        } else {
                            System.out.println("\n--- Daftar Barang ---");
                            for (int i = 0; i < daftarBarang.size(); i++) {
                                Barang b = daftarBarang.get(i);
                                System.out.println(i+1 + ". Nama: " + b.getNama() + ", Stok: " + b.getStok());
                            }
                            System.out.println("------------------------");
                        }
                        break;

                    case 3:
                        if (daftarBarang.isEmpty()) {
                            System.out.println("Tidak ada barang untuk dikurangi.");
                            break;
                        }

                        System.out.println("\n--- Daftar Barang (Pilih untuk Kurangi Stok) ---");
                        for (int i = 0; i < daftarBarang.size(); i++) {
                            Barang b = daftarBarang.get(i);
                            System.out.println(i + 1 + ". " + b.getNama() + " (Stok: " + b.getStok() + ")");
                        }

                        try {
                            System.out.print("Masukkan nomor indeks barang: ");
                            int indeks = scanner.nextInt();

                            System.out.print("Masukkan jumlah stok yang akan diambil: ");
                            int jumlah = scanner.nextInt();

                            if (indeks < 0 || indeks > daftarBarang.size()) {
                                throw new IndexOutOfBoundsException("Indeks tidak valid!");
                            }

                            Barang b = daftarBarang.get(indeks - 1);
                            if (jumlah > b.getStok()) {
                                throw new StokTidakCukupException("Stok untuk '" + b.getNama() +
                                        "' hanya tersisa " + b.getStok());
                            }

                            b.setStok(b.getStok() - jumlah);
                            System.out.println("Stok barang '" + b.getNama() + "' berhasil dikurangi. Sisa stok: " + b.getStok());

                        } catch (InputMismatchException e) {
                            System.out.println("Input harus berupa angka!");
                            scanner.nextLine();
                        } catch (IndexOutOfBoundsException | StokTidakCukupException e) {
                            System.out.println("Waadohh: " + e.getMessage());
                        }
                        break;

                    case 0:
                        isRunning = false;
                        System.out.println("Terima kasih! Program berakhir....");
                        break;

                    default:
                        System.out.println("Opsi tidak valid. Pilih ( 0 - 3 ).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
