package ControlStatements.patterns;

import java.util.Scanner;

public class PascalsTriangle {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter number of rows:");
        int rows=scanner.nextInt();
        for(int i=0;i<rows;i++) {
            int num=1;
            for(int j=0; j<rows-i ;j++) {
                System.out.print(" ");
            }
            for(int k=0;k<=i;k++) {
                System.out.print(num+" ");
                num = num * (i-k)/(k+1);
            }
            System.out.println();

        }
    }
}
