package yahtzee.clone;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game {
    private Die die; //die object
    private static ArrayList<Die> dice = new ArrayList<Die>(); //stores the dice objects
    private int rollNumber, turnNumber; //track roll and turn counts
    private static Score score = new Score(); //score object
    private GameUI UI;
    private static ArrayList<Player> players = new ArrayList<Player>(); //stores the player objects
    
    //Game is a singleton as we need only 1 game
    private static Game uniqueInstance = null;
    private Game() {}
    
    public static Game instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void createPlayer(int id, String name) {
        players.add(new Player(id, name));
    }
    
    public void startGame(Stage primaryStage, GameUI UI) {
        this.UI = UI;
        createDice();
        
        rollNumber = 0;
        turnNumber = 1;
        
        Scene scene = UI.createGameUI();
        primaryStage.setScene(scene);
        
    }
    
    //Method should only get called once
    private void createDice() {
        //Create the dice objects and store them in arraylist
        for (int i = 0; i < 5; i++) {
            die = new Die(i);
            dice.add(die);
        }
    }
    
    //Roll the dice
    public void rollDice() {
        //Rolls dice if not chosen to hold
        for (int i = 0; i < dice.size(); i++) {
            Die temp = dice.get(i);
            if (temp.getToRoll()) temp.rollDie();
        }
        rollNumber++;
    }
    
    //Method to check if there is any dice to roll
    public boolean checkAllDiceHeld() {
        int cnt = 0;
        for (int i = 0; i < dice.size(); i++) {
            if (!(dice.get(i).getToRoll())) cnt++;
        }
        
        if (cnt == 5) return true;
        else return false;
    }
    
    public void endTurn(int scoreOptionID) {
        //Calculate points score for turn if category not chosen yet.
        int points = score.determineScore(dice, scoreOptionID);
        
        //The check that indicates the user has already scored in the selected category.
        if (points == -1) return;

        UI.displayEndTurnScore(points, scoreOptionID);
        
        newTurn();
    }
    
    //Method to reset dice toRoll booleans, roll count, and individual dice hold buttons
    //Also updates turn count. Method gets called after updating scorecard.
    private void newTurn() {
        turnNumber++;
        rollNumber = 0;
        for (int i = 0; i < dice.size(); i++) {
            dice.get(i).setToRoll(true);
        }

        UI.newTurnReset();
        
        if (turnNumber == 14) {
            endGame();
        }
    }
    
    //When the game is over. There's 13 turns in Yahtzee.
    private void endGame() {
        int lowerSectionPoints = score.lowerSection(); //has bonus applied to score
        int upperSectionPoints = score.upperSection(); //has bonuses applied to score
        int grandTotal = lowerSectionPoints + upperSectionPoints;
        
        UI.endGame(lowerSectionPoints, upperSectionPoints, grandTotal, score);
    }
}
