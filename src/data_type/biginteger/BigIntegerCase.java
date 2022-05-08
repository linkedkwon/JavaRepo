package data_type.biginteger;

import java.math.BigInteger;

public class BigIntegerCase {

    static void initInstance(){
        BigInteger A = new BigInteger("9876543210123456789"),
                   B = new BigInteger("12345", 8),
                   C = BigInteger.TEN;
    }

    static void operation(){
        BigInteger A = new BigInteger("12345678987654321"),
                   B = new BigInteger("98765432123456789");

        BigInteger add = A.add(B),
                   subtract = A.subtract(B),
                   multi = A.multiply(B),
                   divide = A.divide(B);
    }

    static void casting(){
        BigInteger A = new BigInteger("12345");
        byte[] arr = A.toByteArray();
        String str = A.toString();
        int aByInt = A.intValue();
        long aByLong = A.longValue();
        //..
    }

    static void arithmeticInLoop(){
        BigInteger sum = BigInteger.ZERO;
        for(int i = 1; i < 100; i++)
            sum = sum.add(BigInteger.valueOf(i));
    }

    static void compareTo(){
        BigInteger A = new BigInteger("12345678987654321"),
                   B = new BigInteger("12345678987654321");

        if(A.compareTo(B) == 0)
            System.out.println("A == B");
        else if(A.compareTo(B) == -1)
            System.out.println("A < B");
        else if(A.compareTo(B) == 1)
            System.out.println("A > B");
    }

}