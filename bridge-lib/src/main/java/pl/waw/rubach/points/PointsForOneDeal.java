package pl.waw.rubach.points;


public class PointsForOneDeal {

    public static final int IS_DOUBLE = 2;
    public static final int IS_REDOUBLE = 4;
    public static final int IS_UNDOUBLE = 1;
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
    private int norDoubleReSingnature;

    /**
     * ATTENTION Auction assumption declarer not we!!!
     * Auction Assumption for Plaing Pair means if pair is  vulnerable or unvulnerable
     */
    private boolean declarerVulnerable;

    /**
     * ATTENTION : It shoul not be Numnber of tricks taken in game by we !!!(Pair who make scoring not always plaing pair)
     *
     * Number of tricks taken in game  by declarer - the player whose bid establishes the suit of the contract
     * and who must therefore play both their own hand and the exposed hand of the dummy.
     * (opponent takes 13 - numberOfTrickTakenByDeclarer)

     */
    private int numberOfTrickTakenDeclarer;

    /**
     * shortDescription -  only with contract main parameter
     */
    private String shortDescription;


    /**
     *  ATTENTION : It should not be Numnber of poins in our hands by we !!!(Pair who make scoring not always plaing pair)
     * number of  Goren (PC) points of both hands of pair who is playing
     * (in future could be astimated from biding part not exactly)
     */
    private float pointsInBothDeclarerHands;

    /**
     *  //calculatedPoinstForContract -> contractScoringPoinst because it is no poinst exactly but scoring (zapis)
     *  Points for contract calculated with modify bridge rules according to 4 play scoring
     * Points  achved by playng contract with some additiona or lacking triks (calculated from duplicate bridge scoring)
     * calculated by DuplicateBridgeScoring by we!!! (Pair who make scoring not always plaing pair)
     */
    private int contractScoringPoints;


    /**
     * Number of Victory Poinst - could be IMP Points   if 0 is equal, if -1 is one less etc ...
     * (for we - Pair who make scoring not always plaing pair)
     */
    private int results;

    /**
     * Description of contract
     * @return short description of contract
     */
    protected String getContractDescription() {
        String assumption = isDeclarerVulnerable() ? ": po parti, " : ": przed partią, ";
        String lew =getNumberOfTrickTakenDeclarer()==1 ? " tylko lewę. ": getNumberOfTrickTakenDeclarer()>1 && getNumberOfTrickTakenDeclarer() <5 ? " lewy." : " lew.";

        if (getNorDoubleReSingnature() == IS_DOUBLE)
            return  " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption +" z kontrą, rozgrywający zebrał " + getNumberOfTrickTakenDeclarer() + lew;
        else if (getNorDoubleReSingnature() == IS_REDOUBLE)
            return  " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption + " z rekontrą, rozgrywający zebrał " + getNumberOfTrickTakenDeclarer() + lew;
        else
            return  " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption + " rozgrywający zebrał " + getNumberOfTrickTakenDeclarer() + lew;
    }


    //getteres and setteres

    public int getContractLevel() {
        return contractLevel;
    }

    public void setContractLevel(int contractLevel) {
        this.contractLevel = contractLevel;
    }

    public String getContractSuit() {
        return contractSuit;
    }

    public void setContractSuit(String contractSuit) {
        this.contractSuit = contractSuit;
    }

    public int getNorDoubleReSingnature() {
        return norDoubleReSingnature;
    }

    public void setNorDoubleReSingnature(int norDoubleReSingnature) {
        this.norDoubleReSingnature = norDoubleReSingnature;
    }

    public boolean isDeclarerVulnerable() {
        return declarerVulnerable;
    }

    public void setDeclarerVulnerable(boolean declarerVulnerable) {
        this.declarerVulnerable = declarerVulnerable;
    }


    public int getNumberOfTrickTakenDeclarer() {
        return numberOfTrickTakenDeclarer;
    }

    public void setNumberOfTrickTakenDeclarer(int numberOfTrickTakenDeclarer) {
        this.numberOfTrickTakenDeclarer = numberOfTrickTakenDeclarer;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getContractScoringPoints() {
        return contractScoringPoints;
    }

    public void setContractScoringPoints(int contractScoringPoints) {
        this.contractScoringPoints = contractScoringPoints;
    }


    public float getPointsInBothDeclarerHands() {
        return pointsInBothDeclarerHands;
    }

    public void setPointsInBothDeclarerHands(float pointsInBothDeclarerHands) {
        this.pointsInBothDeclarerHands = pointsInBothDeclarerHands;
    }


    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
