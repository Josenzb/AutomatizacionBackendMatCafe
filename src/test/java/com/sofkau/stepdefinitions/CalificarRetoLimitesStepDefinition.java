package com.sofkau.stepdefinitions;

import com.sofkau.models.Calificacion;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnResponse.returnResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_GRADER_STUDENT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CalificarRetoLimitesStepDefinition extends ApiSetUp {
    public static Logger LOGGER= Logger.getLogger(CalificarRetoLimitesStepDefinition.class);
    private final Calificacion calificacion = new Calificacion();

    @Given("que el administrador realiza una peticion de calificacion de reto con valores limite")
    public void queElAdministradorRealizaUnaPeticionDeCalificacionDeRetoConValoresLimite() {
        try{
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("el administrador envia la peticion con al limite {int} el comentario {string} el idcurso {string} y la idaprendiz {string}")
    public void elAdministradorEnviaLaPeticionConAlLimiteElComentarioElIdcursoYLaIdaprendiz(Integer nota, String comentario,
                                                                                            String idCurso, String idAprendiz) {
        try{
            calificacion.setGrade(nota);
            calificacion.setComment(comentario);
            calificacion.setCourseId(idCurso);
            calificacion.setLearnerId(idAprendiz);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE_GRADER_STUDENT.getValue())
                            .andTheRequestBody(calificacion)
            );
            LOGGER.info("Peticion realizada");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("se calificara correctamente y se generara un {int}")
    public void seCalificaraCorrectamenteYSeGeneraraUn(Integer status) {
        try {
            Response actualResponse = returnResponse().answeredBy(actor);
            LOGGER.info(actualResponse.asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(status)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
