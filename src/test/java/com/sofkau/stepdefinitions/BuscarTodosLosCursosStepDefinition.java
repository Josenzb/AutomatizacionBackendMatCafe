package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_CREAR_CURSO;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class BuscarTodosLosCursosStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(BuscarTodosLosCursosStepDefinition.class);

    @Given("que el aprendiz desea obtener todos los cursos")
    public void queElAprendizDeseaObtenerTodosLosCursos() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("envia la solicitud de obtener todos los cursos")
    public void enviaLaSolicitudDeObtenerTodosLosCursos() {

        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_CREAR_CURSO.getValue())

            );
            System.out.println(SerenityRest.lastResponse().body().asString());
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("se obtiene todos los cursos disponibles  en el campus")
    public void seObtieneTodosLosCursosDisponiblesEnElCampus() {
        Response actualResponse = returnResponse().answeredBy(actor);

        try {
            actor.should(
                    seeThatResponse("Se debe mostrar el  estatus correspondiente",
                            response -> response.statusCode(200)
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
