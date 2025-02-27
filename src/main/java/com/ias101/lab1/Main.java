package com.ias101.lab1;

import com.ias101.lab1.security.Authenticator;
import com.ias101.lab1.utils.Crud;

import java.sql.SQLException;
import java.util.Scanner;

/* LABORATORY 1: SQL INJECTION
 *
 * This is the main entry point for the demo system.
 * Instructions:
 * 1. Perform an SQL injection attack on the project after running it.
 * 2. Once successful, identify the vulnerabilities in the project.
 * 3. Fix the errors and vulnerabilities.
 * 4. Prepare a report on what you did during the activity and the result of your SQL injection.
 */

/**
 * This class provides a command-line interface for a simple user management system. <br/>
 * It allows users to: <br/>
 * <span style="padding-left: 10px;">- Authenticate with username and password</span><br/>
 * <span style="padding-left: 10px;">- View all user data</span><br/>
 * <span style="padding-left: 10px;">- Search for specific users by username</span> <br/>
 * <span style="padding-left: 10px;">- Delete users by username</span><br/>
 *
 * @author Echaluce, Tomas Paolo A.
 * @version 1.0
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String MENU = """
            1. Get all user data
            2. Search for user by username
            3. Delete user by username""";

    /**
     * Main method that runs the demo system interface
     *
     * @param args Command line arguments (not used)
     * @throws SQLException If there is an error accessing the database
     */
    public static void main(String[] args) throws SQLException {
        displayHeader();

        String[] credentials = getCredentials();

        if (authenticateUser(credentials[0], credentials[1])) {
            displayWelcome(credentials[0]);
            handleUserSelection();
        } else {
            displayAuthenticationError();
        }
    }

    /**
     * Displays the header of the demo system interface
     */
    private static void displayHeader() {
        System.out.println("------------------DEMO SYSTEM------------------");
        System.out.println("Please enter your credentials");
    }

    /**
     * Gets the username and password credentials from user input
     * @return String array containing username at index 0 and password at index 1
     */
    private static String[] getCredentials() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        return new String[]{username, password};
    }

    /**
     * Authenticates a user with given credentials
     * @param username The username to authenticate
     * @param password The password to authenticate
     * @return true if authentication successful, false otherwise
     */
    private static boolean authenticateUser(String username, String password) {
        return Authenticator.authenticateUser(username, password);
    }

    /**
     * Displays welcome message and menu options for authenticated user
     * @param username The username of the authenticated user
     */
    private static void displayWelcome(String username) {
        System.out.printf("Welcome %s%n", username);
        System.out.println("What would you like to do?");
        System.out.println(MENU);
    }

    /**
     * Handles the user's menu selection and routes to appropriate handler method
     */
    private static void handleUserSelection() {
        String selection = scanner.next();
        switch (selection) {
            case "1" -> handleListUsers();
            case "2" -> handleSearchUser();
            case "3" -> handleDeleteUser();
            default -> System.out.println("Invalid selection");
        }
    }

    /**
     * Handles the list users functionality by displaying all users in the system
     */
    private static void handleListUsers() {
        System.out.println("-----------------USER LIST-----------------");
        Crud.getAll().forEach(System.out::println);
    }

    /**
     * Handles the search user functionality by taking username input and displaying matching user
     */
    private static void handleSearchUser() {
        System.out.println("-----------------SEARCH USER-----------------");
        System.out.println("Search Term: ");
        String username = scanner.next();
        System.out.println(Crud.searchByUsername(username));
    }

    /**
     * Handles the delete user functionality by taking username input and removing that user
     */
    private static void handleDeleteUser() {
        System.out.println("-----------------DELETE USER-----------------");
        System.out.println("Enter the username of the account you want to delete: ");
        String username = scanner.next();
        Crud.deleteUserByUsername(username);
        System.out.printf("User with the username '%s' has been removed.%n", username);
        Crud.getAll();
    }

    /**
     * Displays authentication error message when login fails
     */
    private static void displayAuthenticationError() {
        System.err.println("Bad credentials. Shutting down.");
    }
}