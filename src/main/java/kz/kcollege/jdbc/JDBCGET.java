package kz.kcollege.jdbc;

import java.sql.*;

public class JDBCGET {
    //Ссылка, логин и пароль для входа в БД
    private final String url = "jdbc:mysql://localhost:3306/sqlcollegeweber";
    private final String login = "root";
    private final String password = "root";
    //Переменные для работы с БД в Java
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    //Конструктор
    public JDBCGET() {
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
    //Метод для вывода пользователей из БД
    public String print(boolean bool) {
        try {
            if (!bool) {
                //В resultSet помещаем ответ с statement
                resultSet = statement.executeQuery("select * from users");
                //Возвращаем ответ
                return new JDCBDisplay().start(bool, resultSet);
            } else {
                resultSet = statement.executeQuery("select users.id, users.first_name, users.last_name," +
                        "info.age, info.data from users, info where users.id=info.id");
                //Возвращаем ответ
                return new JDCBDisplay().start(bool, resultSet);
            }
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Error SQL select 1";
        }
    }
    //Метод для вывода пользователей из БД
    public String print(boolean bool, int id) {
        try {
            if (!bool) {
                //В resultSet помещаем ответ с statement
                resultSet = statement.executeQuery("select * from users where id=" + id);
                //Возвращаем ответ
                return new JDCBDisplay().start(bool, resultSet);
            } else {
                resultSet = statement.executeQuery("select users.id, users.first_name, users.last_name," +
                        "info.age, info.data from users, info where users.id=" + id + " and info.id=" + id);
                //Возвращаем ответ
                return new JDCBDisplay().start(bool, resultSet);
            }
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Error SQL select 2";
        }
    }
}
