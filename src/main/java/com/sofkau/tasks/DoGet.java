package com.sofkau.tasks;

import com.sofkau.interactions.OurGet;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;


public class DoGet implements Task {

    private String resource;

    public DoGet withTheResource(String resource){
        this.resource=resource;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurGet.to(resource)
                        .with(RequestSpecification::relaxedHTTPSValidation)
                        .with(request->request.contentType(ContentType.JSON))
        );
    }

    public static DoGet doGet(){
        return new DoGet();
    }
}
