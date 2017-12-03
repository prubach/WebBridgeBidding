package pl.waw.rubach.pointsCounting;

public class ResultsOfOneGame {

    /**number of  Goren (PC) points of both hands
     *  user had to imput
     *  (in future could be astimatet from biding part not exactly)
     */
    private int pointsInBothHands;


    /**biding height (you shoud take 6+ this triks to win game
     * from other part of aplication - results of biding part or user imput
    */
    private int gameLevel;

    /**
     * point achved by playng contract with some additiona or lacking triks (calculated from normal bridge notation)
     * (could be also calculated by program in future)
     */
    private int pointsOfContract;

    /**diference betwenn assumpted result from ExpectedResults table and point reach playng contract (pointsOfContract)
     */
    private int pointDifferent;

    /**
     * Number of Imp Point
    if 0 is equal, if -1 is one less etc ...
    */
    private int results;

    ResultsOfOneGame(int pointsInBothHands, int pointsOfContract){
        this.pointsInBothHands=pointsInBothHands;
        this.pointsOfContract = pointsOfContract;
        this.pointDifferent= pointsOfContract - new ExpectedResults(pointsInBothHands).getResults();
        this.results = new ImpTable(pointDifferent).getResults();

    }

    public int getPointsInBothHands() {
        return pointsInBothHands;
    }

    public void setPointsInBothHands(int pointsInBothHands) {
        this.pointsInBothHands = pointsInBothHands;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getPointsOfContract() {
        return pointsOfContract;
    }

    public void setPointsOfContract(int pointsOfContract) {
        this.pointsOfContract = pointsOfContract;
    }

    public int getPointDifferent() {
        return pointDifferent;
    }

    public void setPointDifferent(int pointDifferent) {
        this.pointDifferent = pointDifferent;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }


}
