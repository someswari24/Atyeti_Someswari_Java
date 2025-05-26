package fileEncryptDecrypt;

import java.io.FileNotFoundException;

public class FileValidation {
    public static boolean validate(String file1,String file2) throws FileNotFoundException {
        return FileHandling.readFile(file1).equals(FileHandling.readFile(file2));
    }
}
