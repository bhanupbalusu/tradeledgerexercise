Feature: Validating checkEligibility API
  @TestOne
  Scenario Outline: Verifying the API health status
    Given applicant details with "<name>" "<email>" "<address>" if server is down
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get service unavailable with status code 503
    Examples:
      |name           |email           |address                |
      |Boris          |123@abc.com     |town cross center      |

  @TestTwo
  Scenario Outline: Authorizing applicant details using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are not null
    And   provided "<apiKeyName>" and "<apiKeyValue>" in request header
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call should return success code 200 if "<apiKeyName>" and "<apiKeyValue>" is valid else 401
    Examples:
      |name           |email          |address            |apiKeyName       |apiKeyValue     |
      |Boris          |123@abc.com    |town cross center  |apiKey           |LetMeIn         |
      |Angela         |123@abc.com    |town cross center  |apiKey           |                |
      |Theresa        |123@abc.com    |town cross center  |apiKey           |DoNotLetMeIn    |
      |Test Name       |123@abc.com    |town cross center  |apiKey           |dsds£4545&!^    |

  @TestTwoPositive
  Scenario Outline: Authorizing applicant details using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are not null
    And   provided valid "<apiKeyName>" and "<apiKeyValue>" in request header
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get success with status code 200
    Examples:
      |name           |email          |address            |apiKeyName       |apiKeyValue     |
      |Boris          |123@abc.com    |town cross center  |apiKey           |LetMeIn         |
      |Angela         |123@abc.com    |town cross center  |apiKey           |LetMeIn         |
      |Theresa        |123@abc.com    |town cross center  |apiKey           |LetMeIn         |
      |Test Name       |123@abc.com    |town cross center  |apiKey           |LetMeIn         |

  @TestTwoNegative
  Scenario Outline: Authorizing applicant details using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are not null
    And   provided invalid "<apiKeyName>" and "<apiKeyValue>" in request header`
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get unauthorized with status code 401
    Examples:
      |name           |email          |address            |apiKeyName       |apiKeyValue     |
      |Boris          |123@abc.com    |town cross center  |apiKey           |                |
      |Angela         |123@abc.com    |town cross center  |apiKey           |DonotLetMeIn    |
      |Theresa        |123@abc.com    |town cross center  |apiKey           |$dsdsd%df!^     |
      |Test Name       |123@abc.com    |town cross center  |apiKey           |sdasdasd13123   |

  @TestThree
  Scenario Outline: Verifying eligibility of cards for an applicant using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are not null
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get success with status code 200
    And   for given applicant with "<name>" it should show eligible cards
      |name|eligibleCards|
      |Boris|C1|
      |Angela|C1,C2|
      |Theresa|C2|
      |Test Name||

    Examples:
      |name           |email          |address            |
      |Boris          |123@abc.com    |town cross center  |
      |Angela         |123@abc.com    |town cross center  |
      |Theresa        |123@abc.com    |town cross center  |
      |Test Name      |123@abc.com    |town cross center  |


  @TestFour
  Scenario Outline: Verifying eligibility of cards for an applicant's name with lowercase using CheckEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are not null
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get success with status code 200
    And   for given applicant with "<name>" it should show eligible cards
      |name|eligibleCards|
      |Boris|C1|
      |Angela|C1,C2|
      |Theresa|C2|

    Examples:
      |name           |email          |address            |
      |boris          |123@abc.com    |town cross center  |
      |angela         |123@abc.com    |town cross center  |
      |theresa        |123@abc.com    |town cross center  |
      |Test Name       |123@abc.com    |town cross center  |


  @TestFive
  Scenario Outline: Verifying eligibility of cards for an applicant Name have trailing spaces using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are having trailing spaces
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get success with status code 200
    And   for given applicant with "<name>" it should show eligible cards
      |name|eligibleCards|
      |Boris|C1|
      |Angela|C1,C2|
      |Theresa|C2|
      |Test Name||

    Examples:
      |name           |email           |address               |
      |' Boris '      |123@abc.com     |town cross center     |
      |Theresa          |' 123@abc.com ' |town cross center     |
      |' Angela '      |123@abc.com     |' town cross center ' |
      |' Test Name '   |123@abc.com     |town cross center     |


  @TestSix
  Scenario Outline: Verifying eligibility of cards for an applicant with NULL fields using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are null
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get bad request with status code 400

    Examples:
      |name           |email          |address            |
      |NULL           |123@abc.com    |town cross center  |
      |Boris          |NULL           |town cross center  |
      |Boris          |123@abc.com    |NULL               |


  @TestSeven
  Scenario Outline: Verifying eligibility of cards for an applicant Name Other than String using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are invalid integer data type
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get bad request with status code 400

    Examples:
      |name          |email            |address            |
      |123           |123              |123                |


  @TestEight
  Scenario Outline: Verifying eligibility of cards for an applicant Name Other than String using checkEligibility API
    Given applicant details with "<username>" "<email>" "<address>" with invalid name key in request body
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get bad request with status code 400

    Examples:
      |username          |email                  |address            |
      |Boris             | 123@abc.com           |town cross center  |

  @TestNine
  Scenario: Verifying eligibility of cards for an applicant Name that is empty using checkEligibility API
    Given empty request body without applicant details
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get bad request with status code 400

  @TestTen
  Scenario Outline: Verifying eligibility of cards for an applicant Name Other than String using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" where email is string and not null
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  for applicant "<email>" address API call should return status code 200 if email is valid else 400

    Examples:
      |name          |email                  |address            |
      |Boris         |123@abc.com            |town cross center  |
      |Angela        |@yahoo.com             |town cross center  |
      |Theresa       |user#domain.com        |town cross center  |
      |Test Name      |.abcdef                |town cross center  |
      |Test Name      |' '                    |town cross center  |

  @TestTenPositive
  Scenario Outline: Verifying eligibility of cards for an applicant Name Other than String using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" where email address is valid
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get success with status code 200

    Examples:
      |name          |email                  |address            |
      |Boris         |123@abc.com            |town cross center  |
      |Angela        |x@yahoo.com            |town cross center  |
      |Theresa       |user@domain.co.uk      |town cross center  |
      |Test Name      |test@duck.io           |town cross center  |

  @TestTenNegative
  Scenario Outline: Verifying eligibility of cards for an applicant Name Other than String using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" where email address is invalid
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get bad request with status code 400

    Examples:
      |name          |email                  |address            |
      |Boris         |123@abc.com            |town cross center  |
      |Angela        |@yahoo.com             |town cross center  |
      |Theresa       |user#domain.com        |town cross center  |
      |Test Name      |.abcdef                |town cross center  |
      |Test Name      |' '                    |town cross center  |

  @TestEleven
  Scenario Outline: Verifying eligibility of cards for an applicant name is empty string using checkEligibility API
    Given applicant details with "<name>" "<email>" "<address>" which are empty strings
    When  "CheckEligibilityAPI" is called with "POST" http request
    Then  the API call get bad request with status code 400

    Examples:
      |name           |email          |address            |
      |Boris          |               |                   |
      |Angela         |               |                   |
      |Theresa        |               |                   |
      |Test Name       |               |                   |
      |Boris          |123@abc.com    |                   |
      |Angela         |123@abc.com    |                   |
      |Theresa        |123@abc.com    |                   |
      |Test Name       |123@abc.com    |                   |
      |Boris          |               |town cross center  |
      |Angela         |               |town cross center  |
      |Theresa        |               |town cross center  |
      |Test Name       |               |town cross center  |
      |               |123@abc.com    |town cross center  |
      |               |123@abc.com    |                   |
      |               |               |town cross center  |
      |               |               |                   |
