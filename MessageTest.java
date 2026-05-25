/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.quickchat.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Molokomme
 */

public class MessageTest {

    @Test
    public void testMessageID() {
        Message msg = new Message(1, "+27831234567", "Hello world");
        // check that ID is valid and 10 digits
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testRecipientValid() {
        Message msg = new Message(1, "+27831234567", "Test");
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientInvalid() {
        Message msg = new Message(1, "0831234567", "Test");
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted.", result);
    }

    @Test
    public void testMessageLengthOK() {
        Message msg = new Message(1, "+27831234567", "Short message");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageTooLong() {
        String longMsg = "a".repeat(251);
        Message msg = new Message(1, "+27831234567", longMsg);
        String result = msg.checkMessageLength();
        assertTrue(result.contains("exceeds 250"));
    }

    @Test
    public void testHashFormat() {
        Message msg = new Message(5, "+27831234567", "Hello world");
        String hash = msg.createMessageHash();
        assertTrue(hash.contains(":5:HELLOWORLD"));
    }

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
        assertEquals(7, msg.returnTotalMessages());
    }
}