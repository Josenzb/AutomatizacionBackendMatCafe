package com.sofkau.stepdefinitions;

import com.sofkau.models.Usuario;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_CREATE_USERS;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CrearAprendizVacioStepDefinition extends ApiSetUp {

    public static Logger LOGGER= Logger.getLogger(CrearAprendizVacioStepDefinition.class);
    private final Usuario usuario= new Usuario();
    @Given("el administrador desea crear cuentas de usuario aprendiz")
    public void elAdministradorDeseaCrearCuentasDeUsuarioAprendiz() {
        try{
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @When("el administrador envia la peticion crear aprendiz con  el nombre {string} y el email  {string}")
    public void elAdministradorEnviaLaPeticionCrearAprendizConElNombreYElEmail(String nombre, String correo) {
        try{
            usuario.setName(nombre);
            usuario.setEmail(correo);
            usuario.setRol(false);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE_CREATE_USERS.getValue())
                            .andTheRequestBody(usuario)
            );
            LOGGER.info("Peticion realizada");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("no se creara el usuario de aprendiz con datos vacios y se recibira un estatus {int}")
    public void noSeCrearaElUsuarioDeAprendizConDatosVaciosYSeRecibiraUnEstatus(Integer status) {
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
