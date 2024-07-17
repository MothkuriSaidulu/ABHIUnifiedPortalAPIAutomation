package com.abhi.payloads;

import org.testng.Assert;

import com.abhi.endpoints.MozartEndpoints;
import com.abhi.utilities.ReadExcelData;
import com.abhi.utilities.TestBase;
import com.abhi.utilities.WriteExcell;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResonseValidation extends TestBase {

	public static Response resAgentLogin = null;
	public static Response resGetProductsForAgency = null;

	public static String resString;
	public static JsonPath jsonObject;

	/*
	 * below Method common for store response body for all test cases
	 */
	public static void storeResponseBody(String TestCaseID) {
		if (TestCaseID.equalsIgnoreCase("TC_001")) {
			resString = resAgentLogin.asString();
			WriteExcell.writeDataIntoCell("TestData", TestCaseID, "Responsebody", resString);

		} else if (TestCaseID.equalsIgnoreCase("TC_002")) {
			resString = resGetProductsForAgency.asString();
			WriteExcell.writeDataIntoCell("TestData", TestCaseID, "Responsebody", resString);

		}
	}

	public static void validateResponseBody(String TestCaseID) {

		if (TestCaseID.equalsIgnoreCase("TC_001")) {

			resString = resAgentLogin.asString();
			String expecteAgencyCode = ReadExcelData.getCellData("TestData", TestCaseID, "AgencyCode");
			String expecteAgentCode = ReadExcelData.getCellData("TestData", TestCaseID, "AgentCode");
			String expecteVerticalCode = ReadExcelData.getCellData("TestData", TestCaseID, "VerticalCode");

			JsonPath jsonObject = new JsonPath(resString);
			String actualAgencyCode = jsonObject.getString("agencyCode");
			String actualAgentCode = jsonObject.getString("agentCode");
			String actualVerticalCode = jsonObject.getString("verticalCode");

			Assert.assertEquals(actualAgencyCode, expecteAgencyCode);
			Assert.assertEquals(actualAgentCode, expecteAgentCode);
			Assert.assertEquals(expecteVerticalCode, actualVerticalCode);

		} else if (TestCaseID.equalsIgnoreCase("TC_002")) {
			
			resString = resGetProductsForAgency.asString();
			String expecteAgencyCode = ReadExcelData.getCellData("TestData", TestCaseID, "AgencyCode");
			String expecteInsurenceTypeCode = ReadExcelData.getCellData("TestData", TestCaseID, "Insurance Type Code");
			String expecteVerticalCode = ReadExcelData.getCellData("TestData", TestCaseID, "VerticalCode");
			String expecteChannelName = ReadExcelData.getCellData("TestData", TestCaseID, "Channel Name");
			String expecteInsuranceType = ReadExcelData.getCellData("TestData", TestCaseID, "Insurance Type");
			String expecteAgencyName = ReadExcelData.getCellData("TestData", TestCaseID, "Agency Name");

			JsonPath jsonObject = new JsonPath(resString);
			String actualAgencyCode = jsonObject.getString("agencycode[0]");
			String actualinsurancetypecode = jsonObject.getString("insurancetypecode[0]");
			String actualVerticalCode = jsonObject.getString("verticalcode[0]");
			String actualchannelName = jsonObject.getString("channel_name[0]");
			String actualinsurancetype = jsonObject.getString("insurancetype[0]");
			String actualAgencyName = jsonObject.getString("agencyname[0]");

			Assert.assertEquals(actualAgencyCode, expecteAgencyCode);
			Assert.assertEquals(actualinsurancetypecode, expecteInsurenceTypeCode);
			Assert.assertEquals(actualVerticalCode, expecteVerticalCode);
			Assert.assertEquals(actualchannelName, expecteChannelName);
			Assert.assertEquals(expecteInsuranceType, actualinsurancetype);
			Assert.assertEquals(actualAgencyName, expecteAgencyName);

		}

	}

	/*
	 * TC_001
	 */
	public static void sendPostRequestPayloadForAgentLogin(String TestCaseID) {
		resAgentLogin = MozartEndpoints.sendHTTPRequestPayload(TestCaseID);

	}

	public static void validateAgentLoginSuccessMsg(String successMsg) {
		resString = resAgentLogin.asString();

		JsonPath jsonObject = new JsonPath(resString);
		String message = jsonObject.getString("message");
		Assert.assertEquals(message, successMsg);

	}

	/*
	 * TC_002
	 */

	public static void sendGetRequestPayloadForGetProductsForAgency(String TestCaseID) {
		resGetProductsForAgency = MozartEndpoints.sendHTTPRequestPayload(TestCaseID);

	}

}
