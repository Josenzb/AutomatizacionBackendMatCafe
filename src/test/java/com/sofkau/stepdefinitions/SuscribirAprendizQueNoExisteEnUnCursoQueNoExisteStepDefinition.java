package com.sofkau.stepdefinitions;

import com.sofkau.models.InscripcionRuta;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_SUSCRIBIR_RUTA;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class SuscribirAprendizQueNoExisteEnUnCursoQueNoExisteStepDefinition  extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(SuscribirAprendizQueNoExisteEnUnCursoQueNoExisteStepDefinition.class);
    private final InscripcionRuta inscripcionRuta = new InscripcionRuta();


    @Given("el administrador desea inscribir un aprendiz  que no esta registrado en una ruta de aprendizaje que no esta registrada")
    public void elAdministradorDeseaInscribirUnAprendizQueNoEstaRegistradoEnUnaRutaDeAprendizajeQueNoEstaRegistrada() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("envia el reto con los datos de  id del aprendiz {string} que no esta registrada y  id del de la ruta {string} que no esta registrada")
    public void enviaElRetoConLosDatosDeIdDelAprendizQueNoEstaRegistradaYIdDelDeLaRutaQueNoEstaRegistrada(String idAprendiz, String IdRuta) {
        try {
            inscripcionRuta.setLearnedId(idAprendiz);
            inscripcionRuta.setRouteid(IdRuta);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE_SUSCRIBIR_RUTA.getValue())
                            .andTheRequestBody(inscripcionRuta)
            );
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }

    @Then("no se inscribe el aprendiz a la ruta y se muestra un estatus {int}")
    public void noSeInscribeElAprendizALaRutaYSeMuestraUnEstatus(Integer statusCode) {
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
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        } finally {
            LOGGER.info("Test completado");
        }


    }


}
