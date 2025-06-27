package model;

import javafx.beans.property.SimpleStringProperty;

public class Schedule implements Verifiable {
    private final SimpleStringProperty matkul;
    private final SimpleStringProperty hari;
    private final SimpleStringProperty jam;
    private final SimpleStringProperty ruangan;

    public Schedule(String matkul, String hari, String jam, String ruangan) {
        this.matkul = new SimpleStringProperty(matkul);
        this.hari = new SimpleStringProperty(hari);
        this.jam = new SimpleStringProperty(jam);
        this.ruangan = new SimpleStringProperty(ruangan);
    }

    public String getMatkul() { return matkul.get(); }
    public void setMatkul(String value) { matkul.set(value); }
    public SimpleStringProperty matkulProperty() { return matkul; }

    public String getHari() { return hari.get(); }
    public void setHari(String value) { hari.set(value); }
    public SimpleStringProperty hariProperty() { return hari; }

    public String getJam() { return jam.get(); }
    public void setJam(String value) { jam.set(value); }
    public SimpleStringProperty jamProperty() { return jam; }

    public String getRuangan() { return ruangan.get(); }
    public void setRuangan(String value) { ruangan.set(value); }
    public SimpleStringProperty ruanganProperty() { return ruangan; }

    @Override
    public boolean isValid() {
        return getMatkul() != null && !getMatkul().isEmpty() &&
                getHari() != null && !getHari().isEmpty() &&
                getJam() != null && !getJam().isEmpty();
    }
}
