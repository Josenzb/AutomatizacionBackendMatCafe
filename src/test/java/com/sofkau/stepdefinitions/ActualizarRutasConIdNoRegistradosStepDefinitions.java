package com.sofkau.stepdefinitions;

import com.sofkau.models.Ruta;
import com.sofkau.setup.ApiSetUp;
import com.sofkau.tasks.DoPut;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_ACTUALIZAR_RUTA;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class ActualizarRutasConIdNoRegistradosStepDefinitions extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(ActualizarRutasConIdNoRegistradosStepDefinitions.class);
    private final Ruta ruta = new Ruta();
    @Given("el administrador esta en el servicio para actualizar rutas de aprendizaje")
    public void elAdministradorEstaEnElServicioParaActualizarRutasDeAprendizaje() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Iniciando automatizaciòn en el servicio de actualizar ruta al enviar caracteres");
        } catch (Exception e) {
            LOGGER.error("Test fallido en el servicio  de obtener ruta por Id");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @When("envia  datos con campos de id {string} no registrados, titulo {string}, descripcion {string}, duracion {string}, Id admin {string}")
    public void enviaDatosConCamposDeIdNoRegistradosTituloDescripcionDuracionIdAdmin(String id, String title, String description, String duration, String adminId) {
        try {
            ruta.setTitle(title);
            ruta.setDescription(description);
            ruta.setDuration(duration);
            ruta.setAdminId(adminId);
            actor.attemptsTo(DoPut.doPut().withTheResource(RESOURCE_ACTUALIZAR_RUTA.getValue() + id)
                    .andTheRequestBody(ruta));

            LOGGER.info("Datos enviados correctamente");

        } catch (Exception e) {

            LOGGER.error("Error al enviar los datos");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();

        }
    }

    @Then("no se actualizara la ruta  y retorna un status {int}")
    public void noSeActualizaraLaRutaYRetornaUnStatus(Integer code) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: ",
                            response -> response.statusCode(code))
            );
            actor.should(
                    seeThatResponse("El cuerpo de respuesta no debe ser nulo",
                            response -> response.body(notNullValue()))
            );
            LOGGER.info("La respuesta tiene el código de estado esperado");
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
