package org.example.game.Entity;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private final int min=1;
    private final int max=6;

    ThreadLocal

    private final int  noOfDices;

    public Dice(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    public int rollDice()
    {
        int count=noOfDices,totalSum=0;
        while(count>0)
        {
            totalSum+= ThreadLocalRandom.current().nextDouble(min,max+1);
            count--;
        }

        return totalSum;
    }
}
