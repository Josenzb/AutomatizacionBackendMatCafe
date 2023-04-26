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

public class CrearAprendizCorreoInvalidoStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(CrearAprendizCorreoInvalidoStepDefinition.class);
    private final Usuario usuario= new Usuario();
    @Given("que el administrador intenta crear un usuario aprendiz con un correo invalido")
    public void queElAdministradorIntentaCrearUnUsuarioAprendizConUnCorreoInvalido() {
        try{
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("se envia la peticion con el nombre {string} y el correo invalido {string} como aprendiz")
    public void seEnviaLaPeticionConElNombreYElCorreoInvalidoComoAprendiz(String nombre, String correo) {
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

    @Then("no se creara la cuenta de aprendiz se recibira un codigo de status {string}")
    public void noSeCrearaLaCuentaDeAprendizSeRecibiraUnCodigoDeStatus(String status) {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            LOGGER.info(actualResponse.asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(Integer.parseInt(status))),
                    seeThat("Retorna informacion",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
