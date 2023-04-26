package com.sofkau.utils;

public enum UrlResources {
    BASE_URL("https://campus-matcafe-backend-sofka-production.up.railway.app/"),

    RESOURCE_CREATE_USERS("createUser"),
    RESOURCE_SEARCH_LEARNER("learner/"),
    RESOURCE_SEARCH_ADMIN("admin/"),
    RESOURCE_SEARCH_ACCOUNTS("adminAndLearnerByEmail/"),
    RESOURCE_GRADER_STUDENT("graderStudent"),

    RESOURCE_CREAR_CURSO("Course"),
    RESOURCE_CREAR_RUTA("Route"),
    RESOURCE_OBTENER_RUTA("Route/"),
    RESOURCE_ACTUALIZAR_RUTA("Route/"),
    RESOURCE_ENVIAR_RETO("learner/sendWorkshop"),
    RESOURCE_SUSCRIBIR_RUTA("learner/subscribeRoute");





    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}