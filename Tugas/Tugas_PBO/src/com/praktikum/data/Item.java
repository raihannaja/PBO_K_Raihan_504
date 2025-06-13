package com.praktikum.data;

import javafx.beans.property.SimpleStringProperty;

public class Item {
    private final SimpleStringProperty itemName;
    private final SimpleStringProperty description;
    private final SimpleStringProperty location;
    private final SimpleStringProperty status;

    public Item(String itemName, String description, String location) {
        this.itemName = new SimpleStringProperty(itemName);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.status = new SimpleStringProperty("Reported");
    }

    public String getItemName() {
        return itemName.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getLocation() {
        return location.get();
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}



