package com.gsnotes.web.models;

public class ExportAllModel {

    private String session;
    private String nomNiveau;


    public ExportAllModel() {
    }

    public ExportAllModel(String session, String nomModule) {
        this.session = session;
        this.nomNiveau = nomModule;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getNomNiveau() {
        return nomNiveau;
    }

    public void setNomNiveau(String nomNiveau) {
        this.nomNiveau = nomNiveau;
    }
}
