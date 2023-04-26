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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class CrearCursoConCamposVaciosStepDefinition  extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(CrearCursoConCamposVaciosStepDefinition.class);
    private final Curso curso = new Curso();
    public String mensajeError = "Hola";
    private List<String> contenidoCurso = new ArrayList<>();


    @Given("que el administrador desea crear un curso de aprendizaje con campos vacios")
    public void queElAdministradorDeseaCrearUnCursoDeAprendizajeConCamposVacios() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }


    @When("envia titulo {string}, descripcion {string}, duracion {string}, requerimiento {string}, contenido  {string} y el adminID {string} incluyendo campos vacios")
    public void enviaTituloDescripcionDuracionRequerimientoContenidoYElAdminIDIncluyendoCamposVacios(String titulo, String descripcion, String duracion, String requerimiento, String adminID, String string6) {
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





    @Then("se registrara el curso y  se mostrara estatus {int}")
    public void seRegistraraElCursoYSeMostraraEstatus(Integer statusCode) {

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

