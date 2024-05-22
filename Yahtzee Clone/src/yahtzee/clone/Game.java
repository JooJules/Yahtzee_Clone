package yahtzee.clone;

import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {
    private Button holdDie1, holdDie2, holdDie3, holdDie4, holdDie5; //buttons to hold or roll dice
    private static Group holdDieButtonGroup; //buttons that go underneath a die to indicate if it's being held or rolled
    private Text num1, num2, num3, num4, num5; //to display dice values
    private Group root; //the root node to display UI
    private Group die1, die2, die3, die4, die5; //groups that will contain rectangle and value for a die
    private static Group diceGroup; //a group that will contain all five dice
    private static GridPane scoreCard; //a grid that will contain the scorecard
    private static Scene scene; //the scene where the root goes
    private Die die; //die object
    private static ArrayList<Die> dice = new ArrayList<Die>(); //stores the dice objects
    private int rollNumber, turnNumber; //track roll and turn counts
    private static Group operationButtonGroup = new Group(); //a group for buttons to roll dice and access scorecard
    private static Group counterDisplayerGroup = new Group(); //a group to display roll and turn counts to user
    //private static ArrayList<Text> scores = new ArrayList<Text>(); //display the scores for each of the categories
    private Text rollNum, turnNum; //display roll count and turn number to user
    private static Score score = new Score(); //score object
    
    //Game is a singleton as we need only 1 game
    private static Game uniqueInstance = null;
    private Game() {}
    
    public static Game instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }
    
    
    public void startGame(Stage primaryStage) {
        
        createDice();
        createDieButtons();
        createRollDiceButton();
        createScoreCard();
        createScorePointsButton();
        
        rollNumber = 0;
        turnNumber = 1;
        
        Text gameTitle = new Text("YAHTZEE");
        gameTitle.setStyle("-fx-font: 40 arial");
        gameTitle.setLayoutX(100);
        gameTitle.setLayoutY(40);
        
        Text author = new Text("Programmed by Julia D.");
        author.setStyle("-fx-font: 20 arial");
        author.setLayoutX(5);
        author.setLayoutY(775);
        
        rollNum = new Text("Roll number: " + rollNumber);
        rollNum.setStyle("-fx-font: 20 arial");
        rollNum.setLayoutX(450);
        rollNum.setLayoutY(40);
        turnNum = new Text("Turn number: " + turnNumber);
        turnNum.setStyle("-fx-font: 20 arial");
        turnNum.setLayoutX(450);
        turnNum.setLayoutY(80);
        
        root = new Group(diceGroup, holdDieButtonGroup, scoreCard, author,
                gameTitle, rollNum, turnNum, operationButtonGroup);
        
        String test = root.getChildren().toString();
        scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        
    }
    
    //Method should only get called once
    private void createDice() {
        Rectangle r1, r2, r3, r4, r5; //dice borders. won't change throughout game
        
        //Create the dice objects and store them in arraylist
        for (int i = 0; i < 5; i++) {
            die = new Die(i);
            dice.add(die);
        }
        
        //Create dice UI
        r1 = new Rectangle(650, 40, 100, 100);
        r1.setStrokeWidth(5);
        r1.setStroke(Color.BLACK);
        r1.setFill(Color.WHITE);
        num1 = new Text("1");
        num1.setStyle("-fx-font: 40 arial");
        num1.setLayoutX(690);
        num1.setLayoutY(105);
        die1 = new Group(r1, num1);
        
        r2 = new Rectangle(650, 190, 100, 100);
        r2.setStrokeWidth(5);
        r2.setStroke(Color.BLACK);
        r2.setFill(Color.WHITE);
        num2 = new Text("1");
        num2.setStyle("-fx-font: 40 arial");
        num2.setLayoutX(690);
        num2.setLayoutY(255);
        die2 = new Group(r2, num2);
        
        r3 = new Rectangle(650, 340, 100, 100);
        r3.setStrokeWidth(5);
        r3.setStroke(Color.BLACK);
        r3.setFill(Color.WHITE);
        num3 = new Text("1");
        num3.setStyle("-fx-font: 40 arial");
        num3.setLayoutX(690);
        num3.setLayoutY(405);
        die3 = new Group(r3, num3);
        
        r4 = new Rectangle(650, 490, 100, 100);
        r4.setStrokeWidth(5);
        r4.setStroke(Color.BLACK);
        r4.setFill(Color.WHITE);
        num4 = new Text("1");
        num4.setStyle("-fx-font: 40 arial");
        num4.setLayoutX(690);
        num4.setLayoutY(555);
        die4 = new Group(r4, num4);
        
        r5 = new Rectangle(650, 640, 100, 100);
        r5.setStrokeWidth(5);
        r5.setStroke(Color.BLACK);
        r5.setFill(Color.WHITE);
        num5 = new Text("1");
        num5.setStyle("-fx-font: 40 arial");
        num5.setLayoutX(690);
        num5.setLayoutY(705);
        die5 = new Group(r5, num5);
        
        diceGroup = new Group(die1, die2, die3, die4, die5);
        
    }
    
    //Method should only get called once
    private void createScoreCard() {
        scoreCard = new GridPane();
        scoreCard.setMinSize(300, 700);
        scoreCard.setLayoutX(50);
        scoreCard.setLayoutY(50);
        scoreCard.setGridLinesVisible(true);
        
        //Make the grid fixed with two columns
        ColumnConstraints c1 = new ColumnConstraints(); //column 1
        ColumnConstraints c2 = new ColumnConstraints(); //column 2
        c1.setPercentWidth(75);
        c2.setPercentWidth(25);
        scoreCard.getColumnConstraints().addAll(c1, c2);
        
        //Make the grid fixed with 20 rows
        int numRows = 20;
        for (int i = 0; i < numRows; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100/numRows);
            scoreCard.getRowConstraints().add(rc);
        }
        
        Text aces = new Text("ACES");
        aces.setStyle("-fx-font: 20 arial");
        scoreCard.add(aces, 0, 0);
        GridPane.setHalignment(aces, HPos.CENTER);
        
        Text twos = new Text("TWOS");
        twos.setStyle("-fx-font: 20 arial");
        scoreCard.add(twos, 0, 1);
        GridPane.setHalignment(twos, HPos.CENTER);
        
        Text threes = new Text("THREES");
        threes.setStyle("-fx-font: 20 arial");
        scoreCard.add(threes, 0, 2);
        GridPane.setHalignment(threes, HPos.CENTER);
        
        Text fours = new Text("FOURS");
        fours.setStyle("-fx-font: 20 arial");
        scoreCard.add(fours, 0, 3);
        GridPane.setHalignment(fours, HPos.CENTER);
        
        Text fives = new Text("FIVES");
        fives.setStyle("-fx-font: 20 arial");
        scoreCard.add(fives, 0, 4);
        GridPane.setHalignment(fives, HPos.CENTER);
        
        Text sixes = new Text("SIXES");
        sixes.setStyle("-fx-font: 20 arial");
        scoreCard.add(sixes, 0, 5);
        GridPane.setHalignment(sixes, HPos.CENTER);
        
        Text total1 = new Text("Total Score");
        total1.setStyle("-fx-font: 20 arial");
        scoreCard.add(total1, 0, 6);
        GridPane.setHalignment(total1, HPos.CENTER);
        
        Text bonus = new Text("Bonus if total score >= 63");
        bonus.setStyle("-fx-font: 20 arial");
        scoreCard.add(bonus, 0, 7);
        GridPane.setHalignment(bonus, HPos.CENTER);
        
        Text total2 = new Text("Total");
        total2.setStyle("-fx-font: 20 arial");
        scoreCard.add(total2, 0, 8);
        GridPane.setHalignment(total2, HPos.CENTER);
        
        Text threeAKind = new Text("3 of a Kind");
        threeAKind.setStyle("-fx-font: 20 arial");
        scoreCard.add(threeAKind, 0, 9);
        GridPane.setHalignment(threeAKind, HPos.CENTER);
        
        Text fourAKind = new Text("4 of a Kind");
        fourAKind.setStyle("-fx-font: 20 arial");
        scoreCard.add(fourAKind, 0, 10);
        GridPane.setHalignment(fourAKind, HPos.CENTER);
        
        Text fullHouse = new Text("Full House");
        fullHouse.setStyle("-fx-font: 20 arial");
        scoreCard.add(fullHouse, 0, 11);
        GridPane.setHalignment(fullHouse, HPos.CENTER);
        
        Text smallStraight = new Text("Small Straight");
        smallStraight.setStyle("-fx-font: 20 arial");
        scoreCard.add(smallStraight, 0, 12);
        GridPane.setHalignment(smallStraight, HPos.CENTER);
        
        Text largeStraight = new Text("Large Straight");
        largeStraight.setStyle("-fx-font: 20 arial");
        scoreCard.add(largeStraight, 0, 13);
        GridPane.setHalignment(largeStraight, HPos.CENTER);
        
        Text yahtzee = new Text("YAHTZEE");
        yahtzee.setStyle("-fx-font: 20 arial");
        scoreCard.add(yahtzee, 0, 14);
        GridPane.setHalignment(yahtzee, HPos.CENTER);
        
        Text chance = new Text("Chance");
        chance.setStyle("-fx-font: 20 arial");
        scoreCard.add(chance, 0, 15);
        GridPane.setHalignment(chance, HPos.CENTER);
        
        Text yahtzeeBonus = new Text("YAHTZEE BONUS");
        yahtzeeBonus.setStyle("-fx-font: 20 arial");
        scoreCard.add(yahtzeeBonus, 0, 16);
        GridPane.setHalignment(yahtzeeBonus, HPos.CENTER);
        
        Text lowerTotal = new Text("Total of Lower Section");
        lowerTotal.setStyle("-fx-font: 20 arial");
        scoreCard.add(lowerTotal, 0, 17);
        GridPane.setHalignment(lowerTotal, HPos.CENTER);
        
        Text upperTotal = new Text("Total of Upper Section");
        upperTotal.setStyle("-fx-font: 20 arial");
        scoreCard.add(upperTotal, 0, 18);
        GridPane.setHalignment(upperTotal, HPos.CENTER);
        
        Text grandTotal = new Text("GRAND TOTAL");
        grandTotal.setStyle("-fx-font: 20 arial");
        scoreCard.add(grandTotal, 0, 19);
        GridPane.setHalignment(grandTotal, HPos.CENTER);
        
    }
    
    private void createScorePointsButton() {
        Button score = new Button("Score Points");
        score.setLayoutX(425);
        score.setLayoutY(300);
        score.setMinSize(150, 100);
        score.setStyle("-fx-font: 22.5 arial");
        score.setWrapText(true);
        score.setOnAction(event -> displayScoringOptions());
        
        operationButtonGroup.getChildren().add(score);
    }
    
    private void displayScoringOptions() {
        //Check to see if the user has rolled at least once
        if (rollNumber < 1) return;
        
        root.getChildren().remove(7);
        Group scoreButtonGroup = new Group();
        
        //Upper Section Score Buttons
        for (int i = 0; i < 6; i++) {
            Button scoreOption = new Button("Score");
            scoreOption.setLayoutX(375);
            scoreOption.setLayoutY(55 + (35 * i));
            scoreOption.setMinSize(50, 25);
            scoreOption.setId(String.valueOf(i)); //IDs [0-5]
            scoreOption.setOnAction(event -> endTurn(Integer.valueOf(scoreOption.getId())));
            scoreButtonGroup.getChildren().add(scoreOption);
        }
        //Lower Section Score Buttons
        for (int i = 0; i < 7; i++) {
            Button scoreOption = new Button("Score");
            scoreOption.setLayoutX(375);
            scoreOption.setLayoutY(370 + (35 * i));
            scoreOption.setMinSize(50, 25);
            scoreOption.setId(String.valueOf(i + 6)); //IDs [6-12]
            scoreOption.setOnAction(event -> endTurn(Integer.valueOf(scoreOption.getId())));
            scoreButtonGroup.getChildren().add(scoreOption);
        }
        
        Button back = new Button("Back");
        back.setLayoutX(450);
        back.setLayoutY(300);
        back.setMinSize(150, 100);
        back.setStyle("-fx-font: 30 arial");
        back.setOnAction(event -> displayOperationButtons());
        
        root.getChildren().add(scoreButtonGroup);
        root.getChildren().add(back);
    }
    
    //Method should only be called once
    private void createDieButtons() {
        holdDie1 = new Button("Hold");
        holdDie1.setId("1");
        holdDie1.setOnAction(event -> updateDie(1, 0));
        holdDie1.setMaxSize(100, 25);
        holdDie1.setLayoutX(680);
        holdDie1.setLayoutY(145);
        
        holdDie2 = new Button("Hold");
        holdDie2.setId("2");
        holdDie2.setOnAction(event -> updateDie(2, 1));
        holdDie2.setMaxSize(100, 25);
        holdDie2.setLayoutX(680);
        holdDie2.setLayoutY(295);
        
        holdDie3 = new Button("Hold");
        holdDie3.setId("3");
        holdDie3.setOnAction(event -> updateDie(3, 2));
        holdDie3.setMaxSize(100, 25);
        holdDie3.setLayoutX(680);
        holdDie3.setLayoutY(445);
        
        holdDie4 = new Button("Hold");
        holdDie4.setId("4");
        holdDie4.setOnAction(event -> updateDie(4, 3));
        holdDie4.setMaxSize(100, 25);
        holdDie4.setLayoutX(680);
        holdDie4.setLayoutY(595);

        holdDie5 = new Button("Hold");
        holdDie5.setId("5");
        holdDie5.setOnAction(event -> updateDie(5, 4));
        holdDie5.setMaxSize(100, 25);
        holdDie5.setLayoutX(680);
        holdDie5.setLayoutY(745);
        
        holdDieButtonGroup = new Group(holdDie1, holdDie2, holdDie3, holdDie4, holdDie5);
    }
    
    //Update button text for UI and set toRoll for the particular die
    private void updateDie(int buttonID, int dieID) {
        //check to ensure that the user has rolled at least once, but also not after thrid roll
        if (rollNumber <= 0 || rollNumber >= 3) return;
        
        dice.get(dieID).setToRoll(!(dice.get(dieID).getToRoll()));
        switch (buttonID) {
            case 1:
                if (holdDie1.getText().equalsIgnoreCase("Hold")) {
                    holdDie1.setText("Roll");
                } else {
                    holdDie1.setText("Hold");
                }
                break;
            case 2:
                if (holdDie2.getText().equalsIgnoreCase("Hold")) {
                    holdDie2.setText("Roll");
                } else {
                    holdDie2.setText("Hold");
                }
                break;
            case 3:
                if (holdDie3.getText().equalsIgnoreCase("Hold")) {
                    holdDie3.setText("Roll");
                } else {
                    holdDie3.setText("Hold");
                }
                break;
            case 4:
                if (holdDie4.getText().equalsIgnoreCase("Hold")) {
                    holdDie4.setText("Roll");
                } else {
                    holdDie4.setText("Hold");
                }
                break;
            case 5:
                if (holdDie5.getText().equalsIgnoreCase("Hold")) {
                    holdDie5.setText("Roll");
                } else {
                    holdDie5.setText("Hold");
                }
                break;
        };
    }
    
    //Method to display operations buttons again in case score points was accidentally clicked
    private void displayOperationButtons() {
        //Remove category score buttons and back button
        root.getChildren().remove(8);
        root.getChildren().remove(7);
        //Add back operation buttons
        root.getChildren().add(operationButtonGroup);
    }
    
    //Method should only be called once. Rolls the dice not being held.
    private void createRollDiceButton() {
        Button rollDice = new Button("Roll Dice");
        rollDice.setLayoutX(425);
        rollDice.setLayoutY(450);
        rollDice.setMinSize(150, 100);
        rollDice.setStyle("-fx-font: 30 arial");
        rollDice.setWrapText(true);
        rollDice.setOnAction(event -> rollDice());
        
        operationButtonGroup.getChildren().add(rollDice);
    }
    
    //Roll the dice and update UI
    private void rollDice() {
        //Need to check if player hasn't rolled 3 times for this turn.
        //Or if the player has all the dice held to prevent wasting a turn.
        boolean allDiceHeld = checkAllDiceHeld();
        if (rollNumber == 3 || allDiceHeld) return;
        
        //Rolls dice if not chosen to hold
        for (int i = 0; i < dice.size(); i++) {
            Die temp = dice.get(i);
            if (temp.getToRoll()) temp.rollDie();
        }
        rollNumber++;
        
        //Update UI
        num1.setText(String.valueOf(dice.get(0).getValue()));
        num2.setText(String.valueOf(dice.get(1).getValue()));
        num3.setText(String.valueOf(dice.get(2).getValue()));
        num4.setText(String.valueOf(dice.get(3).getValue()));
        num5.setText(String.valueOf(dice.get(4).getValue()));
        rollNum.setText("Roll number: " + rollNumber);
    }
    
    private boolean checkAllDiceHeld() {
        int cnt = 0;
        for (int i = 0; i < dice.size(); i++) {
            if (!(dice.get(i).getToRoll())) cnt++;
        }
        
        if (cnt == 5) return true;
        else return false;
    }
    
    private void endTurn(int scoreOptionID) {
        int points = score.determineScore(dice, scoreOptionID);
        
        //The check that indicates the user has already scored in the selected category.
        if (points == -1) return;
        
        Text pointsUI = new Text(String.valueOf(points));
        pointsUI.setStyle("-fx-font: 20 arial");
        
        switch (scoreOptionID) {
            case 0: //ones
                scoreCard.add(pointsUI, 1, 0);
                break;
            case 1: //twos
                scoreCard.add(pointsUI, 1, 1);
                break;
            case 2: //threes
                scoreCard.add(pointsUI, 1, 2);
                break;
            case 3: //fours
                scoreCard.add(pointsUI, 1, 3);
                break;
            case 4: //fives
                scoreCard.add(pointsUI, 1, 4);
                break;
            case 5: //sixes
                scoreCard.add(pointsUI, 1, 5);
                break;
            case 6: //three of a kind
                scoreCard.add(pointsUI, 1, 9);
                break;
            case 7: //four of a kind
                scoreCard.add(pointsUI, 1, 10);
                break;
            case 8: //full house
                scoreCard.add(pointsUI, 1, 11);
                break;
            case 9: //small straight
                scoreCard.add(pointsUI, 1, 12);
                break;
            case 10: //large straight
                scoreCard.add(pointsUI, 1, 13);
                break;
            case 11: //yahtzee
                scoreCard.add(pointsUI, 1, 14);
                break;
            case 12: //chance
                scoreCard.add(pointsUI, 1, 15);
                break;
        }
        
        GridPane.setHalignment(pointsUI, HPos.CENTER);
        
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
        holdDie1.setText("Hold");
        holdDie2.setText("Hold");
        holdDie3.setText("Hold");
        holdDie4.setText("Hold");
        holdDie5.setText("Hold");
        rollNum.setText("Roll number: " + rollNumber);
        turnNum.setText("Turn number: " + turnNumber);
        
        //Remove the score buttons and add back the operation buttons
        root.getChildren().remove(8);
        root.getChildren().remove(7);
        root.getChildren().add(operationButtonGroup);
        
        if (turnNumber == 14) {
            endGame();
        }
        
    }
    
    //When the game is over. There's 13 turns in Yahtzee.
    private void endGame() {
        Text topBonus = new Text(), yahtzeeBonus = new Text(), upperTotalB4Bonus = new Text(),
                upperTotalAfterBonus = new Text(), lowerSectionFinal = new Text(),
                grandTotalText = new Text(), upperTotalAfterBonus2 = new Text();
        int lowerSectionPoints = score.lowerSection(); //has bonus applied to score
        int upperSectionPoints = score.upperSection(); //has bonuses applied to score
        int grandTotal = lowerSectionPoints + upperSectionPoints;
        
        if (score.getTopBonus()) {
            upperTotalB4Bonus.setText(String.valueOf(upperSectionPoints - 35));
            topBonus.setText("35");
            scoreCard.add(topBonus, 1, 7);
        } else {
            upperTotalB4Bonus.setText(String.valueOf(upperSectionPoints));
            topBonus.setText("0");
            scoreCard.add(topBonus, 1, 7);
        }
        topBonus.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(topBonus, HPos.CENTER);
        
        upperTotalAfterBonus.setText(String.valueOf(upperSectionPoints));
        scoreCard.add(upperTotalB4Bonus, 1, 6);
        scoreCard.add(upperTotalAfterBonus, 1, 8);
        upperTotalB4Bonus.setStyle("-fx-font: 20 arial");
        upperTotalAfterBonus.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(upperTotalB4Bonus, HPos.CENTER);
        GridPane.setHalignment(upperTotalAfterBonus, HPos.CENTER);
        //complier doesn't like duplicate node children in grids.
        upperTotalAfterBonus2.setText(String.valueOf(upperSectionPoints));
        scoreCard.add(upperTotalAfterBonus2, 1, 18);
        upperTotalAfterBonus2.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(upperTotalAfterBonus2, HPos.CENTER);
        
        if (score.getYahtzeeCount() >= 1) {
           yahtzeeBonus.setText(String.valueOf((score.getYahtzeeCount()) * 100)); 
        } else {
            yahtzeeBonus.setText("0");
        }
        scoreCard.add(yahtzeeBonus, 1, 16);
        yahtzeeBonus.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(yahtzeeBonus, HPos.CENTER);
        
        lowerSectionFinal.setText(String.valueOf(lowerSectionPoints));
        grandTotalText.setText(String.valueOf(grandTotal));
        scoreCard.add(lowerSectionFinal, 1, 17);
        scoreCard.add(grandTotalText, 1, 19);
        lowerSectionFinal.setStyle("-fx-font: 20 arial");
        grandTotalText.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(lowerSectionFinal, HPos.CENTER);
        GridPane.setHalignment(grandTotalText, HPos.CENTER);
        
        root.getChildren().remove(7);
        root.getChildren().remove(6);
        root.getChildren().remove(5);
        root.getChildren().remove(0);
        root.getChildren().remove(0);
        Text congrats = new Text("Congratulations!\nYou completed the\ngame!");
        congrats.setStyle("-fx-font: 40 arial");
        congrats.setLayoutX(400);
        congrats.setLayoutY(300);
        root.getChildren().add(congrats);
    }
    
}
