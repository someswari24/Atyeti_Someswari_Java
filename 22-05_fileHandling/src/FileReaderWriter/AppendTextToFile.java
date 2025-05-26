package FileReaderWriter;

import java.io.*;
import java.util.Scanner;
/*
Append Text to File
Develop a script that appends user input to an existing file.
 */
public class AppendTextToFile {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath="src/FileReaderWriter/sample_input.txt";
        Scanner scanner=new Scanner(System.in);
        try{
            FileWriter fileWriter=new FileWriter(filePath,true);

            System.out.print("Enter text to append: ");
            String input=scanner.nextLine();

            fileWriter.write(input);
            System.out.println("Text appended Successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
