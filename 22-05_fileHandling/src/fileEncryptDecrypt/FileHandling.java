package fileEncryptDecrypt;

import java.io.*;

public class FileHandling {
    public static String readFile(String filename) throws FileNotFoundException {
        StringBuilder data=new StringBuilder();
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(filename))) {
            int ch;
            while ((ch=bufferedReader.read())!=-1){
                data.append((char)ch);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public static void writeFile(String filename,String data) throws FileNotFoundException {
        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
