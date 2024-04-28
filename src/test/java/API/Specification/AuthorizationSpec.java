package API.Specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static API.Properties.Properties.BASE_URI;
import static API.Properties.Properties.PATH_LOGIN;

public class AuthorizationSpec {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public void createReqSpecLogin(String username, String password) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_LOGIN)
                .setContentType(ContentType.URLENC)
                .addFormParam("username", username)
                .addFormParam("password", password)
                .build();
    }

    public void createResSpecLogin(int status) {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();

    }

    public void postLoginSuccess() {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("authorization_success_shema.json"));
    }

    public void postLogin() {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification);
    }
}
