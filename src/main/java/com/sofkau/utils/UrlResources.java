package com.sofkau.utils;

public enum UrlResources {
    BASE_URL("https://stable-use-production.up.railway.app/"),
    RESOURCE_CREAR_CURSO("Course");
    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}