import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import yahtzee.clone.*;

public class ScoreTest {
    private Score score;
    private ArrayList<Die> dice = new ArrayList<Die>();

    public ScoreTest() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {
        score = new Score();
        for (int i = 0; i < 5; i++) {
            Die die = new Die(i);
            dice.add(die);
        }
    }

    @After
    public void tearDown() {
        score = null;
        dice = null;
    }

    @Test
    public void testOnes() {
        //Scenario 1: dice combination of 1 2 4 2 1
        dice.get(0).setValue(1);
        dice.get(1).setValue(2);
        dice.get(2).setValue(4);
        dice.get(3).setValue(2);
        dice.get(4).setValue(1);

        int expected = 2;
        int observed = score.determineScore(dice, 0);
        assertTrue(expected == observed);

        //Reset the score slot to test multiple dice scenarios at once
        score.getScores().set(0, -1);

        //Scenario 2: dice combination of 2 3 4 5 1
        dice.get(0).setValue(2);
        dice.get(1).setValue(3);
        dice.get(2).setValue(4);
        dice.get(3).setValue(5);
        dice.get(4).setValue(1);

        expected = 1;
        observed = score.determineScore(dice, 0);
        assertTrue(expected == observed);

        score.getScores().set(0, -1);

        //Scenario 3: dice combination of 6 6 6 6 6
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(6);
        dice.get(3).setValue(6);
        dice.get(4).setValue(6);

        expected = 0;
        observed = score.determineScore(dice, 0);
        assertTrue(expected == observed);
    }

    @Test
    public void testTwos() {
        //Scenario 1: dice combination of 5 5 1 3 5
        dice.get(0).setValue(5);
        dice.get(1).setValue(5);
        dice.get(2).setValue(1);
        dice.get(3).setValue(3);
        dice.get(4).setValue(5);

        int expected = 0;
        int observed = score.determineScore(dice, 1);
        assertTrue(expected == observed);

        score.getScores().set(1, -1);

        //Scenario 2: dice combination of 2 4 2 2 2
        dice.get(0).setValue(2);
        dice.get(1).setValue(4);
        dice.get(2).setValue(2);
        dice.get(3).setValue(2);
        dice.get(4).setValue(2);

        expected = 8;
        observed = score.determineScore(dice, 1);
        assertTrue(expected == observed);

        score.getScores().set(1, -1);

        //Scenario 3: dice combination of 1 6 2 2 2
        dice.get(0).setValue(1);
        dice.get(1).setValue(6);
        dice.get(2).setValue(2);
        dice.get(3).setValue(2);
        dice.get(4).setValue(2);

        expected = 6;
        observed = score.determineScore(dice, 1);
        assertTrue(expected == observed);
    }

    @Test
    public void testThrees() {
        //Scenario 1: dice combination of 5 5 1 3 5
        dice.get(0).setValue(5);
        dice.get(1).setValue(5);
        dice.get(2).setValue(1);
        dice.get(3).setValue(3);
        dice.get(4).setValue(5);

        int expected = 3;
        int observed = score.determineScore(dice, 2);
        assertTrue(expected == observed);

        score.getScores().set(2, -1);

        //Scenario 2: dice combination of 1 3 4 4 6
        dice.get(0).setValue(1);
        dice.get(1).setValue(3);
        dice.get(2).setValue(4);
        dice.get(3).setValue(4);
        dice.get(4).setValue(6);

        expected = 3;
        observed = score.determineScore(dice, 2);
        assertTrue(expected == observed);

        score.getScores().set(2, -1);

        //Scenario 3: dice combination of 3 3 3 3 3
        dice.get(0).setValue(3);
        dice.get(1).setValue(3);
        dice.get(2).setValue(3);
        dice.get(3).setValue(3);
        dice.get(4).setValue(3);

        expected = 15;
        observed = score.determineScore(dice, 2);
        assertTrue(expected == observed);
    }

    @Test
    public void testFours() {
        //Scenario 1: dice combination of 4 5 4 6 4
        dice.get(0).setValue(4);
        dice.get(1).setValue(5);
        dice.get(2).setValue(4);
        dice.get(3).setValue(6);
        dice.get(4).setValue(4);

        int expected = 12;
        int observed = score.determineScore(dice, 3);
        assertTrue(expected == observed);

        score.getScores().set(3, -1);

        //Scenario 2: dice combination of 4 4 2 1 3
        dice.get(0).setValue(4);
        dice.get(1).setValue(4);
        dice.get(2).setValue(2);
        dice.get(3).setValue(1);
        dice.get(4).setValue(3);

        expected = 8;
        observed = score.determineScore(dice, 3);
        assertTrue(expected == observed);

        score.getScores().set(3, -1);

        //Scenario 3: dice combination of 3 6 2 5 1
        dice.get(0).setValue(3);
        dice.get(1).setValue(6);
        dice.get(2).setValue(2);
        dice.get(3).setValue(5);
        dice.get(4).setValue(1);

        expected = 0;
        observed = score.determineScore(dice, 3);
        assertTrue(expected == observed);
    }

    @Test
    public void testFives() {
        //Scenario 1: dice combination of 6 5 5 5 5
        dice.get(0).setValue(6);
        dice.get(1).setValue(5);
        dice.get(2).setValue(5);
        dice.get(3).setValue(5);
        dice.get(4).setValue(5);

        int expected = 20;
        int observed = score.determineScore(dice, 4);
        assertTrue(expected == observed);

        score.getScores().set(4, -1);

        //Scenario 2: dice combination of 5 4 2 6 5
        dice.get(0).setValue(5);
        dice.get(1).setValue(4);
        dice.get(2).setValue(2);
        dice.get(3).setValue(6);
        dice.get(4).setValue(5);

        expected = 10;
        observed = score.determineScore(dice, 4);
        assertTrue(expected == observed);

        score.getScores().set(4, -1);

        //Scenario 3: dice combination of 1 3 5 2 2
        dice.get(0).setValue(1);
        dice.get(1).setValue(3);
        dice.get(2).setValue(5);
        dice.get(3).setValue(2);
        dice.get(4).setValue(2);

        expected = 5;
        observed = score.determineScore(dice, 4);
        assertTrue(expected == observed);
    }

    @Test
    public void testSixes() {
        //Scenario 1: dice combination of 3 2 3 5 4
        dice.get(0).setValue(3);
        dice.get(1).setValue(2);
        dice.get(2).setValue(3);
        dice.get(3).setValue(5);
        dice.get(4).setValue(4);

        int expected = 0;
        int observed = score.determineScore(dice, 5);
        assertTrue(expected == observed);

        score.getScores().set(5, -1);

        //Scenario 2: dice combination of 6 2 6 6 6
        dice.get(0).setValue(6);
        dice.get(1).setValue(2);
        dice.get(2).setValue(6);
        dice.get(3).setValue(6);
        dice.get(4).setValue(6);

        expected = 24;
        observed = score.determineScore(dice, 5);
        assertTrue(expected == observed);

        score.getScores().set(5, -1);

        //Scenario 3: dice combination of 6 5 5 1 5
        dice.get(0).setValue(6);
        dice.get(1).setValue(5);
        dice.get(2).setValue(5);
        dice.get(3).setValue(1);
        dice.get(4).setValue(5);

        expected = 6;
        observed = score.determineScore(dice, 5);
        assertTrue(expected == observed);
    }

    @Test
    public void testThreeAKind() {
        //Scenario 1: dice combination of 5 5 1 3 5
        dice.get(0).setValue(5);
        dice.get(1).setValue(5);
        dice.get(2).setValue(1);
        dice.get(3).setValue(3);
        dice.get(4).setValue(5);

        int expected = 19;
        int observed = score.determineScore(dice, 6);
        assertTrue(expected == observed);

        score.getScores().set(6, -1);

        //Scenario 2: dice combination of 1 4 1 1 1
        dice.get(0).setValue(1);
        dice.get(1).setValue(4);
        dice.get(2).setValue(1);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);

        expected = 8;
        observed = score.determineScore(dice, 6);
        assertTrue(expected == observed);

        score.getScores().set(6, -1);

        //Scenario 3: dice combination of 1 6 2 2 3
        dice.get(0).setValue(1);
        dice.get(1).setValue(6);
        dice.get(2).setValue(2);
        dice.get(3).setValue(2);
        dice.get(4).setValue(3);

        expected = 0;
        observed = score.determineScore(dice, 6);
        assertTrue(expected == observed);

        score.getScores().set(6, -1);

        //Scenario 4: dice combination of 4 4 4 4 4
        dice.get(0).setValue(4);
        dice.get(1).setValue(4);
        dice.get(2).setValue(4);
        dice.get(3).setValue(4);
        dice.get(4).setValue(4);

        expected = 20;
        observed = score.determineScore(dice, 6);
        assertTrue(expected == observed);
    }

    @Test
    public void testFourAKind() {
        //Scenario 1: dice combination of 5 5 1 3 5
        dice.get(0).setValue(5);
        dice.get(1).setValue(5);
        dice.get(2).setValue(1);
        dice.get(3).setValue(3);
        dice.get(4).setValue(5);

        int expected = 0;
        int observed = score.determineScore(dice, 7);
        assertTrue(expected == observed);

        score.getScores().set(7, -1);

        //Scenario 2: dice combination of 2 4 2 2 2
        dice.get(0).setValue(2);
        dice.get(1).setValue(4);
        dice.get(2).setValue(2);
        dice.get(3).setValue(2);
        dice.get(4).setValue(2);

        expected = 12;
        observed = score.determineScore(dice, 7);
        assertTrue(expected == observed);

        score.getScores().set(7, -1);

        //Scenario 3: dice combination of 6 6 6 6 6
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(6);
        dice.get(3).setValue(6);
        dice.get(4).setValue(6);

        expected = 30;
        observed = score.determineScore(dice, 7);
        assertTrue(expected == observed);
    }

    @Test
    public void testFullHouse() {
        //Scenario 1: dice combination of 5 5 6 6 5
        dice.get(0).setValue(5);
        dice.get(1).setValue(5);
        dice.get(2).setValue(6);
        dice.get(3).setValue(6);
        dice.get(4).setValue(5);

        int expected = 25;
        int observed = score.determineScore(dice, 8);
        assertTrue(expected == observed);

        score.getScores().set(8, -1);

        //Scenario 2: dice combination of 2 2 2 2 2
        dice.get(0).setValue(2);
        dice.get(1).setValue(2);
        dice.get(2).setValue(2);
        dice.get(3).setValue(2);
        dice.get(4).setValue(2);

        expected = 0;
        observed = score.determineScore(dice, 8);
        assertTrue(expected == observed);

        score.getScores().set(8, -1);

        //Scenario 3: dice combination of 1 6 3 3 3
        dice.get(0).setValue(1);
        dice.get(1).setValue(6);
        dice.get(2).setValue(3);
        dice.get(3).setValue(3);
        dice.get(4).setValue(3);

        expected = 0;
        observed = score.determineScore(dice, 8);
        assertTrue(expected == observed);
    }

    @Test
    public void testSmallStraight() {
        //Scenario 1: dice combination of 4 2 1 3 5
        dice.get(0).setValue(4);
        dice.get(1).setValue(2);
        dice.get(2).setValue(1);
        dice.get(3).setValue(3);
        dice.get(4).setValue(5);

        int expected = 30;
        int observed = score.determineScore(dice, 9);
        assertTrue(expected == observed);

        score.getScores().set(9, -1);

        //Scenario 2: dice combination of 2 4 2 3 5
        dice.get(0).setValue(2);
        dice.get(1).setValue(4);
        dice.get(2).setValue(2);
        dice.get(3).setValue(3);
        dice.get(4).setValue(5);

        expected = 30;
        observed = score.determineScore(dice, 9);
        assertTrue(expected == observed);

        score.getScores().set(9, -1);

        //Scenario 3: dice combination of 1 6 2 3 5
        dice.get(0).setValue(1);
        dice.get(1).setValue(6);
        dice.get(2).setValue(2);
        dice.get(3).setValue(3);
        dice.get(4).setValue(5);

        expected = 0;
        observed = score.determineScore(dice, 9);
        assertTrue(expected == observed);

        score.getScores().set(9, -1);

        //Scenario 4: dice combination of 1 6 4 3 5
        dice.get(0).setValue(1);
        dice.get(1).setValue(6);
        dice.get(2).setValue(4);
        dice.get(3).setValue(3);
        dice.get(4).setValue(5);

        expected = 30;
        observed = score.determineScore(dice, 9);
        assertTrue(expected == observed);
    }

    @Test
    public void testLargeStraight() {
        //Scenario 1: dice combination of 5 2 1 3 4
        dice.get(0).setValue(5);
        dice.get(1).setValue(2);
        dice.get(2).setValue(1);
        dice.get(3).setValue(3);
        dice.get(4).setValue(4);

        int expected = 40;
        int observed = score.determineScore(dice, 10);
        assertTrue(expected == observed);

        score.getScores().set(10, -1);

        //Scenario 2: dice combination of 2 4 6 5 3
        dice.get(0).setValue(2);
        dice.get(1).setValue(4);
        dice.get(2).setValue(6);
        dice.get(3).setValue(5);
        dice.get(4).setValue(3);

        expected = 40;
        observed = score.determineScore(dice, 10);
        assertTrue(expected == observed);

        score.getScores().set(10, -1);

        //Scenario 3: dice combination of 1 6 3 4 5
        dice.get(0).setValue(1);
        dice.get(1).setValue(6);
        dice.get(2).setValue(3);
        dice.get(3).setValue(4);
        dice.get(4).setValue(5);

        expected = 0;
        observed = score.determineScore(dice, 10);
        assertTrue(expected == observed);
    }

    @Test
    public void testYahtzee() {
        //Scenario 1: dice combination of 5 5 5 5 5
        dice.get(0).setValue(5);
        dice.get(1).setValue(5);
        dice.get(2).setValue(5);
        dice.get(3).setValue(5);
        dice.get(4).setValue(5);

        int expected = 50;
        int observed = score.determineScore(dice, 11);
        assertTrue(expected == observed);

        score.getScores().set(11, -1);

        //Scenario 2: dice combination of 2 4 2 2 2
        dice.get(0).setValue(2);
        dice.get(1).setValue(4);
        dice.get(2).setValue(2);
        dice.get(3).setValue(2);
        dice.get(4).setValue(2);

        expected = 0;
        observed = score.determineScore(dice, 11);
        assertTrue(expected == observed);

        score.getScores().set(11, -1);

        //Scenario 3: dice combination of 4 1 1 3 6
        dice.get(0).setValue(4);
        dice.get(1).setValue(1);
        dice.get(2).setValue(1);
        dice.get(3).setValue(3);
        dice.get(4).setValue(6);

        expected = 0;
        observed = score.determineScore(dice, 11);
        assertTrue(expected == observed);
    }

    @Test
    public void testChance() {
        //Scenario 1: dice combination of 6 5 6 4 5
        dice.get(0).setValue(6);
        dice.get(1).setValue(5);
        dice.get(2).setValue(6);
        dice.get(3).setValue(4);
        dice.get(4).setValue(5);

        int expected = 26;
        int observed = score.determineScore(dice, 12);
        assertTrue(expected == observed);

        score.getScores().set(12, -1);

        //Scenario 2: dice combination of 2 4 3 1 1
        dice.get(0).setValue(2);
        dice.get(1).setValue(4);
        dice.get(2).setValue(3);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);

        expected = 11;
        observed = score.determineScore(dice, 12);
        assertTrue(expected == observed);

        score.getScores().set(12, -1);

        //Scenario 3: dice combination of 3 4 4 3 6
        dice.get(0).setValue(3);
        dice.get(1).setValue(4);
        dice.get(2).setValue(4);
        dice.get(3).setValue(3);
        dice.get(4).setValue(6);

        expected = 20;
        observed = score.determineScore(dice, 12);
        assertTrue(expected == observed);
    }

    @Test
    //The determineScore function should return -1 if the player has already scored in a category
    public void testRepeatCategoryOption() {
        //Scenario 1: dice combination of 3 4 4 3 6
        dice.get(0).setValue(3);
        dice.get(1).setValue(4);
        dice.get(2).setValue(4);
        dice.get(3).setValue(3);
        dice.get(4).setValue(6);

        int expected = 20;
        int observed = score.determineScore(dice, 12);
        assertTrue(expected == observed);

        //Scenario 2: dice combination of 2 4 3 1 1
        dice.get(0).setValue(2);
        dice.get(1).setValue(4);
        dice.get(2).setValue(3);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);

        expected = -1;
        observed = score.determineScore(dice, 12);
        assertTrue(expected == observed);
    }

    @Test
    public void testIncrementYahtzeeCount() {
        score.getScores().set(11, 50);

        //Scenario 1: dice combination of 5 5 5 5 5
        dice.get(0).setValue(5);
        dice.get(1).setValue(5);
        dice.get(2).setValue(5);
        dice.get(3).setValue(5);
        dice.get(4).setValue(5);

        score.determineScore(dice, 4);
        int expected = 1;
        int observed = score.getYahtzeeCount();
        assertTrue(expected == observed);

        //Scenario 2: dice combination of 2 4 3 1 1. Yahtzee count would still be 1
        dice.get(0).setValue(2);
        dice.get(1).setValue(4);
        dice.get(2).setValue(3);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);

        score.determineScore(dice, 0);
        observed = score.getYahtzeeCount();
        assertTrue(expected == observed);

        //Scenario 3: dice combination of 6 6 6 6 6. Yahzee count should increase
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(6);
        dice.get(3).setValue(6);
        dice.get(4).setValue(6);

        score.determineScore(dice, 5);
        expected = 2;
        observed = score.getYahtzeeCount();
        assertTrue(expected == observed);
    }

    @Test
    public void testLowerSection() {
        //Scenario 1: No yahtzee
        score.getScores().set(6, 17);
        score.getScores().set(7, 0);
        score.getScores().set(8, 25);
        score.getScores().set(9, 30);
        score.getScores().set(10, 40);
        score.getScores().set(11, 0);
        score.getScores().set(12, 15);

        int expected = 127;
        int observed = score.lowerSection();
        assertTrue(expected == observed);

        //Scenario 2: 1 yahtzee, no bonuses
        score.getScores().set(6, 17);
        score.getScores().set(7, 21);
        score.getScores().set(8, 25);
        score.getScores().set(9, 30);
        score.getScores().set(10, 40);
        score.getScores().set(11, 50);
        score.getScores().set(12, 8);

        expected = 191;
        observed = score.lowerSection();
        assertTrue(expected == observed);

        //Scenario 3: 2 yahtzees, 100 point bonus
        score.getScores().set(6, 14);
        score.getScores().set(7, 29);
        score.getScores().set(8, 25);
        score.getScores().set(9, 30);
        score.getScores().set(10, 0);
        score.getScores().set(11, 50);
        score.getScores().set(12, 19);
        score.setYahtzeeCount(1);

        expected = 267;
        observed = score.lowerSection();
        assertTrue(expected == observed);

        //Scenario 4: 3 yahtzees, 200 point bonus
        score.getScores().set(6, 24);
        score.getScores().set(7, 15);
        score.getScores().set(8, 25);
        score.getScores().set(9, 30);
        score.getScores().set(10, 40);
        score.getScores().set(11, 50);
        score.getScores().set(12, 23);
        score.setYahtzeeCount(2);

        expected = 407;
        observed = score.lowerSection();
        assertTrue(expected == observed);
    }

    @Test
    public void testUpperSection() {
        //Scenario 1: Player scores less than 63
        score.getScores().set(0, 2);
        score.getScores().set(1, 6);
        score.getScores().set(2, 9);
        score.getScores().set(3, 8);
        score.getScores().set(4, 15);
        score.getScores().set(5, 12);

        int expected = 52;
        int observed = score.upperSection();
        assertTrue(expected == observed);

        //Scenario 2: Player scores exactly 63
        score.getScores().set(0, 3);
        score.getScores().set(1, 6);
        score.getScores().set(2, 9);
        score.getScores().set(3, 12);
        score.getScores().set(4, 15);
        score.getScores().set(5, 18);

        expected = 98;
        observed = score.upperSection();
        assertTrue(expected == observed);

        //Scenario 3: Player scores more than 63
        score.getScores().set(0, 4);
        score.getScores().set(1, 4);
        score.getScores().set(2, 9);
        score.getScores().set(3, 8);
        score.getScores().set(4, 20);
        score.getScores().set(5, 24);

        expected = 104;
        observed = score.upperSection();
        assertTrue(expected == observed);
    }
}
