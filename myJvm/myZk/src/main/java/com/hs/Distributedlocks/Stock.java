package com.hs.Distributedlocks;

public class Stock {

    private static Integer COUNT = 1;

    public boolean reduceStock(){
        if(COUNT > 0){
            COUNT --;
            return true;
        }
        return false;
    }
}
