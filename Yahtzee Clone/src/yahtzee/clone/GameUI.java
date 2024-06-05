package yahtzee.clone;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameUI {
    
    Game game = Game.instance();

    //UI global variables during start screens
    static int playerNum = 1;
    static int endGameDisplayIndex = 0;
    private static Image background;
    private static ImageView backgroundView;
    private static Text author;
    private static Image diePic1;
    private static ImageView diePic1View;
    private static Image diePic2;
    private static ImageView diePic2View;
    private static Image diePic3;
    private static ImageView diePic3View;
    private static Text title;
    private static Text summary;

    //UI global variables during gameplay
    private Button holdDie1, holdDie2, holdDie3, holdDie4, holdDie5; //buttons to hold or roll dice
    private static Group holdDieButtonGroup; //buttons that go underneath a die to indicate if it's being held or rolled
    private Text num1, num2, num3, num4, num5; //to display dice values
    private Group root; //the root node to display UI at start screens
    private Group root2; //the root node to display UI during the game
    private Group die1, die2, die3, die4, die5; //groups that will contain rectangle and value for a die
    private static Group diceGroup; //a group that will contain all five dice
    private static Group operationButtonGroup = new Group(); //a group for buttons to roll dice and access scorecard
    private Text rollNum, turnNum; //display roll count and turn number to user
    private static ArrayList<Integer> grandTotals = new ArrayList<Integer>();
    
    //Game UI should also be a singleton
    private static GameUI uniqueInstance = null;
    private GameUI() {
        background = new Image("background.jpg", 800, 800, false, true);
        backgroundView = new ImageView(background);
        author = new Text("Programmed by Julia D.");
        author.setStyle("-fx-font: 20 arial");
        author.setLayoutX(5);
        author.setLayoutY(775);
        author.setFill(Color.WHITE);

        diePic1 = new Image("Die 1.png", 100, 100, true, true);
        diePic1View = new ImageView(diePic1);
        diePic1View.setX(50);
        diePic1View.setY(50);

        diePic2 = new Image("Die 2.png", 125, 125, true, true);
        diePic2View = new ImageView(diePic2);
        diePic2View.setX(65);
        diePic2View.setY(600);

        diePic3 = new Image("Die 3.png", 150, 150, true, true);
        diePic3View = new ImageView(diePic3);
        diePic3View.setX(600);
        diePic3View.setY(600);

        title = new Text("Yahtzee");
        title.setStyle("-fx-font: 120 arial");
        title.setLayoutX(175);
        title.setLayoutY(150);
        title.setFill(Color.WHITE);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);

        summary = new Text("The famous 5-dice game");
        summary.setStyle("-fx-font: 40 arial");
        summary.setLayoutX(180);
        summary.setLayoutY(200);
        summary.setFill(Color.WHITE);
        summary.setStroke(Color.BLACK);
        summary.setStrokeWidth(1);
    }

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
        startbtn.setLayoutY(330);
        startbtn.setMinSize(100, 50);
        startbtn.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        startbtn.setOnAction(event -> displayPlayerSelect(primaryStage));
        
        Button about = new Button("About");
        about.setMinSize(194,50);
        about.setLayoutX(300);
        about.setLayoutY(405);
        about.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        about.setOnAction(event -> displayAbout(primaryStage));

        Group diceGroup = new Group(diePic1View, diePic2View, diePic3View);
        
        Group objects = new Group();
        objects.getChildren().addAll(startbtn, title, summary, about, author, diceGroup);
        
        root = new Group(backgroundView, objects);
        Scene scene = new Scene(root, 800, 800);
        
        primaryStage.setTitle("Yahtzee");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void displayPlayerSelect(Stage primaryStage) {
        Text players = new Text("Select number of players");
        players.setStyle("-fx-font: 50 arial");
        players.setLayoutX(130);
        players.setLayoutY(250);
        players.setFill(Color.WHITE);
        players.setStroke(Color.BLACK);
        players.setStrokeWidth(1);

        Button playerbtn1 = new Button();
        playerbtn1.setText("One Player");
        playerbtn1.setLayoutX(150);
        playerbtn1.setLayoutY(300);
        playerbtn1.setMinSize(100, 50);
        playerbtn1.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        playerbtn1.setOnAction(event -> enterPlayerName(primaryStage, 1));

        Button playerbtn2 = new Button();
        playerbtn2.setText("Two Players");
        playerbtn2.setLayoutX(440);
        playerbtn2.setLayoutY(300);
        playerbtn2.setMinSize(100, 50);
        playerbtn2.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        playerbtn2.setOnAction(event -> enterPlayerName(primaryStage, 2));

        Button playerbtn3 = new Button();
        playerbtn3.setText("Three Players");
        playerbtn3.setLayoutX(150);
        playerbtn3.setLayoutY(400);
        playerbtn3.setMinSize(100, 50);
        playerbtn3.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        playerbtn3.setOnAction(event -> enterPlayerName(primaryStage, 3));

        Button playerbtn4 = new Button();
        playerbtn4.setText("Four Players");
        playerbtn4.setLayoutX(440);
        playerbtn4.setLayoutY(400);
        playerbtn4.setMinSize(100, 50);
        playerbtn4.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        playerbtn4.setOnAction(event -> enterPlayerName(primaryStage, 4));

        Button back = new Button();
        back.setText("Back");
        back.setLayoutX(335);
        back.setLayoutY(500);
        back.setMinSize(100, 50);
        back.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        back.setOnAction(event -> displayStartScreen(primaryStage));

        Group playerbtnGroup = new Group(playerbtn1, playerbtn2, playerbtn3, playerbtn4);
        Group diceGroup = new Group(diePic1View, diePic2View, diePic3View);
        Group objects = new Group(backgroundView, title, author, players, playerbtnGroup, back);

        root = new Group(objects, diceGroup);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
    }

    public void enterPlayerName(Stage primaryStage, int players) {
        Group diceGroup = new Group(diePic1View, diePic2View, diePic3View);
        Group objects = new Group(backgroundView, title, diceGroup, author);

        Text instructions = new Text("Please enter the name of player " + playerNum);
        instructions.setStyle("-fx-font: 40 arial");
        instructions.setLayoutX(100);
        instructions.setLayoutY(250);
        instructions.setFill(Color.WHITE);
        instructions.setStroke(Color.BLACK);
        instructions.setStrokeWidth(1);

        TextField nameField = new TextField();
        nameField.setLayoutX(300);
        nameField.setLayoutY(400);
        nameField.setMinHeight(10);
        nameField.setMinWidth(200);
        nameField.setStyle("-fx-background-color: #741315; -fx-text-fill: #ffffff");
        nameField.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                game.createPlayer(playerNum, nameField.getText());

                if (playerNum == players) {
                    startTheGame(primaryStage);
                }
                instructions.setText("Please enter the name of player " + ++playerNum);
                nameField.setText("");
            }
        });

        root = new Group(objects, diceGroup, nameField, instructions);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
    }
    
    public void displayAbout(Stage primaryStage) {
        Text about = new Text();
        about.setLayoutX(10);
        about.setLayoutY(240);
        about.setStyle("-fx-font: 30 arial");
        about.setText("This project is a recreation of the classic game known as\nYahtzee."
                + " The game is entirely programmed with Java and \nJavaFX."
                + " The game has also been designed with the\nassumption that the player"
                + " already knows how to play\nYahtzee. If you don't know"
                + " how to play the game, the rules\ncan be found online or on the GitHub repo:\nhttps://github.com/JooJules/Yahtzee_Clone");
        about.setFill(Color.WHITE);
        
        Button back = new Button("Back");
        back.setMinSize(194, 50);
        back.setLayoutX(300);
        back.setLayoutY(475);
        back.setStyle("-fx-font: 30 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        back.setOnAction(event -> displayStartScreen(primaryStage));

        Group diceGroup = new Group(diePic1View, diePic2View, diePic3View);
        
        root = new Group(backgroundView, title, summary, author, back, about, diceGroup);
        
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
    }

    //Method to actually start the game. EventHandlers, man.
    private void startTheGame(Stage primaryStage) {
        game.startGame(primaryStage, this);
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

        Text playerTurn = new Text(game.getPlayers().get(0).getName() + "\'s turn");
        playerTurn.setStyle("-fx-font: 40 arial");
        playerTurn.setLayoutX(100);
        playerTurn.setLayoutY(40);
        playerTurn.setFill(Color.WHITE);
        
        // Text author = new Text("Programmed by Julia D.");
        // author.setStyle("-fx-font: 20 arial");
        // author.setLayoutX(5);
        // author.setLayoutY(775);
        // author.setFill(Color.WHITE);

        //Player 1's scorecard is displayed since they will always go first.
        root2 = new Group(backgroundView, diceGroup, holdDieButtonGroup, 
        game.getPlayers().get(0).getScoreCard().getCard(), author, playerTurn, rollNum, turnNum, operationButtonGroup);
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

    //Create the score cards for each player
    private void createScoreCard() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            ScoreCard playerScoreCard = new ScoreCard();
            game.getPlayers().get(i).setScoreCard(playerScoreCard);
        }
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
    public void displayEndTurnScore(int points, int scoreOptionID, Player scoringPlayer) {
        Text pointsUI = new Text(String.valueOf(points));
        pointsUI.setStyle("-fx-font: 20 arial");
        
        switch (scoreOptionID) {
            case 0: //ones
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 0);
                break;
            case 1: //twos
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 1);
                break;
            case 2: //threes
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 2);
                break;
            case 3: //fours
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 3);
                break;
            case 4: //fives
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 4);
                break;
            case 5: //sixes
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 5);
                break;
            case 6: //three of a kind
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 9);
                break;
            case 7: //four of a kind
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 10);
                break;
            case 8: //full house
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 11);
                break;
            case 9: //small straight
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 12);
                break;
            case 10: //large straight
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 13);
                break;
            case 11: //yahtzee
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 14);
                break;
            case 12: //chance
                scoringPlayer.getScoreCard().getCard().add(pointsUI, 1, 15);
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

        //Find the player who rolls next
        Player playerToRoll = null;
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (game.getPlayers().get(i).getTheirTurn()) {
                playerToRoll = game.getPlayers().get(i);
                break;
            }
        }
        Text playerTurn = new Text(playerToRoll.getName() + "\'s turn");
        playerTurn.setStyle("-fx-font: 40 arial");
        playerTurn.setLayoutX(100);
        playerTurn.setLayoutY(40);
        playerTurn.setFill(Color.WHITE);
        
        //Remove the score buttons and add back the operation buttons. Update player's turn text and get their scorecard to appear.
        root2.getChildren().remove(9);
        root2.getChildren().remove(8);
        root2.getChildren().add(operationButtonGroup);
        root2.getChildren().set(5, playerTurn);
        root2.getChildren().set(3, playerToRoll.getScoreCard().getCard());
    }

    //Method to display bonus totals UI at the end of the game. Method should only be called once for each player.
    public void writeTotals(int lowerSectionPoints, int upperSectionPoints, int grandTotal, Player player) {
        Text topBonus = new Text(), yahtzeeBonus = new Text(), upperTotalB4Bonus = new Text(), upperTotalAfterBonus = new Text(), 
            lowerSectionFinal = new Text(), grandTotalText = new Text(), upperTotalAfterBonus2 = new Text();

        if (player.getScore().getTopBonus()) {
            upperTotalB4Bonus.setText(String.valueOf(upperSectionPoints - 35));
            topBonus.setText("35");
            player.getScoreCard().getCard().add(topBonus, 1, 7);
        } else {
            upperTotalB4Bonus.setText(String.valueOf(upperSectionPoints));
            topBonus.setText("0");
            player.getScoreCard().getCard().add(topBonus, 1, 7);
        }
        topBonus.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(topBonus, HPos.CENTER);
        
        upperTotalAfterBonus.setText(String.valueOf(upperSectionPoints));
        player.getScoreCard().getCard().add(upperTotalB4Bonus, 1, 6);
        player.getScoreCard().getCard().add(upperTotalAfterBonus, 1, 8);
        upperTotalB4Bonus.setStyle("-fx-font: 20 arial");
        upperTotalAfterBonus.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(upperTotalB4Bonus, HPos.CENTER);
        GridPane.setHalignment(upperTotalAfterBonus, HPos.CENTER);
        //complier doesn't like duplicate node children in grids.
        upperTotalAfterBonus2.setText(String.valueOf(upperSectionPoints));
        player.getScoreCard().getCard().add(upperTotalAfterBonus2, 1, 18);
        upperTotalAfterBonus2.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(upperTotalAfterBonus2, HPos.CENTER);
        
        if (player.getScore().getYahtzeeCount() >= 1) {
           yahtzeeBonus.setText(String.valueOf((player.getScore().getYahtzeeCount()) * 100)); 
        } else {
            yahtzeeBonus.setText("0");
        }
        player.getScoreCard().getCard().add(yahtzeeBonus, 1, 16);
        yahtzeeBonus.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(yahtzeeBonus, HPos.CENTER);
        
        lowerSectionFinal.setText(String.valueOf(lowerSectionPoints));
        grandTotalText.setText(String.valueOf(grandTotal));
        player.getScoreCard().getCard().add(lowerSectionFinal, 1, 17);
        player.getScoreCard().getCard().add(grandTotalText, 1, 19);
        lowerSectionFinal.setStyle("-fx-font: 20 arial");
        grandTotalText.setStyle("-fx-font: 20 arial");
        GridPane.setHalignment(lowerSectionFinal, HPos.CENTER);
        GridPane.setHalignment(grandTotalText, HPos.CENTER);

        grandTotals.add(grandTotal);
    }

    //Method to tell the players who the winner is.
    public void displayWinner() {
        //Find out who the winner is
        int max = 0;
        for (int i = 0; i < grandTotals.size(); i++) {
            if (grandTotals.get(i) > max) {
                max = grandTotals.get(i);
                endGameDisplayIndex = i;
            }
        }
        Player winningPlayer = game.getPlayers().get(endGameDisplayIndex);
        Text congrats = new Text();

        //Remove UI elements not necessary for end game.
        root2.getChildren().remove(8); //Roll dice and score points buttons
        root2.getChildren().remove(7); //Turn number
        root2.getChildren().remove(6); //Roll number
        root2.getChildren().remove(1); //The dice
        root2.getChildren().remove(1); //The hold/roll dice buttons

        //Add congratulations text to UI
        if (game.getPlayers().size() == 1) {
            congrats.setText("Congratulations!\nYou completed the\ngame!");
        } else {
            congrats.setText("Congratulations!\n" + winningPlayer.getName() + " won!");
        }
        congrats.setStyle("-fx-font: 40 arial");
        congrats.setLayoutX(400);
        congrats.setLayoutY(300);
        congrats.setFill(Color.WHITE);
        root2.getChildren().add(congrats);

        //Display winner player's score card first at the end
        Text cardOwner = new Text(winningPlayer.getName() + "\'s score");
        cardOwner.setStyle("-fx-font: 40 arial");
        cardOwner.setLayoutX(100);
        cardOwner.setLayoutY(40);
        cardOwner.setFill(Color.WHITE);
        root2.getChildren().set(1, game.getPlayers().get(endGameDisplayIndex).getScoreCard().getCard());
        root2.getChildren().set(3, cardOwner);

        Button navCards = new Button(">");
        navCards.setLayoutX(400);
        navCards.setLayoutY(700);
        navCards.setMinSize(20, 10);
        navCards.setStyle("-fx-font: 20 arial; -fx-border-width: 1; -fx-border-color: #000000; -fx-background-color: #741315; -fx-text-fill: #ffffff");
        navCards.setWrapText(true);
        navCards.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if ((endGameDisplayIndex + 1) == game.getPlayers().size()) {
                    endGameDisplayIndex = 0;
                    cardOwner.setText(game.getPlayers().get(endGameDisplayIndex).getName() + "\'s score");
                    root2.getChildren().set(1, game.getPlayers().get(endGameDisplayIndex).getScoreCard().getCard());
                    root2.getChildren().set(3, cardOwner);
                } else {
                    endGameDisplayIndex++;
                    cardOwner.setText(game.getPlayers().get(endGameDisplayIndex).getName() + "\'s score");
                    root2.getChildren().set(1, game.getPlayers().get(endGameDisplayIndex).getScoreCard().getCard());
                    root2.getChildren().set(3, cardOwner);
                }
            }
        });
        if (game.getPlayers().size() != 1) root2.getChildren().add(navCards);
    }
}
