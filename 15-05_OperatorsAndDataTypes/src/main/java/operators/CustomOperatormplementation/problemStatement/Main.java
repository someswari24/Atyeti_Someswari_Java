package operators.CustomOperatormplementation.problemStatement;

public class Main {
    public static void main(String[] args) {
        ComplexNumber complexNumber1=new ComplexNumber(3.4,8.0);
        ComplexNumber complexNumber2=new ComplexNumber(5,6.9);

        ComplexNumber sum=complexNumber1.add(complexNumber2);
        ComplexNumber multiply=complexNumber1.multiply(complexNumber2);

        System.out.println("complex number1: "+complexNumber1);
        System.out.println("complex number2: "+complexNumber2);
        System.out.println("sum of two complex numbers: "+sum);
        System.out.println("multiplication of two complex numbers: "+multiply);
    }
}
