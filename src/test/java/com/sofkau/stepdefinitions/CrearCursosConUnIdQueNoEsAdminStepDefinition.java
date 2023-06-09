package com.sofkau.stepdefinitions;

import com.sofkau.models.Curso;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.sofkau.questions.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_CREAR_CURSO;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class CrearCursosConUnIdQueNoEsAdminStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(CrearCursosConUnIdQueNoEsAdminStepDefinition.class);
    private final Curso curso = new Curso();
    private List<String> contenidoCurso = new ArrayList<>();


    @Given("un usuario que no es administrador desea crear un curso")
    public void unUsuarioQueNoEsAdministradorDeseaCrearUnCurso() {

        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }



    }

    @When("envia titulo {string}, descricion {string}, duracion {string}, requerimientos {string}, contenido {string} y el adminID incorrecto {string}")
    public void enviaTituloDescricionDuracionRequerimientosContenidoYElAdminIDIncorrecto(String titulo, String descripcion, String duracion, String requerimiento, String adminID, String string6) {

        try {
            contenidoCurso.add("Esta es mi primer descripcion");
            curso.setTitle(titulo);
            curso.setDescription(descripcion);
            curso.setDuration(duracion);
            curso.setRequirements(requerimiento);
            curso.setAdminId(adminID);
            curso.setContent(contenidoCurso);

            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE_CREAR_CURSO.getValue())
                            .andTheRequestBody(curso)
            );
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("se generara un mensaje de error junto con es estatus {int}")
    public void seGeneraraUnMensajeDeErrorJuntoConEsEstatus(Integer statusCode) {

        Response actualResponse = returnResponse().answeredBy(actor);

        try {
            actor.should(
                    seeThatResponse("Se debe mostrar el  estatus correspondiente",
                            response -> response.statusCode(statusCode)
                    )
            );

            actor.should(
                    seeThatResponse("El cuerpo de respuesta no debe ser nulo",
                            response -> response.body(notNullValue())
                    )
            );
            LOGGER.info("Asercion realizada correctamente con los campos del json");
            LOGGER.info(actualResponse.asString());

        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        } finally {
            LOGGER.info("Test completado");
        }
    }
    }




