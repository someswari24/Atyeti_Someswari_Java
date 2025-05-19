package ControlStatements.patterns;

import java.util.Scanner;

public class DiamondPattern {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows=scanner.nextInt();

        for(int i=1;i<=rows;i++){
            for(int j=1;j<=rows-i;j++){
                System.out.print(" ");
            }
            for(int j=1;j<=i;j++){
                System.out.print(j);
            }
            for(int j=i-1;j>=1;j--){
                System.out.print(j);
            }
            System.out.println();
        }
        for(int i=rows-1;i>=1;i--) {
            for(int j=1;j<=rows-i;j++) {
                System.out.print(" ");
            }
            for(int j=1;j<=i;j++) {
                System.out.print(j);
            }
            for (int j=i-1;j>=1;j--) {
                System.out.print(j);
            }
            System.out.println();
        }

    }
}
