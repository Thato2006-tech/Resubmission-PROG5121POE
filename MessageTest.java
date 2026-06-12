/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mainclass; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testSendOption1() {
        Message msg = new Message(1, "+27831234567", "Test");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testSendOption2() {
        Message msg = new Message(1, "+27831234567", "Test");
        assertEquals("Press 0 to delete message.", msg.sentMessage(2));
    }

    @Test
    public void testSendOption3() {
        Message msg = new Message(1, "+27831234567", "Test");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }

    @Test
    public void testInvalidOption() {
        Message msg = new Message(1, "+27831234567", "Test");
        assertEquals("Invalid option.", msg.sentMessage(9));
    }

    @Test
    public void testTotalMessages() {
        Message msg = new Message(7, "+27831234567", "Test");
        assertEquals(7, Message.returnTotalMessages());
    }
}