package com.sofkau.stepdefinitions;

import com.sofkau.models.Curso;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.sofkau.questions.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_CREAR_CURSO;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class BuscarCursoPorUnIdEspecificoStepDefinition  extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(BuscarCursoPorUnIdEspecificoStepDefinition.class);
    private final Curso curso = new Curso();
    private List<String> contenidoCurso = new ArrayList<>();

    @Given("un aprendiz quiere obtener un curso por id")
    public void unAprendizQuiereObtenerUnCursoPorId() {

        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }
    @When("envia el  id {string} y drealiza la busqueda titulo {string}, descricion {string}, duracion {string}, requerimientos {string}, contenido {string} y el adminID incorrecto {string}")
    public void enviaElIdYDrealizaLaBusquedaTituloDescricionDuracionRequerimientosContenidoYElAdminIDIncorrecto(String idCurso, String titulo, String descripcion, String duracion, String requerimiento, String    contenidoDelCurso, String adminID) {

        contenidoCurso.add("Esta es mi primer descripcion");
        curso.setTitle(titulo);
        curso.setDescription(descripcion);
        curso.setDuration(duracion);
        curso.setRequirements(requerimiento);
        curso.setAdminId(adminID);
        curso.setContent(contenidoCurso);
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_CREAR_CURSO.getValue()+ idCurso)
            );
            System.out.println(SerenityRest.lastResponse().body().asString());
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("se obtendra el curso junto con un estatus {int}")
    public void seObtendraElCursoJuntoConUnEstatus(Integer statusCode) {

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

            actor.should(
                    seeThatResponse("Se debe mostrar el curso creado",
                            response -> response
                                    .body("title", equalTo(curso.getTitle()))
                                    .body("description", equalTo(curso.getDescription()))
                                    .body("duration", equalTo(curso.getDuration()))
                                    .body("requirements", equalTo(curso.getRequirements()))
                                    .body("adminId", equalTo(curso.getAdminId()))
                                    .body("content", equalTo(curso.getContent()))
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


