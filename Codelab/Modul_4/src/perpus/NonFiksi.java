package perpus;

public class NonFiksi extends Buku{
    private String kategori;

    public NonFiksi(String judul, String penulis, String kategori, int stok) {  // Added stok parameter
        super(judul, penulis, stok);
        this.kategori = kategori;
    }

    @Override
    public void displayInfo() {
        System.out.println("Buku non-Fiksi: " + judul + " oleh: " + penulis + " (kategori: " + kategori + ")"+ "| stok: " + stok);
    }
}
