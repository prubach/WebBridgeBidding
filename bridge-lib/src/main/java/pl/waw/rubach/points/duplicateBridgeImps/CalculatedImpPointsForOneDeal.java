package pl.waw.rubach.points.duplicateBridgeImps;

import pl.waw.rubach.points.exceptions.BridgeException;

import static java.lang.Math.abs;

//TO było ResultsOfOneGame
public class CalculatedImpPointsForOneDeal extends OneDealImp {

    /**
     * diference betwenn assumpted result from ExpectedResults table and point reach playng contract (contractScoringPoints)
     */
    private int pointDifferent;

    /**
     * expected Points number acording assumption and fit of those who have more poinst. If the same those who have fit in major suit. If both those who have fit in spades.
     */
    private int expectedPoints;

    //constructors  when both play from points for contract - there are tests
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int pointsForContractWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitWe, boolean fitThey)
            throws BridgeException {
        super(wePlay, pointsInBothHandsWe, pointsForContractWe, auctionAssumptionWe, auctionAssumptionThey, fitWe, fitThey);

        setExpectedPoints(ExpectedResultsTable.getInstance().getPoints(getPointsInBothDeclarerHands(),
                isDeclarerFit(), isOpponensFit(), isDeclarerVulnerable(), isOpponentVulnerable()));

        setPointDifferent(abs(getDeclarerContractScoringPoints() - getExpectedPoints()));

        if (!(ImpTable.getInstance().checkInputValue(0, 10000, getPointDifferent())))
            throw new BridgeException(getPointDifferent());

        setDeclarerResluts(ImpTable.getInstance().getImpPoints(getPointDifferent()));

        if (getExpectedPoints() > getDeclarerContractScoringPoints()) setDeclarerResluts(-getDeclarerResluts());

    }
    //constructors  when both play from contract parameter- there are tests
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey,boolean fitWe, boolean fitThey)
            throws BridgeException {

        this(wePlay, pointsInBothHandsWe,
                (wePlay ? 1 : -1) * new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, wePlay ? auctionAssumptionWe : auctionAssumptionThey,
                        wePlay ? numberOfTrickTakenByWe : NUBEROFTRICS - numberOfTrickTakenByWe).getDeclarerContractScoringPoints(),
                auctionAssumptionWe, auctionAssumptionThey, fitWe, fitThey);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNoDoubleReSignature(normalDoubleRedubleSingnature);
        //setDeclarerVulnerable(wePlay ? auctionAssumptionWe : auctionAssumptionThey);
        setDeclarerNumberOfTrickTaken(wePlay ? numberOfTrickTakenByWe : NUBEROFTRICS - numberOfTrickTakenByWe);
    }

    //constructor using ENUM -no tests
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int pointsForContractWe,
                                         Assumption assumption, boolean fitWe, boolean fitThey)
            throws BridgeException {
        this(wePlay, pointsInBothHandsWe, pointsForContractWe, assumption.areWeVunerable(), assumption.areTheyVunerable(), fitWe, fitThey);
    }


    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int contractLevel, String contractSuit, boolean isDouble, boolean isRedouble, int numberOfTrickTakenByWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitWe, boolean fitThey)
            throws BridgeException {
      this(wePlay,pointsInBothHandsWe,contractLevel,contractSuit,(isDouble ? IS_DOUBLE : (isRedouble ? IS_REDOUBLE : IS_UNDOUBLE)),
              numberOfTrickTakenByWe,auctionAssumptionWe,auctionAssumptionThey,fitWe,fitThey);
         }

    //contructors when only we play:
    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int pointsForContractDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitDeclarer, boolean fitOponents)
            throws BridgeException {
        this(true, pointsInBothDeclarerHands, pointsForContractDeclarer, auctionAssumptionDeclarer, auctionAssumptionOponenst, fitDeclarer, fitOponents);
    }

    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int contractLevel, String contractSuit, boolean isDouble, boolean isRedouble, int numberOfTrickTakenByDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitDeclarer, boolean fitOponents)
            throws BridgeException {

        this(true, pointsInBothDeclarerHands, contractLevel, contractSuit, isRedouble ? IS_REDOUBLE : (isDouble ? IS_DOUBLE : IS_UNDOUBLE), numberOfTrickTakenByDeclarer,
                auctionAssumptionDeclarer, auctionAssumptionOponenst, fitDeclarer, fitOponents);

    }

    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitDeclarer, boolean fitOponents)
            throws BridgeException {

        this(true, pointsInBothDeclarerHands,
                new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer).getDeclarerContractScoringPoints(),
                auctionAssumptionDeclarer, auctionAssumptionOponenst, fitDeclarer, fitOponents);

    }


    public static String impDeclination(int i) {
        String imp;
        if (i == 1) imp = " imp (punkt) ";
        else if (i < 5 | i > 21 && i < 25) imp = " impy";
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
