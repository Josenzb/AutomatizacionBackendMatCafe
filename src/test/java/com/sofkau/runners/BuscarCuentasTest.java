package com.sofkau.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/HUB23-24obtenercuakquiercuentaporemail.feature"},
        glue = {"com.sofkau.stepdefinitions"},
        tags = {"@BuscarCuentas"}
)
public class BuscarCuentasTest {
}
