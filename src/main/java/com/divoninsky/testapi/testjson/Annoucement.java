package com.divoninsky.testapi.testjson;

public class Annoucement {
    String id;
    String text;
    String active;
    String idPanel;

    public Annoucement(String id, String text, String active, String idPanel) {
        this.id = id;
        this.text = text;
        this.active = active;
        this.idPanel = idPanel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIdPanel() {
        return idPanel;
    }

    public void setIdPanel(String idPanel) {
        this.idPanel = idPanel;
    }

    @Override
    public String toString() {
        return "Annoucement{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", active='" + active + '\'' +
                ", idPanel='" + idPanel + '\'' +
                '}';
    }
}

