package yahtzee.clone;

public class Player {
    private int id;
    private String name;
    private ScoreCard scoreCard;
    private boolean theirTurn = false;
    private Score score;

    public Player (int id, String name) {
        this.id = id;
        this.name = name.isEmpty() ? "Player " + id : name;
        this.scoreCard = null;
        this.score = new Score();
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

    public void setScoreCard(ScoreCard scoreCard) {
        this.scoreCard = scoreCard;
    }

    public boolean getTheirTurn() {
        return theirTurn;
    }

    public void setTheirTurn(boolean theirTurn) {
        this.theirTurn = theirTurn;
    }

    public Score getScore() {
        return score;
    }
}
