package UI.Pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AuthorizationPage {
    private String urlPage = "http://172.24.120.5:8081/login";
    // Поле ввода логина
    private By loginTextField = By.id("login-input");
    // Поле ввода пароля
    private By passwordTextField = By.id("password-input");

    // Кнопка "Зарегистрироваться"
    private By registrationButton = By.id("form_register_button");
    // Поле ввода Логина в Поп-апе регистрации
    private By loginFieldRegister = By.cssSelector(".m-3.form-group.required:first-child > input");
    // Поле ввода invalid Логина в Поп-апе регистрации
    private By logininvalidFieldRegister = By.cssSelector(".was-validated > .m-3.form-group.required:first-child > input");
    // Поле ввода Пароля в Поп-апе регистрации
    private By passwordFieldRegister = By.cssSelector(".m-3.form-group.required:nth-child(2) > input");
    // Поле ввода invalid Пароля в Поп-апе регистрации
    private By passwordinvalidFieldRegister = By.cssSelector(".was-validated > .m-3.form-group.required:nth-child(2) > input");

    // Поле ввода Email в Поп-апе регистрации
    private By emailFieldRegister = By.cssSelector(".m-3:nth-child(3) > input");
    // Кнопка "Создать" в Поп-апе регистрации
    private By createButtonRegister = By.cssSelector(".form_create_button.btn.btn-primary");

    // Кнопка "Войти"
    private By loginButton = By.cssSelector(".form_auth_button.btn.btn-primary:first-child");
    private WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открытие страницы авторизации
    @Step
    public void goToAuthorizationPage() {
        driver.get(urlPage);
    }

    // Заполнение поля ввода логина
    @Step
    public void fillInLogin(String loginText) {
        driver.findElement(loginTextField).sendKeys(loginText);
    }

    // Заполнение поля ввода пароля
    @Step
    public void fillInPassword(String passwordText) {
        driver.findElement(passwordTextField).sendKeys(passwordText);
        ;
    }

    // Нажатие на кнопку "Зарегистрироваться"
    @Step
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    // Заполнение поля Логина в Поп-апе регистрации
    @Step
    public void fillRegistrationLogin(String loginRegistrationText) {
        driver.findElement(loginFieldRegister).sendKeys(loginRegistrationText);
    }

    // Заполнение поля Пароля в Поп-апе регистрации
    @Step
    public void fillRegistrationPassword(String passwordRegistrationText) {
        driver.findElement(passwordFieldRegister).sendKeys(passwordRegistrationText);
        ;
    }

    // Заполнение поля Email в Поп-апе регистрации
    @Step
    public void fillRegistrationEmail(String emailRegistrationText) {
        driver.findElement(emailFieldRegister).sendKeys(emailRegistrationText);
    }

    // Нажатие на кнопку "Создать" в Поп-апе регистрации
    @Step
    public void clickCreateButtonRegister() {
        driver.findElement(createButtonRegister).click();
    }

    // Нажатие на кнопку "Войти"
    @Step
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Получение значения поля Email в Поп-апе регистрации
    @Step
    public Object emailIsRegistrationFill() {
        String email = driver.findElement(emailFieldRegister).getAttribute("value");
        return email;
    }

    // Получение invalid Поля ввода логина в Поп-апе регистрации
    @Step
    public boolean getInvalidFieldRegistrationLogin() {
        if (driver.findElement(logininvalidFieldRegister).isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    // Получение invalid Поля ввода пароля в Поп-апе регистрации
    @Step
    public boolean getInvalidFieldRegistrationPassword() {
        if (driver.findElement(passwordinvalidFieldRegister).isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    // Проверка отображения кнопки "Войти"
    @Step
    public boolean loginButtonIsDisplayed() {
        if (driver.findElement(loginButton).isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
