package com.sofkau.stepdefinitions;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_CREAR_CURSO;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class EliminarCursoStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(EliminarCursoStepDefinition.class);


    @Given("que el administrador desea eliminar un curso")
    public void queElAdministradorDeseaEliminarUnCurso() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("envia la solicitud  con el  id del curso que desea eliminar {string}")
    public void enviaLaSolicitudConElIdDelCursoQueDeseaEliminar(String idCurso) {
        try {
            actor.attemptsTo(
                    doDelete()
                            .conElRecurso(RESOURCE_CREAR_CURSO.getValue())
                            .yConelId(idCurso)
            );
            System.out.println(SerenityRest.lastResponse().body().asString());
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("se obtiene un estatus de respuesta {int}")
    public void seObtieneUnEstatusDeRespuesta(Integer statusCode) {
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

