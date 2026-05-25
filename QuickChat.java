/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author Molokomme
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuickChat {

    static Scanner input = new Scanner(System.in);

    static ArrayList<String> sentMessages = new ArrayList<>();
    static ArrayList<String> storedMessages = new ArrayList<>();

    static int totalMessagesSent = 0;

    public static void main(String[] args) {
        
        System.out.println("===== LOGIN =====");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        if (username.equals("admin") && password.equals("1234")) {

            System.out.println("Login successful!");
            System.out.println("Welcome to QuickChat");

            int choice = 0;

            while (choice != 3) {

                System.out.println("\n===== MENU =====");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");

                System.out.print("Choose option: ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {

                    case 1:
                        sendMessages();
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }

        } else {
            System.out.println("Invalid login details.");
        }
    }

    // Send messages
    public static void sendMessages() {

        System.out.print("How many messages do you want to send? ");
        int numMessages = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numMessages; i++) {

            System.out.println("\n===== MESSAGE " + (i + 1) + " =====");

            // Message ID
            String messageID = generateMessageID();

            if (checkMessageID(messageID)) {
                System.out.println("Message ID generated: " + messageID);
            }

            // Recipient
            System.out.print("Enter recipient number: ");
            String recipient = input.nextLine();

            String recipientResult = checkRecipientCell(recipient);
            System.out.println(recipientResult);

            if (!recipientResult.equals("Cell phone number successfully captured.")) {
                continue;
            }

            // Message
            System.out.print("Enter message: ");
            String message = input.nextLine();

            if (message.length() > 250) {

                int extra = message.length() - 250;

                System.out.println("Message exceeds 250 characters by "+ extra+ ", please reduce the size.");
                continue;

            } else {
                System.out.println("Message ready to send.");
            }

            // Number of sent messages
            totalMessagesSent++;

            // HASH
            String hash = createMessageHash(messageID, totalMessagesSent, message);

            System.out.println("Message Hash: " + hash);

            String result = SentMessage(message);

            System.out.println(result);

            if (result.equals("Message successfully sent.")) {

                System.out.println("\n===== MESSAGE DETAILS =====");
                System.out.println("Message ID: " + messageID);
                System.out.println("Message Hash: " + hash);
                System.out.println("Recipient: " + recipient);
                System.out.println("Message: " + message);
            }
        }

        System.out.println("\nTotal messages sent: " + returnTotalMessages());
    }

    // Check message ID
    public static boolean checkMessageID(String messageID) {

        return messageID.length() <= 10;
    }

    // Check recipient cell
    public static String checkRecipientCell(String number) {

        if (number.startsWith("+27") && number.length() <= 12) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrect. Please correct the number and try again.";
        }
    }

    // Create message hash
    public static String createMessageHash(String messageID,int messageNumber,String message) {

        String[] words = message.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        String firstTwo = messageID.substring(0, 2);

        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    
    public static String SentMessage(String message) {

        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Delete Message");
        System.out.println("3. Store Message");

        int option = input.nextInt();
        input.nextLine();

        switch (option) {

            case 1:

                sentMessages.add(message);

                return "Message successfully sent.";

            case 2:

                return "Press 0 to delete the message.";

            case 3:

                storeMessage(message);

                return "Message successfully stored.";

            default:

                return "Invalid option.";
        }
    }

    // Print messages
    public static String printMessages() {

        return sentMessages.toString();
    }

    // Total messages
    public static int returnTotalMessages() {

        return totalMessagesSent;
    }

    // Store messages
    public static void storeMessage(String message) {

        storedMessages.add(message);
    }

    // Generate random 10 digit ID
    public static String generateMessageID() {

        Random random = new Random();

        long number = 1000000000L+ (long) (random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }
}