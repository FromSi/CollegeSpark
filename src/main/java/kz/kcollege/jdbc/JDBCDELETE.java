package kz.kcollege.jdbc;
import java.sql.*;
public class JDBCDELETE {
    //Ссылка, логин и пароль для входа в БД
    private final String url = "jdbc:mysql://localhost:3306/sqlcollegeweber";
    private final String login = "root";
    private final String password = "root";
    //Переменные для работы с БД в Java
    private Connection connection;
    private Statement statement;
    //Конструктор
    public JDBCDELETE() {
        try {
            //Соединение с БД в Java
            connection = DriverManager.getConnection(url, login, password);
            //Передаем управление БД statement
            statement = connection.createStatement();
        } catch (SQLException e) {
            //Возвращаем ответ в консоль
            System.out.println("Error SQL Connecting");
        }
    }
    //Метод для удаления пользователя
    public String delete(int id) {
        try {
            //Проверка пользователя в БД
            if (statement.executeQuery("select * from users where users.id=" + id).next()) {
                //Удаление пользователя с таблицы "users"
                statement.executeUpdate("delete from users where users.id=" + id);
                //Удаление пользователя с таблицы "info"
                statement.executeUpdate("delete from info where users.id=" + id);
                //Возвращаем ответ
                return "User " + id + " deleted";
            } else {
                //Возвращаем ответ
                return "Error while deleting 1";
            }
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Not secure removal. User " + id + " deleted";
        }
    }
}
