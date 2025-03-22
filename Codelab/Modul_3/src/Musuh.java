class Musuh extends KarakterGame{
    public Musuh (String nama, int kesehatan){
        super(nama,kesehatan);
    }
    //Override Method serang
    @Override
    public void serang(KarakterGame target){
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan cangkul!");
        target.setKesehatan(target.getKesehatan() - 15);
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }
}