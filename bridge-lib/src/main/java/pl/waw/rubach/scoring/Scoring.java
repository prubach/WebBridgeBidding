package pl.waw.rubach.scoring;

import pl.waw.rubach.points.InvalidNumberOfPointsException;
import pl.waw.rubach.points.PointsDiferentLessThenZeroException;
import pl.waw.rubach.points.ResultsOfOneGame;

public class Scoring {

    /**
     * game (for contract) ID - not sure if needed but probably for something?  - could be added in future:
     * date and time
     * place
     * names of players
     * photos etc ...
     * should be done automaticly? - gdzieś tak widziałam...
     */
    private int gameID;

    /**
     * number of  contract in 4 contract cycle with different Vulnerability (Both before, We, They, Both after
     */
    private int contractNumber;


    /**
     * Number of Imp Point
     * if 0 is equal, if -1 is one less etc ...
     */
    private ResultsOfOneGame results;

    Scoring(int contractNumber, int pointsInBothHands, int pointsForContract, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        this.contractNumber = contractNumber;


        boolean auctionAssumptionWe = false;
        boolean auctionAssumptionThey = false;

        //System.out.println("numer kontraktu jest:"+contractNumber);
    /*    switch (contractNumber) {
            case 1:
            {auctionAssumptionWe = false;
                auctionAssumptionThey= false; }

            case 2:
            {auctionAssumptionWe = true;
                auctionAssumptionThey= false; }

            case 3:
            {auctionAssumptionWe = false;
                auctionAssumptionThey= true; }

            case 4:
            {auctionAssumptionWe = true;
                auctionAssumptionThey= true; }

            default:
            {
               // System.out.print("jakis błąd w funkcji scoring");
                //throw new PointsDiferentLessThenZeroException("There is not correct case of contract Numner");
            }
        }*/
    if(contractNumber==1) {
        auctionAssumptionWe = false;
        auctionAssumptionThey= false;
    }
    else if(contractNumber ==2) {
        auctionAssumptionWe = true;
        auctionAssumptionThey = false;
    }
    else if(contractNumber ==3) {
        auctionAssumptionWe = false;
        auctionAssumptionThey = true;
    }
    else if (contractNumber==4) {
        auctionAssumptionWe = true;
        auctionAssumptionThey = true;

    }


    this.results = new ResultsOfOneGame(pointsInBothHands,pointsForContract,auctionAssumptionWe,auctionAssumptionThey,fitInOlderColorWe,fitInOlderColorThey);
    }


    public static void main(String [ ] args)
    {
        int pointinHand = 10;
        int pointOfContract = 0;
        boolean fitWe = false;
        boolean fitThey =false;

        try {
           Scoring a = new Scoring(1,pointinHand,pointOfContract,fitWe,fitThey);
        System.out.print("Numer kontraktu jest: "+a.contractNumber+" a wynik jest: "+ a.results.getResults()+" impów.");


            Scoring b = new Scoring(2,pointinHand,pointOfContract,fitWe,fitThey);
            System.out.print("\nNumer kontraktu jest: "+b.contractNumber+" a wynik jest: "+ b.results.getResults()+" impów.");

            Scoring c = new Scoring(3,pointinHand,pointOfContract,fitWe,fitThey);
            System.out.print("\nNumer kontraktu jest: "+c.contractNumber+" a wynik jest: "+ c.results.getResults()+" impów.");

            Scoring d = new Scoring(4,pointinHand,pointOfContract,fitWe,fitThey);
            System.out.print("\nNumer kontraktu jest: "+d.contractNumber+" a wynik jest: "+ d.results.getResults()+" impów.");

        } catch (InvalidNumberOfPointsException e) {
            e.printStackTrace();
        } catch (PointsDiferentLessThenZeroException e) {
            e.printStackTrace();
        }


    }

}
