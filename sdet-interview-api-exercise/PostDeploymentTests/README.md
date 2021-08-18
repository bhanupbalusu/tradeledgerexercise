# Requirement Analysis:

- When an applicant submits name, email and address related details, then the server must respond with the credit cards 
that applicant is eligible for.
- For example  
	|name		|eligibleCards	|
    |Boris		|C1				|
    |Angela		|C1,C2			|
    |Theresa	|C2				|
    |Test Name	|				|
	
## Assumptions:

As the requirement is unclear I made the following assumptions while preparing the testcases:

- @TestOne: 	When API is not listenening it will throw status code 503 (service unavailable)
- @TestTwo: 	The API uses ApiKey based authorization { "apiKey" : "LetMeIn"} which needs to be passed in headers and when authorization
				succeeds it should return statuscode 200 otherwise 401 (unauthorized user)

When applicant submits name, email and address related details:				
- @TestThree: 	which are not null and not empty strings then it should return statuscode 200 and validate response with expected eligible cards
- @TestFour:	where name is in lowercase and other fields are not null and not empty strings then it should return statuscode 200
- @TestFive:	having trailing spaces then it should return statuscode 200
- @TestSix: 	with null data type then it should return statuscode 400 (Bad Request)
- @TestSeven:	with integer or any data type then it should return statuscode 400 (Bad Request)
- @TestEight:	with invalid json name key(s) in request body then it should return statuscode 400 (Bad Request)
- @TestNine:	with empty request body then it should return statuscode 400 (Bad Request)
- @TestTen:		where email is string and not null then it should return 200 when valid and return 400 (Bad Request) when invalid
- @TestEleven:	where applicant name is empty string then it should return 400 (Bad Request)


## Comments:
- I am used to maven so I initially developed this exercise in maven. later when I tried to convert into gradle I faced few issues related to
the junit dependencies. It took ample amount of time to sort it out.
- At present in the checkin code, I am using both junit4 and junit5 dependencies.
- Because of this, the annotation CucumberOptions which is declared in the test runner class is not taking tags parameters.
- If I use @Cucumber annotation using junit5 instead junit4 then test are not getting recognized.
