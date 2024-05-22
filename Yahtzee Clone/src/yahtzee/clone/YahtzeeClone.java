package yahtzee.clone;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class YahtzeeClone extends Application {
    
    Game game = Game.instance();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        displayStartScreen(primaryStage);
    }

    public void displayStartScreen(Stage primaryStage) {
        Button startbtn = new Button();
        startbtn.setText("Start Game");
        startbtn.setLayoutX(300);
        startbtn.setLayoutY(400);
        startbtn.setMinSize(100, 50);
        startbtn.setStyle("-fx-font: 30 arial");
        startbtn.setOnAction(event -> game.startGame(primaryStage));
        
        Text title = new Text("Yahtzee");
        title.setStyle("-fx-font: 120 arial");
        title.setLayoutX(175);
        title.setLayoutY(150);
        
        Text summary = new Text("The famous 5-dice game");
        summary.setStyle("-fx-font: 40 arial");
        summary.setLayoutX(180);
        summary.setLayoutY(200);
        
        Button about = new Button("About");
        about.setMinSize(194,50);
        about.setLayoutX(300);
        about.setLayoutY(475);
        about.setStyle("-fx-font: 30 arial");
        about.setOnAction(event -> displayAbout(primaryStage));
        
        Text author = new Text("Programmed by Julia D.");
        author.setStyle("-fx-font: 20 arial");
        author.setLayoutX(5);
        author.setLayoutY(775);
        
        Group root = new Group();
        root.getChildren().addAll(startbtn, title, summary, about, author);
        
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
        
        Text summary = new Text("The famous 5-dice game");
        summary.setStyle("-fx-font: 40 arial");
        summary.setLayoutX(180);
        summary.setLayoutY(200);
        
        Text author = new Text("Programmed by Julia D.");
        author.setStyle("-fx-font: 20 arial");
        author.setLayoutX(5);
        author.setLayoutY(775);
        
        Text about = new Text();
        about.setLayoutX(10);
        about.setLayoutY(230);
        about.setStyle("-fx-font: 20 arial");
        about.setText("This project is a recreation of the classic game known as Yahtzee."
                + " The game is entirely\nprogrammed with Java and JavaFX."
                + " The game has also been designed with the\nassumption that the player"
                + " already knows how to play Yahtzee. If you don't know"
                + " how\nto play the game, the rules can be found online."
                + "\n\nThis is also not the programmer's first attempt at recreating"
                + " Yahtzee, as she tried to do\nit when she only knew the bare bones"
                + " of the Java programming langauge. This first\nedition was never"
                + " posted anywhere; it was simply terrible code: buggy, and easy for\n"
                + "the player to cheat. Now three years later, with the additional knowledge she has"
                + "\nlearned from her classes, the programmer revamped one of her very first projects."
                + " Now\nit is not easy to cheat, the scoring logic has improved,"
                + " and the game has an actual GUI.");
        
        Button back = new Button("Back");
        back.setMinSize(194, 50);
        back.setLayoutX(300);
        back.setLayoutY(475);
        back.setStyle("-fx-font: 30 arial");
        back.setOnAction(event -> displayStartScreen(primaryStage));
        
        Group root = new Group();
        root.getChildren().addAll(title, summary, author, back, about);
        
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        
    }
    
}
