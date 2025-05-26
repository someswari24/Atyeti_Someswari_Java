package FileReaderWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
Count Words and Lines

Create a program that counts the number of lines, words, and characters in a given text file.
 */
public class WordsCount {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath="src/FileReaderWriter/sample_input.txt";
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath))){
            String line;
            int wordCount=0;
            int lineCount=0;
            while((line=bufferedReader.readLine())!=null){
                String[] s = line.split(" ");
                wordCount+= s.length;
                lineCount++;
            }
            System.out.println("Number of words in the file : "+wordCount);
            System.out.println("Number of lines in the file : "+lineCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
