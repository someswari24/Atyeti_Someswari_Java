package ControlStatements.StudentGradingsystem;

public class Student {
    private int id;
    private String name;
    private int[] marks;

    public Student(int id, String name, int[] marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public double averageMarks(){
        int sum=0;
        for(int i=0;i<5;i++)sum+=marks[i];
        return (double) sum/5;
    }

    public void grade(){
        double average=averageMarks();
        System.out.println("The average marks of the student:"+average);
        if(average>90 && average<100) System.out.println("Grade:A");
        else if(average>80 && average<89) System.out.println("Grade:B");
        else if(average>70 && average<79) System.out.println("Grade:C");
        else if(average>60 && average<69) System.out.println("Grade:D");
        else System.out.println("Fail");
    }
}
