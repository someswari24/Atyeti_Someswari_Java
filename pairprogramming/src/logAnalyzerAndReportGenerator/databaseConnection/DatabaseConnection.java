package logAnalyzerAndReportGenerator.databaseConnection;

import logAnalyzerAndReportGenerator.filehandling.LogAnalyzer;

import java.io.FileNotFoundException;
import java.sql.*;

public class DatabaseConnection {
    public static void database() {
        String dataBaseURL="jdbc:mysql://localhost:3306/logfiles";
        String username="root";
        String password="somi";
        Connection connection=null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(dataBaseURL,username,password);
            Statement statement=connection.createStatement();
            String createTableQuery="create table if not exists logs(" + "id int auto_increment primary key, "+
                    "timestamp timestamp,"+ "error_count int,"+"warning_count int,"+
                    "info_count int)";

            statement.executeUpdate(createTableQuery);
            System.out.println("created table successfully");
            String insertQuery = "INSERT INTO logs (timestamp, error_count, warning_count, info_count) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                preparedStatement.setInt(2, LogAnalyzer.errorCount);
                preparedStatement.setInt(3, LogAnalyzer.warningCount);
                preparedStatement.setInt(4, LogAnalyzer.infoCount);

                preparedStatement.executeUpdate();
                System.out.println("Data inserted successfully.");

                System.out.println("Inserted Log Summary: ERROR=" + LogAnalyzer.errorCount +
                        ", WARNING=" + LogAnalyzer.warningCount +
                        ", INFO=" + LogAnalyzer.infoCount);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
