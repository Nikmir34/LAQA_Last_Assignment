package UI.Pages;

import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbPage{
    private WebDriver driver;

    public DbPage(WebDriver driver) {
        this.driver = driver;
    }

    static int MaxId;

    static int MaxRightsId;
    static int LastId;
    static int LastRightsId;
    // id Пользака
    private Integer id = 789;
    // id Заметки
    protected Integer noteId = 66667;
    // id Прав пользователя
    private Integer rightsId = 888;

    public DbPage() {
    }
    // Получаем MAX+1 Id Пользователя
    public void getSqlMaxIdUser() {
        selectQuery("SELECT MAX(id)+1 FROM nfaut.users");
    }

    // Получаем Last Id Пользователя
    public void getSqlLastIdUser() {
        selectQuery("SELECT MAX(id) FROM nfaut.users");
    }

    // Получаем MAX+1 Id Права Пользователя
    public void getSqlMaxIdRulesUser() {
        selectRightsQuery("SELECT MAX(id)+1 FROM nfaut.users_roles");
    }

    // Получаем Last Id Права Пользователя
    public void getSqlLastIdRulesUser() {
        selectRightsQuery("SELECT MAX(id) FROM nfaut.users_roles");
    }

    // Создаем пользователя в БД

    public void createSqlUser(String user) {
        executeQuery("INSERT INTO nfaut.users (id,login,password) VALUES( " + MaxId + " ,'" + user + "', '$2a$10$xO8gyOgHOG8Im4tVudUQFOK1moV.KmvWDV.Z0OUVni2xS4AApDDWS')");
    }

    // Даем права пользователю в БД

    public void getSqlRights() {
        executeQuery("INSERT INTO nfaut.users_roles (id,user_id,role_id) VALUES( " + MaxRightsId + " ,'" + MaxId + "', 2)");
    }

    // Создаем заметку в БД

    public void createSqlNote() {
        executeQuery("INSERT INTO nfaut.notes (id,user_id,name,color, content, priority) VALUES( " + noteId + " ,'" + MaxId + "', 'SQL Заголовок', '#ccff90', 'SQL Содержание', 1)");
    }

    // Обновляем заметку в БД
    public void updateSqlNote(String name, String content, String color) {
        executeQuery("UPDATE nfaut.notes SET name = '" + name + "', content = '" + content + "', color = '" + color + "'  WHERE id = " + noteId);
    }


    // Удаляем заметку
    public void deleteNote() {
        executeQuery(" DELETE FROM nfaut.notes WHERE user_id = ' " + MaxId + "' ");
    }

    // Удаляем права пользователя
    public void deleteRights() {
        executeQuery(" DELETE FROM nfaut.users_roles WHERE id = ' " + LastRightsId + "' ");
    }

    // Удаляем пользователя
    public void deleteUser() {
        executeQuery(" DELETE FROM nfaut.users WHERE id = ' " + LastId + "' ");
    }

    public void executeQuery(String sql) {
        try {
            String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
            String login = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectQuery(String sql) {
        try {
            String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
            String login = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    MaxId = resultSet.getInt(1);
                    LastId = resultSet.getInt(1);
                }
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectRightsQuery(String sql) {
        try {
            String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
            String login = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    MaxRightsId = resultSet.getInt(1);
                    LastRightsId = resultSet.getInt(1);
                }
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
