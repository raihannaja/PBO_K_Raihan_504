public class Main {
    public static void main(String[] args) {
        Hewan hewan1 = new Hewan();
        Hewan hewan2 = new Hewan();

        hewan1.nama = "Kucing";
        hewan2.nama = "Tokek";

        hewan1.jenis = "Mamalia";
        hewan2.jenis = "Reptil";

        hewan1.suara = "Miaw..miaww miaw myawww\n";
        hewan2.suara = "Totototo too Tokkekk";

        hewan1.simsalabim();
        hewan2.simsalabim();
    }
}