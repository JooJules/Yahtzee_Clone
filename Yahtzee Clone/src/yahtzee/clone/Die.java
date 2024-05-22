package yahtzee.clone;

import java.util.Random;

public class Die {
    
    private int id; //id will be arraylist index
    private int value;
    private boolean toRoll;
    private static Random roll = new Random();
    
    //Make all dice created default at value 1 and toRoll true
    public Die(int id) {
        this.id = id;
        value = 1;
        toRoll = true;
    }
    
    public void rollDie() {
        //values are [1,6]
        value = roll.nextInt(6) + 1;
    }
    
    public void setToRoll(boolean toRoll) {
        this.toRoll = toRoll;
    }
    
    public boolean getToRoll() {
        return toRoll;
    }
    
    public int getValue() {
        return value;
    }
    
}
