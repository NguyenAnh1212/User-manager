package com.codegym.dao;

import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao{

    public static final String URLJDBC = "jdbc:mysql://localhost:3306/demo";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "123456";

    // lấy kết nối với CSDL
    protected Connection getConnection(){
        Connection connection = null ;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    URLJDBC,
                    JDBC_USERNAME,
                    JDBC_PASSWORD);
            System.out.println("Ket noi thanh cong");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ket noi chua thanh cong");
        }
        return connection;

    }
    @Override
    public void insertUser(User user) throws SQLException {

    }

    @Override
    public User selectUser(int id) {
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
       List<User> users = new ArrayList<>();
       try (
               // Lấy kết nối đến CSDL
               Connection connection = getConnection();
               // Chuẩn bị câu lệnh truy vấn
               PreparedStatement preparedStatement = connection.prepareStatement(
                       "select * from users"
               )
               ) {
           // Lưu dữ liệu, thực thi câu lệnh vào resultSet
           ResultSet resultSet = preparedStatement.executeQuery();
           // Lấy dữ liệu ra
           while (resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               String email = resultSet.getString("email");
               String country = resultSet.getString("country");
               User user = new User(id, name, email, country);
               users.add(user);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }
}
