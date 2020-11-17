Feature: Test Create and fetch

  Scenario: Client creates an entity
    When The client makes a POST call to "/test" with body:
    """
    {
        "name": "test"
    }
    """
    Then The client receives status code of 201

  Scenario: Client makes a GET call for a non-existing entity id
    When The client makes a GET call to "/test/123"
    Then The client receives status code of 404

  Scenario: Client makes a GET call for an existing type
    When The client makes a GET call to "/test"
    Then The client receives status code of 200


