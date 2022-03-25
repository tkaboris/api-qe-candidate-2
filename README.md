# task-qe-automation

QE - Task Project

### Setup and Run

1. install Maven on your machine (currently using 3.6.3)
2. run <code>mvn install</code> in the root of this projects directory
3. run the test suite using <code>mvn test</code>

Note: To run a single Feature or Scenario 
1. Add a unique tag (<code>@TestOne</code>) above the "Feature" or "Scenario" keyword in a .feature file.
2. Edit TestRunner.java: <code>tags = "@Regression"</code> with <code>tags = "@TestOne"</code>

   OR
2. Comment out <code>tags = "@Regression"</code> in TestRunner.java and then run <code>mvn test -Dcucumber.filter.tags="@TestOne"</code>

### Structure 

This repo uses the Cucumber testing framework, Rest-Assured as core component for API testing, and MongoDB for database validation.

- Cucumber feature files live in: <code>src/test/resources/features/</code>
- The logic of the feature files live in: <code>src/test/java/stepDefinitions/</code>
- Additional Java helper Classes live in: <code>src/test/java/utilities/</code>


### Reports

A new HTML report generates after each run of <code>mvn test</code>. Locally this can be found in: <code>target/cucumber-html-reports/overview-features.html</code>  
Note: Current reports will be overwritten when executing tests locally.

In Gitlab, reports can be found in:  
CI/CD > Pipelines > qe_automation Job > Browse Job artifacts >
target / cucumber-html-reports / select any one of the overview html files