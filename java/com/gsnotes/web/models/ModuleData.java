package com.gsnotes.web.models;

public class ModuleData {


    private String session;
    private String nomModule;


    public ModuleData() {
    }

    public ModuleData(String session, String nomModule) {
        this.session = session;
        this.nomModule = nomModule;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }
}
