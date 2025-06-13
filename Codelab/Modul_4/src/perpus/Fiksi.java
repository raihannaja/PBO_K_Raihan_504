package perpus;

public class Fiksi extends Buku{
    private String genre;

    public Fiksi(String judul, String penulis, String genre, int stok) {  // Added stok parameter
        super(judul, penulis, stok);
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        System.out.println("Buku Fiksi: " + judul + " oleh: " + penulis + " (Genre: " + genre + ")" + "| stok: " + stok);
    }
}
