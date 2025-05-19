package ControlStatements.LoginSystem;

import java.util.Scanner;

public class LoginMain {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        UserDetails userDetails=new UserDetails("Someswari","somii");
        int attempts=3;

        while (attempts>0){
            System.out.print("Enter Username:");
            String username=scanner.next();
            System.out.print("Enter password:");
            String pwd=scanner.next();

            if(userDetails.authentication(username,pwd)){
                System.out.println("Login Successfull");
                break;
            }
            else{
                attempts--;
                System.out.println("Invalid Credentials");
                System.out.println("Attempts Left="+attempts);
                if(attempts==0) System.out.println("Login failed");
            }
        }
    }
}
