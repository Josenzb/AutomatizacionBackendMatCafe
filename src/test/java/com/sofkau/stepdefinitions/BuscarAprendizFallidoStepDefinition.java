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
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_SEARCH_LEARNER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BuscarAprendizFallidoStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(BuscarAprendizFallidoStepDefinition.class);
    @Given("que el administrador realiza una peticion para obtener la cuenta de un aprendiz")
    public void queElAdministradorRealizaUnaPeticionParaObtenerLaCuentaDeUnAprendiz() {
        try{
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el administrador envia la peticion con correos no registrados {string}")
    public void elAdministradorEnviaLaPeticionConCorreosNoRegistrados(String correo) {
        try{
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_SEARCH_LEARNER.getValue()+correo)
            );
            LOGGER.info("Peticion realizada");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("se recibira una respuesta con el codigo {int} y un mensaje de correo no encontrado")
    public void seRecibiraUnaRespuestaConElCodigoYUnMensajeDeCorreoNoEncontrado(Integer status) {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            LOGGER.info(actualResponse.asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(status)),
                    seeThat("Retorna informacion",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
