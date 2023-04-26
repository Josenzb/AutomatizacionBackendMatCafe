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

public class BuscarCursoPorUnIdEspecificoQueNoExisteStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(BuscarCursoPorUnIdEspecificoQueNoExisteStepDefinition.class);

    @Given("un aprendiz quiere obtener un curso por un id que no existe")
    public void unAprendizQuiereObtenerUnCursoPorUnIdQueNoExiste() {
        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("envia  un id que no existe  {string} y drealiza la busqueda titulo {string}, descricion {string}, duracion {string}, requerimientos {string}, contenido {string} y el adminID incorrecto {string}")
    public void enviaUnIdQueNoExisteYDrealizaLaBusquedaTituloDescricionDuracionRequerimientosContenidoYElAdminIDIncorrecto(String idCurso, String string2, String string3, String string4, String string5, String string6, String string7) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_CREAR_CURSO.getValue()+ idCurso)
            );
            System.out.println(SerenityRest.lastResponse().body().asString());
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("se obtendra un mensaje de error  junto con un estatus {int}")
    public void seObtendraUnMensajeDeErrorJuntoConUnEstatus(Integer statusCode) {

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



