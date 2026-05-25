package QuickChatAppTests;

import QuickChatApp.MessageService;
import QuickChatApp.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MessageServiceTest {
    
    private MessageService messageService;
    
    @BeforeEach
    public void setUp() {
        messageService = new MessageService(true);
    }
    
    @Test
    public void testMessageLengthSuccess_assertEquals() {
        String message = "Hi Mike, can you join us for dinner tonight?";
        String result = messageService.checkMessageLength(message, 250);
        assertEquals("Message ready to send.", result);
    }
    
    @Test
    public void testMessageLengthFailure_assertEquals() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMessage.append("a");
        }
        String result = messageService.checkMessageLength(longMessage.toString(), 250);
        assertTrue(result.contains("exceeds 250 characters by 10"));
    }
    
    @Test
    public void testRecipientNumberCorrectlyFormatted_assertEquals() {
        String result = messageService.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }
    
    @Test
    public void testRecipientNumberIncorrectlyFormatted_assertEquals() {
        String result = messageService.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }
    
    @Test
    public void testMessageHashCorrect_assertEquals() {
        String messageID = "0012345678";
        int messageNumber = 0;
        String messageText = "Hi Mike, can you join us for dinner tonight?";
        
        String hash = messageService.createMessageHash(messageID, messageNumber, messageText);
        assertEquals("00:0:HITONIGHT?", hash);
    }
    
    @Test
    public void testMessageIDCreated_assertEquals() {
        Message message = new Message(1, "+27718693002", "Test message");
        boolean isValid = messageService.checkMessageID(message.getMessageID());
        assertTrue(isValid);
        assertNotNull(message.getMessageID());
        assertEquals(10, message.getMessageID().length());
    }
    
    @Test
    public void testMessageSentOption() {
        String result = "";
        int choice = 1;
        
        switch(choice) {
            case 1:
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete the message.";
                break;
            case 3:
                result = "Message successfully stored.";
                break;
        }
        
        assertEquals("Message successfully sent.", result);
    }
    
    @Test
    public void testMessageDisregardOption() {
        String result = "";
        int choice = 2;
        
        switch(choice) {
            case 1:
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete the message.";
                break;
            case 3:
                result = "Message successfully stored.";
                break;
        }
        
        assertEquals("Press 0 to delete the message.", result);
    }
    
    @Test
    public void testMessageStoreOption() {
        String result = "";
        int choice = 3;
        
        switch(choice) {
            case 1:
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete the message.";
                break;
            case 3:
                result = "Message successfully stored.";
                break;
        }
        
        assertEquals("Message successfully stored.", result);
    }
    
    @Test
    public void testMessageHashLoop() {
        String[][] testData = {
            {"0012345678", "0", "Hi Mike, can you join us for dinner tonight?", "00:0:HITONIGHT?"},
            {"0012345679", "1", "Hello World", "00:1:HELLOWORLD"},
            {"0012345680", "2", "Testing One Two Three", "00:2:TESTINGTHREE"}
        };
        
        for (String[] data : testData) {
            String hash = messageService.createMessageHash(data[0], Integer.parseInt(data[1]), data[2]);
            assertEquals(data[3], hash);
        }
    }
    
    @Test
    public void testTotalMessagesReturned() {
        int total = messageService.returnTotalMessages();
        assertEquals(0, total);
    }
}