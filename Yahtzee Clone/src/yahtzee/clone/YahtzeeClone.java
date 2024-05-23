package yahtzee.clone;

import javafx.application.Application;
import javafx.stage.Stage;

public class YahtzeeClone extends Application {
    
    GameUI UI = GameUI.instance();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        UI.displayStartScreen(primaryStage);
    }    
}
