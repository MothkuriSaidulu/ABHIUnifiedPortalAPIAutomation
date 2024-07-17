package com.abhi.stepdef;

import java.io.File;
import java.io.IOException;

import com.abhi.payloads.ResonseValidation;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.en.*;

public class StepDefination {
	public static ExtentReports extent;
	public static ExtentTest test;

	public static ExtentTest report() {

		extent = new ExtentReports();
		File filePath = new File(".\\src\\test\\resources\\extent-config.xml");
		ExtentSparkReporter spark = new ExtentSparkReporter(filePath);

		try {
			spark.loadXMLConfig(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extent.attachReporter(spark);

		test = extent.createTest("ABHI");
		return test;
	}

	public static void logInfo(String message) {
		// Check if test is initialized
		if (test != null) {
			test.log(Status.INFO, message);
		} else {
			System.err.println("Test is not initialized Unable to print log messages.");
		}
	}

	/*
	 * TC_001
	 */

	@Given("user send requestpayload for Agent Login from excell sheet {string}.")
	public static void user_send_requestpayload_for_agent_login_from_excell_sheet(String TestCaseID) {

		ResonseValidation.sendPostRequestPayloadForAgentLogin(TestCaseID);

		logInfo("This is an info message");

	}

	/*
	 * Below Step common for all store response body in to sheet
	 */
	@Given("store responsebody in to sheet {string}.")
	public void store_responsebody_in_to_sheet(String TestCaseID) {
		ResonseValidation.storeResponseBody(TestCaseID);

	}

	@Given("validate {string} message from responsebody.")
	public void validate_message_from_responsebody(String successMsg) {
		ResonseValidation.validateAgentLoginSuccessMsg(successMsg);

	}

	@Given("validate agencyCode and agentCode and verticalCode for {string} from excell sheet.")
	public void validate_agency_code_and_agent_code_and_vertical_code_for_from_excell_sheet(String TestCaseID) {
		ResonseValidation.validateResponseBody(TestCaseID);

	}

	/*
	 * TC_002
	 */

	@Given("user send request payload for Get Products For Agency from excell sheet {string}.")
	public void user_send_request_payload_for_get_products_for_agency_from_excell_sheet(String TestCaseID) {
		ResonseValidation.sendGetRequestPayloadForGetProductsForAgency(TestCaseID);
		
	}

	@Given("validate resoponse body against sheet {string} testdata.")
	public void validate_resoponse_body_against_sheet_testdata(String TestCaseID) {
		ResonseValidation.validateResponseBody(TestCaseID);
		
	}

}
