package com.sofkau.utils;

public enum UrlResources {
    BASE_URL("https://campus-matcafe-backend-sofka-production.up.railway.app/"),
    RESOURCE_CREAR_RUTA("Route"),
    RESOURCE_OBTENER_RUTA("Route/"),
    RESOURCE_ACTUALIZAR_RUTA("Route/");
    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}