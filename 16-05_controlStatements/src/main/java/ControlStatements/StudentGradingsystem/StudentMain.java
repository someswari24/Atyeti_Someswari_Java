package ControlStatements.StudentGradingsystem;

import java.util.Scanner;

public class StudentMain {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Student Id:");
        int id= scanner.nextInt();
        System.out.print("Enter Student name:");
        String name=scanner.next();
        System.out.print("Enter student marks");
        int[] marks=new int[5];
        for (int i=0;i<5;i++){
           marks[i]=scanner.nextInt();
        }
        Student student1=new Student(id,name,marks);
        student1.grade();

        scanner.close();
    }
}
