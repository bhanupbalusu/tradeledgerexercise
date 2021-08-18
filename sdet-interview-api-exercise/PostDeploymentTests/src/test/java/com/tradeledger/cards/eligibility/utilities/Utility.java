package com.tradeledger.cards.eligibility.utilities;

import com.tradeledger.cards.eligibility.pojo.models.request.ApplicantDetails;
import com.tradeledger.cards.eligibility.pojo.models.request.InvalidDataTypeApplicantDetails;
import com.tradeledger.cards.eligibility.pojo.models.request.InvalidKeyApplicantDetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    private static RequestSpecification request;
    protected RequestSpecification requestSpec;

    protected static class Secrets {
        public static String apiKeyName = "apiKey";
        public static String apiKeyValue = "LetMeIn";
    }

    private static final String GLOBALVARFILEPATH = "src/test/resources/application.properties";
    public RequestSpecification requestSpecification() throws IOException {
        if(request == null) {
            PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
            request = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUrl"))
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();

            return request;
        }

        return request;
    }

    public String getGlobalValue(String baseUrl) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(GLOBALVARFILEPATH);
        properties.load(fileInputStream);
        return properties.getProperty("baseUrl");
    }

    public static String handleNullStrCheck(String source) {
        return (source.trim().equalsIgnoreCase("NULL")) ? null : source;
    }

    public static String handleTrailingSpaceSurroundWithSingleQuoteCheck(String source) {
        String pattern = "^\'|\'$";
        return source.replaceAll(pattern, "");
    }

    public static String handleTrailingSpaceAndNullCheck(String source) {
        return handleNullStrCheck(handleTrailingSpaceSurroundWithSingleQuoteCheck(source));
    }

    protected void mapValidPayloadToRequest(String name, String email, String address) throws IOException {
        ApplicantDetails payload = new TestDataBuild().applicantDetailsPayload(name, email, address);
        requestSpec = given().spec(requestSpecification()).body(payload);
    }

    protected void mapInValidDataTypePayloadToRequest(String name, String email, String address) throws IOException {
        InvalidDataTypeApplicantDetails payload = new TestDataBuild().invalidDataTypeApplicantDetailsPayload(name, email, address);
        requestSpec = given().spec(requestSpecification()).body(payload);
    }


    protected void mapInValidKeyPayloadToRequest(String username, String email, String address) throws IOException {
        InvalidKeyApplicantDetails payload = new TestDataBuild().invalidKeyApplicantDetailsPayload(username, email, address);
        requestSpec = given().spec(requestSpecification()).body(payload);
    }

    protected void mapRequestWithoutPayloadInBody() throws IOException {
        requestSpec = given().spec(requestSpecification());
    }

    protected Boolean isValidEmailAddress(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
