package kz.kcollege.jdbc;

import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class JDCBDisplay {
    //hashMap's для <ключ>:<значение>
    private HashMap<String, HashMap<String, Object>> hashmapMain;
    private HashMap<String, Object> hashmap;

    //Конструктор
    public JDCBDisplay() {
        hashmapMain = new HashMap<>();
    }

    //Метод для конвертирования в json
    public String start(boolean bool, ResultSet resultSet) {
        try {
            //Вывести полное описание пользователя или только id, имя и фамилию?
            if (!bool) {
                //Проверка существования пользователя в БД
                while (resultSet.next()) {
                    //Инициализация
                    hashmap = new HashMap<>();
                    //<id>:<значение>
                    hashmap.put("id", Integer.parseInt(resultSet.getString("id")));
                    //<first_name>:<значение>
                    hashmap.put("first_name", resultSet.getString("first_name"));
                    //<last_name>:<значение>
                    hashmap.put("last_name", resultSet.getString("last_name"));
                    //Помещаем в общий HashMap
                    hashmapMain.put("user" + resultSet.getString("id"), hashmap);
                }
                //Возвращаем ответ
                return new Gson().toJson(hashmapMain);
            } else {
                //Проверка существования пользователя в БД
                while (resultSet.next()) {
                    //Инициализация
                    hashmap = new HashMap<>();
                    //<id>:<значение>
                    hashmap.put("id", Integer.parseInt(resultSet.getString("id")));
                    //<first_name>:<значение>
                    hashmap.put("first_name", resultSet.getString("first_name"));
                    //<last_name>:<значение>
                    hashmap.put("last_name", resultSet.getString("last_name"));
                    //"Age" не пусто?
                    if (resultSet.getString("age") != null)
                        //<age>:<значение>
                        hashmap.put("age", Integer.parseInt(resultSet.getString("age")));
                    //<data>:<значение>
                    hashmap.put("data", resultSet.getString("data"));
                    //Помещаем в общий HashMap
                    hashmapMain.put("user" + resultSet.getString("id"), hashmap);
                }
                //Возвращаем ответ
                return new Gson().toJson(hashmapMain);
            }
        } catch (SQLException e) {
            //Возвращаем ответ
            return "Error display";
        }
    }

}
