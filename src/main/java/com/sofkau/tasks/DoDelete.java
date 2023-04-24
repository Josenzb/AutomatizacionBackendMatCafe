package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DoDelete implements Task {
    private String resource;
    private String id;

    public DoDelete conElRecurso(String resource) {
        this.resource = resource;
        return this;
    }

    public DoDelete yConelId(String id) {
        this.id = id;
        return this;

    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(resource+id)
        );
    }
    public static DoDelete doDelete() { return new DoDelete() ;}
}
