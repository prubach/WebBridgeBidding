package pl.waw.rubach.points;

import pl.waw.rubach.points.bridgeExeption.BridgeException;
import pl.waw.rubach.points.bridgeExeption.InvalidNumberOfPointsException;
import pl.waw.rubach.points.bridgeExeption.PointsDiferentLessThenZeroException;

public class ResultsOfOneGame {

    /**
     * The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken to win.
     * in future from other part of aplication - results of biding part or user imput
     */
    private int contractLevel;
    /**
     * Cards suits [denomination or strain] that denotes the proposed trump suit or notrump.
     * Thus, there are five denominations – notrump, spades, hearts, diamonds and clubs.
     */
    private String contractSuit;
    /**
     *  Signature that shows is it undouble (=1), double (=2) or redouble (=4) contract.
     */
    private int normalDoubleRedubleSingnature;




    /**
     * Numnber of tricks taken in game
     */
    private int numberOfTrickTaken;

    /**
     * Points  achved by playng contract with some additiona or lacking triks (calculated from duplicate bridge scoring)
     * calculated by PointsForContract
     */
    private int pointsForContract;

    /**
     * number of  Goren (PC) points of both hands
     * (in future could be astimated from biding part not exactly)
     */
    private float pointsInBothHands;

    /**
     * diference betwenn assumpted result from ExpectedResults table and point reach playng contract (pointsForContract)
     */
    private int pointDifferent;

    /**
     * Number of Imp Point  if 0 is equal, if -1 is one less etc ...
     */
    private int results; //= 0;     //pyt dlaczego tak?  że niżej a nie tu zerowanie (to samo pytanie jest w PointsForContract i ono ma dwa razy i działa a tu nie ?

    //todo ogólne: to się ma zmienić na ImpPoints  albo CalculatedImpPointsForOneDeal czy coś pomiędzy i rozumiem że tego nie rozbudowywać bo klasę Game chcesz robić w Androidzie od razu?

    public ResultsOfOneGame(float pointsInBothHands, int pointsForContract, boolean auctionAssumptionWe,
                            boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
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

    public ResultsOfOneGame(float pointsInBothHands, int contractLevel,int numberOfTrickTaken, String contractSuit,
                            boolean doubleGame, boolean redoubleGame, boolean auctionAssumptionWe,
                            boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(pointsInBothHands,
                new PointsForContract(contractLevel, numberOfTrickTaken, contractSuit, doubleGame, redoubleGame, auctionAssumptionWe).getCalculatedPointsForContract(),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        this.contractLevel = contractLevel;
        this.contractSuit= contractSuit;
        this.numberOfTrickTaken =numberOfTrickTaken;
    }

    public ResultsOfOneGame(float pointsInBothHands, int contractLevel,int numberOfTrickTaken, String contractSuit,
                            int normalDoubleRedubleSingnature , boolean auctionAssumptionWe,
                            boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(pointsInBothHands,
                new PointsForContract(contractLevel, numberOfTrickTaken, contractSuit, normalDoubleRedubleSingnature, auctionAssumptionWe).getCalculatedPointsForContract(),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        this.contractLevel = contractLevel;
        this.contractSuit= contractSuit;
        this.normalDoubleRedubleSingnature = normalDoubleRedubleSingnature;
        this.numberOfTrickTaken =numberOfTrickTaken;
    }

    //getteres and setteres

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

    public int getNumberOfTrickTaken() {
        return numberOfTrickTaken;
    }

    public void setNumberOfTrickTaken(int numberOfTrickTaken) {
        this.numberOfTrickTaken = numberOfTrickTaken;
    }

    public int getNormalDoubleRedubleSingnature() {
        return normalDoubleRedubleSingnature;
    }

    public void setNormalDoubleRedubleSingnature(int normalDoubleRedubleSingnature) {
        this.normalDoubleRedubleSingnature = normalDoubleRedubleSingnature;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public static void main(String[] args) {

        try {
            ResultsOfOneGame roog1 = new ResultsOfOneGame(20,3,9,"nt",false,false,false,false,false,false);
            ResultsOfOneGame roog2 = new ResultsOfOneGame(20,3,9,"nt",1,false,false,false,false);

            System.out.println("Końcowy wynik liczony od podstaw jest: " + roog1.getResults() + " \n");
            System.out.println("Końcowy wynik liczony od podstaw jest: " + roog2.getResults() + " \n");


        } catch (BridgeException e) {
            e.printStackTrace();
        }

    }


}
