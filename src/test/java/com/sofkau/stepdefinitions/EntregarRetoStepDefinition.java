package com.sofkau.stepdefinitions;

import com.sofkau.models.Taller;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.UrlResources.BASE_URL;
import static com.sofkau.utils.UrlResources.RESOURCE_ENVIAR_RETO;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class EntregarRetoStepDefinition extends ApiSetUp {

    public static Logger LOGGER= Logger.getLogger(EntregarRetoStepDefinition.class);
    private final Taller taller = new Taller();

    @Given("el aprendiz quiere enviar su reto para ser revisado")
    public void elAprendizQuiereEnviarSuRetoParaSerRevisado() {

        try {
            setUp(BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("envia el reto con los datos de  id del aprendiz {string}, id del curso {string}, el link del repositorio {string} y un comentario {string}")
    public void enviaElRetoConLosDatosDeIdDelAprendizIdDelCursoElLinkDelRepositorioYUnComentario(String idAprendiz, String idCurso, String linkRepo, String comentario) {
        try {
            taller.setLearnedId(idAprendiz);
            taller.setCourseid(idCurso);
            taller.setGithub(linkRepo);
            taller.setComent(comentario);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE_ENVIAR_RETO.getValue())
                            .andTheRequestBody(taller)
            );
            LOGGER.info("Peticion realizada");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }


    @Then("se envia el taller y se retorna un estatus {int}")
    public void seEnviaElTallerYSeRetornaUnEstatus(Integer statusCode) {

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


