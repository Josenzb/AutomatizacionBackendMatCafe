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
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_CREAR_CURSO;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class EditarCursoPorIdConUnIdQueNoEsAdminStepDefinition  extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(EditarCursoPorIdConUnIdQueNoEsAdminStepDefinition.class);
    private final Curso curso = new Curso();
    private List<String> contenidoCurso = new ArrayList<>();

    @Given("que alguien  intenta editar un curso de aprendizaje sin un id de administrador")
    public void queAlguienIntentaEditarUnCursoDeAprendizajeSinUnIdDeAdministrador() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("envia  alguno de los siguientes campos  titulo {string}, descripcion {string}, duracion {string}, requerimiento {string}, contenido  {string} y el adminID {string}, con el id del curso a editar {string} sin ser administrador")
    public void enviaAlgunoDeLosSiguientesCamposTituloDescripcionDuracionRequerimientoContenidoYElAdminIDConElIdDelCursoAEditarSinSerAdministrador(String titulo, String descripcion, String duracion, String requerimiento, String contenido, String adminID, String idCurso) {
        try {
            contenidoCurso.add("Esta es mi primer descripcion");
            curso.setTitle(titulo);
            curso.setDescription(descripcion);
            curso.setDuration(duracion);
            curso.setRequirements(requerimiento);
            curso.setAdminId(adminID);
            //curso.setContent(contenidoCurso);
            actor.attemptsTo(
                    doPut()
                            .withTheResource(RESOURCE_CREAR_CURSO.getValue()+ idCurso)
                            .andTheRequestBody(curso)
            );
            System.out.println(SerenityRest.lastResponse().body().asString());
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }

    @Then("se generara un mensaje de error  indicando que no es administrador junto con un  estatus {int}")
    public void seGeneraraUnMensajeDeErrorIndicandoQueNoEsAdministradorJuntoConUnEstatus(Integer statusCode) {
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
