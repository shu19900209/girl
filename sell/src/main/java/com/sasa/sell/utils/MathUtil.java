package com.sasa.sell.utils;



public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两个金额是否相等
     * @param db1
     * @param db2
     * @return
     */
    public static Boolean equals(Double db1, Double db2){
        Double result = Math.abs(db1 - db2);
        if (result < MONEY_RANGE){
            return true;
        }else{
            return false;
        }



    }
}
