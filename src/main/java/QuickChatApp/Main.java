package QuickChatApp;

import java.util.Scanner;
import java.util.List;


public class Main {
    
    private static LogIn loginSystem = new LogIn();
    private static MessageService messageService;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isLoggedIn = false;
    
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     Welcome to Chat App Registration");
        System.out.println("==================================================");
        
        boolean registered = false;
        System.out.println("\n--- REGISTRATION ---");
        
        while (!registered) {
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter username (must contain _ and be ≤5 chars): ");
            String username = scanner.nextLine();
            System.out.print("Enter password (min 8 chars, 1 capital, 1 number, 1 special): ");
            String password = scanner.nextLine();
            System.out.print("Enter cell phone number (+27XXXXXXXXX): ");
            String cellNumber = scanner.nextLine();
            
            String result = loginSystem.registerUser(username, password, cellNumber, firstName, lastName);
            System.out.println("\n" + result);
            
            if (result.equals("User registered successfully!")) {
                registered = true;
                System.out.println("Registration complete!\n");
            } else {
                System.out.println("Please try again.\n");
            }
        }
        
        System.out.println("--- LOGIN ---");
        int attempts = 0;
        
        while (!isLoggedIn && attempts < 3) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            String status = loginSystem.returnLoginStatus(username, password);
            System.out.println(status);
            
            if (loginSystem.loginUser(username, password)) {
                isLoggedIn = true;
                System.out.println("\nYou are now logged into Chat App!");
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("Attempts remaining: " + (3 - attempts) + "\n");
                }
            }
        }
        
        if (!isLoggedIn) {
            System.out.println("\nToo many failed attempts. Please try again later.");
            scanner.close();
            return;
        }
        
        messageService = new MessageService(isLoggedIn);
        
        System.out.println("\n==================================================");
        System.out.println("Welcome to QuickChat");
        System.out.println("==================================================");
        
        boolean quit = false;
        
        while (!quit) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Show recently sent messages");
            System.out.println("Option 3) Stored Messages");
            System.out.println("Option 4) Quit");
            System.out.print("Choose an option: ");
            
            int menuChoice = scanner.nextInt();
            scanner.nextLine();
            
            switch (menuChoice) {
                case 1:
                    sendMessagesFlow();
                    break;
                case 2:
                    String sentMessages = messageService.printMessages();
                    if (sentMessages.equals("No messages to display.")) {
                        System.out.println("No messages have been sent yet.");
                    } else {
                        System.out.println(sentMessages);
                    }
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case 3:
                    storedMessagesMenu();
                    break;
                case 4:
                    quit = true;
                    System.out.println("\nQuitting application...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
            }
        }
        
        System.out.println("\n==================================================");
        System.out.println("FINAL SUMMARY");
        System.out.println("==================================================");
        System.out.println(messageService.printMessages());
        System.out.println("Total number of messages sent: " + messageService.returnTotalMessages());
        
        scanner.close();
    }
    
    private static void sendMessagesFlow() {
        System.out.print("\nHow many messages do you wish to enter? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();
        
        for (int currentMessage = 1; currentMessage <= numMessages; currentMessage++) {
            System.out.println("\n----------------------------------------");
            System.out.println("Message " + currentMessage + " of " + numMessages);
            System.out.println("----------------------------------------");
            
            System.out.print("Enter recipient cell number (+27XXXXXXXXX): ");
            String recipient = scanner.nextLine();
            
            System.out.print("Enter your message (max 250 characters): ");
            String messageText = scanner.nextLine();
            
            messageService.createAndProcessMessage(currentMessage, recipient, messageText);
        }
    }
    
    private static void storedMessagesMenu() {
        MessageStorage storage = messageService.getMessageStorage();
        
        boolean back = false;
        while (!back) {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("a) Display sender and recipient of all stored messages");
            System.out.println("b) Display the longest stored message");
            System.out.println("c) Search for a message by ID");
            System.out.println("d) Search for all messages by recipient");
            System.out.println("e) Delete a message by hash");
            System.out.println("f) Display full report");
            System.out.println("g) Back to main menu");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice.toLowerCase()) {
                case "a":
                    storage.displayStoredMessagesSenderRecipient();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case "b":
                    System.out.println("\nLongest stored message: " + storage.findLongestStoredMessage());
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Message ID to search: ");
                    String searchId = scanner.nextLine();
                    System.out.println("\n" + storage.searchByMessageID(searchId));
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case "d":
                    System.out.print("Enter recipient number to search: ");
                    String searchRecipient = scanner.nextLine();
                    List<String> results = storage.searchByRecipient(searchRecipient);
                    System.out.println("\nMessages found:");
                    if (results.isEmpty()) {
                        System.out.println("No messages found for this recipient.");
                    } else {
                        for (String msg : results) {
                            System.out.println("- " + msg);
                        }
                    }
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case "e":
                    System.out.print("Enter Message Hash to delete: ");
                    String hashToDelete = scanner.nextLine();
                    if (storage.deleteMessageByHash(hashToDelete)) {
                        System.out.println("Message successfully deleted.");
                    } else {
                        System.out.println("Message hash not found.");
                    }
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case "f":
                    storage.displayFullReport();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case "g":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a-g.");
            }
        }
    }
}