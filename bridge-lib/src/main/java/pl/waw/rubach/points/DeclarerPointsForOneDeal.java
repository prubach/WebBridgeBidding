package pl.waw.rubach.points;


public class DeclarerPointsForOneDeal {

    public static final int IS_DOUBLE = 2;
    public static final int IS_REDOUBLE = 4;
    public static final int IS_UNDOUBLE = 1;

    /**
     * indicates who is Declarer - but all value here for declarer
      */
    private boolean wePlay;

    /**
     * The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken to win.
     * in future from other part of application - results of biding part or user input
     */
    private int contractLevel;
    /**
     * Cards suits [denomination or strain] that denotes the proposed trump suit or no trump.
     * Thus, there are five denominations – no trump, spades, hearts, diamonds and clubs.
     */
    private String contractSuit;
    /**
     *  Signature that shows is it undouble (=1), double (=2) or redouble (=4) contract.
     */
    private int noDoubleReSignature;

    /**
     * ATTENTION fit opponents not they!!!
     * Fit for  opponents of playing Pair means if they have 8 cards in suit (major or all depending of points)
     */
    private boolean opponensFit;
    /**
     * ATTENTION fit declarer not we!!!
     * Fit for Plaing Pair means if they have 8 cards in suit (major or all depending of poinst)
     */
    private boolean declarerFit;

    /**
     * ATTENTION Auction assumption opponents  not they!!
     * Auction Assumption for opponens of Plaing Pair means if pair is  vulnerable or unvulnerable
     */
    private boolean opponentVulnerable;

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
    private int declarerNumberOfTrickTaken;

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
        String lew = getDeclarerNumberOfTrickTaken()==1 ? " tylko lewę. ": getDeclarerNumberOfTrickTaken()>1 && getDeclarerNumberOfTrickTaken() <5 ? " lewy." : " lew.";

        if (getNoDoubleReSignature() == IS_DOUBLE)
            return  " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption +" z kontrą, rozgrywający zebrał " + getDeclarerNumberOfTrickTaken() + lew;
        else if (getNoDoubleReSignature() == IS_REDOUBLE)
            return  " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption + " z rekontrą, rozgrywający zebrał " + getDeclarerNumberOfTrickTaken() + lew;
        else
            return  " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption + " rozgrywający zebrał " + getDeclarerNumberOfTrickTaken() + lew;
    }

    public String getFullDescriprtion(){
        String fitDes = isDeclarerFit() ? " z fitem. " : " bez fitu. ";
        String fitODes = isOpponensFit() ? " Przeciwnicy mają fit i są : " :" Przeciwnicy nie mają fitu i są" ;
        String assOp = isOpponentVulnerable() ? " przed partią. " : " po partii.";
        return getContractDescription() + "Rozgrywający mieli " +getPointsInBothDeclarerHands()+ " punkty  "+ fitDes + fitODes + assOp ;
    }


    public int getNumberOfTricksTakenWe() {
        if (isWePlay()) return getDeclarerNumberOfTrickTaken();
        else return 13-getDeclarerNumberOfTrickTaken();
    }

    public float getPoinsOnHandsWe() {
        if (isWePlay()) return getPointsInBothDeclarerHands();
        else return 40-getPointsInBothDeclarerHands();
            }

     public int getContractScoringPointsWe(){
        if(isWePlay()) return getContractScoringPoints();
        else return -getContractScoringPoints();
    }
    //getteres and setteres
    public boolean isWePlay() {
        return wePlay;
    }

    public void setWePlay(boolean wePlay) {
        this.wePlay = wePlay;
    }

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

    public int getNoDoubleReSignature() {
        return noDoubleReSignature;
    }

    public void setNoDoubleReSignature(int noDoubleReSignature) {
        this.noDoubleReSignature = noDoubleReSignature;
    }

    public boolean isDeclarerVulnerable() {
        return declarerVulnerable;
    }

    public void setDeclarerVulnerable(boolean declarerVulnerable) {
        this.declarerVulnerable = declarerVulnerable;
    }

    public boolean isOpponentVulnerable() {
        return opponentVulnerable;
    }

    public boolean isOpponensFit() {
        return opponensFit;
    }

    public void setOpponensFit(boolean opponensFit) {
        this.opponensFit = opponensFit;
    }

    public boolean isDeclarerFit() {
        return declarerFit;
    }

    public void setDeclarerFit(boolean declarerFit) {
        this.declarerFit = declarerFit;
    }

    public void setOpponentVulnerable(boolean opponentVulnerable) {
        this.opponentVulnerable = opponentVulnerable;
    }

    public int getDeclarerNumberOfTrickTaken() {
        return declarerNumberOfTrickTaken;
    }

    public void setDeclarerNumberOfTrickTaken(int declarerNumberOfTrickTaken) {
        this.declarerNumberOfTrickTaken = declarerNumberOfTrickTaken;
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
