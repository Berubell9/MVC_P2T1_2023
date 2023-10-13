package Controllers;

import Models.Models;
import Views.Views;

public class Controllers {
    private Views view;
    private boolean isJSONFormat; // Indicates whether the output format is JSON
    private String sqlQuery; // Stores the SQL query
    private String mqlQuery; // Stores the MQL query
    private String result; // Stores the query result
    private long executionTime; // Stores the query execution time

    public Controllers(Views view) {
        this.view = view;
        isJSONFormat = false; // Set the default output format to non-JSON
    }

    // Getter method for the view field
    public Views getView() {
        return view;
    }

    // Setter method for the view field
    public void setView(Views view) {
        this.view = view;
    }

    // Add a getter method to retrieve the result
    public String getResult() {
        return result;
    }

    // Process SQL query
    public void SQLQuery(String sqlQuery) {
        // Send the SQL query to the Model (Query Engine) for processing
        String resultAndTime = Models.processSQLWithTime(sqlQuery);
        
        // Split the result and execution time
        String[] parts = resultAndTime.split(";");
        result = parts[0];
        executionTime = Long.parseLong(parts[1]);
        
        // Call the view method to display the result
        view.displayResult(sqlQuery, result, executionTime);
    }

    // Process MQL query
    public void MQLQuery(String mqlQuery) {
        // Send the MQL query to the Model (Query Engine) for processing
        String resultAndTime = Models.processMQLWithTime(mqlQuery);
        
        // Split the result and execution time
        String[] parts = resultAndTime.split(";");
        result = parts[0];
        executionTime = Long.parseLong(parts[1]);
        
         // Call the view method to display the result
         view.displayResult(mqlQuery, result, executionTime);
    }

    // Method for toggling the display format
    public void toggleDisplayFormat() {
        isJSONFormat = !isJSONFormat;
    
        if (isJSONFormat) {
            // Update the view to display in JSON format
            view.updateDisplayFormat(true, sqlQuery, result, executionTime); // Pass true for JSON format
        } else {
            // Update the view to display in non-JSON format
            view.updateDisplayFormat(false, mqlQuery, result, executionTime); // Pass false for non-JSON format
        }
    }
}
