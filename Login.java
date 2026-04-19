/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Oratile_Dell
 */
import java.util.Scanner;

class Login {

    private String savedUsername = "";
    private String savedPassword = "";
    private String savedPhone = "";

    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password validation 
    public boolean checkPassword(String password) {

        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Cell phone validation 
    public boolean checkPhone(String phone) {

        if (!phone.startsWith("+27")) return false;
        if (phone.length() != 12) return false;

        for (int i = 3; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // Registration
    public void register(Scanner sc) {

        System.out.println("=== REGISTER ===");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (checkUserName(username)) {
            System.out.println("Username successfully captured.");
            savedUsername = username;
        } else {
            System.out.println("Username is not correctly arranged.");
            return; // stop if invalid
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (checkPassword(password)) {
            System.out.println("Password captured.");
            savedPassword = password;
        } else {
            System.out.println("Password is not correctly written.");
            return;
        }

        System.out.print("Enter cell phone (+27...): ");
        String phone = sc.nextLine();

        if (checkPhone(phone)) {
            System.out.println("Cell phone number added.");
            savedPhone = phone;
        } else {
            System.out.println("Cell phone number incorrectly arranged.");
            return;
        }

        System.out.println("Registration successful");
    }

    // Login 
    public void login(Scanner sc) {

        System.out.println("=== LOGIN ===");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (username.equals(savedUsername) && password.equals(savedPassword)) {
            System.out.println("Welcome back! Login successful.");
        } else {
            System.out.println("Username or password is incorrect , try again");
        }
    }
}