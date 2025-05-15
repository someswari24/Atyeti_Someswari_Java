package dataTypes;

import java.math.BigInteger;
//converting big integer to primitive values
//Write a Java program to extract the primitive type value from a given BigInteger value.
public class PrimitiveValue {
    public static void main(String[] args) {
        BigInteger bigInteger=BigInteger.valueOf(Long.MAX_VALUE);
        System.out.println("BigInteger value:"+bigInteger);
        long longValue=bigInteger.longValue();
        System.out.println("Big Integer to Long value:"+longValue);
        int intValue=bigInteger.intValue();
        System.out.println("Big Integer to int value:"+intValue);
        short shortValue=bigInteger.shortValue();
        System.out.println("Big Integer to short value:"+shortValue);
        byte byteValue=bigInteger.byteValue();
        System.out.println("Big Integer to byte value:"+byteValue);
    }
}
