package com.praktikum.users;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class User {
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty nim = new SimpleStringProperty();

    public User(String nama, String nim) {
        setNama(nama);
        setNim(nim);
    }

    // Properti dan getter/setter untuk nama
    public StringProperty namaProperty() {
        return nama;
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    // Properti dan getter/setter untuk NIM
    public StringProperty nimProperty() {
        return nim;
    }

    public String getNim() {
        return nim.get();
    }

    public void setNim(String nim) {
        this.nim.set(nim);
    }

    public abstract boolean login(String param1, String param2);
    public abstract void displayAppMenu();

    public void displayInfo() {
        System.out.println("Nama: " + getNama());
        System.out.println("NIM: " + getNim());
    }
}
