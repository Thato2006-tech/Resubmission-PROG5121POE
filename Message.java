/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author Molokomme
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    public Message(int messageNumber,String recipient,String message) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;

        createMessageID();
        createMessageHash();
    }

    // Generate ID
    public void createMessageID() {

        Random random = new Random();

        long number =1000000000L+ (long)(random.nextDouble()* 9000000000L);

        messageID = String.valueOf(number);
    }

    // Check ID
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    // Check recipient
    public String checkRecipientCell() {

        if (recipient.startsWith("+27")&& recipient.length() <= 12) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted.";
        }
    }

    // Check message length
    public String checkMessageLength() {

        if (message.length() <= 250) {

            return "Message ready to send.";

        } else {

            int extra = message.length() - 250;

            return "Message exceeds 250 characters by "+ extra;
        }
    }

    // Create hash
    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        messageHash = messageID.substring(0, 2)+ ":" + messageNumber+ ":" + firstWord.toUpperCase()+ lastWord.toUpperCase();

        return messageHash;
    }

    // Send message
    public String sentMessage(int option) {

        if (option == 1) {

            return "Message successfully sent.";

        } else if (option == 2) {

            return "Press 0 to delete message.";

        } else if (option == 3) {

            return "Message successfully stored.";

        } else {

            return "Invalid option.";
        }
    }
    public void storeMessage() {
    JSONObject messageObj = new JSONObject();
    messageObj.put("messageID", messageID);
    messageObj.put("messageNumber", messageNumber);
    messageObj.put("recipient", recipient);
    messageObj.put("message", message);
    messageObj.put("messageHash", messageHash);

    JSONArray messageList = new JSONArray();
    messageList.add(messageObj);

    try (FileWriter file = new FileWriter("stored_messages.json", true)) {
        file.write(messageList.toJSONString());
        file.write("\n");
        file.flush();
        System.out.println("Message successfully stored in JSON file.");
    } catch (IOException e) {
        System.out.println("Error storing message: " + e.getMessage());
    }
}

    // Print details
    public String printMessages() {

        return "\nMessage ID: " + messageID+ "\nMessage Hash: " + messageHash+ "\nRecipient: " + recipient+ "\nMessage: " + message;
    }

    // Total messages
    public int returnTotalMessages() {

        return messageNumber;
    }

    public String getMessageHash() {

        return messageHash;
    }
}