# Welcome
Учебное заведение "Карагандинский Коммерческий Колледж".

Программа создана для отчета по производственно-технической практике

с 04.09.2017г. по 26.11.2017г.

# Gradle
> compile 'mysql:mysql-connector-java:5.1.6'

> compile 'com.google.code.gson:gson:2.8.0'

> compile 'com.sparkjava:spark-core:2.6.0'

# REST
> GET
```java
//Синтаксис для подробного просмотра пользователя из БД
// http://localhost/users/{id} - где id - это любой id пользователя взятый из БД
get("/users/:id", (request, response) -> new JDBCGET().print(true, Integer.parseInt(request.params(":id"))));

//Синтаксис для просмотра всех пользователей из БД
// http://localhost/users
get("/users", (request, response) -> new JDBCGET().print(false));
```
> POST
```java
//Синтаксис для добавления новых пользователей
// http://localhost/add ?
// & first = <Имя пользователя>
// & last = <Фамилия пользователя>
// & age = <Возраст пользователя>
// & data = <Дата рождения пользователя>
post("/add", (request, response) -> new JDBCPOST().start(request));
```
> DELETE
```java
//Синтаксис для удаления пользователей в БД
// http://localhost/users/{id} - где id - это любой id пользователя взятый из БД
delete("/users/:id", (request, response) -> new JDBCDELETE().delete(Integer.parseInt(request.params(":id"))));
```
> PUT
```java
//Синтаксис для изменения информации пользователя в БД
// http://localhost/users/{id} ? - где id - это любой id пользователя взятый из БД
// & first = <Имя пользователя>
// & last = <Фамилия пользователя>
// & age = <Возраст пользователя>
// & data = <Дата рождения пользователя>
put("/users/:id", (request, response) -> new JDBCPUT().update(request, Integer.parseInt(request.params(":id"))));
```

# MySQL
```SQL
CREATE SCHEMA `sqlcollegeweber` DEFAULT CHARACTER SET utf8 ;
USE sqlcollegeweber;
CREATE TABLE `sqlcollegeweber`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
CREATE TABLE `sqlcollegeweber`.`info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `age` INT NULL DEFAULT NULL,
  `data` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
```
