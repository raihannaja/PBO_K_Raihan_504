public class Mahasiswa {
        String namaValid = "Muhamad Raihan Naja";
        long nimValid = 202410370110504L;

        boolean login(String nama, long nim){
            return nama.equals(namaValid) && nim == nimValid;
        }

        void displayInfo(String nama, long nim){
            System.out.println("Selamat Login Mahasiswa Berhasil");
            System.out.println("Nama: " + nama);
            System.out.println("NIM: " + nim);
        }
    }

