package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfPointsException;
import pl.waw.rubach.points.exceptions.InvalidParameterException;

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
     * rubberDescription describe parameter of game - if accesible
     */
    private String rubberDescription, resultsDescription;

    /**
     * Pair of Auction Assumption according contract number (1 none, 2, we, 3, they, 4 both)
     */
    private boolean[] auctionAssumption={false, false};

    /**
     * Map number of game with scorring for one game
     */
    private Map<Integer, CalculatedImpPointsForOneDeal> scorringForOneGame = new HashMap<>();

    /**
     * Result of 4 games
     */
    private int summ;

    //todo powinno się nazywać 4GameImpPoints albo coś takiego bo scorring jak rozumiem to zapis a tu są już punkty ... no i nie Rubber bo to jest dla zapisu robrowego - my błędnie mówimy na 4 gry rober wydaje mi się ...
    //odp jeżeli już to FourGameImpPoints, ale np. przy robrze niekoniecznie będą 4 gry, więc może GameImpPoints
    //odp :2  ale jak rober to nie będą impy - wydaje mi się ze wtedy to będzie musiało być inne ?
    //po polsku mogło by być Punkty/ZapisPorównawczy i drugi ZapisRobrowy
    //

    public RubberScoring() {
        this(1);
        //this.gameID = gameID + 1;  //todo how to change number to one plus before?
        //odp Tak się nie da to będzie zawsze 1
    }

    //create special number scorring
    public RubberScoring(int gameID) {
        this.gameID = gameID;
        this.rubberDescription = " Tworzę nową serię 4 gier z konkertnym numerem:  " + gameID + ". \n";
        this.resultsDescription = " Wyniki: \n";
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


    public RubberScoring(boolean wp1, boolean wp2, boolean wp3, boolean wp4, int lev1, String color1, int lev2, String color2, int lev3, String color3, int lev4, String color4,
                         int piH1, int piH2, int piH3, int piH4, int ntt1, int ntt2, int ntt3, int ntt4,
                         boolean d1, boolean r1, boolean d2, boolean r2, boolean d3, boolean r3, boolean d4, boolean r4,
                         boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws BridgeException {
        this();

        fillOneContractFrom4GameSet(1, wp1, piH1, lev1, color1, ntt1, d1, r1, fW1, fT1);
        fillOneContractFrom4GameSet(2, wp2, piH2, lev2, color2, ntt2, d2, r2, fW2, fT2);
        fillOneContractFrom4GameSet(3, wp3, piH3, lev3, color3, ntt3, d3, r3, fW3, fT3);
        fillOneContractFrom4GameSet(4, wp4, piH4, lev4, color4, ntt4, d4, r4, fW4, fT4);

        this.summ = getSumm();
    }


    public void fillOneContractFrom4GameSet(int contractNumber, float pointsInBothHands, int pointsForContract,
                                            boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException,  InvalidParameterException {

        fillAssumption(contractNumber);
        CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(pointsInBothHands, pointsForContract, auctionAssumption[0], auctionAssumption[1], fitInOlderColorWe, fitInOlderColorThey);
        scorringForOneGame.put(contractNumber, a);
    }

    public void fillOneContractFrom4GameSet(int contractNumber, CalculatedImpPointsForOneDeal rooG)
            throws InvalidNumberOfPointsException, InvalidParameterException{
        fillAssumption(contractNumber);
        scorringForOneGame.put(contractNumber, rooG);
    }


    public void fillOneContractFrom4GameSet( int contractNumber, boolean whoPlay, float pointsInBothHands, DuplicateBridgeScoring pFC,
                                            boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        fillAssumption(contractNumber);
        rubberDescription = rubberDescription + pFC.getShortDescription() + "\n";
        CalculatedImpPointsForOneDeal a = new CalculatedImpPointsForOneDeal(whoPlay,pointsInBothHands, pFC.getContractScoringPoints(), auctionAssumption[0], auctionAssumption[1], fitInOlderColorWe, fitInOlderColorThey);

        scorringForOneGame.put(contractNumber, a);
    }

    public void fillOneContractFrom4GameSet(int contractNumber, boolean whoPlay, float pointsInBothHands, int gameLevel, String suit,
                                            int numberOfTricksTaken, boolean doub, boolean redouble,
                                            boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        fillAssumption(contractNumber);

        //todo check if is good with number of tricks - shoudl be taken by declarer and imput is we !!! also assumtpto to check
        DuplicateBridgeScoring pFC = new DuplicateBridgeScoring(gameLevel, suit, doub, redouble, auctionAssumption[0], numberOfTricksTaken);
        rubberDescription = rubberDescription + pFC.getShortDescription() + "\n";
        CalculatedImpPointsForOneDeal rooG = new CalculatedImpPointsForOneDeal(pointsInBothHands, pFC.getContractScoringPoints(), auctionAssumption[0], auctionAssumption[1], fitInOlderColorWe, fitInOlderColorThey);

        scorringForOneGame.put(contractNumber, rooG);
    }

    private void fillAssumption(int contractNumber) {

        auctionAssumption[0] = false;
        auctionAssumption[1] = false;
        if (contractNumber == 2) {
            auctionAssumption[0]  = true;
        } else if (contractNumber == 3) {
            auctionAssumption[1] = true;
        } else if (contractNumber == 4) {
            auctionAssumption[0] = true;
            auctionAssumption[1]  = true;
        }
    }

    public String getRubberScoringAsString() throws InvalidNumberOfPointsException, InvalidParameterException {
        StringBuilder s = new StringBuilder("\n*** Wyniki dla gry numer: " + this.getGameID() + ".  ***  \n");

        Map<Integer, CalculatedImpPointsForOneDeal> map = this.scorringForOneGame;
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        s.append("\n").append(this.getRubberDescription());

        for (Integer key : ptsMapSet) {

            s.append("\n dla ").append(key).append(" gry jest ").append(map.get(key).getContractScoringPoints()).append(" punktów i wynik jest ").append(map.get(key).getResults()).append(" impów");

        }

        s.append("\n\n \t \t***\n");
        return s.toString();
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public int getSumm() throws InvalidNumberOfPointsException, InvalidParameterException {

        SortedSet<Integer> ptsMapSet = new TreeSet<>(scorringForOneGame.keySet());
        int s = 0;
        for (Integer key : ptsMapSet) {

            if (scorringForOneGame.get(key).getPointsInBothDeclarerHands() != 0)
                s = s + scorringForOneGame.get(key).getResults();

               setResultsDescription(getResultsDescription() + "Wynik rozdania " + key + " jest: " + scorringForOneGame.get(key).getResults() + " \t Do tej pory  wynik jest: " + s + " \n");

        }
        return s;
    }




    public int getGameID() {
        return gameID;
    }

    public String getRubberDescription() {
        return rubberDescription;
    }

    public Map<Integer, CalculatedImpPointsForOneDeal> getScorringForOneGame() {
        return scorringForOneGame;
    }

    public void setRubberDescription(String rubberDescription) {
        this.rubberDescription = rubberDescription;
    }

    public String getResultsDescription() {
        return resultsDescription;
    }

    public void setResultsDescription(String resultsDescription) {
        this.resultsDescription = resultsDescription;
    }



    //**********************************************************
    //      For easier tests of rubberDescription etc
    public static void main(String[] args) {
        try {
            DuplicateBridgeScoring dbs = new DuplicateBridgeScoring(3,"nt",1,false,9);
            System.out.print(dbs.getShortDescription());
            System.out.print(dbs.getDescription());
            System.out.print("Za powżysze rodzanie uzyskano: "+dbs.getContractScoringPoints());

            CalculatedImpPointsForOneDeal ipr2 = new CalculatedImpPointsForOneDeal(true,20, dbs.getContractScoringPoints(),false, false, false, false);
            CalculatedImpPointsForOneDeal ipr1 = new CalculatedImpPointsForOneDeal(true,20,400,false, false, false, false);
            CalculatedImpPointsForOneDeal ipr0 = new CalculatedImpPointsForOneDeal(true,20,3, "nt", 1, 9, false,false,false,false);

            System.out.println("\n 1Końcowy wynik jednego rozdania jest: " + ipr2.getResults() + " \n");
            System.out.println("Końcowy wynik jednego rozdania dla ustalonej liczby punktów za rodanie jest: " + ipr1.getResults() + " \n");
            System.out.println("Końcowy wynik jednego rozdania liczony od podstaw jest: " + ipr0.getResults() + " \n");



        //    CalculatedImpPointsForOneDeal ipr22 = new CalculatedImpPointsForOneDeal(false,20, dbs.getContractScoringPoints(),false, false, false, false);
       //     CalculatedImpPointsForOneDeal ipr12 = new CalculatedImpPointsForOneDeal(false,20,400,false, false, false, false);
       //     CalculatedImpPointsForOneDeal ipr02 = new CalculatedImpPointsForOneDeal(false,20,3, "nt", 1, 4, false,false,false,false);

         //   System.out.println("\n Końcowy wynik jednego rozdania jest: " + ipr22.getResults() + " \n");
         //   System.out.println("\nKońcowy wynik jednego rozdania dla ustalonej liczby punktów za rodanie jest: " + ipr12.getResults() + " \n");
        //    System.out.println("\nKońcowy wynik jednego rozdania liczony od podstaw jest: " + ipr02.getResults() + " \n");
            //*******************************************

        //    RubberScoring a = new RubberScoring(20, 21, 22, 23, 110, 110, 110, 110, false, false, false, false, false, false, false, false);
     //       System.out.println(a.getRubberScoringAsString());
     //       System.out.println("Końcowy wynik jest: " + a.getSumm() + " \n");
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
            System.out.println("Akuku: "+rooG.getSumm());
           System.out.println(rooG.getResultsDescription());


        } catch (BridgeException e) {
            e.printStackTrace();
        }

    }


}



