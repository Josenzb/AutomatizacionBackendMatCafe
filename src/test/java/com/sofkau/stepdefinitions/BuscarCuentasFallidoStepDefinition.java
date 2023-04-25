package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BuscarCuentasFallidoStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(BuscarAprendizFallidoStepDefinition.class);
    @Given("que el administrador realiza una petición para obtener una cuenta invalida")
    public void queElAdministradorRealizaUnaPeticiónParaObtenerUnaCuentaInvalida() {
        try{
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el administrador envía la peticion con el correo invalido {string}")
    public void elAdministradorEnvíaLaPeticionConElCorreoInvalido(String correo) {
        try{
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_SEARCH_ACCOUNTS.getValue()+correo)
            );
            LOGGER.info("Peticion realizada");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("se recibira un codigo {int} y un mensaje de correo no encontrado")
    public void seRecibiraUnCodigoYUnMensajeDeCorreoNoEncontrado(Integer status) {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            LOGGER.info(actualResponse.asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(status)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
