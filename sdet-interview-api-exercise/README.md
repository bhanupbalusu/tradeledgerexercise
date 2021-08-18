# Cards Application

## Problem statement

A bank has launched 2 new credit card products, C1 and C2. They have built an API to check the eligibility of the person applying. The app works by the user entering and submitting their name, email and address. The server then responds with the credit cards that person is eligible for.

Before they deploy this API to production they need to ensure the quality of it and data integrity.

## Exercise Overview

Write API tests for TradeLedger API under `PostDeploymentTests` in your choice of framework preferably Cucumber with Java RestAssured or Karate

1. Entering name, email and address
2. Submitting the form with a range of different values
3. The expected response based on the names Boris, Angela and Theresa

## How to submit the exercise

1. Clone the repo locally.
2. Complete the exercise.
3. Upload your completed project to your GitHub, and then send us a link to your repository along with any comments you have about your solution.

## How to Run

Open the project with VS Code, IntelliJ, Eclipse or any other IDE of your choice

Run backend locally:

1. In terminal navigate to repository root level
2. `./gradlew build` -- to build the app
3. `./gradlew bootrun` -- to run the app

Once backend is running, you can view the Swagger specification here:

 `http://localhost:8080/swagger-ui.html`

## Task

1. Using an API framework write tests for all possible scenarios of the web app to ensure confidence of it's quality
2. Create a folder/file to hold all your tests
3. Add instructions to this README file for running the tests

## Considerations (only for Solution Design section)

1. The bank would like end-to-end tests to be executed each time before a deployment
2. The bank would like to test the security of their application to ensure sensitive data cannot be compromised
3. The bank would like to devise a test strategy for introducing new features to the web app
