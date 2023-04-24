package com.sofkau.utils;

public enum UrlResources {
    JSONPLACE_BASE_URL("https://jsonplaceholder.typicode.com/"),
    POSTS_BY_ID_RESOURCE("posts/");
    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}