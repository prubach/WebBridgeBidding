package pl.waw.rubach.points;

import pl.waw.rubach.points.bridgeExeption.BridgeException;
import pl.waw.rubach.points.bridgeExeption.InvalidNumberOfPointsException;
import pl.waw.rubach.points.bridgeExeption.InvalidParameterException;

//TO było ResultsOfOneGame
public class CalculatedImpPointsForOneDeal extends PointsForOneDeal {

    //pyt przeniosłam to tu z powrotem - to jest specyficzny parametr dla liczenia impów (może wogóle nie potrzebny jako parametr klasy - ewentualnie tylko do wyciągnięcia i sprawdzenia jak było liczone...
    /**
     * diference betwenn assumpted result from ExpectedResults table and point reach playng contract (contractScoringPoints)
     */
    private int pointDifferent;




    //TODO zrobić opcje oni grają innych konstruktorów bo nie ten zawsze jest używany
    //constructors  when both play
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe, int pointsForContractWe,
                                         boolean auctionAssumptionWe,  boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException,  InvalidParameterException {
        //assumed that we play
        setPointsInBothHands(pointsInBothHandsWe);
        setContractScoringPoints(pointsForContractWe);
        int expectedPoints = ExpectedResultsTable.getInstance().getPoints(getPointsInBothHands(), fitInOlderColorWe, fitInOlderColorThey, auctionAssumptionWe, auctionAssumptionThey);

 //chyba jest już dobrze (dodałam testy)-  nadal coś jest źle ... ale nie wiem czy testy czy formuły czy jedno i drugie (co najbardziej prawdopodobne) na poniższym testy przechodzą ale formuły mi się nie podobają)
//to poniżej też dobrze - ale pewnie do wywalenia? - chyba lepiej jak to siedzi w tabeli ?
//   /*    if (pointsInBothHands == 20) {
//            if (fitInOlderColorWe) {
//                expectedPoints = 50; //ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, true, auctionAssumptionWe);
//            } else if (fitInOlderColorThey) {
//                expectedPoints = -50; //ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, true,  auctionAssumptionThey);
//            } else
//                expectedPoints = 0;// ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, false,auctionAssumptionWe);
//
//        } else if (pointsInBothHands < 20) {
//            expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, fitInOlderColorThey,auctionAssumptionThey);
//        } else {
//            expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, fitInOlderColorWe, auctionAssumptionWe);
//        }
//*/
        //if they play parameters should change because as imput we have points for we and scoring for we
        if(!wePlay) {
            setPointsInBothHands(40 - pointsInBothHandsWe);
            setContractScoringPoints(-pointsForContractWe);
            expectedPoints = -expectedPoints;
            //expectedPoints = ExpectedResultsTable.getInstance().getPoints(40-pointsInBothHands,fitInOlderColorThey,fitInOlderColorWe,auctionAssumptionThey,auctionAssumptionWe);
        }



        if (expectedPoints <= getContractScoringPoints()) setPointDifferent(getContractScoringPoints()- expectedPoints);
        else setPointDifferent(expectedPoints - getContractScoringPoints());

        if (!(ImpTable.getInstance().checkInputValue(0,10000,getPointDifferent())))  throw new InvalidParameterException();
        int result = ImpTable.getInstance().getPoints(getPointDifferent());

        if (expectedPoints <= getContractScoringPoints()) setResults(result);
        else setResults(-result);
    }




    //TODO sprawdzić czy dobrze poprawiony konsturktor!!! - ma wszędzie  uwzględniać kto
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByWe,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(wePlay, pointsInBothHandsWe,
                new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, wePlay ? auctionAssumptionWe : auctionAssumptionThey, numberOfTrickTakenByWe).getContractScoringPoints(),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNormalDoubleRedubleSingnature(normalDoubleRedubleSingnature);
        setNumberOfTrickTaken(numberOfTrickTakenByWe);
    }

    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe,
                                         int contractLevel, String contractSuit, boolean doubleGame, boolean redoubleGame, int numberOfTrickTakenByDeclarer,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException
    {

        this(wePlay, pointsInBothHandsWe, contractLevel, contractSuit, redoubleGame ? 4 : (doubleGame ? 2 :1), numberOfTrickTakenByDeclarer,
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
    }



    //contructors when only we play:
    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int pointsForContractDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitInOlderColorDeclarer, boolean fitInOlderColorOponents)
            throws InvalidNumberOfPointsException, InvalidParameterException {
    this(true,pointsInBothDeclarerHands,pointsForContractDeclarer,auctionAssumptionDeclarer,auctionAssumptionOponenst,fitInOlderColorDeclarer,fitInOlderColorOponents);
    }

    public CalculatedImpPointsForOneDeal(float pointsInBothDeclarerHands,
                                         int contractLevel, String contractSuit, boolean doubleGame, boolean redoubleGame, int numberOfTrickTakenByDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitInOlderColorDeclarer, boolean fitInOlderColorOponents)
            throws BridgeException {

        this(pointsInBothDeclarerHands, contractLevel, contractSuit, redoubleGame ? 4 : (doubleGame ? 2 :1), numberOfTrickTakenByDeclarer,
                auctionAssumptionDeclarer,auctionAssumptionOponenst,fitInOlderColorDeclarer,fitInOlderColorOponents);
    }

    public CalculatedImpPointsForOneDeal(float pointsInBothHands,
                                         int contractLevel, String contractSuit, int normalDoubleRedubleSingnature, int numberOfTrickTakenByDeclarer,
                                         boolean auctionAssumptionDeclarer, boolean auctionAssumptionOponenst, boolean fitInOlderColorDeclarer, boolean fitInOlderColorOponents)
            throws BridgeException {

        this(pointsInBothHands,
                new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer).getContractScoringPoints(),
                auctionAssumptionDeclarer,auctionAssumptionOponenst,fitInOlderColorDeclarer,fitInOlderColorOponents);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNormalDoubleRedubleSingnature(normalDoubleRedubleSingnature);
        setNumberOfTrickTaken(numberOfTrickTakenByDeclarer);
    }




    //geters and setters
    public int getPointDifferent() {
        return pointDifferent;
    }

    public void setPointDifferent(int pointDifferent) {
        this.pointDifferent = pointDifferent;
    }




    public static void main(String[] args) {

        try {
            CalculatedImpPointsForOneDeal roog1 = new CalculatedImpPointsForOneDeal(20,3, "nt", false, false, 9, false,false,false,false);
            CalculatedImpPointsForOneDeal roog2 = new CalculatedImpPointsForOneDeal(20,3, "nt", 1, 9, false,false,false,false);

            System.out.println("Końcowy wynik liczony od podstaw jest: " + roog1.getResults() + " \n");
            System.out.println("Końcowy wynik liczony od podstaw jest: " + roog2.getResults() + " \n");


        } catch (BridgeException e) {
            e.printStackTrace();
        }

    }


}
