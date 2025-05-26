package FileReaderWriter;
/*
Read and Display File Content

Write a program that reads a text file and prints its content line by line.
 */
import java.io.*;

public class DisplayContent {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath="src/FileReaderWriter/sample_input.txt";
        String outputFilePath="src/FileReaderWriter/sample_output.txt";

        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            while ((line=bufferedReader.readLine())!=null){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            System.out.println("File copied Successfully...");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
