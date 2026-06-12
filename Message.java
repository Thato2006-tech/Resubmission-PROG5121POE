/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainclass;

/**
 *
 * @author Molokomme
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Message {
    private static int totalMessages = 0;

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    // Only ONE constructor
    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        createMessageID();
        createMessageHash();
        totalMessages = messageNumber; // for testTotalMessages
    }

    public void createMessageID() {
        Random random = new Random();
        long number = 1000000L + (long)(random.nextDouble() * 9000000L);
        messageID = String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted.";
        }
    }

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extra = message.length() - 250;
            return "Message exceeds 250 characters by " + extra;
        }
    }

    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");
        if (words.length == 0 || words[0].isEmpty()) {
            messageHash = messageID.substring(0, 2) + ":" + messageNumber + "EMPTY";
            return messageHash;
        }
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        messageHash = messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
        return messageHash;
    }

    // Method needed for JUnit
    public String sentMessage(int option) {
        if (option == 1) {
            return "Message successfully sent.";
        } else if (option == 2) {
            return "Press 0 to delete message.";
        } else if (option == 3) {
            storeMessage();
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // Method needed for JUnit
    public static int returnTotalMessages() {
        return totalMessages;
    }

    public void storeMessage() {
        JSONObject messageObj = new JSONObject();
        messageObj.put("messageID", messageID);
        messageObj.put("messageNumber", messageNumber);
        messageObj.put("recipient", recipient);
        messageObj.put("message", message);
        messageObj.put("messageHash", messageHash);

        JSONArray messageList = new JSONArray();

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("stored_messages.json")) {
            Object obj = parser.parse(reader);
            messageList = (JSONArray) obj;
        } catch (Exception e) {}

        messageList.add(messageObj);

        try (FileWriter file = new FileWriter("stored_messages.json")) {
            file.write(messageList.toJSONString());
            file.flush();
        } catch (IOException e) {}
    }

    public String printDetails() {
        return "\nMessage ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + message;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
}