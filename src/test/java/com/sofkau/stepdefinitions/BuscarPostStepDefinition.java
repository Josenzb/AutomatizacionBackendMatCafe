package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.sofkau.questions.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.JSONPLACE_BASE_URL;
import static com.sofkau.utils.UrlResources.POSTS_BY_ID_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BuscarPostStepDefinition extends ApiSetUp {

    public static Logger LOGGER= Logger.getLogger(BuscarPostStepDefinition.class);
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    String id;

    @Given("El usuario se encuentra en la web de JsonPlaceHolder")
    public void elUsuarioSeEncuentraEnLaWebDeJsonPlaceHolder() {
        try {
            setUp(JSONPLACE_BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("El usuario envia una solicitud con el {string} del post deseado")
    public void elUsuarioEnviaUnaSolicitudConElDelPostDeseado(String idPost) {
        try {
            id = idPost;
            actor.attemptsTo(
                    doGet()
                            .withTheResource(POSTS_BY_ID_RESOURCE.getValue() + idPost)
            );
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("El usuario debe recibir un respuesta de status {int} y el post solicitado")
    public void elUsuarioDebeRecibirUnRespuestaDeStatusYElPostSolicitado(Integer statusCode) throws ParseException {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar id",
                            idC -> responseBody.get("id").toString(), equalTo(id))
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}