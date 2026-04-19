/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Oratile_Dell
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Login user = new Login();

        int choice;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine(); // FIX for Scanner (important!)

            switch (choice) {
                case 1:
                    user.register(sc);
                    break;

                case 2:
                    user.login(sc);
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid");
            }

        } while (choice != 3);

        sc.close();
    }
}
