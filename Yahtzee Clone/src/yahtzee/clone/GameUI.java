package yahtzee.clone;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameUI {
    
    Game game = Game.instance();

    //UI global variables
    private Button holdDie1, holdDie2, holdDie3, holdDie4, holdDie5; //buttons to hold or roll dice
    private static Group holdDieButtonGroup; //buttons that go underneath a die to indicate if it's being held or rolled
    private Text num1, num2, num3, num4, num5; //to display dice values
    private Group root; //the root node to display UI at start screens
    private Group root2; //the root node to display UI during the game
    private Group die1, die2, die3, die4, die5; //groups that will contain rectangle and value for a die
    private static Group diceGroup; //a group that will contain all five dice
    private static GridPane scoreCard; //a grid that will contain the scorecard
    private static Group operationButtonGroup = new Group(); //a group for buttons to roll dice and access scorecard
    private Text rollNum, turnNum; //display roll count and turn number to user
    private static Image background = new Image("background.jpg", 800, 800, false, true);
    private static ImageView backgroundView = new ImageView(background);

    
    //Game UI should also be a singleton
    private static GameUI uniqueInstance = null;
    private GameUI() {}

    public static GameUI instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GameUI();
        }
        return uniqueInstance;
    }

    //Methods pertaining to the start screens.
    public void displayStartScreen(Stage primaryStage) {
        Button startbtn = new Button();
        startbtn.setText("Start Game");
        startbtn.setLayoutX(300);
        startbtn.setLayoutY(400);
        startbtn.setMinSize(100, 50);
        startbtn.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        startbtn.setOnAction(event -> game.startGame(primaryStage, this));
        
        Text title = new Text("Yahtzee");
        title.setStyle("-fx-font: 120 arial");
        title.setLayoutX(175);
        title.setLayoutY(150);
        title.setFill(Color.WHITE);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);
        
        Text summary = new Text("The famous 5-dice game");
        summary.setStyle("-fx-font: 40 arial");
        summary.setLayoutX(180);
        summary.setLayoutY(200);
        summary.setFill(Color.WHITE);
        summary.setStroke(Color.BLACK);
        summary.setStrokeWidth(1);
        
        Button about = new Button("About");
        about.setMinSize(194,50);
        about.setLayoutX(300);
        about.setLayoutY(475);
        about.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        about.setOnAction(event -> displayAbout(primaryStage));
        
        Text author = new Text("Programmed by Julia D.");
        author.setStyle("-fx-font: 20 arial");
        author.setLayoutX(5);
        author.setLayoutY(775);
        author.setFill(Color.WHITE);

        Image die1 = new Image("Die 1.png", 100, 100, true, true);
        ImageView viewDie1 = new ImageView(die1);
        viewDie1.setX(50);
        viewDie1.setY(50);

        Image die2 = new Image("Die 2.png", 125, 125, true, true);
        ImageView viewDie2 = new ImageView(die2);
        viewDie2.setX(65);
        viewDie2.setY(600);

        Image die3 = new Image("Die 3.png", 150, 150, true, true);
        ImageView viewDie3 = new ImageView(die3);
        viewDie3.setX(600);
        viewDie3.setY(600);

        Group diceGroup = new Group(viewDie1, viewDie2, viewDie3);
        
        Group objects = new Group();
        objects.getChildren().addAll(startbtn, title, summary, about, author, diceGroup);
        
        root = new Group(backgroundView, objects);
        Scene scene = new Scene(root, 800, 800);
        
        primaryStage.setTitle("Yahtzee");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void displayAbout(Stage primaryStage) {
        Text title = new Text("Yahtzee");
        title.setStyle("-fx-font: 120 arial");
        title.setLayoutX(175);
        title.setLayoutY(150);
        title.setFill(Color.WHITE);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);
        
        Text summary = new Text("The famous 5-dice game");
        summary.setStyle("-fx-font: 40 arial");
        summary.setLayoutX(180);
        summary.setLayoutY(200);
        summary.setFill(Color.WHITE);
        summary.setStroke(Color.BLACK);
        summary.setStrokeWidth(1);
        
        Text author = new Text("Programmed by Julia D.");
        author.setStyle("-fx-font: 20 arial");
        author.setLayoutX(5);
        author.setLayoutY(775);
        author.setFill(Color.WHITE);
        
        Text about = new Text();
        about.setLayoutX(10);
        about.setLayoutY(230);
        about.setStyle("-fx-font: 20 arial");
        about.setText("This project is a recreation of the classic game known as Yahtzee."
                + " The game is entirely\nprogrammed with Java and JavaFX."
                + " The game has also been designed with the\nassumption that the player"
                + " already knows how to play Yahtzee. If you don't know"
                + " how\nto play the game, the rules can be found online or on the GitHub repo."
                + "\n\nThis is also not the programmer's first attempt at recreating"
                + " Yahtzee, as she tried to do\nit when she only knew the bare bones"
                + " of the Java programming langauge. This first\nedition was never"
                + " posted anywhere; it was simply terrible code: buggy, and easy for\n"
                + "the player to cheat. Now three years later, with the additional knowledge she has"
                + "\nlearned from her classes, the programmer revamped one of her very first projects."
                + " Now\nit is not easy to cheat, the scoring logic has improved,"
                + " and the game has an actual GUI.");
        about.setFill(Color.WHITE);
        
        Button back = new Button("Back");
        back.setMinSize(194, 50);
        back.setLayoutX(300);
        back.setLayoutY(475);
        back.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        back.setOnAction(event -> displayStartScreen(primaryStage));

        Image die1 = new Image("Die 1.png", 100, 100, true, true);
        ImageView viewDie1 = new ImageView(die1);
        viewDie1.setX(50);
        viewDie1.setY(50);

        Image die2 = new Image("Die 2.png", 125, 125, true, true);
        ImageView viewDie2 = new ImageView(die2);
        viewDie2.setX(65);
        viewDie2.setY(600);

        Image die3 = new Image("Die 3.png", 150, 150, true, true);
        ImageView viewDie3 = new ImageView(die3);
        viewDie3.setX(600);
        viewDie3.setY(600);

        Group diceGroup = new Group(viewDie1, viewDie2, viewDie3);
        
        root = new Group(backgroundView, title, summary, author, back, about, diceGroup);
        
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        
    }

    //Main method to create UI for gameplay. Should only be called once.
    public Scene createGameUI() {
        createDice();
        createDieButtons();
        createRollDiceButton();
        createScoreCard();
        createScorePointsButton();

        int rollNumber = game.getRollNumber();
        int turnNumber = game.getTurnNumber();
        rollNum = new Text("Roll number: " + rollNumber);
        rollNum.setStyle("-fx-font: 20 arial");
        rollNum.setLayoutX(450);
        rollNum.setLayoutY(40);
        rollNum.setFill(Color.WHITE);
        turnNum = new Text("Turn number: " + turnNumber);
        turnNum.setStyle("-fx-font: 20 arial");
        turnNum.setLayoutX(450);
        turnNum.setLayoutY(80);
        turnNum.setFill(Color.WHITE);

        Text gameTitle = new Text("YAHTZEE");
        gameTitle.setStyle("-fx-font: 40 arial");
        gameTitle.setLayoutX(100);
        gameTitle.setLayoutY(40);
        gameTitle.setFill(Color.WHITE);
        
        Text author = new Text("Programmed by Julia D.");
        author.setStyle("-fx-font: 20 arial");
        author.setLayoutX(5);
        author.setLayoutY(775);
        author.setFill(Color.WHITE);

        root2 = new Group(backgroundView, diceGroup, holdDieButtonGroup, scoreCard, author, gameTitle, rollNum, turnNum, operationButtonGroup);
        Scene gameboard = new Scene(root2, 800, 800);
        return gameboard;
    }

    //Method to create Dice UI
    private void createDice() {
        Rectangle r1, r2, r3, r4, r5; //dice borders. won't change throughout game
        
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

    //Method to create the buttons that holds/unholds individual dice
    private void createDieButtons() {
        holdDie1 = new Button("Hold");
        holdDie1.setId("1");
        holdDie1.setOnAction(event -> updateDie(1, 0));
        holdDie1.setMaxSize(100, 25);
        holdDie1.setLayoutX(680);
        holdDie1.setLayoutY(145);
        holdDie1.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        
        holdDie2 = new Button("Hold");
        holdDie2.setId("2");
        holdDie2.setOnAction(event -> updateDie(2, 1));
        holdDie2.setMaxSize(100, 25);
        holdDie2.setLayoutX(680);
        holdDie2.setLayoutY(295);
        holdDie2.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        
        holdDie3 = new Button("Hold");
        holdDie3.setId("3");
        holdDie3.setOnAction(event -> updateDie(3, 2));
        holdDie3.setMaxSize(100, 25);
        holdDie3.setLayoutX(680);
        holdDie3.setLayoutY(445);
        holdDie3.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        
        holdDie4 = new Button("Hold");
        holdDie4.setId("4");
        holdDie4.setOnAction(event -> updateDie(4, 3));
        holdDie4.setMaxSize(100, 25);
        holdDie4.setLayoutX(680);
        holdDie4.setLayoutY(595);
        holdDie4.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");

        holdDie5 = new Button("Hold");
        holdDie5.setId("5");
        holdDie5.setOnAction(event -> updateDie(5, 4));
        holdDie5.setMaxSize(100, 25);
        holdDie5.setLayoutX(680);
        holdDie5.setLayoutY(745);
        holdDie5.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        
        holdDieButtonGroup = new Group(holdDie1, holdDie2, holdDie3, holdDie4, holdDie5);
    }

    //Updates the hold die button UI
    private void updateDie(int buttonID, int dieID) {
        //check to ensure that the user has rolled at least once, but also not after thrid roll
        if (game.getRollNumber() <= 0 || game.getRollNumber() >= 3) return;
        
        game.getDice().get(dieID).setToRoll(!(game.getDice().get(dieID).getToRoll()));
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

    //Creates the button to roll the dice not held. Should only be called once.
    private void createRollDiceButton() {
        Button rollDice = new Button("Roll Dice");
        rollDice.setLayoutX(425);
        rollDice.setLayoutY(450);
        rollDice.setMinSize(150, 100);
        rollDice.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        rollDice.setWrapText(true);
        rollDice.setOnAction(event -> rollDice());
        
        operationButtonGroup.getChildren().add(rollDice);
    }

    //Method to update UI upon player rolling the dice.
    private void rollDice() {
        //Need to check if player hasn't rolled 3 times for this turn.
        //Or if the player has all the dice held to prevent wasting a turn.
        boolean allDiceHeld = game.checkAllDiceHeld();
        if (game.getRollNumber() == 3 || allDiceHeld) return;
        
        game.rollDice();
        
        //Update UI
        num1.setText(String.valueOf(game.getDice().get(0).getValue()));
        num2.setText(String.valueOf(game.getDice().get(1).getValue()));
        num3.setText(String.valueOf(game.getDice().get(2).getValue()));
        num4.setText(String.valueOf(game.getDice().get(3).getValue()));
        num5.setText(String.valueOf(game.getDice().get(4).getValue()));
        rollNum.setText("Roll number: " + game.getRollNumber());
    }

    private void createScoreCard() {
        scoreCard = new GridPane();
        scoreCard.setMinSize(300, 700);
        scoreCard.setLayoutX(50);
        scoreCard.setLayoutY(50);
        scoreCard.setGridLinesVisible(true);
        scoreCard.setStyle("-fx-background-color: #ffffff");
        
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
        bonus.setStyle("-fx-font: 18 arial");
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
        score.setStyle("-fx-font: 22.5 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        score.setWrapText(true);
        score.setOnAction(event -> displayScoringOptions());
        
        operationButtonGroup.getChildren().add(score);
    }

    //Display score buttons next to each category on scorecard
    private void displayScoringOptions() {
        //Check to see if the user has rolled at least once
        if (game.getRollNumber() < 1) return;
        
        root2.getChildren().remove(8);
        Group scoreButtonGroup = new Group();
        
        //Upper Section Score Buttons
        for (int i = 0; i < 6; i++) {
            Button scoreOption = new Button("Score");
            scoreOption.setLayoutX(375);
            scoreOption.setLayoutY(55 + (35 * i));
            scoreOption.setMinSize(50, 25);
            scoreOption.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
            scoreOption.setId(String.valueOf(i)); //IDs [0-5]
            scoreOption.setOnAction(event -> game.endTurn(Integer.valueOf(scoreOption.getId())));
            scoreButtonGroup.getChildren().add(scoreOption);
        }
        //Lower Section Score Buttons
        for (int i = 0; i < 7; i++) {
            Button scoreOption = new Button("Score");
            scoreOption.setLayoutX(375);
            scoreOption.setLayoutY(370 + (35 * i));
            scoreOption.setMinSize(50, 25);
            scoreOption.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
            scoreOption.setId(String.valueOf(i + 6)); //IDs [6-12]
            scoreOption.setOnAction(event -> game.endTurn(Integer.valueOf(scoreOption.getId())));
            scoreButtonGroup.getChildren().add(scoreOption);
        }
        
        Button back = new Button("Back");
        back.setLayoutX(450);
        back.setLayoutY(300);
        back.setMinSize(150, 100);
        back.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        back.setOnAction(event -> displayOperationButtons());
        
        root2.getChildren().add(scoreButtonGroup);
        root2.getChildren().add(back);
    }

    //Display roll dice and score points buttons again
    private void displayOperationButtons() {
        //Remove category score buttons and back button
        root2.getChildren().remove(9);
        root2.getChildren().remove(8);
        //Add back operation buttons
        root2.getChildren().add(operationButtonGroup);
    }

    //Method to write in points on scorecard
    public void displayEndTurnScore(int points, int scoreOptionID) {
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
    }

    //Reset UI for new turn
    public void newTurnReset() {
        holdDie1.setText("Hold");
        holdDie2.setText("Hold");
        holdDie3.setText("Hold");
        holdDie4.setText("Hold");
        holdDie5.setText("Hold");
        rollNum.setText("Roll number: " + game.getRollNumber());
        turnNum.setText("Turn number: " + game.getTurnNumber());
        
        //Remove the score buttons and add back the operation buttons
        root2.getChildren().remove(9);
        root2.getChildren().remove(8);
        root2.getChildren().add(operationButtonGroup);
    }

    //Method to display UI at end of game. Method should only be called once.
    public void endGame(int lowerSectionPoints, int upperSectionPoints, int grandTotal, Score score) {
        Text topBonus = new Text(), yahtzeeBonus = new Text(), upperTotalB4Bonus = new Text(), upperTotalAfterBonus = new Text(), 
            lowerSectionFinal = new Text(), grandTotalText = new Text(), upperTotalAfterBonus2 = new Text();

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
        
        root2.getChildren().remove(8);
        root2.getChildren().remove(7);
        root2.getChildren().remove(6);
        root2.getChildren().remove(1);
        root2.getChildren().remove(1);
        Text congrats = new Text("Congratulations!\nYou completed the\ngame!");
        congrats.setStyle("-fx-font: 40 arial");
        congrats.setLayoutX(400);
        congrats.setLayoutY(300);
        congrats.setFill(Color.WHITE);
        root2.getChildren().add(congrats);
    }
}
