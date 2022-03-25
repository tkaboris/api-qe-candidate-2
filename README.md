# QE API Candidate 2

### Steps:
1. After cloning this repo, create a feature branch off of master
2. Add the test logic for the 4 Scenarios, found in <code>SampleTest.feature</code>
3. Verify the tests run and pass by running <code>mvn test</code>
4. Commit and push your code

Note: the API used for the repo is documented here: https://jsonplaceholder.typicode.com/

### Setup and Run

1. install Maven on your machine
2. run <code>mvn install</code> in the root of this projects directory
3. run the test suite using <code>mvn test</code>

### Structure 

This repo uses the Cucumber testing framework, Rest-Assured as core component for API testing

- Cucumber feature files live in: <code>src/test/resources/features/</code>
- The logic of the feature files live in: <code>src/test/java/stepDefinitions/</code>
- Additional Java helper Classes live in: <code>src/test/java/utilities/</code>
