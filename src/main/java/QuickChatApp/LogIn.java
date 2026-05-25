package QuickChatApp;

import java.util.regex.Pattern;

public class LogIn {
    
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String firstName;
    private String lastName;
    
    public LogIn() {
    }
    
    public LogIn(String username, String password, String cellNumber, 
                 String firstName, String lastName) {
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
    
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        
        return hasCapital && hasNumber && hasSpecial;
    }
    
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) return false;
        String regex = "^\\+27[0-9]{9}$";
        return Pattern.matches(regex, cellNumber);
    }
    
    public String registerUser(String username, String password, 
                               String cellNumber, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username " +
                   "contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password " +
                   "contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "User registered successfully!";
    }
    
    public boolean loginUser(String username, String password) {
        if (storedUsername == null || storedPassword == null) return false;
        return storedUsername.equals(username) && storedPassword.equals(password);
    }
    
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    public String getStoredUsername() { return storedUsername; }
    public String getStoredPassword() { return storedPassword; }
    public String getStoredCellNumber() { return storedCellNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}