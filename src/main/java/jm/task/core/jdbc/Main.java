package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Alex","Лев", (byte) 25);
        userDaoJDBC.saveUser("Max","Mad", (byte) 54);
        userDaoJDBC.saveUser("Иван","Грозный", (byte) 30);
        userDaoJDBC.saveUser("Георгий","Дронов", (byte) 24);
        userDaoJDBC.getAllUsers().forEach(System.out::println);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
