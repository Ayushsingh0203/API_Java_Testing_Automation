Feature: insert products using post API
  Scenario Outline: validate post product api status code works correctly
    Given I hit the url of post product api endpoint
    When I pass the url in the request
    And I pass the request body of product title<ProductTitle>
    And I pass the request body of price<price>
    Then I receive the response code as 200

    Examples:
    | ProductTitle | price |
    | Shoes        | 13.9  |

  Scenario Outline: validate post product api response body works correctly
    Given I hit the url of post product api endpoint
    When I pass the url in the request
    And I pass the request body of product title<ProductTitle>
    Then I receive the response body with id as <id>

    Examples:
      | ProductTitle | id |
      | Shoes        | 21  |