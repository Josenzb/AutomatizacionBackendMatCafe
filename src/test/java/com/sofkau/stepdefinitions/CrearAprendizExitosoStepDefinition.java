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

public class CrearAprendizExitosoStepDefinition extends ApiSetUp {

    public static Logger LOGGER= Logger.getLogger(CrearAprendizExitosoStepDefinition.class);
    private final Usuario usuario= new Usuario();
    @Given("que el administrador realiza una peticion para crear un usuario con el rol de aprendiz")
    public void queElAdministradorRealizaUnaPeticionParaCrearUnUsuarioConElRolDeAprendiz() {
        try{
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el administrador envia la peticion con el nombre {string} y el correo {string} del aprendiz")
    public void elAdministradorEnviaLaPeticionConElNombreYElCorreoDelAprendiz(String nameUser, String email) {
        try{
            usuario.setName(nameUser);
            usuario.setEmail(email);
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
    @Then("se creara el usuario aprendiz correctamente")
    public void seCrearaElUsuarioAprendizCorrectamente() {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            LOGGER.info(actualResponse.asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(201)),
                    seeThat("Retorna informacion",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
