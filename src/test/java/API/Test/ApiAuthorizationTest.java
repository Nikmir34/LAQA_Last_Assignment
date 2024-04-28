package API.Test;

import API.Specification.AuthorizationSpec;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class ApiAuthorizationTest {
    AuthorizationSpec authorizationSpec = new AuthorizationSpec();

    @Test
    @DisplayName(value = "Авторизация зарегистрированного пользователя")
    public void authorizationRegisterUserTest() {
        authorizationSpec.createReqSpecLogin("nikmir", "Qwerty$4xdd");
        authorizationSpec.createResSpecLogin(200);
        authorizationSpec.postLoginSuccess();
    }

    @Test
    @DisplayName(value = "Авторизация зарегистрированного пользователя без Пароля")
    public void authorizationRegisterWithoutPasswordUserTest() {
        authorizationSpec.createReqSpecLogin("nikmir", "");
        authorizationSpec.createResSpecLogin(403);
        authorizationSpec.postLogin();
    }

    @Test
    @DisplayName(value = "Авторизация зарегистрированного пользователя без Логина")
    public void authorizationRegisterWithoutLoginUserTest() {
        authorizationSpec.createReqSpecLogin("", "Qwerty$4xdd");
        authorizationSpec.createResSpecLogin(403);
        authorizationSpec.postLogin();
    }

    @Test
    @DisplayName(value = "Авторизация зарегистрированного пользователя с неверным Паролем")
    public void authorizationRegisterWrongPasswordUserTest() {
        authorizationSpec.createReqSpecLogin("nikmir", "Qwerty$4");
        authorizationSpec.createResSpecLogin(403);
        authorizationSpec.postLogin();
    }

    @Test
    @DisplayName(value = "Авторизация зарегистрированного пользователя с неверным Логином")
    public void authorizationRegisterWrongLoginUserTest() {
        authorizationSpec.createReqSpecLogin("nik", "Qwerty$4xdd");
        authorizationSpec.createResSpecLogin(403);
        authorizationSpec.postLogin();
    }

    @Test
    @DisplayName(value = "Авторизация незарегистрированного пользователя")
    public void authorizationUnregisterUserTest() {
        authorizationSpec.createReqSpecLogin("Unreg5555", "Qwerty1234");
        authorizationSpec.createResSpecLogin(403);
        authorizationSpec.postLogin();
    }

    @Test
    @DisplayName(value = "Авторизация без заполненных полей Логина и Пароля")
    public void authorizationWithoutLoginPasswordUserTest() {
        authorizationSpec.createReqSpecLogin("", "");
        authorizationSpec.createResSpecLogin(403);
        authorizationSpec.postLogin();
    }
}
