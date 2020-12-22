package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao{
    Connection connection = getConnection();
    Statement statement;
    PreparedStatement preparedStatement;
    public UserDaoJDBCImpl() {}

    public void createUsersTable(){
        try {
            preparedStatement = connection.prepareStatement("CREATE TABLE user(id int NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR(45) NOT NULL," +
                    "age int NOT NULL," +
                    "PRIMARY KEY (id))");
            preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException syntaxErrorException) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable(){
        try {
            preparedStatement = connection.prepareStatement("drop table user");
            preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException syntaxErrorException) {
            System.out.println("База данных уже удалена");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("drop user table successfully");
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("User с именем – %s добавлен в базу данных\n",name);
    }

    public void removeUserById(long id){
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE Id ="+id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("remove user in table successfully");

    }

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id,name,lastName,age FROM user");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));;

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable(){
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM user");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("clean user table successfully");
    }
}
