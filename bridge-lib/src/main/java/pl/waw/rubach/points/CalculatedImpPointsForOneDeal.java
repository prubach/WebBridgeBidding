package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;

import static java.lang.Math.abs;

//TO było ResultsOfOneGame
public class CalculatedImpPointsForOneDeal extends OneDeal {

    /**
     * diference betwenn assumpted result from ExpectedResults table and point reach playng contract (contractScoringPoints)
     */
    private int pointDifferent;

    /**
     * expected Points number acording assumption and fit of those who have more poinst. If the same those who have fit in major suit. If both those who have fit in spades.
     */
    private int expectedPoints;

    //constructors  when both play - there are tests
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int pointsForContractWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitWe, boolean fitThey)
            throws BridgeException {

        setWePlay(wePlay);
        setDeclarerVulnerable(wePlay ? auctionAssumptionWe : auctionAssumptionThey);
        setOpponentVulnerable(wePlay ? auctionAssumptionThey : auctionAssumptionWe);
        setDeclarerFit(wePlay ? fitWe : fitThey);
        setOpponensFit(wePlay ? fitThey : fitWe);

        setPointsInBothDeclarerHands(wePlay ? pointsInBothHandsWe : MAXNUBEROFPOINTS - pointsInBothHandsWe);  //pyt gra nie jestem pewna czy zawsze prawda (z doliczaniem za single i renons)

        setDeclarerContractScoringPoints(wePlay ? pointsForContractWe : -pointsForContractWe);

        setExpectedPoints(ExpectedResultsTable.getInstance().getPoints(getPointsInBothDeclarerHands(),
                isDeclarerFit(), isOpponensFit(), isDeclarerVulnerable(), isOpponentVulnerable()));

        setPointDifferent(abs(getDeclarerContractScoringPoints() - getExpectedPoints()));

        if (!(ImpTable.getInstance().checkInputValue(0, 10000, getPointDifferent())))
            throw new BridgeException(getPointDifferent(), true);

        setDeclarerResluts(ImpTable.getInstance().getImpPoints(getPointDifferent()));

        if (getExpectedPoints() > getDeclarerContractScoringPoints())  setDeclarerResluts(-getDeclarerResluts());


    }

    //second constructor using ENUM
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int pointsForContractWe,
                                        Assumption assumption, boolean fitWe, boolean fitThey)
            throws BridgeException {
       this(wePlay,pointsInBothHandsWe,pointsForContractWe,assumption.areWeVunerable(),assumption.areTheyVunerable(),fitWe,fitThey);
    }



    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(wePlay, pointsInBothHandsWe,
                (wePlay ? 1 : -1) * new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, wePlay ? auctionAssumptionWe : auctionAssumptionThey,
                        wePlay ? numberOfTrickTakenByWe : NUBEROFTRICS - numberOfTrickTakenByWe).getDeclarerContractScoringPoints(),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNoDoubleReSignature(normalDoubleRedubleSingnature);
        setDeclarerVulnerable(wePlay ? auctionAssumptionWe : auctionAssumptionThey);
        setDeclarerNumberOfTrickTaken(wePlay ? numberOfTrickTakenByWe : NUBEROFTRICS - numberOfTrickTakenByWe);
    }

    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int contractLevel, String contractSuit, boolean isDouble,boolean isRedouble, int numberOfTrickTakenByWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(wePlay, pointsInBothHandsWe,
                (wePlay ? 1 : -1) * new DuplicateBridgeScoring(contractLevel, contractSuit, isDouble ? 2 : (isRedouble  ? 4 :1), wePlay ? auctionAssumptionWe : auctionAssumptionThey,
                        wePlay ? numberOfTrickTakenByWe : NUBEROFTRICS - numberOfTrickTakenByWe).getDeclarerContractScoringPoints(),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNoDoubleReSignature(isDouble ? 2 : (isRedouble  ? 4 :1));
        setDeclarerVulnerable(wePlay ? auctionAssumptionWe : auctionAssumptionThey);
        setDeclarerNumberOfTrickTaken(wePlay ? numberOfTrickTakenByWe : NUBEROFTRICS - numberOfTrickTakenByWe);
    }

    //contructors when only we play:
    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int pointsForContractDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitInOlderColorDeclarer, boolean fitInOlderColorOponents)
            throws  BridgeException {
        this(true, pointsInBothDeclarerHands, pointsForContractDeclarer, auctionAssumptionDeclarer, auctionAssumptionOponenst, fitInOlderColorDeclarer, fitInOlderColorOponents);
    }

    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int contractLevel, String contractSuit, boolean doubleGame, boolean redoubleGame, int numberOfTrickTakenByDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitInOlderColorDeclarer, boolean fitInOlderColorOponents)
            throws BridgeException {

        this(true, pointsInBothDeclarerHands, contractLevel, contractSuit, redoubleGame ? IS_REDOUBLE : (doubleGame ? IS_DOUBLE : IS_UNDOUBLE), numberOfTrickTakenByDeclarer,
                auctionAssumptionDeclarer, auctionAssumptionOponenst, fitInOlderColorDeclarer, fitInOlderColorOponents);

    }

    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitInOlderColorDeclarer, boolean fitInOlderColorOponents)
            throws BridgeException {

        this(true, pointsInBothDeclarerHands,
                new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer).getDeclarerContractScoringPoints(),
                auctionAssumptionDeclarer, auctionAssumptionOponenst, fitInOlderColorDeclarer, fitInOlderColorOponents);

    }


    public static String impDeclination(int i) {
        String imp;
        if (i == 1) imp = " imp (punkt) ";
        else if (i < 5 | i>21  && i < 25 ) imp = " impy";
        else imp = " impów";
        return imp;


    }

    //geters and setters
    public int getPointDifferent() {
        return pointDifferent;
    }

    private void setPointDifferent(int pointDifferent) {
        this.pointDifferent = pointDifferent;
    }

    public int getExpectedPoints() {
        return expectedPoints;
    }

    private void setExpectedPoints(int expectedPoints) {
        this.expectedPoints = expectedPoints;
    }
}
