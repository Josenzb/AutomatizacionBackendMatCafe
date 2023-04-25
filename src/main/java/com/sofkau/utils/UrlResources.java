package com.sofkau.utils;

public enum UrlResources {
    BASE_URL("https://campus-matcafe-backend-sofka-production.up.railway.app/"),
    RESOURCE_CREATE_USERS("createUser"),
    RESOURCE_SEARCH_LEARNER("learner/"),
    RESOURCE_SEARCH_ADMIN("admin/");
    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}