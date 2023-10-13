package Views;

import Controllers.Controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Views {
    private JFrame frame; // The main application window
    private JTextField inputField; // Input text field for queries
    private JTextArea outputArea; // Output text area for displaying results
    private JButton sqlButton; // Button to execute SQL queries
    private JButton mqlButton; // Button to execute MQL queries
    private JButton switchButton; // Button to switch display format
    private JButton toggleFormatButton; // Button to toggle display format

    // Store query-related information
    private String sqlQuery;
    private String mqlQuery;
    private String result;
    private long executionTime;

    // Flags for controlling display and format
    private boolean isDisplayingResults = true;
    private boolean isJSONFormat = false;

    private Controllers controller; // Reference to the controller

    public Views(Controllers controller) {
        this.controller = controller; // Set the controller for communication

        // Create the main application window
        frame = new JFrame("SQL and MQL Processor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        // Create input field for queries
        inputField = new JTextField(20);

        // Create buttons for executing SQL and MQL queries
        sqlButton = new JButton("Execute SQL");
        mqlButton = new JButton("Execute MQL");

        // Create output text area for displaying results
        outputArea = new JTextArea(5, 20);

        // Create buttons for switching display format and toggling format
        switchButton = new JButton("Switch Display Format");
        toggleFormatButton = new JButton("Toggle Format");
        // toggleFormatButton.setVisible(false);

        // Create the main panel with a border layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a subpanel for input components (input field and query execution buttons)
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sqlButton, BorderLayout.WEST);
        inputPanel.add(mqlButton, BorderLayout.EAST);

        // Add the input subpanel to the main panel at the top (north)
        panel.add(inputPanel, BorderLayout.NORTH);

        // Add a scrollable output area to the main panel in the center
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Create a subpanel for buttons at the bottom (south)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(switchButton);
        buttonPanel.add(toggleFormatButton);

        // Add the button subpanel to the main panel at the bottom (south)
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the application frame
        frame.add(panel);

        // Register action listeners for SQL and MQL query execution buttons
        sqlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlQuery = inputField.getText();
                controller.SQLQuery(sqlQuery);
            }
        });

        mqlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mqlQuery = inputField.getText();
                controller.MQLQuery(mqlQuery);
            }
        });

        // Register action listeners for switching and toggling display format buttons
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle between displaying results and format
                isDisplayingResults = !isDisplayingResults;

                if (isDisplayingResults) {
                    displayResult(inputField.getText(), result, executionTime);
                } else {
                    updateDisplayFormat(isJSONFormat, inputField.getText(), result, executionTime);
                }
            }
        });

        toggleFormatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.toggleDisplayFormat();
            }
        });
    }

    // Method to display query results in the output area
    public void displayResult(String query, String result, long executionTime) {
        outputArea.setText("Query: " + query + "\nResult: " + result + "\nQuery Execution Time: " + executionTime + " ms");
    }

    // Update the display format and use the result from the controller
    public void updateDisplayFormat(boolean isJSONFormat, String query, String result, long executionTime) {
        String displayText = !isJSONFormat
                ? "{\"status\": \"false\"}"
                : "{\"status\": \"" + isJSONFormat + "\", \"query\": \"" + query + "\", \"result\": \"" + result + "\", \"execution_time\": \"" + executionTime + "\"}";

        outputArea.setText(displayText);
    }

    // Method to make the main application window visible
    public void show() {
        frame.setVisible(true);
    }
}
