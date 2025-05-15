package dataTypes;
//You are given a double value. Convert it to float, int, and long, and analyze the precision loss at each step.
public class Precision {
    public static void main(String[] args) {
        double value=12345678.1245678;

        int intValue= (int) value;
        System.out.println("Converted into int:"+intValue);

        float floatValue= (float) value;
        System.out.println("converted to float:"+floatValue);

        long longValue= (long) value;
        System.out.println("Converted to long:"+longValue);
    }
}
