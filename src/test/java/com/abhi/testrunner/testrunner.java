package com.abhi.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		".\\src\\test\\resources\\FeatureFiles\\ABHIUnified.feature" }, dryRun = false, monochrome = true, glue = {
				"com.abhi.stepdef" }, plugin = { "pretty", "html:target\\cucumber-reports\\CucumberTestReport.html",
						"html:target/cucumber-reports/cucumber-pretty",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
						tags = "@TC_001"

)
public class testrunner {

}
