import java.util.Scanner;

// superclass
class KarakterGame {
    private String nama;
    private int kesehatan;

    //Constructor
    public KarakterGame(String nama, int kesehatan){
        this.nama = nama;
        this.kesehatan = kesehatan;
    }
    // menambahkan setter dan getter untuk nama
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    // menambahkan setter dan getter untuk kesehatan

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }
    // Method untuk serang yang akan di override subclass
    public void serang(KarakterGame target){
    }
}