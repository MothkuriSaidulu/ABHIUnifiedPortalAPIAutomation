package com.abhi.endpoints;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import com.abhi.utilities.ReadExcelData;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MozartEndpoints {
	public static Response responseBody;

	public static Response sendHTTPRequestPayload(String TestCaseID) {

//		 Get HttpMethod From sheet
//		 Get API Url From Sheet
//		 Get Requestbody Column from sheet to post request

		String httpMethod = ReadExcelData.getCellData("TestData", TestCaseID, "HttpMethod");
		String endPointUrl = ReadExcelData.getCellData("TestData", TestCaseID, "API_URL");
		String Requestbody = ReadExcelData.getCellData("TestData", TestCaseID, "Requestbody");

		if (httpMethod.equalsIgnoreCase("Post")) {
			
			try {
				responseBody = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(Requestbody).when()
						.post(endPointUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (httpMethod.equalsIgnoreCase("Get")) {
			try {
				responseBody = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(Requestbody).when().get(endPointUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return responseBody;

	}

}
