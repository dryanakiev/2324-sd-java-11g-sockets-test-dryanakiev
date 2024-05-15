package ChatRoom.Client;

import ChatRoom.Server.Server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    private Server server;
    private Client client;

    @BeforeEach
    void setUp() {
        server = new Server();
        Server.SocketServer clientHandler = new Server.SocketServer();
        Thread clientHandlerThread = new Thread(clientHandler);
        clientHandlerThread.start();
        client = new Client();
    }

    @AfterEach
    void tearDown() {
        client.closeConnection();
        server.stopServer();
    }

    // TODO: Change the greeting message from the server on established connection
    // Change the greeting string of the server and/or the unit test to a personalized greeting.
    @Test
    public void testGreetingMessage() throws IOException {
        // Arrange
        String expectedGreeting = "\"Welcome to our chat room!\"";

        // Act
        client.sendMessage(expectedGreeting);
        String receivedMessage = client.receiveMessage();

        // Assert
        assertEquals(expectedGreeting, receivedMessage);
    }

    // TODO: Implement a server reply to the client
    // Make the server reply to a message sent from the client. Change the message to reflect a different reply.
    @Test
    public void testReplyFromServer() throws IOException {
        // Arrange
        String message = "Hello Server!";
        String expectedResponse = "Hi there! How can I assist you?";

        // Act
        client.sendMessage(message);
        String receivedMessage = client.receiveMessage();

        // Assert
        assertEquals(expectedResponse, receivedMessage);
    }

    // TODO: Implement a method in the server that calculates the factorial of a number sent by the client.
    // If the message contains a number, the server should reply with its factorial.
    @Test
    public void testFactorialCalculation() throws IOException {
        // Arrange
        int number = 5;
        long expectedResponse = 120; // Factorial of 5 is 5 * 4 * 3 * 2 * 1 = 120

        // Act
        client.sendMessage(String.valueOf(number));
        String receivedMessage = client.receiveMessage();

        // Assert
        assertEquals("The factorial of " + number + " is " + expectedResponse + ".", receivedMessage);
    }

    // TODO: Implement a method in the server that checks an incoming message from a client is a palindrome.
    // If the message is a palindrome, the server should reply with "Yes", otherwise "No".
    @Test
    public void testPalindromeCheck() throws IOException {
        // Arrange
        String palindrome = "radar";
        String nonPalindrome = "hello";

        // Act and Assert for palindrome
        client.sendMessage(palindrome);
        String receivedPalindromeMessage = client.receiveMessage();
        assertEquals("Yes", receivedPalindromeMessage);

        // Act and Assert for non-palindrome
        client.sendMessage(nonPalindrome);
        String receivedNonPalindromeMessage = client.receiveMessage();
        assertEquals("No", receivedNonPalindromeMessage);
    }

    // TODO: Implement a method in the server that counts the number of vowels in a string sent by the client.
    // The server should reply with the count of vowels.
    @Test
    public void testCountVowels() throws IOException {
        // Arrange
        String message = "Hello World!";
        int expectedVowelCount = 3; // 'e' appears twice, 'o' appears once

        // Act
        client.sendMessage(message);
        String receivedMessage = client.receiveMessage();

        // Assert
        assertEquals("The number of vowels in the message is " + expectedVowelCount + ".", receivedMessage);
    }
}
