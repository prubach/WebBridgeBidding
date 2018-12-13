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
    private int contractLevel;

    /**
     * color atutowy
     */
    private String contractSuit;

    /**
     * numnber of tricks taken in game
     */
    private int numberOfTricskTaken;

    /**
     * double =2, redouble -4, nothing =1
     */
    private int nothingDoubleRedoube;
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
    private int results;// = 0;
    //pyt dlaczego tak?  że niżej a nie tu

    public ResultsOfOneGame(float pointsInBothHands, int pointsForContract, boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        this.results = 0;
        this.pointsInBothHands = pointsInBothHands;
        this.pointsForContract = pointsForContract;
        int expectedPoints;
        if (pointsInBothHands == 20) {
            if (fitInOlderColorWe) {
                expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, true, auctionAssumptionWe, auctionAssumptionThey);
            } else if (fitInOlderColorThey) {
                expectedPoints = -ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, true, auctionAssumptionWe, auctionAssumptionThey);
            } else
                expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, false, auctionAssumptionWe, auctionAssumptionThey);

        } else if (pointsInBothHands < 20) {
            expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, fitInOlderColorThey, auctionAssumptionWe, auctionAssumptionThey);
        } else {
            expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, fitInOlderColorWe, auctionAssumptionWe, auctionAssumptionThey);
        }
        if (expectedPoints <= pointsForContract) this.pointDifferent = pointsForContract - expectedPoints;
        else this.pointDifferent = expectedPoints - pointsForContract;

        if (pointDifferent < 0)
            throw new PointsDiferentLessThenZeroException();
        int resutl = ImpTable.getInstance().getPoints(pointDifferent);

        if (expectedPoints <= pointsForContract) this.results = resutl;
        else this.results = -resutl;
    }

    public ResultsOfOneGame(float pointsInBothHands, int contractLevel,int numberOfTrickTaken, String contractSuit,  boolean doubleGame, boolean redoubleGame, boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {
        this.pointsInBothHands = pointsInBothHands;
        this.contractLevel = contractLevel;
        this.contractSuit= contractSuit;
        this.numberOfTricskTaken=numberOfTrickTaken;
      //  if( doubleGame)  this.nothingDoubleRedoube = 2;
      //  if(redoubleGame)  this.nothingDoubleRedoube=4;  this.nothingDoubleRedoube=1;


        boolean asumption = auctionAssumptionWe;
        PointsForContract obj =new  PointsForContract(contractLevel,numberOfTrickTaken, contractSuit, doubleGame,redoubleGame, asumption);
        this.pointsForContract = obj.getCalculatedPointsForContract();
       //pyt dlaczego tak? Chciałam uruchomić konstruktor ale on tam na końcu zeruje wynik? dlaczego?
        this.results = new ResultsOfOneGame(pointsInBothHands,obj.getCalculatedPointsForContract(),auctionAssumptionWe,auctionAssumptionThey,fitInOlderColorWe,fitInOlderColorThey).getResults();

    }


    public float getPointsInBothHands() {
        return pointsInBothHands;
    }

    public void setPointsInBothHands(int pointsInBothHands) {
        this.pointsInBothHands = pointsInBothHands;
    }

    public int getContractLevel() {
        return contractLevel;
    }

    public void setContractLevel(int contractLevel) {
        this.contractLevel = contractLevel;
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

    public void setPointsInBothHands(float pointsInBothHands) {
        this.pointsInBothHands = pointsInBothHands;
    }

    public String getContractSuit() {
        return contractSuit;
    }

    public void setContractSuit(String contractSuit) {
        this.contractSuit = contractSuit;
    }

    public int getNumberOfTricskTaken() {
        return numberOfTricskTaken;
    }

    public void setNumberOfTricskTaken(int numberOfTricskTaken) {
        this.numberOfTricskTaken = numberOfTricskTaken;
    }

    public int getNothingDoubleRedoube() {
        return nothingDoubleRedoube;
    }

    public void setNothingDoubleRedoube(int nothingDoubleRedoube) {
        this.nothingDoubleRedoube = nothingDoubleRedoube;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public static void main(String[] args) {

        try {
            ResultsOfOneGame roog = new ResultsOfOneGame(20,3,9,"nt",false,false,false,false,false,false);


            System.out.println("Końcowy wynik liczony od podstaw jest: " + roog.getResults() + " \n");


        } catch (BridgeException e) {
            e.printStackTrace();
        }

    }


}
