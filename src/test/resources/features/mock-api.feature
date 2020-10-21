Feature: An entity with non-existing id cannot be found
  Scenario: Client makes call to GET /test/123
    When The client calls "/test/123"
    Then The client receives status code of 404
