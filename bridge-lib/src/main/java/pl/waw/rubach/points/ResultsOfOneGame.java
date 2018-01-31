package pl.waw.rubach.points;

public class ResultsOfOneGame {

    /**
     * number of  Goren (PC) points of both hands
     * user had to imput
     * (in future could be astimatet from biding part not exactly)
     */
    private float pointsInBothHands;


    /**
     * biding height (you shoud take 6+ this triks to win game
     * from other part of aplication - results of biding part or user imput
     */
    private int gameLevel;

    /**
     * point achved by playng contract with some additiona or lacking triks (calculated from normal bridge scoring)
     * (could be also calculated by program in future)
     */
    private int pointsForContract;

    /**
     * diference betwenn assumpted result from ExpectedResults table and point reach playng contract (pointsForContract)
     */
    private int pointDifferent;

    /**
     * Number of Imp Point
     * if 0 is equal, if -1 is one less etc ...
     */
    private int results;

    public ResultsOfOneGame(float pointsInBothHands, int pointsForContract, boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        this.pointsInBothHands = pointsInBothHands;
        this.pointsForContract = pointsForContract;
        int expectedPoints;
        if(pointsInBothHands == 20)
        { if(fitInOlderColorWe) {
            expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, true, auctionAssumptionWe, auctionAssumptionThey);
        }  else if(fitInOlderColorThey) {
            expectedPoints = - ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, true, auctionAssumptionWe, auctionAssumptionThey);
        } else expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, false, auctionAssumptionWe, auctionAssumptionThey);

        }
        else {
            expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, fitInOlderColorWe, auctionAssumptionWe, auctionAssumptionThey);
        }
        if (expectedPoints<=pointsForContract) this.pointDifferent = pointsForContract - expectedPoints;
        else this.pointDifferent = expectedPoints - pointsForContract;

        if(pointDifferent<0) throw new PointsDiferentLessThenZeroException("Róznica punktow nie może być ujemna - bład programu chyba");
        int resutl = ImpTable.getInstance().getPoints(pointDifferent);

        if (expectedPoints<=pointsForContract) this.results = resutl;
        else this.results = -resutl;
    }



    public float getPointsInBothHands() {
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

    public int getPointsForContract() {
        return pointsForContract;
    }

    public void setPointsForContract(int pointsForContract) {
        this.pointsForContract = pointsForContract;
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
