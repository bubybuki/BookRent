/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projecttemplate;

import java.sql.*;

/**
 *
 * @author Lenovo
 */
public class LoginListener {
    
    //create 
    private String loggedInUsername;  // เพิ่มตัวแปรนี้

    public boolean authenticateUser(String username, String password) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM accountList WHERE username = ? AND Password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);  

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // ถ้ามีผู้ใช้ที่ตรงกับ username และ password
                        loggedInUsername = username;  // กำหนดค่า loggedInUsername
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
        }
        return false;
    }
    
}
