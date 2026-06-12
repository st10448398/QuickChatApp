package QuickChatApp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class MessageStorage {
    private List<Message> sentMessages;
    private List<Message> disregardedMessages;
    private List<Message> storedMessages;
    private List<String> messageHashes;
    private List<String> messageIDs;
    
    public MessageStorage() {
        this.sentMessages = new ArrayList<>();
        this.disregardedMessages = new ArrayList<>();
        this.storedMessages = new ArrayList<>();
        this.messageHashes = new ArrayList<>();
        this.messageIDs = new ArrayList<>();
    }
    
    public void addMessage(Message message) {
        if (message.getStatus().equals("sent")) {
            sentMessages.add(message);
        } else if (message.getStatus().equals("disregarded")) {
            disregardedMessages.add(message);
        } else if (message.getStatus().equals("stored")) {
            storedMessages.add(message);
            messageHashes.add(message.getMessageHash());
            messageIDs.add(message.getMessageID());
        }
    }
    
    public void loadStoredMessagesFromJSON() {
        try {
            File file = new File("messages.json");
            if (file.exists() && file.length() > 0) {
                String content = new String(Files.readAllBytes(file.toPath()));
                if (!content.trim().isEmpty()) {
                    JSONArray messagesArray = new JSONArray(content);
                    for (int i = 0; i < messagesArray.length(); i++) {
                        JSONObject obj = messagesArray.getJSONObject(i);
                        Message msg = new Message(
                            obj.getString("messageID"),
                            obj.getInt("messageNumber"),
                            obj.getString("recipient"),
                            obj.getString("messageText"),
                            obj.getString("messageHash"),
                            obj.getString("status")
                        );
                        if (msg.getStatus().equals("stored")) {
                            storedMessages.add(msg);
                            messageHashes.add(msg.getMessageHash());
                            messageIDs.add(msg.getMessageID());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading stored messages: " + e.getMessage());
        }
    }
    
    public void displayStoredMessagesSenderRecipient() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
            return;
        }
        System.out.println("\n--- STORED MESSAGES - SENDER & RECIPIENT ---");
        for (Message msg : storedMessages) {
            System.out.println("Sender: Current User | Recipient: " + msg.getRecipient());
        }
    }
    
    public String findLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        Message longest = storedMessages.get(0);
        for (Message msg : storedMessages) {
            if (msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }
        return longest.getMessageText();
    }
    
    public String searchByMessageID(String messageID) {
        for (Message msg : storedMessages) {
            if (msg.getMessageID().equals(messageID)) {
                return "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessageText();
            }
        }
        return "Message ID not found.";
    }
    
    public List<String> searchByRecipient(String recipient) {
        List<String> results = new ArrayList<>();
        for (Message msg : storedMessages) {
            if (msg.getRecipient().equals(recipient)) {
                results.add(msg.getMessageText());
            }
        }
        return results;
    }
    
    public boolean deleteMessageByHash(String messageHash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(messageHash)) {
                storedMessages.remove(i);
                for (int j = 0; j < messageHashes.size(); j++) {
                    if (messageHashes.get(j).equals(messageHash)) {
                        messageHashes.remove(j);
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public void displayFullReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLETE STORED MESSAGES REPORT");
        System.out.println("=".repeat(60));
        
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages to display.");
            return;
        }
        
        for (Message msg : storedMessages) {
            System.out.println("\n--- MESSAGE ---");
            System.out.println("Message Hash: " + msg.getMessageHash());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessageText());
            System.out.println("Message ID: " + msg.getMessageID());
            System.out.println("Status: " + msg.getStatus());
        }
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Total stored messages: " + storedMessages.size());
        System.out.println("=".repeat(60));
    }
    
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<String> getMessageIDs() { return messageIDs; }
    
    public void populateTestData() {
        // Clear existing lists first
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        
        // Create test messages
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg1.setMessageHash("12:1:DIDYOUCAKE?");
        msg1.setStatus("sent");
        
        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.setMessageHash("34:2:WHEREON TIME.");
        msg2.setStatus("stored");
        
        Message msg3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.");
        msg3.setMessageHash("56:3:YOHOOGATE.");
        msg3.setStatus("disregarded");
        
        Message msg4 = new Message(4, "0838884567", "It is dinner time !");
        msg4.setMessageHash("78:4:ITIS TIME !");
        msg4.setStatus("sent");
        
        Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
        msg5.setMessageHash("90:5:OKYOU.");
        msg5.setStatus("stored");
        
        // Add messages using the addMessage method
        addMessage(msg1);
        addMessage(msg2);
        addMessage(msg3);
        addMessage(msg4);
        addMessage(msg5);
    }
    
    public List<String> getSentMessagesText() {
        List<String> texts = new ArrayList<>();
        for (Message msg : sentMessages) {
            texts.add(msg.getMessageText());
        }
        return texts;
    }
}