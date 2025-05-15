package operators.CustomOperatormplementation.problemStatement;

public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber num){
        double newReal=this.real+num.real;
        double newImaginary=this.imaginary+num.imaginary;
        return new ComplexNumber(newReal,newImaginary);
    }

    //if A = a + bi and B = c + di
    //A * B = (a + bi)(c + di)
    //      = ac + adi + bci + bdi²
    //i² = -1 (by definition of imaginary unit)
    //A * B = ac + adi + bci - bd
    //      = (ac - bd) + (ad + bc)i
    public ComplexNumber multiply(ComplexNumber num){
        double newReal=this.real*num.real-this.imaginary*num.imaginary;
        double newImaginary=this.real*num.imaginary+num.real* num.imaginary;
        return new ComplexNumber(newReal,newImaginary);
    }

    @Override
    public String toString(){
        if(imaginary>=0){
            return  real+"+"+imaginary+"i";
        }
        else{
            return  real+"-"+imaginary+"i";
        }
    }
}
