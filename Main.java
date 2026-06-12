/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainclass;

/**
 *
 * @author Molokomme
 */
import java.util.Scanner;

public class Main {
    public static final int MAX = 100;

    public static String[] sentMessages = new String[MAX];
    public static String[] storedMessages = new String[MAX];
    public static String[] messageHashes = new String[MAX];
    public static String[] messageIDs = new String[MAX];
    public static String[] recipients = new String[MAX];

    public static int sentCount = 0;
    public static int storedCount = 0;

    private static String regUsername;
    private static String regPassword;
    private static boolean registered = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    register(sc);
                    break;
                case 2:
                    if (login(sc)) {
                        quickChatMenu(sc);
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid");
            }
        } while (choice!= 3);

        sc.close();
    }

    // Part 1: Register + Login
    private static void register(Scanner sc) {
        System.out.print("Enter new username: ");
        regUsername = sc.nextLine();
        System.out.print("Enter new password: ");
        regPassword = sc.nextLine();
        registered = true;
        System.out.println("User registered successfully!");
    }

    private static boolean login(Scanner sc) {
        if (!registered) {
            System.out.println("No user registered yet. Please register first.");
            return false;
        }
        System.out.print("Enter username: ");
        String u = sc.nextLine();
        System.out.print("Enter password: ");
        String p = sc.nextLine();

        if (u.equals(regUsername) && p.equals(regPassword)) {
            System.out.println("Login successful! Welcome " + regUsername);
            return true;
        } else {
            System.out.println("Username or password incorrect.");
            return false;
        }
    }

    // Part 3: QuickChat menu
    private static void quickChatMenu(Scanner sc) {
        System.out.println("Welcome to QuickChat");
        boolean running = true;
        while (running) {
            System.out.println("\n===== QUICKCHAT MENU =====");
            System.out.println("1. Send Message");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Stored Messages Menu");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");

            int choice = readMenuChoice(sc);

            switch (choice) {
                case 1:
                    sendMessage(sc);
                    break;
                case 2:
                    showReport();
                    break;
                case 3:
                    storedMessagesMenu(sc);
                    break;
                case 4:
                    running = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }
    }

    private static int readMenuChoice(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void sendMessage(Scanner sc) {
        System.out.print("How many messages do you want to send? ");
        int num = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < num; i++) {
            System.out.println("\n--- Message " + (i + 1) + " ---");
            System.out.print("Enter recipient number (+27...): ");
            String recip = sc.nextLine().trim();

            System.out.print("Enter message: ");
            String msg = sc.nextLine();

            Message m = new Message(sentCount + storedCount + 1, recip, msg);

            System.out.println(m.checkRecipientCell());
            System.out.println(m.checkMessageLength());

            if (!m.checkRecipientCell().equals("Cell phone number successfully captured.")) continue;
            if (!m.checkMessageLength().equals("Message ready to send.")) continue;

            System.out.print("Send / Store / Disregard? ");
            String flag = sc.nextLine().trim();

            if (flag.equalsIgnoreCase("Send")) {
                sentMessages[sentCount] = msg;
                recipients[sentCount] = recip;
                messageIDs[sentCount] = m.getMessageID();
                messageHashes[sentCount] = m.getMessageHash();
                sentCount++;
                System.out.println("Message sent!");
                System.out.println(m.printDetails());

            } else if (flag.equalsIgnoreCase("Store")) {
                storedMessages[storedCount] = msg;
                recipients[storedCount] = recip;
                messageIDs[storedCount] = m.getMessageID();
                messageHashes[storedCount] = m.getMessageHash();
                storedCount++;
                m.storeMessage();

            } else {
                System.out.println("Message disregarded.");
            }
        }
    }

    private static void showReport() {
        if (sentCount == 0) {
            System.out.println("No sent messages yet.");
            return;
        }
        System.out.println("\n--- Recently Sent Messages ---");
        for (int i = 0; i < sentCount; i++) {
            System.out.println((i + 1) + ". To: " + recipients[i] + " | " + sentMessages[i]);
        }
    }

    private static void storedMessagesMenu(Scanner sc) {
        System.out.println("\n--- Stored Messages Menu ---");
        System.out.println("a. Show all recipients");
        System.out.println("b. Show longest message");
        System.out.print("Choose: ");
        String choice = sc.nextLine().trim().toLowerCase();

        if (choice.equals("a")) {
            if (storedCount == 0) {
                System.out.println("No stored messages.");
                return;
            }
            for (int i = 0; i < storedCount; i++) {
                System.out.println((i + 1) + ". " + recipients[i]);
            }
        } else if (choice.equals("b")) {
            if (storedCount == 0) {
                System.out.println("No stored messages.");
                return;
            }
            String longest = storedMessages[0];
            for (int i = 1; i < storedCount; i++) {
                if (storedMessages[i].length() > longest.length()) {
                    longest = storedMessages[i];
                }
            }
            System.out.println("Longest: " + longest);
        } else {
            System.out.println("Invalid option.");
        }
    }
}