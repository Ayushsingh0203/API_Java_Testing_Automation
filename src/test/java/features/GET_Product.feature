Feature: Get all products from the API
  Scenario: Verify the get API for the products
    Given I hit the url of products to get the api endpoint
    When I pass the url in the request
    Then I receive the response code as 200


  Scenario Outline: Verify the rate of the first product is correct
    Given I hit the url of products to get the api endpoint
    When I pass the url in the request
    Then I Verify That the rate of the first product is <FirstProductRate>

    Examples:
      | FirstProductRate |
      | 3.9              |