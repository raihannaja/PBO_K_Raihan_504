public class Main {
    public static void main(String[] args) {
        RekeningBank rek1 = new RekeningBank();
        RekeningBank rek2 = new RekeningBank();

        rek1.noRek = "202410370110504";
        rek2.noRek = "202410370110525";

        rek1.pemilik = "Muhamad Raihan Naja";
        rek2.pemilik = "Raihan Ghifari Alfatah";

        rek1.saldo = 1250000d;
        rek2.saldo = 2000000d;

        rek1.setoran = 200000d;
        rek2.setoran = 500000d;

        rek1.tarikan = 1500000d;
        rek2.tarikan = 750000d;

        rek1.munculInfo();
        rek2.munculInfo();
        rek1.setorUang(rek1.setoran);
        rek2.setorUang(rek2.setoran);
        rek1.tarikUang(rek1.tarikan);
        rek2.tarikUang(rek2.setoran);
        rek1.infoUpdate();
        rek2.infoUpdate();
    }
}