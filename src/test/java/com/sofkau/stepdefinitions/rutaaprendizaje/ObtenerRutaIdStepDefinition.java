package com.sofkau.stepdefinitions.rutaaprendizaje;

import com.sofkau.setup.ApiSetUp;
import com.sofkau.tasks.DoGet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class ObtenerRutaIdStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(ObtenerRutaIdStepDefinition.class);

    @Given("que el administrador esta en el servicio de rutas")
    public void queElAdministradorEstaEnElServicioDeRutas() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Iniciando automatizaciòn en el servicio de obtener ruta");
        } catch (Exception e) {
            LOGGER.error("Test fallido en el servicio  de obtener ruta");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }

    }

    @When("se envia la peticion para obtener  ruta por {string}")
    public void seEnviaLaPeticionParaObtenerRutaPor(String id) {
        try {

            actor.attemptsTo(DoGet.doGet().withTheResource(  RESOURCE_OBTENER_RUTA.getValue() + id));
            LOGGER.info("Enviò de peticiòn para obtene ruta por id");

        } catch (Exception e) {

            LOGGER.error("Error al enviar la petición Get");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @Then("se mostrara la informacion de la ruta de aprendizaje junto con el estatus {int}")
    public void seMostraraLaInformacionDeLaRutaDeAprendizajeJuntoConElEstatus(Integer code) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code))
            );

            actor.should(
                    seeThatResponse("El cuerpo de respuesta no debe ser nulo",
                            response -> response.body(notNullValue()))
            );

            LOGGER.info("Asercion pasada. La respuesta tiene el código de estado esperado");
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


