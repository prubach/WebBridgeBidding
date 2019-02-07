package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfPointsException;

import static java.lang.Math.abs;

//TO było ResultsOfOneGame
public class CalculatedImpPointsForOneDeal extends DeclarerPointsForOneDeal {

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

        setContractScoringPoints(wePlay ? pointsForContractWe : -pointsForContractWe);

      /*  if (wePlay)
            setExpectedPoints(ExpectedResultsTable.getInstance().getImpPoints(getPointsInBothDeclarerHands(),
                    fitWe, fitThey, auctionAssumptionWe, auctionAssumptionThey));
        else
            //if they play parameters should change because as imput we have points for we and scoring for we
            setExpectedPoints(ExpectedResultsTable.getInstance().getImpPoints(getPointsInBothDeclarerHands(),
                    fitThey, fitWe, auctionAssumptionThey, auctionAssumptionWe));
*/
        setExpectedPoints(ExpectedResultsTable.getInstance().getPoints(getPointsInBothDeclarerHands(),
                isDeclarerFit(), isOpponensFit(), isDeclarerVulnerable(), isOpponentVulnerable()));

        boolean winWe = getExpectedPoints() <= getContractScoringPoints();

        setPointDifferent(abs(getContractScoringPoints() - getExpectedPoints()));

        if (!(ImpTable.getInstance().checkInputValue(0, 10000, getPointDifferent())))
            throw new BridgeException(getPointDifferent(), true);

        setResults(ImpTable.getInstance().getImpPoints(getPointDifferent()));

        if (!winWe) setResults(-getResults());

    }


    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(wePlay, pointsInBothHandsWe,
                (wePlay ? 1 : -1) * new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, wePlay ? auctionAssumptionWe : auctionAssumptionThey,
                        wePlay ? numberOfTrickTakenByWe : NUBEROFTRICS - numberOfTrickTakenByWe).getContractScoringPoints(),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNoDoubleReSignature(normalDoubleRedubleSingnature);
        setDeclarerVulnerable(wePlay ? auctionAssumptionWe : auctionAssumptionThey);
        setDeclarerNumberOfTrickTaken(wePlay ? numberOfTrickTakenByWe : NUBEROFTRICS - numberOfTrickTakenByWe);
    }


    //contructors when only we play:
    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int pointsForContractDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitInOlderColorDeclarer, boolean fitInOlderColorOponents)
            throws InvalidNumberOfPointsException, BridgeException {
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
                new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer).getContractScoringPoints(),
                auctionAssumptionDeclarer, auctionAssumptionOponenst, fitInOlderColorDeclarer, fitInOlderColorOponents);

    }


    //geters and setters
    public int getPointDifferent() {
        return pointDifferent;
    }

    public void setPointDifferent(int pointDifferent) {
        this.pointDifferent = pointDifferent;
    }

    public int getExpectedPoints() {
        return expectedPoints;
    }

    public void setExpectedPoints(int expectedPoints) {
        this.expectedPoints = expectedPoints;
    }
}
