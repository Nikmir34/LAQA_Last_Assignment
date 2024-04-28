package UI.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private By logoutButton = By.id("logout-btn");
    private By note = By.id("note-container-" + 66667);
    // Контейнер с заметкой By.cssSelector
    private By noteContainerCss = By.cssSelector(".Card_container__YjYSI");
    // Блок создания заметки
    private By noteContainerCreate = By.className("modal-header");
    // Кнопка Создать заголовок
    private By createNoteButton = By.className("Card_containerNew__adAai");
    // Поле Заголовок
    private By noteTitle = By.cssSelector(".ModalCard_cardBodyInput__ghZU0.modal-title");
    // Поле Текст
    private By noteText = By.id("note-modal-content-new_empty");
    // Кнопка Палитра Цветов
    private By noteColor = By.id("palette-btn-new_empty");
    // Кнопка Зеленого цвета
    private By greenColorButton = By.cssSelector(".PalettePopover_colorOption__lno8b:nth-of-type(4)");
    // Кнопка ОК
    private By okButton = By.id("note-modal-save-btn-new_empty");

    // Кнопка корзина
    private By trashButton = By.cssSelector(".img-fluid:nth-of-type(2)");
    // Кнопка Ок в Корзине
    private By okTrashButton = By.cssSelector(".btn.btn-primary");

    // Аллерт Сохранения Заметки
    private By alertSaveNote = By.className("AlertModal_modalBody__3MORK");

    // Кнопка "Удаленные"
    private By deletedButton = By.xpath("//span[contains(.,'Удаленные')]");

    // Последняя удаленная заметка
    private By lastDeletedNote = By.xpath("//div[contains(@id,'note-container')][last()]//p");

    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Клик на нопку Создать заголовок
    public void createNoteButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(createNoteButton)).click();
    }

    // Заполнение поля Заголовок
    public void fillNoteTitle(String title) {
        driver.findElement(noteTitle).sendKeys(title);
    }

    // Получение Значения поля Заголовок
    public String getNoteTitle() {
        return driver.findElement(noteTitle).getText();
    }

    // Заполнение поля Текст
    public void fillNoteText(String text) {
        driver.findElement(noteText).sendKeys(text);
    }

    // Получение Значения поля Текст
    public String getNoteText() {
        return driver.findElement(noteText).getText();
    }

    //Клик на нопку Палитра Цветов
    public void noteColorButtonClick() {
        driver.findElement(noteColor).click();
    }

    //Клик на кнопку Зеленого цвета
    public void greenColorButtonClick() {
        driver.findElement(greenColorButton).click();
    }

    // Получение Значения цвета
    public String getNoteColor() {
        return driver.findElement(noteContainerCreate).getCssValue("background-color");
    }

    //Клик на кнопку ОК
    public void okButtonClick() {
        driver.findElement(okButton).click();
    }

    //Удаление заметки
    public void deleteNote() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(trashButton)).click();
        driver.findElement(okTrashButton).click();
    }

    // Обновление страницы
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // Получение id Заметки
    @Step
    public String getNoteId() {
        String NoteId = driver.findElement(noteContainerCss).getAttribute("id");
        System.out.println(NoteId);
        return NoteId;
    }

    // Клик на кнопку Удаленные
    @Step
    public void deletedButtonClick() {
        driver.findElement(deletedButton).click();
    }

    // Проверка Наличия Удаленной Заметки
    @Step
    public Boolean deletedNoteIsTrue(String NoteId) {

        if (driver.findElement(By.id(NoteId)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }


    // Проверка Наличия Заметки
    @Step
    public Boolean notesIsCreated() {
        if (driver.findElement(noteContainerCss).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    // Проверка отображения кнопки выхода
    @Step
    public Boolean logoutButtonIsDisplayed() {
        if (driver.findElement(logoutButton).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    // Проверка отображения Алерта Сохранения Заметки
    @Step
    public Boolean alertSaveNoteIsDisplayed() {
        if (driver.findElement(alertSaveNote).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
