@Regression
Feature: Posts and Comments API calls

  Scenario: GET all posts
    When a GET call is made to "https://jsonplaceholder.typicode.com/posts"
    Then a 200 status code is returned

  Scenario: POST a new post
    Given a json payload "newPost.json"
    When a POST call is made to "https://jsonplaceholder.typicode.com/posts"
    Then a 201 status code is returned
    And the response body contains an id

  Scenario: GET post's comments
    When a GET call is made to "https://jsonplaceholder.typicode.com/posts/3/comments"
    Then a 200 status code is returned
    And the response contains 5 comments

  Scenario: PATCH post title and body
    Given a json payload "patchPost.json"
    When a PATCH call is made to "https://jsonplaceholder.typicode.com/posts/99"
    Then a 200 status code is returned
    And the "title" equals "New Test Title"
    And the "body" equals "New Sample Body"
