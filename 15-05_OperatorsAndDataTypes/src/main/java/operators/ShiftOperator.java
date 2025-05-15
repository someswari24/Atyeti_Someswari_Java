package operators;

import java.util.Scanner;

/*Takes an integer input and performs left shift (<<) and right shift (>>) operations.
 */
public class ShiftOperator {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int number= scanner.nextInt();

        System.out.print("Enter number of positions to shift: ");
        int shift= scanner.nextInt();

        int leftShift=number<<shift;
        int rightShift=number>>shift;

        System.out.println("left shift of the number: "+leftShift);
        System.out.println("right shift of the number: "+rightShift);

    }
}
