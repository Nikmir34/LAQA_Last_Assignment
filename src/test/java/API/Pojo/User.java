package API.Pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String login;
    private String password;
    private String email;
    private List<Roles> roles;


    public void setDefaultRoles() {
        Roles defaultRoles = new Roles();
        defaultRoles.setId("2");
        defaultRoles.setName("ROLE_USER");

        List<Roles> defaultListRole = new ArrayList<>();
        defaultListRole.add(defaultRoles);

        this.roles = defaultListRole;
    }

    public User genetateUser(){
        int num = 1 + (int) (Math.random() * 55555);

        // Генерация Юзера
        User newUser = new User();
        newUser.setLogin("autotest" + num);
        newUser.setPassword("888777");
        newUser.setEmail("autotest" + num + "@mail.ru");
        newUser.setDefaultRoles();
        return newUser;

    }
}
