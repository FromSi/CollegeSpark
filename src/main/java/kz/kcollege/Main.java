package kz.kcollege;

import kz.kcollege.jdbc.JDBCDELETE;
import kz.kcollege.jdbc.JDBCGET;
import kz.kcollege.jdbc.JDBCPOST;
import kz.kcollege.jdbc.JDBCPUT;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        //Обращение начинается с http://localhost/
        port(80);
        //Синтаксис для подробного просмотра пользователя из БД
        // http://localhost/users/{id} - где id - это любой id пользователя взятый из БД
        get("/users/:id", (request, response) -> new JDBCGET().print(true, Integer.parseInt(request.params(":id"))));
        //Синтаксис для просмотра всех пользователей из БД
        // http://localhost/users
        get("/users", (request, response) -> new JDBCGET().print(false));
        //Синтаксис для добавления новых пользователей
        // http://localhost/add ?
        // & first = <Имя пользователя>
        // & last = <Фамилия пользователя>
        // & age = <Возраст пользователя>
        // & data = <Дата рождения пользователя>
        post("/add", (request, response) -> new JDBCPOST().start(request));
        //Синтаксис для удаления пользователей в БД
        // http://localhost/users/{id} - где id - это любой id пользователя взятый из БД
        delete("/users/:id", (request, response) -> new JDBCDELETE().delete(Integer.parseInt(request.params(":id"))));
        //Синтаксис для изменения информации пользователя в БД
        // http://localhost/users/{id} - где id - это любой id пользователя взятый из БД
        put("/users/:id", (request, response) -> new JDBCPUT().update(request, Integer.parseInt(request.params(":id"))));

    }
}
