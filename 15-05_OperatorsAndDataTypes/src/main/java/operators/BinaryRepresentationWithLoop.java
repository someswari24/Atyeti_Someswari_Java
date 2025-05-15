package operators;

/*Count the number of 1s in the binary representation of the number
using bitwise AND (&) and right shift (>>).
 */

import java.util.Scanner;

public class BinaryRepresentationWithLoop {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter the number:");
        int number= scanner.nextInt();

        int count=0;
        while(number!=0){
            if((number & 1)==1){
                count++;
            }
             number=number>>1;
        }
        System.out.println("1 bit count="+count);
    }
}
