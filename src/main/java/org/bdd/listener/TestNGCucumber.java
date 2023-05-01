package org.bdd.listener;

import org.testng.annotations.Listeners;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@Listeners(TestListener.class)
public class TestNGCucumber extends AbstractTestNGCucumberTests {

}
