package API.Specification;

import API.DTO.NoteDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static API.Properties.Properties.*;
import static org.hamcrest.Matchers.equalTo;

public class MainSpec {

    public String token;
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public void requestSpecCreateNote(NoteDTO noteDTO) {
        List<NoteDTO> noteList = new ArrayList<>();
        noteList.add(noteDTO);

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(CREATE_NOTE_ENDPOINT)
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON)
                .setBody(noteList)
                .build();
    }

    public void requestSpecDeleteNote() {

        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri(BASE_URI)
                .setBasePath(ARCHIVE_NOTE)
                .setContentType(ContentType.JSON)
                .build();
    }

    public void responseSpecCreateNote(int status) {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public void postNotes() {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createNote.json"))
                .body("[0].name", equalTo("1"))
                .body("[0].content", equalTo("1"))
                .body("[0].color", equalTo("#fcba03"))
                .body("[0].priority", equalTo(0));
    }

    public void putNotes() {
        RestAssured.given(requestSpecification).log().all()
                .put()
                .then().log().all().spec(responseSpecification);
    }

    public void deleteNotes() {
        RestAssured.given(requestSpecification).log().all()
                .delete()
                .then().log().all().spec(responseSpecification);
    }
}
