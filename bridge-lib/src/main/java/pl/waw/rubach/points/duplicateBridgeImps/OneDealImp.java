package pl.waw.rubach.points.duplicateBridgeImps;


import pl.waw.rubach.points.AbstractOneDeal;

public class OneDealImp extends AbstractOneDeal {

    public static final int MAXNUBEROFPOINTS = 40;
    public static final float MEDIUMNUMBEROFPOINS = 20;
    public static final int MINNUMBEROFPOINTS = 0;

    public static final int NUMBEROFGAMEINRUBBER =4;




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
     * ATTENTION : It should not be Numnber of poins in our hands by we !!!(Pair who make scoring not always plaing pair)
     * number of  Goren (PC) points of both hands of pair who is playing
     * (in future could be astimated from biding part not exactly)
     */
    private float pointsInBothDeclarerHands;




    /**
     * shortDescription -  only with contract main parameter
     */
    private String shortDescription;


    protected OneDealImp(boolean wePlay,
               float pointsInBothHandsWe, int pointsForContractWe,
               boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitWe,boolean fitThey){


        setWePlay(wePlay);
        setDeclarerVulnerable(wePlay ? auctionAssumptionWe : auctionAssumptionThey);
        setOpponentVulnerable(wePlay ? auctionAssumptionThey : auctionAssumptionWe);
        setDeclarerFit(wePlay ? fitWe : fitThey);
        setOpponensFit(wePlay ? fitThey : fitWe);

        setPointsInBothDeclarerHands(wePlay ? pointsInBothHandsWe : MAXNUBEROFPOINTS - pointsInBothHandsWe);  //pyt gra nie jestem pewna czy zawsze prawda (z doliczaniem za single i renons)

        setDeclarerContractScoringPoints(wePlay ? pointsForContractWe : -pointsForContractWe);


    }

    /**
     * Description of contract
     *
     * @return short description of contract
     */


    public String getFitDescriprtion() {
        String fitDes = isDeclarerFit() ? " z fitem. " : " bez fitu. ";
        String fitODes = isOpponensFit() ? " Przeciwnicy mają fit i są : " : " Przeciwnicy nie mają fitu i są";
        String assOp = isOpponentVulnerable() ? " przed partią. " : " po partii.";
        return "Rozgrywający mieli " + getPointsInBothDeclarerHands() + " punkty  " + fitDes + fitODes + assOp;
    }



    public boolean areWeVulnerable(boolean wePlay) {
        return wePlay ? isDeclarerVulnerable() : isOpponentVulnerable() ;
    }

    public void setWeVulnerable(boolean wePlay, boolean areWeVulnerable) {
        if(wePlay) this.declarerVulnerable = areWeVulnerable;
        else this.opponentVulnerable= areWeVulnerable;
    }

    public boolean areTheyVulnerable(boolean wePlay) {
        return wePlay ? isOpponentVulnerable(): isDeclarerVulnerable();
    }

    public void setTheyVulnerable(boolean wePlay, boolean areTheyVulnerable) {
        if(wePlay) this.declarerVulnerable = areTheyVulnerable;
        else this.opponentVulnerable= areTheyVulnerable;
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


    //***************** FIT

    public boolean isFitWe() {
        return areWePlay() ? declarerFit : opponensFit;
    }

    public boolean isFitThey() {
        return areWePlay() ? opponensFit : declarerFit;
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

    //*****OTHER




    public int getContractScoringPointsWe() {
        if (areWePlay()) return getDeclarerContractScoringPoints();
        else return -getDeclarerContractScoringPoints();
    }

    public int getContractScoringPointsWe(boolean whoPlay) {
        if (whoPlay) return getDeclarerContractScoringPoints();
        else return -getDeclarerContractScoringPoints();
    }

    public float getPointsInBothDeclarerHands() {
        return pointsInBothDeclarerHands;
    }

    public void setPointsInBothDeclarerHands(float pointsInBothDeclarerHands) {
        this.pointsInBothDeclarerHands = pointsInBothDeclarerHands;
    }
    public void setPoinsInHandsWe(boolean wePlay, float pointsOnHandsWe){
        if(wePlay) this.pointsInBothDeclarerHands = pointsOnHandsWe;
        else this.pointsInBothDeclarerHands = MAXNUBEROFPOINTS - pointsOnHandsWe;
    }

    public float getPoinsOnHandsWe() {
        if (areWePlay()) return getPointsInBothDeclarerHands();
        else return MAXNUBEROFPOINTS - getPointsInBothDeclarerHands();
    }



    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}
