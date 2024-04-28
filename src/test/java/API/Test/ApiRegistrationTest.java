package API.Test;

import API.DTO.UserCreationDTO;
import API.Pojo.User;
import API.Specification.RegistrationSpec;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ApiRegistrationTest {

    RegistrationSpec registrationSpec = new RegistrationSpec();
    private User newUser;

    private UserCreationDTO userCreationDTO;

    @Before
    public void beforeTest() {
        //Генерация пользователя
        newUser = new User();
        newUser = newUser.genetateUser();
    }

    @Test
    @DisplayName(value = "Регистрация пользователя c Логином и паролем")
    public void registrationLoginPasswordUserTest() {
        userCreationDTO = UserCreationDTO.builder()
                .login(newUser.getLogin())
                .password(newUser.getPassword())
                .build();


        registrationSpec.createReqSpecRegistration(userCreationDTO);
        registrationSpec.createResSpecRegistration(201);
        registrationSpec.postRegistration();
    }

    @Test
    @DisplayName(value = "Регистрация пользователя cо всеми полями")
    public void registrationAllFieldsUserTest() {
        userCreationDTO = UserCreationDTO.builder()
                .login(newUser.getLogin())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .roles(newUser.getRoles())
                .build();


        registrationSpec.createReqSpecRegistration(userCreationDTO);
        registrationSpec.createResSpecRegistration(201);
        registrationSpec.postRegistration();
    }

    @Test
    @DisplayName(value = "Регистрация пользователя c Логином")
    public void registrationLoginUserTest() {
        userCreationDTO = UserCreationDTO.builder()
                .login(newUser.getLogin())
                .build();


        registrationSpec.createReqSpecRegistration(userCreationDTO);
        registrationSpec.createResSpecRegistration(500);
        registrationSpec.postRegistrationFail("Password is required");
    }

    @Test
    @DisplayName(value = "Регистрация пользователя c Паролем")
    public void registrationPasswordUserTest() {
        userCreationDTO = UserCreationDTO.builder()
                .password(newUser.getPassword())
                .build();


        registrationSpec.createReqSpecRegistration(userCreationDTO);
        registrationSpec.createResSpecRegistration(500);
        registrationSpec.postRegistrationFail("Login is required");
    }

    @Test
    @DisplayName(value = "Регистрация пользователя с пустыми полями")
    public void registrationWithoutFieldsUserTest() {
        userCreationDTO = UserCreationDTO.builder()
                .build();


        registrationSpec.createReqSpecRegistration(userCreationDTO);
        registrationSpec.createResSpecRegistration(500);
        registrationSpec.postRegistrationFail("Login is required Password is required");
    }
}
