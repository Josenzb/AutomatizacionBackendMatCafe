package com.sofkau.stepdefinitions;

import com.sofkau.models.Ruta;
import com.sofkau.setup.ApiSetUp;
import com.sofkau.tasks.DoPut;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class ActualizarRutaStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(ActualizarRutaStepDefinition.class);
    private final Ruta ruta = new Ruta();

    @Given("que el administrador esta en el servicio de actualizar rutas")
    public void queElAdministradorEstaEnElServicioDeActualizarRutas() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Iniciando automatizaciòn en el servicio de actualizar ruta");
        } catch (Exception e) {
            LOGGER.error("Test fallido en el servicio  de obtener ruta por Id");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @When("envia  datos de actualizacion id {string}, titulo {string}, descripcion {string}, duracion {string}, Id admin {string}")
    public void enviaDatosDeActualizacionIdTituloDescripcionDuracionIdAdmin(String id, String title, String description, String duration, String adminId) {

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

    @Then("se actualizara la ruta junto con un status {int}")
    public void seActualizaraLaRutaJuntoConUnStatus(Integer code) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: ",
                            response -> response.statusCode(code)),

                    seeThatResponse("Se debe mostrar la ruta creada",
                            response -> response
                                    .body("title", equalTo(ruta.getTitle()))
                                    .body("description", equalTo(ruta.getDescription()))
                                    .body("duration", equalTo(ruta.getDuration()))
                                    .body("adminId", equalTo(ruta.getAdminId()))
                    )
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
