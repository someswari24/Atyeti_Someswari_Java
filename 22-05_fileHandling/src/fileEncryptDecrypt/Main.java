package fileEncryptDecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {

        while(true){
            System.out.println("File Encryption Decryption");
            System.out.println("1.Encryption");
            System.out.println("2.Decryption");
            System.out.println("3.Exit");

            System.out.print("Enter your choice: ");
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    fileEncryption();
                    break;
                case 2:
                    fileDecryption();
                    break;
                case 3:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void fileEncryption() throws FileNotFoundException {
        System.out.print("Enter input file name:");
        String input=scanner.nextLine();

        File file=new File(input);

        if (!file.exists()){
            System.out.println("file does not exist");
            return;
        }

        System.out.print("Choose method (caesar/xor): ");
        String method = scanner.nextLine().toLowerCase();

        System.out.print("Enter shift/key: ");
        String value = scanner.nextLine();
        int key=Integer.parseInt(value);


        String text=FileHandling.readFile(input);
        String encryptedText;

        if (method.equals("caesar")){
            encryptedText=CaesarCipher.ceaserEncrypt(text,key);
        } else if (method.equals("xor")) {
            encryptedText=XORCipher.xorEncryptDecrypt(text,key);
        }
        else {
            System.out.println("Invalid method");
            return;
        }
        FileHandling.writeFile("encrypted.txt",encryptedText);
        System.out.println("file encrypted and saved into encrypted.txt");
    }

    private static void fileDecryption() throws FileNotFoundException {
        System.out.print("Enter encrypted file name:");
        String input=scanner.nextLine();

        File file=new File(input);

        if (!file.exists()){
            System.out.println("file does not exist");
            return;
        }

        System.out.print("choose method caesar/xor:");
        String method=scanner.nextLine().toLowerCase();
        System.out.print("Enter shift/key: ");
        String value = scanner.nextLine();
        int key = Integer.parseInt(value);


        String text=FileHandling.readFile(input);
        String decryptedText;

        if (method.equals("caesar")){
            decryptedText=CaesarCipher.ceaserDecrypt(text,key);
        } else if (method.equals("xor")) {
            decryptedText=XORCipher.xorEncryptDecrypt(text,key);
        }
        else {
            System.out.println("Invalid method");
            return;
        }

        FileHandling.writeFile("decrypted.txt", decryptedText);
        System.out.println("file decrypted and saved into decrypted.txt");

    }

}
