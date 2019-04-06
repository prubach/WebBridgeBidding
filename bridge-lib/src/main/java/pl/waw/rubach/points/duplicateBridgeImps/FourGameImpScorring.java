package pl.waw.rubach.points.duplicateBridgeImps;

import pl.waw.rubach.points.AbstractWholeGameScorring;
import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static pl.waw.rubach.points.duplicateBridgeImps.Assumption.fillAssumptionThey;
import static pl.waw.rubach.points.duplicateBridgeImps.Assumption.fillAssumptionWe;

//this was RubberScorring
public class FourGameImpScorring extends AbstractWholeGameScorring {


    /**
     * Map number of game with scorring for one game
     */
    protected Map<Integer, CalculatedImpPointsForOneDeal> scorringForOneGame = new HashMap<>();


    //powinno się nazywać 4GameImpPoints albo coś takiego bo scorring jak rozumiem to zapis a tu są już punkty ... no i nie Rubber bo to jest dla zapisu robrowego - my błędnie mówimy na 4 gry rober wydaje mi się ...
    //odp jeżeli już to FourGameImpPoints, ale np. przy robrze niekoniecznie będą 4 gry, więc może GameImpPoints
    //ale jak rober to nie będą impy - wydaje mi się ze wtedy to będzie musiało być inne ?  po polsku mogło by być Punkty/ZapisPorównawczy i drugi ZapisRobrowy
    //

    public FourGameImpScorring() {
        this(1);
    }

    public FourGameImpScorring(int gameID) {
        super(gameID,"na impy");
        setGameType("IMP");
    }


    public FourGameImpScorring(int result1, int result2, int result3, int result4) {
        this();
        setSumm(result1 + result2 + result3 + result4);
        setResultsDescription("Końcowy wynik gry na impy numer:" + getGameID() + " z podanymi wynikami " + result1 + " " + result2 + " " + result3 + " " + result4 + " jest: " + getSumm() + " \n");
    }


    //0ld no very usefull  but in tests ...
    public FourGameImpScorring(boolean wp1, boolean wp2, boolean wp3, boolean wp4,
                               float piH1, float piH2, float piH3, float piH4,
                               int sp1, int sp2, int sp3, int sp4,
                               boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws BridgeException {
        this();

        int result1 = fillOneContract(1, wp1, piH1, sp1, fW1, fT1);
        int result2 = fillOneContract(2, wp2, piH2, sp2, fW2, fT2);
        int result3 = fillOneContract(3, wp3, piH3, sp3, fW3, fT3);
        int result4 = fillOneContract(4, wp4, piH4, sp4, fW4, fT4);

        setSumm(result1 + result2 + result3 + result4);
        setResultsDescription("Końcowy wynik gry numer:" + getGameID() + " z obliczonymi wynikami " + result1 + " " + result2 + " " + result3 + " " + result4 + " jest: " + getSumm() + " \n");

    }


    //0ld no very usefull  but in tests ...
    public FourGameImpScorring(boolean wp1, boolean wp2, boolean wp3, boolean wp4, int lev1, String color1, int lev2, String color2, int lev3, String color3, int lev4, String color4,
                               float piH1, float piH2, float piH3, float piH4, int ntt1, int ntt2, int ntt3, int ntt4,
                               boolean d1, boolean r1, boolean d2, boolean r2, boolean d3, boolean r3, boolean d4, boolean r4,
                               boolean fW1, boolean fW2, boolean fW3, boolean fW4, boolean fT1, boolean fT2, boolean fT3, boolean fT4)
            throws BridgeException {
        this();

        int result1 = fillOneContract(1, wp1, piH1, lev1, color1, d1, r1, ntt1, fW1, fT1);
        int result2 = fillOneContract(2, wp2, piH2, lev2, color2, d2, r2, ntt2, fW2, fT2);
        int result3 = fillOneContract(3, wp3, piH3, lev3, color3, d3, r3, ntt3, fW3, fT3);
        int result4 = fillOneContract(4, wp4, piH4, lev4, color4, d4, r4, ntt4, fW4, fT4);

        setSumm(result1 + result2 + result3 + result4);
        setResultsDescription("Końcowy wynik gry numer:" + getGameID() + " na podstawie danych podstawowych wprowadzonych na raz,  z obliczonymi wynikami " + result1 + " " + result2 + " " + result3 + " " + result4 + " jest: " + getSumm() + " \n");

    }

    public int fillOneContract(boolean whoPlay, int contractLevel, String contractSuit,
                               boolean isContractDouble, boolean isContractRedouble,
                               int numberOfTrickTakenByDeclarer) throws BridgeException {
        int contractNumber = 1; //askForContractNumber();

        return fillOneContract(contractNumber,whoPlay,20,contractLevel,contractSuit,
                isContractDouble,isContractRedouble,numberOfTrickTakenByDeclarer,true,true);
    }

    /**
     * Function to fill ruberScoring wiht results of each game
     *
     * @param contractNumber is numnber of contract
     * @param cIPfoDforWe    is object where results for one deal is calculated
     * @return results of this particular game (one deal)
     */
    public int fillOneContract(int contractNumber, CalculatedImpPointsForOneDeal cIPfoDforWe) {

        // pyt co jest bardziej elegancko put po prostu czy ten setter?
        setScorringForOneGame(contractNumber, cIPfoDforWe);
        //scorringForOneGame.put(contractNumber, cIPfoDforWe);
        setSumm();
    //    return cIPfoDforWe.getDeclarerResluts();
        return cIPfoDforWe.getResultsWe(cIPfoDforWe.areWePlay());
    }

    /**
     * Function to fill ruberScoring wiht results of each game
     *
     * @param contractNumber      is numnber of contract with some othrer parameter:
     * @param whoPlay             (indicates who play game)
     * @param pointsInBothHandsWe (indicates how many points are in our hands)
     * @param scoringPointsWe     (indicates how many poinst are for game)
     * @param fitInOlderColorWe   describe if it is fit or not
     * @param fitInOlderColorThey describe if it is fit or not
     * @return results of this particular game (one deal)
     * @throws BridgeException if parameter are wrong
     */
    public int fillOneContract(int contractNumber, boolean whoPlay, float pointsInBothHandsWe,
                               int scoringPointsWe,
                               boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {

        return fillOneContract(contractNumber,
                new CalculatedImpPointsForOneDeal(whoPlay, whoPlay ? pointsInBothHandsWe : 40 - pointsInBothHandsWe,
                        whoPlay ? scoringPointsWe : -scoringPointsWe,
                        //pyt czy bardzie elegancko jest z tym enumem, czy wcześniejsza wersja (zakomentowana) była lepsza? (to samo w dwóch poniższych wariantach funkcji
                        //whoPlay ? fillAssumption(contractNumber)[0] : fillAssumption(contractNumber)[1],
                        //whoPlay ? fillAssumption(contractNumber)[1] : fillAssumption(contractNumber)[0],
                        whoPlay ? fillAssumptionWe(contractNumber) : fillAssumptionThey(contractNumber),
                        whoPlay ? fillAssumptionThey(contractNumber) : fillAssumptionWe(contractNumber),
                        whoPlay ? fitInOlderColorWe : fitInOlderColorThey, whoPlay ? fitInOlderColorThey : fitInOlderColorWe));

    }

    /**
     * Function to fill ruberScoring wiht results of each game
     *
     * @param contractNumber      is numnber of contract with some othrer parameter:
     * @param whoPlay             (indicates who play game)
     * @param pointsInBothHandsWe (indicates how many points are in our hands)
     * @param dBS                 is object with all calculated points
     * @param fitInOlderColorWe   describe if it is fit or not
     * @param fitInOlderColorThey describe if it is fit or not
     * @return results of this particular game (one deal)
     * @throws BridgeException if parameter are wrong
     */
    public int fillOneContract(int contractNumber, boolean whoPlay, float pointsInBothHandsWe,
                               DuplicateBridgeScoring dBS,
                               boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {
        setRubberSpecialDescription(getRubberSpecialDescription() + dBS.getShortDescription() + "\n");
        return fillOneContract(contractNumber,
                new CalculatedImpPointsForOneDeal(whoPlay, whoPlay ? pointsInBothHandsWe : 40 - pointsInBothHandsWe,
                       // whoPlay ? dBS.getDeclarerContractScoringPoints() : -
                        dBS.getContractScoringPointsWe(whoPlay),
                        //whoPlay ? fillAssumption(contractNumber)[0] : fillAssumption(contractNumber)[1],
                        //whoPlay ? fillAssumption(contractNumber)[1] : fillAssumption(contractNumber)[0],
                        whoPlay ? fillAssumptionWe(contractNumber) : fillAssumptionThey(contractNumber),
                        whoPlay ? fillAssumptionThey(contractNumber) : fillAssumptionWe(contractNumber),
                        whoPlay ? fitInOlderColorWe : fitInOlderColorThey, whoPlay ? fitInOlderColorThey : fitInOlderColorWe));
    }

    /**
     * Function to fill ruberScoring wiht results of each game
     *
     * @param contractNumber        is numnber of contract with some othrer parameter:
     * @param whoPlay               (indicates who play game)
     * @param pointsInBothHandsWe   (indicates how many points are in our hands)
     * @param gameLevel             having game level
     * @param suit                  is suit of game
     * @param isdouble              indicates if it is double contract or
     * @param isredouble            is redouble
     * @param numberOfTricksTakenWe is number of tricck taken by we
     * @param fitInOlderColorWe     describe if it is fit or not
     * @param fitInOlderColorThey   describe if it is fit or not
     * @return results of this particular game (one deal)
     * @throws BridgeException if parameter are wrong
     */
    public int fillOneContract(int contractNumber, boolean whoPlay, float pointsInBothHandsWe,
                               int gameLevel, String suit, boolean isdouble, boolean isredouble, int numberOfTricksTakenWe,
                               boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws BridgeException {
        return fillOneContract(contractNumber, whoPlay, pointsInBothHandsWe,
                new DuplicateBridgeScoring(gameLevel, suit, isdouble, isredouble,
                        // whoPlay ? fillAssumption(contractNumber)[0] : fillAssumption(contractNumber)[1],
                      //  whoPlay ? fillAssumptionA(contractNumber).areWeVunerable() : fillAssumptionA(contractNumber).areTheyVunerable(),
                        whoPlay ? fillAssumptionWe(contractNumber) : fillAssumptionThey(contractNumber),
                        whoPlay ? numberOfTricksTakenWe : 13 - numberOfTricksTakenWe),
                fitInOlderColorWe, fitInOlderColorThey);


    }


  /*  public static Assumption fillAssumptionA(int contractNumber) throws BridgeException {
        for (Assumption a : Assumption.values())
            if (contractNumber == a.getContractNumber()) return a;

        throw new BridgeException(contractNumber);
    }
*/

    public String getGameScoringAsString() {
        StringBuilder s = new StringBuilder("\n*** Wyniki dla gry numer: " + getGameID() + ".  ***  \n");
        //  s.append("\n").append(this.getRubberSpecialDescription());
        for (Integer key : new TreeSet<>(getScorringForOneGame().keySet())) {
            s.append("\n dla ").append(key).append(" gry jest (dla rozgrywającego: ").append(getScorringForOneGame().get(key).getDeclarerContractScoringPoints())
                    .append(" punktów i wynik jest ").append(getScorringForOneGame().get(key).getDeclarerResluts()).append(" impów");

        }

        s.append("\n\n \t \t***\n");
        return s.toString();
    }


    private void setScorringForOneGame(Integer contractNumber, CalculatedImpPointsForOneDeal cIPfOD) {
        this.scorringForOneGame.put(contractNumber, cIPfOD);
    }

    public Map<Integer, CalculatedImpPointsForOneDeal > getScorringForOneGame() {
        return scorringForOneGame;
    }


    protected void setSumm() {
        int summ = 0;
        for (int key : new TreeSet<>(getScorringForOneGame().keySet())) {
//            if (scorringForOneGame.get(key).getPointsInBothDeclarerHands() != 0)
            summ = summ + getScorringForOneGame().get(key).getResultsWe(getScorringForOneGame().get(key).areWePlay(),getScorringForOneGame().get(key).getDeclarerResluts());
        //    summ = summ + getScorringForOneGame().get(key).getDeclarerResluts();

            setResultsDescription(getResultsDescription() + "Wynik rozdania " + key + " jest: "
                    + getScorringForOneGame().get(key).getResultsWe(getScorringForOneGame().get(key).areWePlay(),getScorringForOneGame().get(key).getDeclarerResluts())
                    + " \t Do tej pory  wynik jest: " + summ + " \n");
        }

        setSumm(summ);
    }

}



