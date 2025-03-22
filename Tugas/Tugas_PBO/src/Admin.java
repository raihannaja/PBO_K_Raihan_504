public class Admin{
    String usernameValid = "Admin504";
    String passwordValid = "Password504";

    boolean login(String username , String password ){
        return username.equals(usernameValid) && password.equals(passwordValid);
    }
    void displayInfo(){
        System.out.println("Login Admin Berhasil!");
    }
}

