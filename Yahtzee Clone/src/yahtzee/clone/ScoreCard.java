package yahtzee.clone;

import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class ScoreCard {
    private GridPane card;

    //Create the score card GridPane. In constructor b/c it is only needed once per player.
    public ScoreCard() {
        card = new GridPane();
        card.setMinSize(300, 700);
        card.setLayoutX(50);
        card.setLayoutY(50);
        card.setGridLinesVisible(true);
        card.setStyle("-fx-background-color: #ffffff");
        
        //Make the grid fixed with two columns
        ColumnConstraints c1 = new ColumnConstraints(); //column 1
        ColumnConstraints c2 = new ColumnConstraints(); //column 2
        c1.setPercentWidth(75);
        c2.setPercentWidth(25);
        card.getColumnConstraints().addAll(c1, c2);
        
        //Make the grid fixed with 20 rows
        int numRows = 20;
        for (int i = 0; i < numRows; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100/numRows);
            card.getRowConstraints().add(rc);
        }
        
        Text aces = new Text("ACES");
        aces.setStyle("-fx-font: 20 arial");
        card.add(aces, 0, 0);
        GridPane.setHalignment(aces, HPos.CENTER);
        
        Text twos = new Text("TWOS");
        twos.setStyle("-fx-font: 20 arial");
        card.add(twos, 0, 1);
        GridPane.setHalignment(twos, HPos.CENTER);
        
        Text threes = new Text("THREES");
        threes.setStyle("-fx-font: 20 arial");
        card.add(threes, 0, 2);
        GridPane.setHalignment(threes, HPos.CENTER);
        
        Text fours = new Text("FOURS");
        fours.setStyle("-fx-font: 20 arial");
        card.add(fours, 0, 3);
        GridPane.setHalignment(fours, HPos.CENTER);
        
        Text fives = new Text("FIVES");
        fives.setStyle("-fx-font: 20 arial");
        card.add(fives, 0, 4);
        GridPane.setHalignment(fives, HPos.CENTER);
        
        Text sixes = new Text("SIXES");
        sixes.setStyle("-fx-font: 20 arial");
        card.add(sixes, 0, 5);
        GridPane.setHalignment(sixes, HPos.CENTER);
        
        Text total1 = new Text("Total Score");
        total1.setStyle("-fx-font: 20 arial");
        card.add(total1, 0, 6);
        GridPane.setHalignment(total1, HPos.CENTER);
        
        Text bonus = new Text("Bonus if total score >= 63");
        bonus.setStyle("-fx-font: 18 arial");
        card.add(bonus, 0, 7);
        GridPane.setHalignment(bonus, HPos.CENTER);
        
        Text total2 = new Text("Total");
        total2.setStyle("-fx-font: 20 arial");
        card.add(total2, 0, 8);
        GridPane.setHalignment(total2, HPos.CENTER);
        
        Text threeAKind = new Text("3 of a Kind");
        threeAKind.setStyle("-fx-font: 20 arial");
        card.add(threeAKind, 0, 9);
        GridPane.setHalignment(threeAKind, HPos.CENTER);
        
        Text fourAKind = new Text("4 of a Kind");
        fourAKind.setStyle("-fx-font: 20 arial");
        card.add(fourAKind, 0, 10);
        GridPane.setHalignment(fourAKind, HPos.CENTER);
        
        Text fullHouse = new Text("Full House");
        fullHouse.setStyle("-fx-font: 20 arial");
        card.add(fullHouse, 0, 11);
        GridPane.setHalignment(fullHouse, HPos.CENTER);
        
        Text smallStraight = new Text("Small Straight");
        smallStraight.setStyle("-fx-font: 20 arial");
        card.add(smallStraight, 0, 12);
        GridPane.setHalignment(smallStraight, HPos.CENTER);
        
        Text largeStraight = new Text("Large Straight");
        largeStraight.setStyle("-fx-font: 20 arial");
        card.add(largeStraight, 0, 13);
        GridPane.setHalignment(largeStraight, HPos.CENTER);
        
        Text yahtzee = new Text("YAHTZEE");
        yahtzee.setStyle("-fx-font: 20 arial");
        card.add(yahtzee, 0, 14);
        GridPane.setHalignment(yahtzee, HPos.CENTER);
        
        Text chance = new Text("Chance");
        chance.setStyle("-fx-font: 20 arial");
        card.add(chance, 0, 15);
        GridPane.setHalignment(chance, HPos.CENTER);
        
        Text yahtzeeBonus = new Text("YAHTZEE BONUS");
        yahtzeeBonus.setStyle("-fx-font: 20 arial");
        card.add(yahtzeeBonus, 0, 16);
        GridPane.setHalignment(yahtzeeBonus, HPos.CENTER);
        
        Text lowerTotal = new Text("Total of Lower Section");
        lowerTotal.setStyle("-fx-font: 20 arial");
        card.add(lowerTotal, 0, 17);
        GridPane.setHalignment(lowerTotal, HPos.CENTER);
        
        Text upperTotal = new Text("Total of Upper Section");
        upperTotal.setStyle("-fx-font: 20 arial");
        card.add(upperTotal, 0, 18);
        GridPane.setHalignment(upperTotal, HPos.CENTER);
        
        Text grandTotal = new Text("GRAND TOTAL");
        grandTotal.setStyle("-fx-font: 20 arial");
        card.add(grandTotal, 0, 19);
        GridPane.setHalignment(grandTotal, HPos.CENTER);
    }

    public GridPane getCard() {
        return card;
    }
}
