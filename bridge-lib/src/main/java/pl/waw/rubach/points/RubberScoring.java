package pl.waw.rubach.points;

import java.util.HashMap;
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
     * should be done automaticly? but how????- gdzieś tak widziałam...
     */
    private int gameID =0;

    private Map<Integer, ResultsOfOneGame> scorringForOneGame = new HashMap<>();




 public RubberScoring(float piH1, float piH2, float piH3, float piH4, int pfC1, int pfC2, int pfC3, int pfC4,
                          boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        this.gameID = 1;


        fillMapRubberScorring(1,piH1,pfC1,fW1,fT1);
        fillMapRubberScorring(2,piH2,pfC2,fW2,fT2);
        fillMapRubberScorring(3,piH3,pfC3,fW3,fT3);
        fillMapRubberScorring(4,piH4,pfC4,fW4,fT4);

    }


    private void fillMapRubberScorring(int contractNumber,float pointsInBothHands, int pointsForContract, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
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

        scorringForOneGame.put(contractNumber,new ResultsOfOneGame(pointsInBothHands, pointsForContract, auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey));
    }

    public static int getSumm(RubberScoring ruberScoring )throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        Map<Integer, ResultsOfOneGame> map = ruberScoring.scorringForOneGame;
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        int s=0;
        for (Integer key : ptsMapSet) {

            if(map.get(key).getPointsInBothHands() !=0) s  = s+map.get(key).getResults();
            System.out.println("Do tej pory  wynik jest: "+ s +" \n");
        }
        return s;
    };


    public static String getRubberScoringAsString(int piH1,int piH2, int piH3, int piH4, int pfC1, int pfC2, int pfC3, int pfC4,
                                          boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        return getRubberScoringAsString(new RubberScoring(piH1,piH2,piH3,piH4,pfC1,pfC2,pfC3,pfC4,fW1,fW2,fW3,fW4,fT1,fT2,fT3,fT4));

    }

    public static String getRubberScoringAsString(RubberScoring ruberScoring) throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        StringBuilder s = new StringBuilder("\n*** Wyniki dla gry ****  \n");
        Map<Integer, ResultsOfOneGame> map = ruberScoring.scorringForOneGame;
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());

        for (Integer key : ptsMapSet) {
            s.append("\n dla ").append(key).append(" gry jest ").append(map.get(key).getPointsForContract()).append(" punktów i wynik jest ").append(map.get(key).getResults()).append(" impów");

        }
        s.append("\n\n \t \t***\n");
        return s.toString();
    };

      public static void main(String [ ] args)
    {

        try {
            RubberScoring a = new RubberScoring(20,21,22,23,110,110,110,110,false,false,false,false,false,false,false,false);

            System.out.println(getRubberScoringAsString(a));
            System.out.println("Końcowy wynik jest: "+ getSumm(a) +" \n");

            RubberScoring a2 = new RubberScoring(20,19,18,17,-110,-110,-110,-110,false,false,false,false,false,false,false,false);
            System.out.println(getRubberScoringAsString(a2));
            System.out.println("Końcowy wynik jest: "+ getSumm(a2) +" \n");



        } catch (InvalidNumberOfPointsException | PointsDiferentLessThenZeroException e) {
            e.printStackTrace();
        }

    }



}



