Feature: Test User API

  Background:
    * url baseUrl

  Scenario: Get all predefined emp
    Given path '/emp'
    When method GET
    Then status 200
    And assert response.length == 3
    And match response[*].id == [1, 2, 3]
    And match response[0].nickName == 'emp1'

  Scenario: Manage new emp
    Given path '/emp'
    And request { nickName: 'emp100' }
    When method post
    Then status 201
    And match response == {id: '#number', nickName: 'emp100'}

    * def id = response.id
    Given path '/emp', id
    When method get
    Then status 200
    And match response == {id: '#(id)', nickName: 'emp100'}

    Given path '/emp'
    When method GET
    Then status 200
    And assert response.length == 4
    And match response[*].id == [1, 2, 3, 4]
    And match response[*].nickName == ['emp1', 'emp2', 'emp3', 'emp100']

    Given path '/emp', id
    When method delete
    Then status 204

    Given path '/emp'
    When method GET
    Then status 200
    And assert response.length == 3
    And match response[*].id == [1, 2, 3]
