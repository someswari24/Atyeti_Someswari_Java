package ControlStatements.patterns;

import java.util.Scanner;

public class PyramidPattern {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows=scanner.nextInt();
        for(int i=0;i<=5;i++){
            for(int j=0;j<=5-i;j++){
                System.out.print(" ");
            }
            for (int k=0;k<=2*i-1;k++)
            System.out.print("*");
            System.out.println();
        }

    }
}
