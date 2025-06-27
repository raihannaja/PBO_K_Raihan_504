package model;

public class User extends Akun {

    public User(String username, String password) {
        super(username, password);
    }

    @Override
    public String displayWelcome() {
        return "Hello " + username + " 👋";
    }
}
