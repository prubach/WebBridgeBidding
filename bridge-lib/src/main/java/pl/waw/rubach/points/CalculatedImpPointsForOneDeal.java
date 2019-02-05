package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfPointsException;

import static java.lang.Math.abs;

//TO było ResultsOfOneGame
public class CalculatedImpPointsForOneDeal extends DeclarerPointsForOneDeal {

    //pyt przeniosłam to tu z powrotem - to jest specyficzny parametr dla liczenia impów (może wogóle nie potrzebny jako parametr klasy
    // - ewentualnie tylko do wyciągnięcia i sprawdzenia jak było liczone... ale może wobec tego też co powinno być ?
    //odp jeśli się przydaje do odczytania jak było liczone, to niech będzie.
    /**
     * diference betwenn assumpted result from ExpectedResults table and point reach playng contract (contractScoringPoints)
     */
    private int pointDifferent;

    /**
     * expected Points number acording assumption and fit of those who have more poinst. If the same those who have fit in major suit. If both those who have fit in spades.
     */
    private int expectedPoints;


    //pyt proszę rzuć okiem czy tak lepie -  pozmieniałam na tak jak wydaje się bardziej elegancko (stare zostało w komentarzach-  chyba jest ok nie jestem pewna .. testy już są chyba ok..
    //odp lepiej, dodałem tylko if else, bo po co ma to robić 2 razy dla przypadku They
    //constructors  when both play - there are tests
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int pointsForContractWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitWe, boolean fitThey)
            throws InvalidNumberOfPointsException, BridgeException {

        //pyt czy to jest potrzebne - myślałam że lepiej żeby przechowywało w jedym miejscu
        //odp chyba potrzebne
        setWePlay(wePlay);
        setDeclarerVulnerable(wePlay ? auctionAssumptionWe : auctionAssumptionThey);
        setOpponentVulnerable(wePlay ? auctionAssumptionThey : auctionAssumptionWe);
        setDeclarerFit(wePlay ? fitWe: fitThey);
        setOpponensFit(wePlay ? fitThey : fitWe);
        setPointsInBothDeclarerHands(wePlay ? pointsInBothHandsWe : 40 - pointsInBothHandsWe);
        setContractScoringPoints(wePlay ? pointsForContractWe : -pointsForContractWe);
        if (wePlay)
            setExpectedPoints(ExpectedResultsTable.getInstance().getPoints(getPointsInBothDeclarerHands(),
                fitWe, fitThey, auctionAssumptionWe, auctionAssumptionThey));
        else
        //if they play parameters should change because as imput we have points for we and scoring for we
            setExpectedPoints(ExpectedResultsTable.getInstance().getPoints(getPointsInBothDeclarerHands(),
               fitThey, fitWe, auctionAssumptionThey, auctionAssumptionWe));
        boolean winWe = getExpectedPoints() <= getContractScoringPoints();
        setPointDifferent(abs(getContractScoringPoints() - getExpectedPoints()));
        // if (getExpectedPoints() <= getContractScoringPoints()) setPointDifferent(getContractScoringPoints()- getExpectedPoints());
        // else setPointDifferent(getExpectedPoints() - getContractScoringPoints());

        if (!(ImpTable.getInstance().checkInputValue(0, 10000, getPointDifferent())))
            throw new BridgeException(getPointDifferent(),true);
        //  int results = ImpTable.getInstance().getPoints(getPointDifferent());
        setResults(ImpTable.getInstance().getPoints(getPointDifferent()));

        if (!winWe) setResults(-getResults());
        //  if (getExpectedPoints() <= getContractScoringPoints()) setResults(result);
        //  else setResults(-result);
    }


    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(wePlay, pointsInBothHandsWe,
                (wePlay ? 1 : -1) * new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, wePlay ? auctionAssumptionWe : auctionAssumptionThey,
                        wePlay ? numberOfTrickTakenByWe : 13 - numberOfTrickTakenByWe).getContractScoringPoints(),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNoDoubleReSignature(normalDoubleRedubleSingnature);
        setDeclarerVulnerable(wePlay ? auctionAssumptionWe : auctionAssumptionThey);
        setDeclarerNumberOfTrickTaken(wePlay ? numberOfTrickTakenByWe : 13 - numberOfTrickTakenByWe);
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

        this(true, pointsInBothDeclarerHands, contractLevel, contractSuit, redoubleGame ? 4 : (doubleGame ? 2 : 1), numberOfTrickTakenByDeclarer,
                auctionAssumptionDeclarer, auctionAssumptionOponenst, fitInOlderColorDeclarer, fitInOlderColorOponents);

    }

    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitInOlderColorDeclarer, boolean fitInOlderColorOponents)
            throws BridgeException {

        this(true,pointsInBothDeclarerHands,
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
