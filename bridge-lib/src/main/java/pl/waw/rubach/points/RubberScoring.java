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
     * should be done automaticly incriasing of 1 - but how????- gdzieś tak widziałam...
     */
    private int gameID = 0;


    /**
     * description describe parameter of game - if accesible
     */
    private String description;


    /**
     * Result of 4 games
     */
    private int summ=0;


    /**
     * Map number of game with scorring for one game
     */
    private Map<Integer, ResultsOfOneGame> scorringForOneGame = new HashMap<>();


    public RubberScoring(float piH1, float piH2, float piH3, float piH4, int pfC1, int pfC2, int pfC3, int pfC4,
                         boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        this.gameID = gameID+1;
        this.description= " ";

        fillMapRubberScorring(1, piH1, pfC1, fW1, fT1);
        fillMapRubberScorring(2, piH2, pfC2, fW2, fT2);
        fillMapRubberScorring(3, piH3, pfC3, fW3, fT3);
        fillMapRubberScorring(4, piH4, pfC4, fW4, fT4);

        this.summ=getSumm(this);

    }
    public RubberScoring(int result1,int result2,int result3,int result4)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        setSumm(result1+result2+result3+result4);
        System.out.println("Do tej pory  wynik jest: " + getSumm(this) + " \n");
    }

    public RubberScoring(int lev1, String color1, int lev2,  String color2, int lev3, String color3, int lev4, String color4,
                         int piH1, int piH2, int piH3, int piH4, int ntt1, int ntt2, int ntt3, int ntt4,
                         boolean d1,boolean r1, boolean d2, boolean r2, boolean d3, boolean r3, boolean d4, boolean r4,
                         boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws PointsDiferentLessThenZeroException, InvalidNumberOfPointsException, InvalidContractLevelException {

        this.gameID = 1;
        this.description= " ";

        fillMapRubberScorring(1, piH1, lev1, color1, ntt1, d1,r1,fW1,fT1);
        fillMapRubberScorring(2, piH2, lev2, color2, ntt2, d2,r2,fW2,fT2);
        fillMapRubberScorring(3, piH3, lev3, color3, ntt3, d3,r3,fW3,fT3);
        fillMapRubberScorring(4, piH4, lev4, color4, ntt4, d4,r4,fW4,fT4);

        this.summ=getSumm(this);
    }

    public int getSumm(RubberScoring ruberScoring) throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
      return getSumm(ruberScoring,false);
    }
//pyt było static teraz udało się zmienić na nie static nie wiem jak powinno być - chyba lepiej nie static żeby było do każdego elementu ale nie czuję różnicy
    public int getSumm(RubberScoring ruberScoring,boolean print) throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        Map<Integer, ResultsOfOneGame> map = ruberScoring.scorringForOneGame;
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        int s = 0;
        for (Integer key : ptsMapSet) {

            if (map.get(key).getPointsInBothHands() != 0) s = s + map.get(key).getResults();
            if(print)System.out.println("Do tej pory  wynik jest: " + s + " \n");
        }
        return s;
    }



    public static String getRubberScoringAsString(int piH1, int piH2, int piH3, int piH4, int pfC1, int pfC2, int pfC3, int pfC4,
                                                  boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        return getRubberScoringAsString(new RubberScoring(piH1, piH2, piH3, piH4, pfC1, pfC2, pfC3, pfC4, fW1, fW2, fW3, fW4, fT1, fT2, fT3, fT4));

    }

    public static String getRubberScoringAsString(int lev1, String color1, int lev2,  String color2, int lev3, String color3, int lev4, String color4,
                                                  int piH1, int piH2, int piH3, int piH4, int ntt1, int ntt2, int ntt3, int ntt4,
                                                  boolean d1,boolean r1, boolean d2, boolean r2, boolean d3, boolean r3, boolean d4, boolean r4,
                                                  boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException, InvalidContractLevelException {

        return getRubberScoringAsString(new RubberScoring(lev1, color1, lev2, color2, lev3, color3, lev4, color4, piH1, piH2, piH3, piH4, ntt1, ntt2, ntt3, ntt4, d1, r1, d2, r2, d3, r3, d4, r4, fW1, fW2, fW3, fW4, fT1, fT2, fT3, fT4));

    }


    public static String getRubberScoringAsString(RubberScoring ruberScoring) throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        StringBuilder s = new StringBuilder("\n*** Wyniki dla gry numer: "+ruberScoring.getGameID()+".  ***  \n");

        Map<Integer, ResultsOfOneGame> map = ruberScoring.scorringForOneGame;
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        s.append("\n").append(ruberScoring.getDescription());

        for (Integer key : ptsMapSet) {

            s.append("\n dla ").append(key).append(" gry jest ").append(map.get(key).getPointsForContract()).append(" punktów i wynik jest ").append(map.get(key).getResults()).append(" impów");

        }

        s.append("\n\n \t \t***\n");
        return s.toString();
    }



    private void fillMapRubberScorring(int contractNumber, float pointsInBothHands, int pointsForContract, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
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
        ResultsOfOneGame a = new ResultsOfOneGame(pointsInBothHands, pointsForContract, auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);

        scorringForOneGame.put(contractNumber,a );
    }

    private void fillMapRubberScorring(int contractNumber, float pointsInBothHands, int levelOfGame, String color,int numberOfTricksTaken, boolean doub, boolean redouble, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException, InvalidContractLevelException {

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
        PointsForContract b = new PointsForContract(levelOfGame,numberOfTricksTaken,color,doub, redouble, auctionAssumptionWe);
        description = description+ b.getShortDescription()+ "\n";
        ResultsOfOneGame a = new ResultsOfOneGame(pointsInBothHands, b.getCalculatedPointsForContract(), auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);

        scorringForOneGame.put(contractNumber,a );
    }

    //getters

    public int getGameID() {
        return gameID;
    }
    public String getDescription() {
        return description;
    }
    public Map<Integer, ResultsOfOneGame> getScorringForOneGame() {
        return scorringForOneGame;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }


//For easier tests
    public static void main(String[] args) {

        try {
            RubberScoring a = new RubberScoring(20, 21, 22, 23, 110, 110, 110, 110, false, false, false, false, false, false, false, false);

            System.out.println(getRubberScoringAsString(a));
            System.out.println("Końcowy wynik jest: " + a.getSumm(a) + " \n");

            RubberScoring a2 = new RubberScoring(20, 19, 18, 17, -110, -110, -110, -110, false, false, false, false, false, false, false, false);
            System.out.println(getRubberScoringAsString(a2));
            System.out.println("Końcowy wynik jest: " + a.getSumm(a2) + " \n");

            RubberScoring b = new RubberScoring(1, "nt", 1,"nt", 3,"nt",3,"nt",20,21,22,23,7,6,8,9,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
            System.out.println(getRubberScoringAsString(b));

            System.out.println("Końcowy wynik liczony od podstaw jest: " + a.getSumm(b) + " \n");



        } catch (InvalidNumberOfPointsException | PointsDiferentLessThenZeroException |InvalidContractLevelException e) {
            e.printStackTrace();
        }

    }


}



