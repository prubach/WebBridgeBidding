package pl.waw.rubach.scoring;

import pl.waw.rubach.points.InvalidNumberOfPointsException;
import pl.waw.rubach.points.PointsDiferentLessThenZeroException;
import pl.waw.rubach.points.ResultsOfOneGame;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class RubberScoring {

    /**
     * game (for contract) ID - not sure if needed but probably for something?  - could be added in future:
     * date and time
     * place
     * names of players
     * photos etc ...
     * should be done automaticly? - gdzieś tak widziałam...
     */
    private int gameID;

    private Map<Integer, ResultsOfOneGame> scorringForOneGame;



    private RubberScoring(int piH1, int piH2, int piH3, int piH4, int pfC1, int pfC2, int pfC3, int pfC4,
                          boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        this.gameID = 1;


        fillMapRubberScorring(1,piH1,pfC1,fW1,fT1);
        fillMapRubberScorring(2,piH2,pfC2,fW2,fT2);
        fillMapRubberScorring(3,piH3,pfC3,fW3,fT3);
        fillMapRubberScorring(4,piH4,pfC4,fW4,fT4);

    }


    private void fillMapRubberScorring(int contractNumber,int pointsInBothHands, int pointsForContract, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        boolean auctionAssumptionWe = false;
        boolean auctionAssumptionThey = false;



        {
            if (contractNumber == 1) {
                auctionAssumptionWe = false;
                auctionAssumptionThey = false;
            } else if (contractNumber == 2) {
                auctionAssumptionWe = true;
                auctionAssumptionThey = false;
            } else if (contractNumber == 3) {
                auctionAssumptionWe = false;
                auctionAssumptionThey = true;
            } else if (contractNumber == 4) {
                auctionAssumptionWe = true;
                auctionAssumptionThey = true;

            }
        }
        scorringForOneGame.put(contractNumber, new ResultsOfOneGame(pointsInBothHands, pointsForContract, auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey));


    }

    public static String getRubberScoringAsString(int piH1,int piH2, int piH3, int piH4, int pfC1, int pfC2, int pfC3, int pfC4,
                                          boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        StringBuilder s = new StringBuilder("*** Wyniki dla gry \n");

        Map<Integer, ResultsOfOneGame> map = new RubberScoring(piH1,piH2,piH3,piH4,pfC1,pfC2,pfC3,pfC4,fW1,fW2,fW3,fW4,fT1,fT2,fT3,fT4).scorringForOneGame;
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());

        for (Integer key : ptsMapSet) {
            s.append("\n dla ").append(key).append(" Wyniki oczekiwane ").append(map.get(key)).append(" : jkjkjkj");

        }
        return s.toString();
    }


      public static void main(String [ ] args)
    {


        try {
            RubberScoring a = new RubberScoring(20,21,22,23,110,110,110,110,false,false,false,false,false,false,false,false);
            getRubberScoringAsString(20,21,22,23,110,110,110,110,false,false,false,false,false,false,false,false);



        } catch (InvalidNumberOfPointsException | PointsDiferentLessThenZeroException e) {
            e.printStackTrace();
        }


    }



}



