package com.sofkau.stepdefinitions.rutaaprendizaje;

import com.sofkau.setup.ApiSetUp;
import com.sofkau.tasks.DoGet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_OBTENER_RUTA;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class ObtenerRutaIdNoRegistradoStedDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(ObtenerRutaIdNoRegistradoStedDefinition.class);
    @Given("el administrador esta en el servicio de rutas")
    public void elAdministradorEstaEnElServicioDeRutas() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Iniciando automatizaciòn en el servicio de obtener ruta");
        } catch (Exception e) {
            LOGGER.error("Test fallido en el servicio  de obtener ruta por Id");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @When("se envia la peticion para obtener  ruta por {string} de ruta no registrada")
    public void seEnviaLaPeticionParaObtenerRutaPorDeRutaNoRegistrada(String id) {
        try {

            actor.attemptsTo(DoGet.doGet().withTheResource(  RESOURCE_OBTENER_RUTA.getValue() + id));
            LOGGER.info("Enviò de peticiòn para obtene ruta por id de ruta no registrada");

        } catch (Exception e) {

            LOGGER.error("Error al enviar la petición Get");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @Then("se mostrara un mensaje de error junto con el estatus {int}")
    public void seMostraraUnMensajeDeErrorJuntoConElEstatus(Integer code) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_BAD_REQUEST,
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
