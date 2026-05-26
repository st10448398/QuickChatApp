package QuickChatApp;

import java.util.Scanner;

public class Main {
    
    private static LogIn loginSystem = new LogIn();
    private static MessageService messageService;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isLoggedIn = false;
    
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("     Welcome to Chat App Registration");
        System.out.println("=".repeat(50));
        
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
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Welcome to QuickChat");
        System.out.println("=".repeat(50));
        
        System.out.print("\nHow many messages do you wish to enter? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();
        
        for (int currentMessage = 1; currentMessage <= numMessages; currentMessage++) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("Message " + currentMessage + " of " + numMessages);
            System.out.println("-".repeat(40));
            
            System.out.print("Enter recipient cell number (+27XXXXXXXXX): ");
            String recipient = scanner.nextLine();
            
            System.out.print("Enter your message (max 250 characters): ");
            String messageText = scanner.nextLine();
            
            messageService.createAndProcessMessage(currentMessage, recipient, messageText);
            
            if (currentMessage < numMessages) {
                System.out.println("\n--- MENU ---");
                System.out.println("Option 1) Send Messages");
                System.out.println("Option 2) Show recently sent messages");
                System.out.println("Option 3) Quit");
                System.out.print("Choose an option: ");
                
                int menuChoice = scanner.nextInt();
                scanner.nextLine();
                
                if (menuChoice == 3) {
                    System.out.println("\nQuitting application...");
                    break;
                } else if (menuChoice == 2) {
                    System.out.println("\nComing Soon.");
                }
            }
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("FINAL SUMMARY");
        System.out.println("=".repeat(50));
        System.out.println(messageService.printMessages());
        System.out.println("Total number of messages sent: " + messageService.returnTotalMessages());
        
        scanner.close();
    }
}