package com.divoninsky.testapi;

public enum Credentials {
    USERNAME("john"),
    PASSWORD("123"),
//    URL("http://213.184.251.118:8734"),
    URL("http://192.168.0.130:8734"),
//    PANELID ("marinam");
//    PANELID ("panelId");
    PANELID ("your_panel_id");
    private String title;

    Credentials (String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}
