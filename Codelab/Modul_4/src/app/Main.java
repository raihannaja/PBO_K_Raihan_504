package app;
import perpus.*;
public class Main {
    public static void main(String[] args) {
        Buku buku1 = new NonFiksi("Learning How To Learn","Barbara Oakley","Pengetahuan",2);
        Buku buku2 = new Fiksi("Bumi","Tere Liye","Fantasi",0);

        buku1.displayInfo();        //info buku
        buku2.displayInfo();
        System.out.println();

        Anggota ang1 = new Anggota("Muran", "K504");
        Anggota ang2 = new Anggota("Wonyong", "K520");

        ang1.displayPeminjam();     //info anggota
        ang2.displayPeminjam();
        System.out.println();

        ang1.pinjamBuku("Learning How To Learn");
        ang2.pinjamBuku(buku2,3);
        System.out.println();

        ang1.kembalikanBuku("Learning How To Learn");
        ang2.kembalikanBuku("Bumi");
    }
}
