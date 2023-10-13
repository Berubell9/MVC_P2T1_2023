package Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Models {
   
    /* ----------------------------- SQL Query Processing ----------------------------- */

    // Process an SQL query and return the result as a string
    public static String processSQL(String sqlQuery) {
        try {
            // Check if the SQL query starts with "SELECT"
            if (sqlQuery.startsWith("SELECT")) {
                sqlQuery = sqlQuery.trim();

                // Check if the SQL query matches the expected formats
                if (sqlQuery.matches("SELECT\\s+\\d+;") || sqlQuery.matches("SELECT\\s+\\d+\\s*\\+\\s*\\d+;")) {
                    // Parse and calculate the result
                    int result = parseSQL(sqlQuery);
                    return Integer.toString(result);
                } else {
                    return "Wrong Syntax!";
                }
            } else {
                return "Wrong Syntax!";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Process an SQL query with execution time measurement
    public static String processSQLWithTime(String sqlQuery) {
        try {
            long startTime = System.currentTimeMillis();

            // Process the SQL query
            String result = processSQL(sqlQuery);

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            return result + ";" + executionTime;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Parse and calculate the result of an SQL query
    private static int parseSQL(String sqlQuery) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(sqlQuery);

        int result = 0;
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            result += number;
        }

        return result;
    }

    /* ----------------------------- MQL Query Processing ----------------------------- */

    // Process an MQL query and return the result as a string
    public static String processMQL(String mqlQuery) {
        try {
            // Check if the MQL query starts with "print"
            if (mqlQuery.startsWith("print")) {
                mqlQuery = mqlQuery.substring("print".length()).trim();

                // Check if the MQL query matches the expected formats
                if (mqlQuery.matches("\\d+;") || mqlQuery.matches("\\d+\\s*\\+\\s*\\d+;")) {
                    // Parse and calculate the result
                    int result = parseMQL(mqlQuery);
                    return Integer.toString(result);
                } else {
                    return "Wrong Syntax!";
                }
            } else {
                return "Wrong Syntax!";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Process an MQL query with execution time measurement
    public static String processMQLWithTime(String mqlQuery) {
        try {
            long startTime = System.currentTimeMillis();

            // Process the MQL query
            String result = processMQL(mqlQuery);

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            return result + ";" + executionTime;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Parse and calculate the result of an MQL query
    private static int parseMQL(String mqlQuery) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(mqlQuery);

        int result = 0;
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            result += number;
        }

        return result;
    }
}
