package kz.kcollege.jdbc;

import spark.Request;

import java.sql.*;

public class JDBCPOST {
    //Ссылка, логин и пароль для входа в БД
    private final String url = "jdbc:mysql://localhost:3306/sqlcollegeweber";
    private final String login = "root";
    private final String password = "root";
    //Переменные для работы с БД в Java
    private Connection connection;
    private Statement statement;
    //Конструктор
    public JDBCPOST() {
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

    public String start(Request request) {
        //Проверка "first" и "last", чтобы продолжить работу
        if ((request.queryParams("first") != null) && (request.queryParams("last") != null)) {
            //Проверка "age" и "data", для работы с "age" и "data"
            if ((request.queryParams("age") != null) || (request.queryParams("data") != null)) {
                //Если "age" и "data" верны, записываем "age" и "data"
                if ((request.queryParams("age") != null) && (dataTest(request))) {
                    try {
                        if (Integer.parseInt(request.queryParams("age")) >= 14 && Integer.parseInt(request.queryParams("age")) <= 100) {
                            //Возвращаем ответ
                            return create(request.queryParams("first"), request.queryParams("last"),
                                    Integer.parseInt(request.queryParams("age")), request.queryParams("data"));
                        } else {
                            //Возвращаем ответ
                            return "Error: only min 14 - max 100 -> age";
                        }
                    } catch (NumberFormatException e) {
                        //Возвращаем ответ
                        return "Error: only int -> age";
                    }
                } else if (request.queryParams("age") != null) { //Если "age" верен, записываем "age"
                    try {
                        //Если "age" (14<=age<=100), то добавим age
                        if (Integer.parseInt(request.queryParams("age")) >= 14 && Integer.parseInt(request.queryParams("age")) <= 100) {
                            //Возвращаем ответ
                            return create(request.queryParams("first"), request.queryParams("last"),
                                    Integer.parseInt(request.queryParams("age")));
                        } else {
                            //Возвращаем ответ
                            return "Error: only min 14 - max 100 -> age";
                        }
                    } catch (NumberFormatException e) {
                        //Возвращаем ответ
                        return "Error: only int -> age";
                    }
                } else if (dataTest(request)) {//Если "data" верен, записываем "data"
                    //Возвращаем ответ
                    return create(request.queryParams("first"), request.queryParams("last"), request.queryParams("data"));
                } else {
                    //Возвращаем ответ
                    return "Error Link";
                }
            }
            //Возвращаем ответ
            return create(request.queryParams("first"), request.queryParams("last"));
        } else {
            //Возвращаем ответ
            return "Error initialization";
        }
    }
    //Метод для создания нового пользователя в БД
    public String create(String firsName, String lastName) {
        try {
            //Добавляем в таблицу "users" Имя и Фамилию пользователя
            statement.execute("insert into users (first_name, last_name) values ('" + firsName + "', '" + lastName + "')");
            //Добавляем в таблицу "users" пустой Возраст и пустю Дату пользователя
            statement.execute("insert into info (age, data) values (null, null)");
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Error create information 1";
        }
        //Возвращаем ответ
        return "Success";
    }
    //Метод для создания нового пользователя в БД
    public String create(String firsName, String lastName, int age) {
        try {
            //Добавляем в таблицу "users" Имя и Фамилию пользователя
            statement.execute("insert into users (first_name, last_name) values ('" + firsName + "', '" + lastName + "')");
            //Добавляем в таблицу "users" Возраст и пустю Дату пользователя
            statement.execute("insert into info (age, data) values ('" + age + "', null)");
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Error create information 2";
        }
        //Возвращаем ответ
        return "Success";
    }
    //Метод для создания нового пользователя в БД
    public String create(String firsName, String lastName, String data) {
        try {
            //Добавляем в таблицу "users" Имя и Фамилию пользователя
            statement.execute("insert into users (first_name, last_name) values ('" + firsName + "', '" + lastName + "')");
            //Добавляем в таблицу "users" пустой Возраст и Дату пользователя
            statement.execute("insert into info (age, data) values (null, '" + data + "')");
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Error create information 3";
        }
        //Возвращаем ответ
        return "Success";
    }
    //Метод для создания нового пользователя в БД
    public String create(String firsName, String lastName, int age, String data) {
        try {
            //Добавляем в таблицу "users" Имя и Фамилию пользователя
            statement.execute("insert into users (first_name, last_name) values ('" + firsName + "', '" + lastName + "')");
            //Добавляем в таблицу "users" Возраст и Дату пользователя
            statement.execute("insert into info (age, data) values ('" + age + "', '" + data + "')");
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Error create information 4";
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
