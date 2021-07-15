Feature: To Validate SBDB Close-Approach Data API

@apivalidation
Scenario: Validate the API as per the given inputs

Given the query parameter from command line arguments
Then send GET http request and get the asteroids and comets list
