package operators;

import java.util.Scanner;

public class BinaryRepresentation {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter the number:");
        int number= scanner.nextInt();

        //converting the number to binary reprsentation
       int binary= Integer.parseInt(Integer.toBinaryString(number));

       System.out.println("Binary Representation of number: "+number+ " is: "+binary);

       //to get the number of 1's in our number
       int oneBitCount=Integer.bitCount(number);
        System.out.println("Number of 1's: "+oneBitCount);

        //to get the number of 1's in our number
        int totalBits=Integer.toBinaryString(number).length();
        int zeroBitCount=totalBits-oneBitCount;
        System.out.println("Zero bit count: "+zeroBitCount);


    }
}
