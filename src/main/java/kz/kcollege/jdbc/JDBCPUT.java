package kz.kcollege.jdbc;

import spark.Request;

import java.sql.*;

public class JDBCPUT {
    //Ссылка, логин и пароль для входа в БД
    private final String url = "jdbc:mysql://localhost:3306/sqlcollegeweber";
    private final String login = "root";
    private final String password = "root";
    //Переменные для работы с БД в Java
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    //Конструктор
    public JDBCPUT() {
        try {
            //Соединение с БД в Java
            connection = DriverManager.getConnection(url, login, password);
            //Передаем управление БД statement
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error SQL Connecting");
        }
    }
    //Метод для изменения информации пользователя в БД
    public String update(Request request, int id) {
        try {
            //В resultSet помещаем ответ с statement
            resultSet = statement.executeQuery("select * from users where users.id=" + id);
            //Проверка на существование пользователя
            if (resultSet.next()) {
                //Проверка "first" ввода
                if (request.queryParams("first") != null)
                    //Обновляем информацию "first"
                    statement.executeUpdate("update users set users.first_name='" + request.queryParams("first") + "' where users.id=" + id);
                //Проверка "last" ввода
                if (request.queryParams("last") != null)
                    //Обновляем информацию"last"
                    statement.executeUpdate("update users set users.last_name='" + request.queryParams("last") + "' where users.id=" + id);
                //Проверка "age" ввода
                if ((request.queryParams("age") != null) &&
                        (Integer.parseInt(request.queryParams("age")) >= 14 && Integer.parseInt(request.queryParams("age")) <= 100))
                    //Обновляем информацию "age"
                    statement.executeUpdate("update info set info.age=" + Integer.parseInt(request.queryParams("age")) + " where info.id=" + id);
                //Проверка "data" ввода
                if ((request.queryParams("data") != null) && (dataTest(request)))
                    //Обновляем информацию "data"
                    statement.executeUpdate("update info set info.data=\'" + request.queryParams("data") + "\' where info.id=" + id);
            } else {
                //Возвращаем ответ
                return "Error update 1";
            }
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Error update 2";
        }
        //Возвращаем ответ
        return "Success";
    }

    private static boolean dataTest(Request request) {
        //Проверка на ввод "data"
        if (request.queryParams("data") != null) {
            //Только 10 символов
            if (request.queryParams("data").length() == 10) {
                //На пятом и восьмом символе должна быть '-'
                if ((request.queryParams("data").charAt(4) == '-') && (request.queryParams("data").charAt(7) == '-')) {
                    //Возвращаем ответ
                    return true;
                } else {
                    //Возвращаем ответ
                    return false;
                }
            } else {
                //Возвращаем ответ
                return false;
            }
        } else {
            //Возвращаем ответ
            return false;
        }
    }
}
