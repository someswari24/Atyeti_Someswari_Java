package operators;

/*Ternary Puzzle
Create a program that determines the largest of three numbers using the ternary operator (? :). Additionally,
use nested ternary operators to check if the largest number is even or odd.*/

import java.util.Scanner;

public class TernaryPuzzle {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter number1: ");
        int number1=scanner.nextInt();
        System.out.print("Enter number1: ");
        int number2=scanner.nextInt();
        System.out.print("Enter number1: ");
        int number3=scanner.nextInt();

        int largest=(number1>number2)&&(number1>number3)?number1:(number2>number1)&&(number2>number3)?number2:number3;

        System.out.println("Largest number :"+largest);

        String number=(largest%2==0)?"largest is even":"largest is odd";
        System.out.println(number);
    }
}
