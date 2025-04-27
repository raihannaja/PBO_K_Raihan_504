public class User {
    private String nama;
    private String nim;

    public User(String nama , String nim){
        this.nama = nama;
        this.nim = nim;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getNama(){
        return nama;
    }
    public void setNim(String nim){
        this.nim = nim;
    }
    public String getNim(){
        return nim;
    }
    public void login() {
        if (this.nama.equals("Muran") && this.nim.equals("504")){
            displayInfo();
        }else {
            System.out.println("Login gagal. Nama atau NIM salah.");
        }
    }
    public void displayInfo() {
        System.out.println("Login berhasil sebagai User");
    }
}

