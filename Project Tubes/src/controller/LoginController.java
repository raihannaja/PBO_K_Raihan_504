package controller;

import exception.RegistrationException;
import model.User;
import java.util.ArrayList;

public class LoginController {
    private static final ArrayList<User> users = new ArrayList<>();

    public static User signIn(String username, String password) {
        try {
            if (username.equals("user") && password.equals("user")) {
                return new User("Pengguna", "user");
            }

            int i = 0;
            while (i < users.size()) {
                User user = users.get(i);
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
                i++;
            }
        } catch (Throwable t) {
            System.err.println("⚠️ Terjadi kesalahan saat proses login: " + t.getMessage());
        }

        return null;
    }

    public static String signUp(String username, String password) {
        try {
            if (username.isEmpty() || password.isEmpty()) {
                throw new RegistrationException("❌ Username dan Password tidak boleh kosong!");
            }

            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    throw new RegistrationException("⚠️ Username sudah terdaftar!");
                }
            }

            users.add(new User(username, password));
            return "✅ Berhasil daftar! Silakan login.";

        } catch (RegistrationException e) {
            return e.getMessage();  // Menampilkan pesan error ke View
        } catch (Throwable t) {
            return "❌ Terjadi kesalahan tidak terduga: " + t.getMessage();
        }
    }
}
