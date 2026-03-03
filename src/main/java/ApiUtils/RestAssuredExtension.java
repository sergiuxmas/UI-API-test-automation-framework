package ApiUtils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import utils.ReadConfigFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RestAssuredExtension {


    RequestSpecBuilder builder = new RequestSpecBuilder();
    public static RequestSpecification Request;

    static String baseURI = ReadConfigFile.readConfig("baseURI");
    static String basePath = ReadConfigFile.readConfig("basePath");

    public RestAssuredExtension() {
        builder.setBaseUri(baseURI);
        builder.setBasePath(basePath);
        builder.addHeader("x-api-key", "reqres_01fd813fbf3d4b79beee380b409dad3d");
        builder.addHeader("User-Agent", "ui-api-tests/1.0");
        builder.setContentType(ContentType.JSON);

        RequestSpecification requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
    }


    public static ResponseOptions<Response> POSTOpsWithBody(String url, Map<String, String> body) throws URISyntaxException {
        Request.body(body);
        return Request.post(new URI(baseURI + basePath + url));

    }


    public static ResponseOptions<Response> GetOps(String URL) throws URISyntaxException {
        return Request.get(new URI(baseURI + basePath + URL));

    }

    public static ResponseOptions<Response> DeleteOps(String pathParams) throws URISyntaxException {
        return Request.delete(pathParams);
    }

}
