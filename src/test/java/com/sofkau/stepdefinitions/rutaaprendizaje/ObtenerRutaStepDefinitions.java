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
import static com.sofkau.utils.UrlResources.RESOURCE_CREAR_RUTA;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class ObtenerRutaStepDefinitions extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(ObtenerRutaStepDefinitions.class);
    @Given("que el aprendiz desea obtener la informacion de todas la rutas de aprendizaje")
    public void queElAprendizDeseaObtenerLaInformacionDeTodasLaRutasDeAprendizaje() {
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

    @When("se envia la peticion para obtener todas las rutas")
    public void seEnviaLaPeticionParaObtenerTodasLasRutas() {
        try {

            actor.attemptsTo(DoGet.doGet().withTheResource(RESOURCE_CREAR_RUTA.getValue()));
            LOGGER.info("Enviò de peticiòn para obtener rutas");

        } catch (Exception e) {

            LOGGER.error("Error al enviar los datos");
            LOGGER.error(e.getMessage());
            LOGGER.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @Then("se mostrara la informacion de todas las rutas de aprendizaje y un estatus {int}")
    public void seMostraraLaInformacionDeTodasLasRutasDeAprendizajeYUnEstatus(Integer code)  {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code))
            );

            actor.should(
                    seeThatResponse("El cuerpo de respuesta no debe ser nulo",
                            response -> response.body(notNullValue()))
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
