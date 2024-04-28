package API.Test;

import API.DTO.NoteDTO;
import API.Specification.MainSpec;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class ApiMainTest {

    MainSpec mainSpec = new MainSpec();
    private NoteDTO noteDTO;

    @BeforeEach
    public void loginNotesTest() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "nikmir");
        params.put("password", "Qwerty$4xdd");

        JsonPath response = RestAssured.given()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();

        mainSpec.token = response.get("access_token");
    }

    @Test
    @DisplayName(value = "Создание заметки")
    public void createNoteTest() {

        noteDTO = NoteDTO.builder()
                .name("1")
                .content("1")
                .color("#fcba03")
                .priority(0)
                .build();

        mainSpec.requestSpecCreateNote(noteDTO);
        mainSpec.responseSpecCreateNote(201);
        mainSpec.postNotes();

    }

    @Test
    @DisplayName(value = "Обновление заметки")
    public void editNoteTest() {
        noteDTO = NoteDTO.builder()
                .id(449)
                .name("1")
                .content("1")
                .color("#fcba03")
                .priority(0)
                .build();

        mainSpec.requestSpecCreateNote(noteDTO);
        mainSpec.responseSpecCreateNote(204);
        mainSpec.putNotes();

    }

    @Test
    @DisplayName(value = "Архивирование заметки")
    public void archiveNoteTest() {
        mainSpec.requestSpecDeleteNote();
        mainSpec.responseSpecCreateNote(204);
        mainSpec.deleteNotes();
    }
}
