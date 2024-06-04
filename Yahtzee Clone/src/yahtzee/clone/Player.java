package yahtzee.clone;

public class Player {
    private int id;
    private String name;
    private ScoreCard scoreCard;

    public Player (int id, String name) {
        this.id = id;
        this.name = name.isEmpty() ? name : "Player " + id;
        scoreCard = new ScoreCard();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ScoreCard getScoreCard() {
        return scoreCard;
    }
}
