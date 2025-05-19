package ControlStatements;

import java.util.Scanner;

public class MagicNumber {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter a number:");
        int number=scanner.nextInt();
        while(number>9){
            int sum=0;
            while (number>0){
                int rem=number%10;
                sum+=rem;
                number/=10;
            }
            number=sum;
        }
        if(number==1)
        System.out.println("magic number");
        else System.out.println("Not a magic number");
    }
}
