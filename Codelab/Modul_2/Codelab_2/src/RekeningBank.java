public class RekeningBank {
    String noRek,pemilik;
    Double saldo,setoran,tarikan,saldoPlus,saldoLast;

    void munculInfo(){
        System.out.println("Nomor Rekening: " + noRek);
        System.out.println("Nama Pemilik: " + pemilik);
        System.out.println("Saldo: Rp" + saldo + "\n");
    }

    void setorUang(double setoran){
        System.out.println((pemilik) + " Menyetor Rp:" + (setoran) + " Saldo Sekarang: Rp" + (saldo + setoran));
    }

    void tarikUang(double tarikan) {
        saldoPlus = saldo + setoran;
        if (tarikan > saldoPlus) {
            System.out.println(("\n") + pemilik + " Menarik Rp:" + (tarikan) + " (Gagal,Saldo tidak mencukupi) Saldo saat ini: Rp" + saldoPlus);
        }else {
            System.out.println((pemilik) + " Menarik Rp:" + (tarikan) + " (Berhasil) Saldo Sekarang: Rp" + (saldoPlus - tarikan) + "\n");
        }
    }

    void infoUpdate(){
        if (tarikan <= saldoPlus){
            saldoLast = saldoPlus - tarikan;
        }else {
            saldoLast = saldoPlus;
        }
        System.out.println("Nomor Rekening: " + noRek);
        System.out.println("Nama Pemilik: " + pemilik);
        System.out.println("Saldo: Rp" + saldoLast);
    }
}
