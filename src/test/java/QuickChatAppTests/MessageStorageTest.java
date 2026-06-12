package QuickChatAppTests;

import QuickChatApp.MessageStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class MessageStorageTest {
    
    private MessageStorage messageStorage;
    
    @BeforeEach
    public void setUp() {
        messageStorage = new MessageStorage();
        messageStorage.populateTestData();
    }
    
    @Test
    public void testSentMessagesArrayCorrectlyPopulated_assertEquals() {
        List<String> sentMessages = messageStorage.getSentMessagesText();
        assertTrue(sentMessages.contains("Did you get the cake?"));
        assertTrue(sentMessages.contains("It is dinner time !"));
        assertEquals(2, sentMessages.size());
    }
    
    @Test
    public void testDisplayLongestMessage_assertEquals() {
        String longest = messageStorage.findLongestStoredMessage();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }
    
    @Test
    public void testSearchForMessageID_assertEquals() {
        List<QuickChatApp.Message> storedMessages = messageStorage.getStoredMessages();
        if (!storedMessages.isEmpty()) {
            String messageIdToSearch = storedMessages.get(0).getMessageID();
            String result = messageStorage.searchByMessageID(messageIdToSearch);
            assertTrue(result.contains("Recipient:"));
            assertTrue(result.contains("Message:"));
        }
    }
    
    @Test
    public void testSearchAllMessagesForRecipient_assertEquals() {
        String recipientToSearch = "+27838884567";
        List<String> results = messageStorage.searchByRecipient(recipientToSearch);
        // Should find exactly 2 messages for this recipient
        assertEquals(2, results.size());
        assertTrue(results.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(results.contains("Ok, I am leaving without you."));
    }
    
    @Test
    public void testDeleteMessageUsingMessageHash_assertEquals() {
        List<QuickChatApp.Message> storedMessages = messageStorage.getStoredMessages();
        // Store the hash of the first stored message before deletion
        String hashToDelete = storedMessages.get(0).getMessageHash();
        int originalSize = storedMessages.size();
        
        boolean deleted = messageStorage.deleteMessageByHash(hashToDelete);
        
        assertTrue(deleted);
        assertEquals(originalSize - 1, messageStorage.getStoredMessages().size());
    }
    
    @Test
    public void testSentMessagesArrayContainsTestData() {
        List<String> sentMessages = messageStorage.getSentMessagesText();
        assertTrue(sentMessages.contains("Did you get the cake?"));
        assertTrue(sentMessages.contains("It is dinner time !"));
    }
    
    @Test
    public void testStoredMessagesArraySize() {
        // Should have exactly 2 stored messages (msg2 and msg5)
        assertEquals(2, messageStorage.getStoredMessages().size());
    }
    
    @Test
    public void testMessageHashesArray() {
        assertFalse(messageStorage.getMessageHashes().isEmpty());
        assertEquals(2, messageStorage.getMessageHashes().size());
    }
    
    @Test
    public void testMessageIDsArray() {
        assertFalse(messageStorage.getMessageIDs().isEmpty());
        assertEquals(2, messageStorage.getMessageIDs().size());
    }
}