package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfPointsException;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

//this was RubberScorring
public class FourGameImpScorring {
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
     * rubberSpecialDescription describe parameter of game - if accesible and results describe results of rubber
     */
    private String rubberSpecialDescription, resultsDescription;

    /**
     * Map number of game with scorring for one game
     */
    private Map<Integer, CalculatedImpPointsForOneDeal> scorringForOneGame = new HashMap<>();

    /**
     * Result of 4 games - imp points
     */
    private int summ;

    //powinno się nazywać 4GameImpPoints albo coś takiego bo scorring jak rozumiem to zapis a tu są już punkty ... no i nie Rubber bo to jest dla zapisu robrowego - my błędnie mówimy na 4 gry rober wydaje mi się ...
    //odp jeżeli już to FourGameImpPoints, ale np. przy robrze niekoniecznie będą 4 gry, więc może GameImpPoints
    //ale jak rober to nie będą impy - wydaje mi się ze wtedy to będzie musiało być inne ?  po polsku mogło by być Punkty/ZapisPorównawczy i drugi ZapisRobrowy
    //

    //defut constructor adding number of game 1 - possible use other constructor with special number of game with next constructor
     public FourGameImpScorring() {
        this(1);
       }

    //create special game with special gameID
    public FourGameImpScorring(int gameID) {
        this.gameID = gameID;
        this.rubberSpecialDescription = " \n *** Nowa seria 4 gier z numerem:  " + gameID + ". ***  \n";
        this.resultsDescription = " \nWyniki: \n";
        this.summ = 0;
    }


    public FourGameImpScorring(int result1, int result2, int result3, int result4)
            throws BridgeException {
        this();
        setSumm(result1 + result2 + result3 + result4);
        resultsDescription = ("Końcowy wynik gry numer:" + getGameID() + " z podanymi wynikami " + result1 + " " + result2 + " " + result3 + " " + result4 + " jest: " + getSumm() + " \n");
    }


    //0ld no very usefull  but in tests ...
    public FourGameImpScorring(boolean wp1, boolean wp2, boolean wp3, boolean wp4,
                               float piH1, float piH2, float piH3, float piH4,
                               int sp1, int sp2, int sp3, int sp4,
                               boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws BridgeException {
        this();

        int result1 = fillOneContractFrom4GameSet(1, wp1, piH1, sp1, fW1, fT1);
        int result2 = fillOneContractFrom4GameSet(2, wp2, piH2, sp2, fW2, fT2);
        int result3 = fillOneContractFrom4GameSet(3, wp3, piH3, sp3, fW3, fT3);
        int result4 = fillOneContractFrom4GameSet(4, wp4, piH4, sp4, fW4, fT4);

        setSumm(result1 + result2 + result3 + result4);
        resultsDescription = ("Końcowy wynik gry numer:" + getGameID() + " z obliczonymi wynikami " + result1 + " " + result2 + " " + result3 + " " + result4 + " jest: " + getSumm() + " \n");

    }


    //0ld no very usefull  but in tests ...
    public FourGameImpScorring(boolean wp1, boolean wp2, boolean wp3, boolean wp4, int lev1, String color1, int lev2, String color2, int lev3, String color3, int lev4, String color4,
                               float piH1, float piH2, float piH3, float piH4, int ntt1, int ntt2, int ntt3, int ntt4,
                               boolean d1, boolean r1, boolean d2, boolean r2, boolean d3, boolean r3, boolean d4, boolean r4,
                               boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws BridgeException {
        this();

        int result1 = fillOneContractFrom4GameSet(1, wp1, piH1, lev1, color1, ntt1, d1, r1, fW1, fT1);
        int result2 = fillOneContractFrom4GameSet(2, wp2, piH2, lev2, color2, ntt2, d2, r2, fW2, fT2);
        int result3 = fillOneContractFrom4GameSet(3, wp3, piH3, lev3, color3, ntt3, d3, r3, fW3, fT3);
        int result4 = fillOneContractFrom4GameSet(4, wp4, piH4, lev4, color4, ntt4, d4, r4, fW4, fT4);

        setSumm(result1 + result2 + result3 + result4);
        resultsDescription = ("Końcowy wynik gry numer:" + getGameID() + " na podstawie danych podstawowych wprowadzonych na raz,  z obliczonymi wynikami " + result1 + " " + result2 + " " + result3 + " " + result4 + " jest: " + getSumm() + " \n");

    }


    /**
     * Function to fill ruberScoring wiht results of each game
     *
     * @param contractNumber is numnber of contract
     * @param cIPfoDforWe    is object where results for one deal is calculated
     * @return results of this particular game (one deal)
     * @throws InvalidNumberOfPointsException if number of points is wrong
     * @throws BridgeException                if parameter are wrong
     */
    public int fillOneContractFrom4GameSet(int contractNumber,
                                           CalculatedImpPointsForOneDeal cIPfoDforWe)
            throws BridgeException {

        scorringForOneGame.put(contractNumber, cIPfoDforWe);
        setSumm();
        return cIPfoDforWe.getResults();
    }

    public int fillOneContractFrom4GameSet(int contractNumber, boolean whoPlay, float pointsInBothHandsWe,
                                           int scoringPointsWe,
                                           boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        return fillOneContractFrom4GameSet(contractNumber,
                new CalculatedImpPointsForOneDeal(whoPlay, whoPlay ? pointsInBothHandsWe : 40 - pointsInBothHandsWe,
                        whoPlay ? scoringPointsWe : -scoringPointsWe,
                        //pyt czy bardzie elegancko jest z tym enumem, czy wcześniejsza wersja (zakomentowana) była lepsza?
                        //     whoPlay ? fillAssumption(contractNumber)[0] : fillAssumption(contractNumber)[1],
                        //     whoPlay ? fillAssumption(contractNumber)[1] : fillAssumption(contractNumber)[0],
                        whoPlay ? fillAssumptionA(contractNumber).areWeVunerable() : fillAssumptionA(contractNumber).areTheyVunerable(),
                        whoPlay ? fillAssumptionA(contractNumber).areTheyVunerable() : fillAssumptionA(contractNumber).areWeVunerable(),
                        whoPlay ? fitInOlderColorWe : fitInOlderColorThey, whoPlay ? fitInOlderColorThey : fitInOlderColorWe));

    }


    public int fillOneContractFrom4GameSet(int contractNumber, boolean whoPlay, float pointsInBothHandsWe,
                                           DuplicateBridgeScoring pFC,
                                           boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {
        rubberSpecialDescription = rubberSpecialDescription + pFC.getShortDescription() + "\n";
        return fillOneContractFrom4GameSet(contractNumber,
                new CalculatedImpPointsForOneDeal(whoPlay, whoPlay ? pointsInBothHandsWe : 40 - pointsInBothHandsWe,
                        whoPlay ? pFC.getContractScoringPoints() : -pFC.getContractScoringPointsWe(),
                        //pyt czy bardzie elegancko jest z tym enumem, czy wcześniejsza wersja (zakomentowana) była lepsza?
                        //     whoPlay ? fillAssumption(contractNumber)[0] : fillAssumption(contractNumber)[1],
                        //     whoPlay ? fillAssumption(contractNumber)[1] : fillAssumption(contractNumber)[0],
                        whoPlay ? fillAssumptionA(contractNumber).areWeVunerable() : fillAssumptionA(contractNumber).areTheyVunerable(),
                        whoPlay ? fillAssumptionA(contractNumber).areTheyVunerable() : fillAssumptionA(contractNumber).areWeVunerable(), whoPlay ? fitInOlderColorWe : fitInOlderColorThey, whoPlay ? fitInOlderColorThey : fitInOlderColorWe));
    }


    public int fillOneContractFrom4GameSet(int contractNumber, boolean whoPlay, float pointsInBothHandsWe,
                                           int gameLevel, String suit, int numberOfTricksTakenWe, boolean isdouble, boolean isredouble,
                                           boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {
        return fillOneContractFrom4GameSet(contractNumber, whoPlay, pointsInBothHandsWe,
                new DuplicateBridgeScoring(gameLevel, suit, isdouble, isredouble,
                        // whoPlay ? fillAssumption(contractNumber)[0] : fillAssumption(contractNumber)[1],
                        whoPlay ? fillAssumptionA(contractNumber).areWeVunerable() : fillAssumptionA(contractNumber).areTheyVunerable(),
                        whoPlay ? numberOfTricksTakenWe : 13 - numberOfTricksTakenWe),
                fitInOlderColorWe, fitInOlderColorThey);


    }



    private Assumption fillAssumptionA(int contractNumber) throws BridgeException {
        for (Assumption a : Assumption.values())
            if (contractNumber == a.getContractNumber()) return a;

        throw new BridgeException(contractNumber);
    }


    public String getRubberScoringAsString() throws BridgeException {
        StringBuilder s = new StringBuilder("\n*** Wyniki dla gry numer: " + this.getGameID() + ".  ***  \n");

        Map<Integer, CalculatedImpPointsForOneDeal> map = this.scorringForOneGame;
        SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
        //  s.append("\n").append(this.getRubberSpecialDescription());

        for (Integer key : ptsMapSet) {

            s.append("\n dla ").append(key).append(" gry jest ").append(map.get(key).getContractScoringPoints()).append(" punktów i wynik jest ").append(map.get(key).getResults()).append(" impów");

        }

        s.append("\n\n \t \t***\n");
        return s.toString();
    }

    public int getSumm() {
        return this.summ;
    }

    private void setSumm(int summ) {
        this.summ = summ;
    }

    public void setSumm() {
        int summ = 0;
        for (int p : new TreeSet<Integer>(scorringForOneGame.keySet())) {
            summ = summ + scorringForOneGame.get(p).getResults();
        }

        this.summ = summ;
    }

    public int getSummCalulatedFromScorringForOneGame() throws BridgeException {
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

    public String getRubberSpecialDescription() {
        return rubberSpecialDescription;
    }

    public void setRubberSpecialDescription(String rubberSpecialDescription) {
        this.rubberSpecialDescription = rubberSpecialDescription;
    }

    public Map<Integer, CalculatedImpPointsForOneDeal> getScorringForOneGame() {
        return scorringForOneGame;
    }

    public String getResultsDescription() {
        return resultsDescription;
    }

    public void setResultsDescription(String resultsDescription) {
        this.resultsDescription = resultsDescription;
    }


}



