// subclass dari KarakterGame
class Pahlawan extends KarakterGame{
    public Pahlawan(String nama, int kesehatan){ //Constructor
        super(nama,kesehatan);
    }
    // Override method serang
    @Override
    public void serang(KarakterGame target){
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan pisau dapur!");
        target.setKesehatan(target.getKesehatan() - 25 );
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }
}
