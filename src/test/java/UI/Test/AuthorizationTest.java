package UI.Test;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AuthorizationTest extends BaseTest {
    @Test
    @DisplayName("Регистрация Пользователя с Email")
    public void registrationUserWithEmailTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.clickRegistrationButton();
        authorizationPage.fillRegistrationLogin(generatorPage.user);
        authorizationPage.fillRegistrationPassword("Qwerty$4xdd");
        authorizationPage.fillRegistrationEmail(generatorPage.user + "@mail.ru");
        // Проверка заполнения поля Email
        Assertions.assertNotNull(authorizationPage.emailIsRegistrationFill());
        authorizationPage.clickCreateButtonRegister();
    }

    @Test
    @DisplayName("Регистрация Пользователя без Email")
    public void registrationUserWithoutEmailTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.clickRegistrationButton();
        authorizationPage.fillRegistrationLogin(generatorPage.user);
        authorizationPage.fillRegistrationPassword("Qwerty$4xdd");
        // Проверка, что поле Email пустое
        Assertions.assertEquals("" ,authorizationPage.emailIsRegistrationFill());
        authorizationPage.clickCreateButtonRegister();
    }

    @Test
    @DisplayName("Регистрация Пользователя без Логина")
    public void registrationUserWithoutLoginTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.clickRegistrationButton();
        authorizationPage.fillRegistrationLogin("");
        authorizationPage.fillRegistrationPassword("Qwerty$4xdd");
        authorizationPage.clickCreateButtonRegister();
        // Проверка пустого поля Логина
        Assertions.assertEquals(true, authorizationPage.getInvalidFieldRegistrationLogin());
    }

    @Test
    @DisplayName("Регистрация Пользователя без Пароля")
    public void registrationUserWithoutPasswordTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.clickRegistrationButton();
        authorizationPage.fillRegistrationLogin(generatorPage.user);
        authorizationPage.fillRegistrationPassword("");
        authorizationPage.clickCreateButtonRegister();
        // Проверка пустого поля Пароля
        Assertions.assertEquals(true, authorizationPage.getInvalidFieldRegistrationPassword());
    }

    @Test
    @DisplayName("Регистрация Пользователя без Логина и Пароля")
    public void registrationUserWithoutLoginPasswordTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.clickRegistrationButton();
        authorizationPage.fillRegistrationLogin("");
        authorizationPage.fillRegistrationPassword("");
        authorizationPage.clickCreateButtonRegister();
        // Проверка пустого поля Логина и Пароля
        Assertions.assertEquals(true, authorizationPage.getInvalidFieldRegistrationLogin());
        Assertions.assertEquals(true, authorizationPage.getInvalidFieldRegistrationPassword());
    }


    @Test
    @DisplayName("Авторизация Зарегистрированного Пользователя")
    public void authorizationRegisterUserTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin("nikmir");
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        assertEquals(true, mainPage.logoutButtonIsDisplayed());
    }

    @Test
    @DisplayName("Авторизация Пользователя с Неверным Логином")
    public void authorizationWithInvalidLoginTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin("2231231");
        authorizationPage.fillInPassword("Qwerty$4xdd");
        authorizationPage.clickLoginButton();
        assertEquals(true, authorizationPage.loginButtonIsDisplayed());
    }

    @Test
    @DisplayName("Авторизация Пользователя с Неверным Паролем")
    public void authorizationWithInvalidPasswordTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin("nikmir");
        authorizationPage.fillInPassword("55555555");
        authorizationPage.clickLoginButton();
        assertEquals(true, authorizationPage.loginButtonIsDisplayed());
    }

    @Test
    @DisplayName("Авторизация Пользователя с Пустым логином и Паролем")
    public void authorizationWithEmptyLoginPasswordTest() {
        authorizationPage.goToAuthorizationPage();
        authorizationPage.fillInLogin("");
        authorizationPage.fillInPassword("");
        authorizationPage.clickLoginButton();
        assertEquals(true, authorizationPage.loginButtonIsDisplayed());
    }

}
