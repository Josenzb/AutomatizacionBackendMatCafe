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

public class CrearAprendizEspecialesStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(CrearAprendizEspecialesStepDefinition.class);
    private final Usuario usuario= new Usuario();
    @Given("el administrador desea crear usuarios de aprendiz con caracteres especiales")
    public void elAdministradorDeseaCrearUsuariosDeAprendizConCaracteresEspeciales() {
        try{
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el administrador envía la petición con el nombre truncado {string} y el email  {string} para aprendiz")
    public void elAdministradorEnvíaLaPeticiónConElNombreTruncadoYElEmailParaAprendiz(String nombre, String correo) {
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
    @Then("no se creará el usuario de aprendiz con datos especiales y se recibirá un estatus {int}")
    public void noSeCrearáElUsuarioDeAprendizConDatosEspecialesYSeRecibiráUnEstatus(Integer status) {
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
