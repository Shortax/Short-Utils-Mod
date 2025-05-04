package com.shortax.short_utils.helpers;

public class mcMath {

    public static double sigmoid(double value)
    {
        return ( 2.0 / ( 1.0 + Math.pow(Math.E , -value) ) ) -1.0;
    }
}
