package API.Specification;

import API.DTO.UserCreationDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static API.Properties.Properties.BASE_URI;
import static API.Properties.Properties.PATH_REGISTRATION;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationSpec {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public void createReqSpecRegistration(UserCreationDTO userCreationDTO) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(PATH_REGISTRATION)
                .setContentType(ContentType.JSON)
                .setBody(userCreationDTO)
                .build();
    }

    public void createResSpecRegistration(int status) {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();

    }

    public void postRegistration() {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification);
    }

    public void postRegistrationFail(String message) {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("registrarion_error_shema.json"))
                .body("level", equalTo("ERROR"),
                        "message", equalTo(message));
    }
}
