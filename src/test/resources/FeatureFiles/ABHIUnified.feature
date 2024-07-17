Feature: verify POST GET PUT DEL Requests.

  @TC_001
  Scenario: TC_001 Verify Request  Payload for POST Request for Agent Login.
    Given user send requestpayload for Agent Login from excell sheet "TC_001".
    And store responsebody in to sheet "TC_001".
    And validate "Login Successful!" message from responsebody.
    And validate agencyCode and agentCode and verticalCode for "TC_001" from excell sheet.

  @TC_002
  Scenario: TC_002 Verify Get Rquest Payload for Get Products For Agency.
    Given user send request payload for Get Products For Agency from excell sheet "TC_002".
    And store responsebody in to sheet "TC_002".
    And validate resoponse body against sheet "TC_002" testdata.