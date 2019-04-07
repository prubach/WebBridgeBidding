package pl.waw.rubach.points;


import pl.waw.rubach.points.exceptions.*;

public class OneDeal {

    public static final int IS_DOUBLE = 2;
    public static final int IS_REDOUBLE = 4;
    public static final int IS_UNDOUBLE = 1;
    public static final int NUBEROFTRICS = 13;
    public static final int MINCONTRACTLEVEL = 1;
    public static final int MAXCONTRACTLEVEL = 7;

    /**
     * indicates who is Declarer - but all value here for declarer
     */
    private boolean wePlay = true;

    /**
     * The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken to win.
     * in future from other part of application - declarerResluts of biding part or user input
     */
    private int contractLevel;

    /**
     * Cards suits [denomination or strain] that denotes the proposed trump suit or no trump.
     * Thus, there are five denominations – no trump, spades, hearts, diamonds and clubs.
     */
    private String contractSuit;

    /**
     * Signature that shows is it undouble (=1), double (=2) or redouble (=4) contract.
     */
    private int noDoubleReSignature;

    /**
     * ATTENTION : It shoul not be Numnber of tricks taken in game by we !!!(Pair who make scoring not always plaing pair)
     * <p>
     * Number of tricks taken in game  by declarer - the player whose bid establishes the suit of the contract
     * and who must therefore play both their own hand and the exposed hand of the dummy.
     * (opponent takes 13 - numberOfTrickTakenByDeclarer)
     */
    private int declarerNumberOfTrickTaken;


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
     * //calculatedPoinstForContract -> contractScoringPoinst because it is no poinst exactly but scoring (zapis)
     * Points for contract calculated with modify bridge rules according to 4 play scoring
     * Points  achved by playng contract with some additiona or lacking triks (calculated from duplicate bridge scoring)
     * calculated by DuplicateBridgeScoring by we!!! (Pair who make scoring not always plaing pair)
     */
    private int declarerContractScoringPoints;


    /**
     * Number of Victory Poinst - could be IMP Points   if 0 is equal, if -1 is one less etc ...
     * (for we - Pair who make scoring not always plaing pair)
     */
    private int declarerResluts;

    /**
     * shortDescription -  only with contract main parameter
     */
    private String shortDescription;


    protected OneDeal() {
    }

    protected OneDeal(int contractLevel,String contractSuit,
                      int noDoubleReSignature, int declarerNumberOfTrickTaken,boolean auctionAssumptionDeclarer)
    throws  BridgeException{

        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNoDoubleReSignature(noDoubleReSignature);
        setDeclarerVulnerable(auctionAssumptionDeclarer);
        setDeclarerNumberOfTrickTaken(declarerNumberOfTrickTaken);

    }

    /**
     * Description of contract
     *
     * @return short description of contract
     */
    public String getContractDescription() {
       return getContractDescription(isDeclarerVulnerable());    }

    public String getContractDescription(boolean assumptionB) {
        String assumption = assumptionB ? ": po parti, " : ": przed partią, "; //fixme poprawić wczytywanie założeń
        String lew = getDeclarerNumberOfTrickTaken() == 1 ? " tylko lewę. " : getDeclarerNumberOfTrickTaken() > 1 && getDeclarerNumberOfTrickTaken() < 5 ? " lewy." : " lew.";

        if (getNoDoubleReSignature() == IS_DOUBLE)
            return " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption + " z kontrą, rozgrywający zebrał " + getDeclarerNumberOfTrickTaken() + lew;
        else if (getNoDoubleReSignature() == IS_REDOUBLE)
            return " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption + " z rekontrą, rozgrywający zebrał " + getDeclarerNumberOfTrickTaken() + lew;
        else
            return " Kontrakt jest: " + getContractLevel() + getContractSuit() + assumption + " rozgrywający zebrał " + getDeclarerNumberOfTrickTaken() + lew;
    }


    //getteres and setteres
    public boolean areWePlay() {
        return wePlay;
    }

    public void setWePlay(boolean wePlay) {
        this.wePlay = wePlay;
    }

    public int getContractLevel() {
        return contractLevel;
    }

    public void setContractLevel(int contractLevel) throws InvalidContractLevelException {
        //checking if contractLevel is correct
        if (contractLevel > MAXCONTRACTLEVEL || contractLevel < MINCONTRACTLEVEL)
            throw new InvalidContractLevelException(contractLevel);

        this.contractLevel = contractLevel;
    }

    public String getContractSuit() {
        return contractSuit;
    }

    public void setContractSuit(String contractSuit) throws InvalidContractSuitException {
        this.contractSuit = contractSuit;
/*
        switch (getContractSuit().toUpperCase()) {
            case "S":
            case "H":
                this.contractSuit = contractSuit;
                break;
            case "D":
            case "C":
                this.contractSuit = contractSuit;
                break;
            case "N":
            case "NT":
                this.contractSuit = contractSuit;
                break;
            default:
                throw new InvalidContractSuitException(getContractSuit());
 }*/
    }

    public int getNoDoubleReSignature() {
        return noDoubleReSignature;
    }

    public void setNoDoubleReSignature(int nDRSignature) throws BridgeException {
        //checking if double/ redouble or undouble signature  is correct
        if (!(nDRSignature == IS_UNDOUBLE || nDRSignature == IS_DOUBLE || nDRSignature == IS_REDOUBLE))
            throw new InvalidNDRSignatureException(nDRSignature);
        this.noDoubleReSignature = nDRSignature;
    }

    public boolean areWeVulnerable(boolean wePlay) {
        return wePlay ? isDeclarerVulnerable() : isOpponentVulnerable() ;
    }

    public void setWeVulnerable(boolean wePlay, boolean areWeVunerable) {
        if(wePlay) this.declarerVulnerable =areWeVunerable;
        else this.opponentVulnerable=areWeVunerable;
    }

    public boolean areTheyVulnerable(boolean wePlay) {
        return wePlay ? isOpponentVulnerable(): isDeclarerVulnerable();
    }

    public void setTheyVulnerable(boolean wePlay, boolean areTheyVunerable) {
        if(wePlay) this.declarerVulnerable =areTheyVunerable;
        else this.opponentVulnerable=areTheyVunerable;
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

    public void setOpponentVulnerable(boolean opponentVulnerable) {
        this.opponentVulnerable = opponentVulnerable;
    }


    //*****OTHER

    public int getDeclarerNumberOfTrickTaken() {
        return declarerNumberOfTrickTaken;
    }

    public void setDeclarerNumberOfTrickTaken(int declarerNumberOfTrickTaken) throws InvalidNumberOfTrickTakenException {
        //checking if number of tricks is correct
        if (declarerNumberOfTrickTaken > NUBEROFTRICS || declarerNumberOfTrickTaken < 0)
            throw new InvalidNumberOfTrickTakenException(declarerNumberOfTrickTaken);
        this.declarerNumberOfTrickTaken = declarerNumberOfTrickTaken;
    }
    public void setNuberoftricsTakenWe(int nuberoftricsTakenWe) throws InvalidNumberOfTrickTakenException {
        if (nuberoftricsTakenWe > NUBEROFTRICS || nuberoftricsTakenWe < 0)
            throw new InvalidNumberOfTrickTakenException(declarerNumberOfTrickTaken);
        this.declarerNumberOfTrickTaken = areWePlay() ? nuberoftricsTakenWe :NUBEROFTRICS -nuberoftricsTakenWe;

    }

    public int getNumberOfTricksTakenWe() {
        if (areWePlay()) return getDeclarerNumberOfTrickTaken();
        else return 13 - getDeclarerNumberOfTrickTaken();
    }

    public int getDeclarerContractScoringPoints() {
        return declarerContractScoringPoints;
    }

    public void setDeclarerContractScoringPoints(int declarerContractScoringPoints) {
        this.declarerContractScoringPoints = declarerContractScoringPoints;
    }

    public int getContractScoringPointsWe() {
        if (areWePlay()) return getDeclarerContractScoringPoints();
        else return -getDeclarerContractScoringPoints();
    }

    public int getContractScoringPointsWe(boolean whoPlay) {
        if (whoPlay) return getDeclarerContractScoringPoints();
        else return -getDeclarerContractScoringPoints();
    }


    public int getDeclarerResluts() {
        return declarerResluts;
    }

    public void setDeclarerResluts(int declarerResluts) {
        this.declarerResluts = declarerResluts;
    }



    public void setResultsWe(boolean wePlay, int weResults) {
        setDeclarerResluts(wePlay ? weResults : -weResults);
    }
    public int getResultsWe(boolean wePlay) {
        return wePlay ? declarerResluts : -declarerResluts;
    }

    public int getResultsWe(boolean wePlay, int declarerResluts) {
        return wePlay ? declarerResluts : -declarerResluts;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public void setShortDescription() {
        this.shortDescription = getContractDescription();
    }

}
