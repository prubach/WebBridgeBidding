package pl.waw.rubach.points;

import pl.waw.rubach.points.bridgeExeption.BridgeException;
import pl.waw.rubach.points.bridgeExeption.InvalidNumberOfPointsException;
import pl.waw.rubach.points.bridgeExeption.InvalidParameterException;

//TO było ResultsOfOneGame
public class CalculatedImpPointsForOneDeal extends PointsForOneDeal {


//TODO zrobić opcje oni grają innych konstruktorów bo nie ten zawsze jest używany
    public CalculatedImpPointsForOneDeal(boolean wePlay, float pointsInBothHandsWe, int pointsForContractWe,
                                         boolean auctionAssumptionWe,  boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException,  InvalidParameterException {
        //assumed that we play
        setPointsInBothHands(pointsInBothHandsWe);
        setPointsForContract(pointsForContractWe);
        int expectedPoints = ExpectedResultsTable.getInstance().getPoints(getPointsInBothHands(), fitInOlderColorWe, fitInOlderColorThey, auctionAssumptionWe, auctionAssumptionThey);



 //chyba jest już dobrze (dodałam testy)-  nadal coś jest źle ... ale nie wiem czy testy czy formuły czy jedno i drugie (co najbardziej prawdopodobne) na poniższym testy przechodzą ale formuły mi się nie podobają)
//to poniżej też dobrze - ale pewnie do wywalenia? - chyba lepiej jak to siedzi w tabeli ?
   /*    if (pointsInBothHands == 20) {
            if (fitInOlderColorWe) {
                expectedPoints = 50; //ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, true, auctionAssumptionWe);
            } else if (fitInOlderColorThey) {
                expectedPoints = -50; //ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, true,  auctionAssumptionThey);
            } else
                expectedPoints = 0;// ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, false,auctionAssumptionWe);

        } else if (pointsInBothHands < 20) {
            expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, fitInOlderColorThey,auctionAssumptionThey);
        } else {
            expectedPoints = ExpectedResultsTable.getInstance().getPoints(pointsInBothHands, fitInOlderColorWe, auctionAssumptionWe);
        }
//*/


        if(!wePlay) {
            setPointsInBothHands(40 - pointsInBothHandsWe);
            setPointsForContract(-pointsForContractWe);
            expectedPoints = -expectedPoints;
            //expectedPoints = ExpectedResultsTable.getInstance().getPoints(40-pointsInBothHands,fitInOlderColorThey,fitInOlderColorWe,auctionAssumptionThey,auctionAssumptionWe);
        }

        //}


        if (expectedPoints <= getPointsForContract()) setPointDifferent(getPointsForContract()- expectedPoints);
        else setPointDifferent(expectedPoints - getPointsForContract());

        if (!(ImpTable.getInstance().checkInputValue(0,10000,getPointDifferent())))  throw new InvalidParameterException();
        int result = ImpTable.getInstance().getPoints(getPointDifferent());

        if (expectedPoints <= getPointsForContract()) setResults(result);
        else setResults(-result);
    }


    public CalculatedImpPointsForOneDeal(float pointsInBothHands,
                                         int pointsForContract,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException, InvalidParameterException {
    this(true,pointsInBothHands,pointsForContract,auctionAssumptionWe,auctionAssumptionThey,fitInOlderColorWe,fitInOlderColorThey);
    }

    public CalculatedImpPointsForOneDeal(float pointsInBothHands,
                                         int contractLevel, int numberOfTrickTakenByDeclarer, String contractSuit, boolean doubleGame, boolean redoubleGame,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(pointsInBothHands, contractLevel, numberOfTrickTakenByDeclarer, contractSuit, redoubleGame ? 4 : (doubleGame ? 2 :1),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
    }

    public CalculatedImpPointsForOneDeal(float pointsInBothHands,
                                         int contractLevel, int numberOfTrickTakenByDeclarer, String contractSuit, int normalDoubleRedubleSingnature,
                                         boolean auctionAssumptionWe, boolean auctionAssumptionThey, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        this(pointsInBothHands,
                new DuplicateBridgeScoring(contractLevel, contractSuit, normalDoubleRedubleSingnature, auctionAssumptionWe, numberOfTrickTakenByDeclarer).getCalculatedPointsForContract(),
                auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNormalDoubleRedubleSingnature(normalDoubleRedubleSingnature);
        setNumberOfTrickTakenByDeclarer(numberOfTrickTakenByDeclarer);
    }


    public static void main(String[] args) {

        try {
            CalculatedImpPointsForOneDeal roog1 = new CalculatedImpPointsForOneDeal(20,3,9,"nt",false,false,false,false,false,false);
            CalculatedImpPointsForOneDeal roog2 = new CalculatedImpPointsForOneDeal(20,3,9,"nt",1,false,false,false,false);

            System.out.println("Końcowy wynik liczony od podstaw jest: " + roog1.getResults() + " \n");
            System.out.println("Końcowy wynik liczony od podstaw jest: " + roog2.getResults() + " \n");


        } catch (BridgeException e) {
            e.printStackTrace();
        }

    }


}
