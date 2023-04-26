package com.sofkau.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/HUB07calificartaller.feature"},
        glue = {"com.sofkau.stepdefinitions"},
        tags = {"@Calificacion"}
)
public class CalificarTallerTest {
}
