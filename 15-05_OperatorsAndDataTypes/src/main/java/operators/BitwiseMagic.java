package operators;

import java.util.Scanner;

/*Bitwise Magic
Swap the two numbers using bitwise XOR (^) without using a temporary variable.*/
public class BitwiseMagic {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter the first number: ");
        int number1= scanner.nextInt();
        System.out.println("Enter the second number: ");
        int number2= scanner.nextInt();
        //double number2=scanner.nextDouble();

        System.out.println("Before swapping the values of number1="+number1+"and number2="+number2);

        number1=number1^number2;
        number2=number1^number2;
        number1=number2^number1;

        System.out.println("After swapping the values of number1="+number1+"and number2="+number2);

    }
}
