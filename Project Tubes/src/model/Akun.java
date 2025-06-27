package model;

public abstract class Akun {
    protected String username;
    protected String password;

    public Akun(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // Abstract methods
    public abstract String displayWelcome();
}

