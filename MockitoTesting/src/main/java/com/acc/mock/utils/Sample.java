package com.acc.mock.utils;

public class Sample {
    public static int discount(int amount){
        if(amount>=10000){
            return (int) (amount*0.1);
        }
        else{
            return 0;
        }
    }

    private String privateMethod(String k){
        return k;
    }
}
