package pl.waw.rubach.points;


public class PointsForOneDeal {
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
     * number of  Goren (PC) points of both hands
     * (in future could be astimated from biding part not exactly)
     */
    private float pointsInBothHands;

    /**
     * Numnber of tricks taken in game by we !!!(Pair who make scoring not always plaing pair)
     */
    private int numberOfTrickTaken;

    /**
     * Points  achved by playng contract with some additiona or lacking triks (calculated from duplicate bridge scoring)
     * calculated by DuplicateBridgeScoring by we!!! (Pair who make scoring not always plaing pair)
     */
    private int contractScoringPoints;


    /**
     * Number of Victory Poinst - could be IMP Points   if 0 is equal, if -1 is one less etc ...
     * (for we - Pair who make scoring not always plaing pair)
     */
    private int results;


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

    public int getContractScoringPoints() {
        return contractScoringPoints;
    }

    public void setContractScoringPoints(int contractScoringPoints) {
        this.contractScoringPoints = contractScoringPoints;
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
}
