package perpus;

public class Anggota implements Peminjaman{
    private String nama;
    private String id;

    public Anggota(String nama, String id){
        this.nama = nama;
        this.id = id;
    }
    @Override
    public void pinjamBuku(String judulBuku) {
        System.out.println(nama + " meminjam buku dengan judul: " + judulBuku);
    }
    public void pinjamBuku(Buku buku, int durasi) {
        if (buku.isTersedia()) {
            buku.kurangiStok();
            System.out.println(nama + " meminjam buku " + buku.judul +
                    " selama " + durasi + " hari " + "| Stok tersisa: " + buku.getStok());
        } else {
            System.out.println("Maaf, buku " + buku.judul + " sedang habis");
        }
    }

    @Override
    public void kembalikanBuku(String judulBuku) {
        System.out.println(nama + " mengembalikan buku dengan judul: " + judulBuku);
    }

    public void displayPeminjam(){
        System.out.println("Anggota: " + nama + " Id: " + id);
    }
}
