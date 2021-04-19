package com.example;

/**
 * @Author dongkw
 * @Date 2021/4/16、10:15 上午
 **/

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        plugin = {"pretty"},
        tags = {"@test", "not @ignore"})
public class RunCucumberTest {
}
