package QuickChatAppTests;

import QuickChatApp.LogIn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LogInTest {
    
    private LogIn login;
    
    @BeforeEach
    public void setUp() {
        login = new LogIn();
    }
    
    @Test
    public void testUsernameCorrectlyFormatted_assertEquals() {
        String username = "kyl_1";
        boolean result = login.checkUserName(username);
        assertTrue(result);
        String regResult = login.registerUser(username, "Ch&sec@ke99!", "+27838968976", "John", "Doe");
        assertEquals("User registered successfully!", regResult);
    }
    
    @Test
    public void testUsernameIncorrectlyFormatted_assertEquals() {
        String username = "kyle!!!!!!!";
        boolean result = login.checkUserName(username);
        assertFalse(result);
        String regResult = login.registerUser(username, "Ch&sec@ke99!", "+27838968976", "John", "Doe");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", regResult);
    }
    
    @Test
    public void testPasswordMeetsComplexity_assertEquals() {
        String password = "Ch&sec@ke99!";
        boolean result = login.checkPasswordComplexity(password);
        assertTrue(result);
        String regResult = login.registerUser("kyl_1", password, "+27838968976", "John", "Doe");
        assertEquals("User registered successfully!", regResult);
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexity_assertEquals() {
        String password = "password";
        boolean result = login.checkPasswordComplexity(password);
        assertFalse(result);
        String regResult = login.registerUser("kyl_1", password, "+27838968976", "John", "Doe");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", regResult);
    }
    
    @Test
    public void testCellPhoneCorrectlyFormatted_assertEquals() {
        String cellNumber = "+27838968976";
        boolean result = login.checkCellPhoneNumber(cellNumber);
        assertTrue(result);
        String regResult = login.registerUser("kyl_1", "Ch&sec@ke99!", cellNumber, "John", "Doe");
        assertEquals("User registered successfully!", regResult);
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormatted_assertEquals() {
        String cellNumber = "08966553";
        boolean result = login.checkCellPhoneNumber(cellNumber);
        assertFalse(result);
        String regResult = login.registerUser("kyl_1", "Ch&sec@ke99!", cellNumber, "John", "Doe");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", regResult);
    }
    
    @Test
    public void testLoginSuccessful_assertTrue() {
        String regResult = login.registerUser("jo_1", "Test@1234", "+27731234567", "John", "Smith");
        assertEquals("User registered successfully!", regResult);
        boolean result = login.loginUser("jo_1", "Test@1234");
        assertTrue(result);
    }
    
    @Test
    public void testLoginFailed_assertFalse() {
        login.registerUser("ja_1", "Test@1234", "+27731234567", "Jane", "Doe");
        boolean result = login.loginUser("ja_1", "wrongpassword");
        assertFalse(result);
    }
    
    @Test
    public void testUsernameCorrectlyFormatted_assertTrue() {
        boolean result = login.checkUserName("kyl_1");
        assertTrue(result);
    }
    
    @Test
    public void testUsernameIncorrectlyFormatted_assertFalse() {
        boolean result = login.checkUserName("testuser");
        assertFalse(result);
    }
    
    @Test
    public void testPasswordMeetsComplexity_assertTrue() {
        boolean result = login.checkPasswordComplexity("StrongP@ss123");
        assertTrue(result);
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexity_assertFalse() {
        boolean result = login.checkPasswordComplexity("weakpass");
        assertFalse(result);
    }
    
    @Test
    public void testCellPhoneNumberCorrectlyFormatted_assertTrue() {
        boolean result = login.checkCellPhoneNumber("+27731234567");
        assertTrue(result);
    }
    
    @Test
    public void testCellPhoneNumberIncorrectlyFormatted_assertFalse() {
        boolean result = login.checkCellPhoneNumber("0831234567");
        assertFalse(result);
    }
}