package ControlStatements.MathQuizGame;

import java.util.Random;
import java.util.Scanner;

public class MathQuiz {
    private static Scanner scanner=new Scanner(System.in);
    private static Random random=new Random();

    public static void main(String[] args) {
        while (true){
            System.out.println("######Math Quiz Game######");
            System.out.println("Main Menu Options:");
            System.out.println("1.View Instructions");
            System.out.println("2.Start Quiz");
            System.out.println("3.Quit");

            System.out.print("Enter your choice: ");
            int choice=scanner.nextInt();
            switch (choice) {
                case 1:
                    printInstructions();
                    break;
                case 2:
                    startQuiz();
                    break;
                case 3:
                    System.out.println("Quit");
                    return;
                default:
                    System.out.println("select the option 1, 2, 3:");
            }
        }
    }
    //instructions
    private static void printInstructions(){
        System.out.println("***Instructions***");
        System.out.println("1.Choose the difficulty level");
        System.out.println("2.Answer the question");
        System.out.println("3.Enter Quit if you dont want to continue");
        System.out.println("4.Invalid inputs are not counted");
        System.out.println("5.Marks are generated at the end of the quiz");
    }

    //start quiz
    private static void startQuiz(){
        while (true){
            System.out.println("1.Easy level(only addition and substraction)");
            System.out.println("2.Medium level(addition,substraction and multiplication)");
            System.out.println("3.Hard level(addition,substraction, multiplication and division)");
            System.out.print("Enter the choice of difficulty:");
            int difficultyChoice=scanner.nextInt();

            playQuiz(difficultyChoice);

            System.out.println("Play again(yes/no)");
            String replay=scanner.next();
            if(!replay.equalsIgnoreCase("yes")) break;

        }
    }

    private static void playQuiz(int difficulty){
        int questions=5;
        int correct=0;
        int incorrect=0;
        System.out.println("Enter 'Quit' to exit the quiz");
        for(int i=0;i<questions;i++){
            Problem problem=generateProblem(difficulty);
            System.out.printf("Q%d: %s= ? ", i,problem.getQuestion());

            String answer=scanner.next();
            if(answer.equalsIgnoreCase("quit")){
                System.out.println("Exiting quiz in the middle");
                return;
            }

            int userAnswer;
            try{
                userAnswer=Integer.parseInt(answer);
                if(userAnswer==problem.getAnswer()){
                    System.out.println("Correct Answer.");
                    correct++;
                }
                else {
                    System.out.println("Incorrect Answer.");
                    System.out.println("Correct Answer is "+problem.getAnswer());
                    incorrect++;
                }
            }
            catch (NumberFormatException exception){
                exception.printStackTrace();
            }
        }
        System.out.println("Your Score Correct Answers="+correct+" Incorrect Answers: "+incorrect);
    }

    private static Problem generateProblem(int difficulty) {
        int a=0;
        int b=0;
        int answer=0;String operation="";
        switch (difficulty){
            case 1:
                a=random.nextInt(100);
                b=random.nextInt(100);
                if(random.nextBoolean()){
                    operation="+";
                    answer=a+b;
                }
                else{
                    operation="-";
                    answer=a-b;
                }
                break;
            case 2:
                a=random.nextInt(100);
                b=random.nextInt(100);
                int choice=random.nextInt(3);
                if(choice==0){
                    operation="+";
                    answer=a+b;
                }
                else if(choice==1){
                    operation="-";
                    answer=a-b;
                }
                else{
                    operation="*";
                    answer=a*b;
                }
                break;
            case 3:
                a=random.nextInt(100);
                b=random.nextInt(100);
                int option=random.nextInt(4);
                if(option==0){
                    operation="+";
                    answer=a+b;
                }
                else if(option==1){
                    operation="-";
                    answer=a-b;
                }
                else if(option==2){
                    operation="*";
                    answer=a*b;
                }
                else{
                    operation="/";
                    answer=a/b;
                }
                break;
        }
        String question= a+" "+operation+" "+b;
        return new Problem(question,answer);
    }


}
