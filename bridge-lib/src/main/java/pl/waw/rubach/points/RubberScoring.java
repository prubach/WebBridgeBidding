package pl.waw.rubach.points;

import pl.waw.rubach.points.bridgeExeption.BridgeException;
import pl.waw.rubach.points.bridgeExeption.InvalidNumberOfPointsException;
import pl.waw.rubach.points.bridgeExeption.PointsDiferentLessThenZeroException;

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
    private int gameID;

    /**
     * description describe parameter of game - if accesible
     */
    private String description;

    /**
     * Auction Assumption for PlaingPair means if pair is  vulnerable or not vulnerable
     */
    private boolean auctionAssumptionWe;
    /**
     * Auction Assumption for Oponens means if pair is  vulnerable or not vulnerable
     */
    private boolean auctionAssumptionThey;
    /**
     * Map number of game with scorring for one game
     */
    private Map<Integer, ResultsOfOneGame> scorringForOneGame = new HashMap<>();

    /**
     * Result of 4 games
     */
    private int summ;

    //todo powinno się nazywać 4GameImpPoints albo coś takiego bo scorring jak rozumiem to zapis a tu są już punkty ... no i nie Rubber bo to jest dla zapisu robrowego - my błędnie mówimy na 4 gry rober wydaje mi się ...
    //odp jeżeli już to FourGameImpPoints, ale np. przy robrze niekoniecznie będą 4 gry, więc może GameImpPoints
    //po polsku mogło by być Punkty/ZapisPorównawczy i drugi ZapisRobrowy

    public RubberScoring() {
        this(1);
        //this.gameID = gameID + 1;  //todo how to change number to one plus before?
        //odp Tak się nie da to będzie zawsze 1
    }

    //create special number scorring
    public RubberScoring(int gameID) {
        this.gameID = gameID;
        this.description = " Tworzę nową serię 4 gier z konkertnym numerem:  " + gameID + ". \n";
        this.summ = 0;
    }


    //to co mówiłeś przez telefon czyli konstruktor taki który jeszcze nie ma tych parametrów tylko tworzy obiekt a potem zrobić fukncje tak żeby te kolejne wiersze
    //uzupełniało w czasie jak się liczy - wtedy będzie wiedziało co jest do tego rozdania a co nie - o to mi chodziło tylko się pogubiłam!!! na razie zostawię te stare konstruktory

    public RubberScoring(int result1, int result2, int result3, int result4)
            throws BridgeException {
        this();
        setSumm(result1 + result2 + result3 + result4);
        System.out.println("Końcowy wynik gry numer:" + getGameID() + " z podanymi wynikami " + result1 + " " + result2 + " " + result3 + " " + result4 + " jest: " + getSumm() + " \n");
    }


    public RubberScoring(float piH1, float piH2, float piH3, float piH4, int pfC1, int pfC2, int pfC3, int pfC4,
                         boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws BridgeException {
        this();

        fillOneContractFrom4GameSet(1, piH1, pfC1, fW1, fT1);
        fillOneContractFrom4GameSet(2, piH2, pfC2, fW2, fT2);
        fillOneContractFrom4GameSet(3, piH3, pfC3, fW3, fT3);
        fillOneContractFrom4GameSet(4, piH4, pfC4, fW4, fT4);

        this.summ = getSumm();

    }


    public RubberScoring(int lev1, String color1, int lev2, String color2, int lev3, String color3, int lev4, String color4,
                         int piH1, int piH2, int piH3, int piH4, int ntt1, int ntt2, int ntt3, int ntt4,
                         boolean d1, boolean r1, boolean d2, boolean r2, boolean d3, boolean r3, boolean d4, boolean r4,
                         boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws BridgeException {
        this();

        fillOneContractFrom4GameSet(1, piH1, lev1, color1, ntt1, d1, r1, fW1, fT1);
        fillOneContractFrom4GameSet(2, piH2, lev2, color2, ntt2, d2, r2, fW2, fT2);
        fillOneContractFrom4GameSet(3, piH3, lev3, color3, ntt3, d3, r3, fW3, fT3);
        fillOneContractFrom4GameSet(4, piH4, lev4, color4, ntt4, d4, r4, fW4, fT4);

        this.summ = getSumm();
    }


    public void fillOneContractFrom4GameSet(int contractNumber, float pointsInBothHands, int pointsForContract,
                                            boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        fillAssumption(contractNumber);
        ResultsOfOneGame a = new ResultsOfOneGame(pointsInBothHands, pointsForContract, auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);
        scorringForOneGame.put(contractNumber, a);
    }

    public void fillOneContractFrom4GameSet(int contractNumber, ResultsOfOneGame rooG)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        fillAssumption(contractNumber);
        scorringForOneGame.put(contractNumber, rooG);
    }


    public void fillOneContractFrom4GameSet(int contractNumber, float pointsInBothHands, PointsForContract pFC,
                                            boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        fillAssumption(contractNumber);
        description = description + pFC.getShortDescription() + "\n";
        ResultsOfOneGame a = new ResultsOfOneGame(pointsInBothHands, pFC.getCalculatedPointsForContract(), auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);

        scorringForOneGame.put(contractNumber, a);
    }

    public void fillOneContractFrom4GameSet(int contractNumber, float pointsInBothHands, int gameLevel, String suit,
                                            int numberOfTricksTaken, boolean doub, boolean redouble,
                                            boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        fillAssumption(contractNumber);
        PointsForContract pFC = new PointsForContract(gameLevel, suit, doub, redouble, auctionAssumptionWe, numberOfTricksTaken);
        description = description + pFC.getShortDescription() + "\n";
        ResultsOfOneGame rooG = new ResultsOfOneGame(pointsInBothHands, pFC.getCalculatedPointsForContract(), auctionAssumptionWe, auctionAssumptionThey, fitInOlderColorWe, fitInOlderColorThey);

        scorringForOneGame.put(contractNumber, rooG);
    }

    private void fillAssumption(int contractNumber) {

        auctionAssumptionWe = false;
        auctionAssumptionThey = false;
        if (contractNumber == 2) {
            auctionAssumptionWe = true;
        } else if (contractNumber == 3) {
            auctionAssumptionThey = true;
        } else if (contractNumber == 4) {
            auctionAssumptionWe = true;
            auctionAssumptionThey = true;
        }
    }

    public String getRubberScoringAsString() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        StringBuilder s = new StringBuilder("\n*** Wyniki dla gry numer: " + this.getGameID() + ".  ***  \n");

        Map<Integer, ResultsOfOneGame> map = this.scorringForOneGame;
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        s.append("\n").append(this.getDescription());

        for (Integer key : ptsMapSet) {

            s.append("\n dla ").append(key).append(" gry jest ").append(map.get(key).getPointsForContract()).append(" punktów i wynik jest ").append(map.get(key).getResults()).append(" impów");

        }

        s.append("\n\n \t \t***\n");
        return s.toString();
    }

    public int getSumm() throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {
        return getSumm(false);
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public int getSumm(boolean print) throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

        SortedSet<Integer> ptsMapSet = new TreeSet<>(scorringForOneGame.keySet());
        int s = 0;
        for (Integer key : ptsMapSet) {

            if (scorringForOneGame.get(key).getPointsInBothHands() != 0)
                s = s + scorringForOneGame.get(key).getResults();
            if (print) {
                System.out.println("Wynik rozdania " + key + " jest: " + scorringForOneGame.get(key).getResults() + " \n");
                System.out.println("Do tej pory  wynik jest: " + s + " \n");
            }
        }
        return s;
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

    //For easier tests
    public static void main(String[] args) {
        try {
            RubberScoring a = new RubberScoring(20, 21, 22, 23, 110, 110, 110, 110, false, false, false, false, false, false, false, false);
            System.out.println(a.getRubberScoringAsString());
            System.out.println("Końcowy wynik jest: " + a.getSumm(true) + " \n");
/*
            RubberScoring a2 = new RubberScoring(20, 19, 18, 17, -110, -110, -110, -110, false, false, false, false, false, false, false, false);
            System.out.println(a2.getRubberScoringAsString());
            System.out.println("Końcowy wynik jest: " + a2.getSumm() + " \n");

            RubberScoring b = new RubberScoring(1, "nt", 1, "nt", 3, "nt", 3, "nt", 20, 21, 22, 23, 7, 6, 8, 9, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
            System.out.println(b.getRubberScoringAsString());

            System.out.println("Końcowy wynik liczony od podstaw jest: " + b.getSumm() + " \n");

      //      new RubberScoring(3,-5,5,3);*/

            RubberScoring rooG = new RubberScoring(15);
            rooG.fillOneContractFrom4GameSet(1, 20, 110, false, false);
            rooG.fillOneContractFrom4GameSet(2, 21, 110, false, false);
            rooG.fillOneContractFrom4GameSet(3, 22, 110, false, false);
            rooG.fillOneContractFrom4GameSet(4, 23, 110, false, false);

            System.out.println(rooG.getRubberScoringAsString());
            System.out.println("Końcowy wynik jest: " + rooG.getSumm(true) + " \n");


        } catch (BridgeException e) {
            e.printStackTrace();
        }

    }


}



