package yahtzee.clone;

import java.util.ArrayList;

public class Score {
    
    private final int arraySize = 5;
    private final int max = 6;
    private ArrayList<Integer> scores = new ArrayList<Integer>(); //arraylist to store scores for end calc
    private boolean topBonus = false; //helps with top section bonus
    private int yahtzeeCount = 0; //tracks extra yahtzees
    
    public Score() {
        //make the arraylist scores have size 13 for all of the scores.
        //-1 means that the user hasn't used that category yet in scoring
        for (int i = 0; i < 13; i++) {
            scores.add(-1);
        }
    }
    
    public int determineScore(ArrayList<Die> dice, int scoreOptionID) {
        //Need a check if the user has scored the selected category already.
        if (scores.get(scoreOptionID) != -1) return -1;
        
        int[] occuranceArray = doCounting(dice);
        int toReturn = 0;
        
        switch (scoreOptionID) {
            case 0: //ones
                toReturn = 1 * occuranceArray[1];
                break;
            case 1: //twos
                toReturn = 2 * occuranceArray[2];
                break;
            case 2: //threes
                toReturn = 3 * occuranceArray[3];
                break;
            case 3: //fours
                toReturn = 4 * occuranceArray[4];
                break;
            case 4: //fives
                toReturn = 5 * occuranceArray[5];
                break;
            case 5: //sixes
                toReturn = 6 * occuranceArray[6];
                break;
            case 6: //three of a kind
                toReturn = threeAKind(occuranceArray);
                break;
            case 7: //four of a kind
                toReturn = fourAKind(occuranceArray);
                break;
            case 8: //full house
                toReturn = fullHouse(occuranceArray);
                break;
            case 9: //small straight
                toReturn = smallStraight(occuranceArray);
                break;
            case 10: //large straight
                toReturn = largeStraight(occuranceArray);
                scores.set(scoreOptionID, toReturn);
                break;
            case 11: //yahtzee
                toReturn = yahtzee(occuranceArray);
                break;
            case 12: //chance
                toReturn = chance(occuranceArray);
                break;
        }
        
        incrementYahtzeeCount(occuranceArray);
        scores.set(scoreOptionID, toReturn);
        
        return toReturn;
    }
    
    //Method to determine if yahtzee count should be incremented for yahtzee bonus
    //Player needs to already have a yahtzee before the variable can increment
    private void incrementYahtzeeCount(int[] occurances) {
        if ((yahtzee(occurances) == 50) && (scores.get(11) == 50)) yahtzeeCount++;
    }
    
    //Method to calculate lower section total
    public int lowerSection() {
        int sum = 0;
        for (int i = 6; i < scores.size(); i++) {
            sum += scores.get(i);
        }
        
        int temp = yahtzeeCount;
        while (temp >= 1) {
            sum += 100;
            temp--;
        }
        
        return sum;
    }
    
    //Method to calculate upper sction total
    public int upperSection() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += scores.get(i);
        }
        
        if (sum >= 63) {
            topBonus = true;
            sum += 35;
        }
        
        return sum;
    }
    
    public boolean getTopBonus() {
        return topBonus;
    }
    
    public int getYahtzeeCount() {
        return yahtzeeCount;
    }
    
    //Method that counts occurances of each number on a die
    private int[] doCounting(ArrayList<Die> dice) {
       int[] countArray = new int[max + 1];
       
       for (int i = 0; i < arraySize; i++) {
           countArray[dice.get(i).getValue()]++;
       }
       
       return countArray;
    }
    
    //Methods to determine scores for all lower section categories
    private int threeAKind(int[] occurances) {
        for (int i = 0; i < occurances.length; i++) {
            if (occurances[i] >= 3) {
                return ((1 * occurances[1]) + (2 * occurances[2]) + (3 * occurances[3])
                + (4 * occurances[4]) + (5 * occurances[5]) + (6 * occurances[6]));
            }
        }
        
        return 0;
    }
    
    private int fourAKind(int[] occurances) {
        for (int i = 0; i < occurances.length; i++) {
            if (occurances[i] >= 4) {
                return ((1 * occurances[1]) + (2 * occurances[2]) + (3 * occurances[3])
                + (4 * occurances[4]) + (5 * occurances[5]) + (6 * occurances[6]));
            }
        }
        
        return 0;
    }
    
    private int fullHouse(int[] occurances) {
        boolean apair = false;
        boolean atoak = false;
        
        for (int i = 0; i < occurances.length; i++) {
            if (occurances[i] == 2) {
                apair = true;
            } else if (occurances[i] == 3) {
                atoak = true;
            }
        }
        
        if (apair && atoak) return 25;
        
        return 0;
    }
    
    private int smallStraight(int[] occurances) {
        //base case: you need a 3 and a 4 in a small straight
        if ((occurances[3] == 0) || (occurances[4] == 0)) return 0;
        
        //This if-else chain assumes you have a 3 and 4 in your dice
        if ((occurances[1] > 0) && (occurances[2] > 0)) {
            return 30;
        } else if((occurances[2] > 0) && (occurances[5] > 0)) {
            return 30;
        } else if ((occurances[5] > 0) && (occurances[6] > 0)) {
            return 30;
        }
        
        return 0;
    }
    
    private int largeStraight(int[] occurances) {
        //base case: you cannot have a 1 and a 6 in a large straight
        if ((occurances[1] > 0) && (occurances[6] > 0)) return 0;
        
        int oneOccuranceCnt = 0;
        
        for (int i = 0; i < occurances.length; i++) {
            if (occurances[i] == 1) oneOccuranceCnt++;
        }
        
        if (oneOccuranceCnt == 5) return 40;
        
        return 0;
    }
    
    private int yahtzee(int[] occurances) {
        for (int i = 0; i < occurances.length; i++) {
            if (occurances[i] == 5) {
                return 50;
            }
        }
        
        return 0;
    }
    
    private int chance(int[] occurances) {
        return ((1 * occurances[1]) + (2 * occurances[2]) + (3 * occurances[3])
                + (4 * occurances[4]) + (5 * occurances[5]) + (6 * occurances[6]));
    }
}
