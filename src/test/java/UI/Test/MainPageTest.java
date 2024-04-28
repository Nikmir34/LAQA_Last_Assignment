package UI.Test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MainPageTest extends BaseTest {

    // Создание Юзера, Добавление прав Юзеру, Создание Заметки
    @BeforeEach
    public void bdCreateTest() {
        dbPage.getSqlMaxIdUser();
        dbPage.getSqlMaxIdRulesUser();
        dbPage.createSqlUser(generatorPage.user);
        dbPage.getSqlRights();
    }

    @DisplayName(value = "Создание заметки с Заголовком, Содержанием и Цветом ")
    @Test
    public void createTitleTextColorNoteTest() throws InterruptedException {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin(generatorPage.user);
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        Thread.sleep(1000);
        mainPage.createNoteButton();
        mainPage.fillNoteTitle("Самый Позитивный Заголовок");
        mainPage.fillNoteText("Самое Позитивное Содержание");
        mainPage.noteColorButtonClick();
        mainPage.greenColorButtonClick();
        mainPage.okButtonClick();
        assertEquals(true, mainPage.notesIsCreated());
    }

    @DisplayName(value = "Создание заметки с Заголовком")
    @Test
    public void createTitleNoteTest() throws InterruptedException {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin(generatorPage.user);
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        Thread.sleep(1000);
        mainPage.createNoteButton();
        mainPage.fillNoteTitle("Позитивный Заголовок");
        mainPage.okButtonClick();
        assertEquals(true, mainPage.notesIsCreated());
    }

    @DisplayName(value = "Создание заметки с Содержанием")
    @Test
    public void createTextNoteTest() throws InterruptedException {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin(generatorPage.user);
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        Thread.sleep(1000);
        mainPage.createNoteButton();
        mainPage.fillNoteText("Позитивное Содержание");
        mainPage.okButtonClick();
        assertEquals(true, mainPage.notesIsCreated());
    }

    @DisplayName(value = "Создание заметки без Заголовка, Содержания, Цвета")
    @Test
    public void createWithoutAnythingNoteTest() throws InterruptedException {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin(generatorPage.user);
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        Thread.sleep(1000);
        mainPage.createNoteButton();
        mainPage.okButtonClick();
        Thread.sleep(1000);
        assertEquals(true, mainPage.alertSaveNoteIsDisplayed());
    }

    @DisplayName(value = "Проверка наличия Заметки ")
    @Test
    public void checkNoteTest() {
        dbPage.createSqlNote();
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin(generatorPage.user);
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        assertEquals(true, mainPage.notesIsCreated());

    }

    @DisplayName(value = "Удаление Заметки")
    @Test
    public void deleteNoteTest() throws InterruptedException {
        dbPage.createSqlNote();
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin(generatorPage.user);
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        Thread.sleep(1000);
        mainPage.deleteNote();
    }

    // Удаление Заметки, Удаление прав Юзеру, Удаление Юзера
    @AfterEach
    public void bdDeleteTest() {
        dbPage.deleteNote();
        dbPage.getSqlLastIdRulesUser();
        dbPage.deleteRights();
        dbPage.getSqlLastIdUser();
        dbPage.deleteUser();
    }
    @AfterEach
    public void afterTest(){
        authorizationPage.makeScreenshot(driver);}
}
