package perpus;

public abstract class  Buku {
    protected String judul;
    protected String penulis;
    protected int stok;

    public Buku(String judul, String penulis, int stok) {
        this.judul = judul;
        this.penulis = penulis;
        this.stok = stok;
    }
    // menambahkan fitur hitung stok
    public void kurangiStok() {
        if (stok > 0) stok--;
    }
    public int getStok() {
        return stok;
    }
    public boolean isTersedia() {
        return stok > 0;
    }
    public abstract void displayInfo();

}
