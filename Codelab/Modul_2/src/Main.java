import java.util.Random;

public class Main {
    private static final String[] KATA1 = {"Senja", "Hujan", "Pagi", "Malam", "Angin", "Bulan"};
    private static final String[] KATA2 = {"menari", "berbisik", "menghampiri", "menyapa", "berlari", "bernyanyi"};
    private static final String[] KATA3 = {"hati", "ombak", "cahaya", "bintang", "sunyi", "rindu"};

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            String baris = KATA1[random.nextInt(KATA1.length)] + " " +
                    KATA2[random.nextInt(KATA2.length)] + " " +
                    KATA3[random.nextInt(KATA3.length)] + ".";
            System.out.println(baris);
        }
    }
}
