package robustStudentDataProcessor.main;

import robustStudentDataProcessor.errorlogger.ErrorLogger;
import robustStudentDataProcessor.exception.IndexException;
import robustStudentDataProcessor.exception.InvalidStudentDataException;
import robustStudentDataProcessor.exception.ValueException;
import robustStudentDataProcessor.model.Student;
import robustStudentDataProcessor.processor.StudentProcessor;

import java.io.*;
import java.util.Scanner;

public class StudentDataMain {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int retries = 3;
        File file = null;

        while (retries > 0) {
            System.out.print("Enter the file name:");
            String fileName = scanner.nextLine();
            file = new File(fileName);
            if (file.exists()) {
                break;
            } else {
                System.out.println("file not found error");
                file = null;
            }
            retries--;
        }
        if (file == null) {
            System.out.println("Maximum retries exceeded");
        }
        readFile(file);
    }

    public static void readFile(File fileName) throws IOException {
        int lineNumber = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                try {
                    Student student = StudentProcessor.studentData(line, lineNumber);
                    System.out.printf("Student ID: %s, Name: %s, Average Score: %.2f%n",
                            student.getStudentId(), student.getName(), student.calculateAverage());
                } catch (InvalidStudentDataException | ValueException | IndexException e) {
                    System.out.println("Error in line " + lineNumber + " : " + e.getMessage());
                    ErrorLogger.writeErrorLog(lineNumber, e);
                } catch (Exception e) {
                    System.out.println("Unexpected error in line " + lineNumber);
                    ErrorLogger.writeErrorLog(lineNumber, e);
                }
            }
        }
    }
}
