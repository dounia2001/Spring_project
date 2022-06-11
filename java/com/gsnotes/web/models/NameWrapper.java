package com.gsnotes.web.models;

import java.util.List;

public class NameWrapper {

    private String[] Names;

    public NameWrapper(String[] names) {
        Names = names;
    }

    public String[] getNames() {
        return Names;
    }

    public void setNames(String[] names) {
        Names = names;
    }
}
