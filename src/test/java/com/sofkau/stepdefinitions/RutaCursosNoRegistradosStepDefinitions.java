package com.sofkau.stepdefinitions;

import com.sofkau.models.Ruta;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_CREAR_RUTA;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class RutaCursosNoRegistradosStepDefinitions extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(CrearRutaStepDefinition.class);
    private final Ruta ruta = new Ruta();
    private List<String> cursos = new ArrayList<>();
    @Given("que el administrador asigna los datos para crear una ruta de aprendizaje con cursos que no existen")
    public void queElAdministradorAsignaLosDatosParaCrearUnaRutaDeAprendizajeConCursosQueNoExisten() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Iniciando automatizaciòn en el servicio");
        } catch (Exception e) {
            LOGGER.error("Test fallido");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @When("envia datos de titulo {string}, descripcion {string}, duracion {string}, Id admin {string}")
    public void enviaDatosDeTituloDescripcionDuracionIdAdmin(String title, String description, String duration, String adminId) {
        try {
            ruta.setTitle(title);
            ruta.setDescription(description);
            ruta.setDuration(duration);
            ruta.setAdminId(adminId);
            ruta.setCourses(cursos);
            actor.attemptsTo(doPost().withTheResource(RESOURCE_CREAR_RUTA.getValue())
                    .andTheRequestBody(ruta));

            LOGGER.info("Datos enviados correctamente");

        } catch (Exception e) {

            LOGGER.error("Error al enviar los datos");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @Then("no deberian agregarse cursos que no existen a la ruta y un status {int}")
    public void noDeberianAgregarseCursosQueNoExistenALaRutaYUnStatus(Integer code) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_BAD_REQUEST,
                            response -> response.statusCode(code))
            );
            actor.should(
                    seeThatResponse("El cuerpo de respuesta no debe ser nulo",
                            response -> response.body(notNullValue())
                    )
            );
            LOGGER.info("Asercion pasada");
        } catch (Exception e) {
            LOGGER.error("Test fallido");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        } finally {
            LOGGER.info("Test completado");
        }
    }
}
