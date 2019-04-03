package pl.waw.rubach.points.rubberBridge;

import pl.waw.rubach.points.AbstractWholeGameScorring;
import pl.waw.rubach.points.OneDeal;
import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RubberScorring extends AbstractWholeGameScorring {


    /**
     * Map number of game with scorring for one game
     */
    private Map<Integer, OneDeal> scorringForOneGame = new HashMap<>();
    private boolean areWeVunerable, areTheyVunerable, wePlay;
    private boolean winWe, winThey;
    private int ourWiningScorring = 0;
    private int aboveWe = 0, aboveThey = 0, underWe = 0, underThey = 0;
    private int aboveWeSumm = 0, aboveTheySumm = 0, underWeSumm = 0, underTheySumm = 0;
    private int contractNumber=0;

    //defut constructor adding number of game 1 - possible use other constructor with special number of game with next constructor
    public RubberScorring() {
        this(1);
    }

    //create special game with special gameID
    public RubberScorring(int gameID) {
        super(gameID, " robrowej ");
        setGameType("RUBER");
        setAreWeVunerable(false);
        setAreTheyVunerable(false);
        setOurWiningScorring(0);
        setContractNumber(0);
    }


    public int fillOneContract(boolean whoPlay, CalculatedOneDealRubberScorring d) throws BridgeException {
        setContractParameter(whoPlay,d.getContractLevel(),d.getContractSuit(),
                d.getNoDoubleReSignature(),d.getDeclarerNumberOfTrickTaken());
        setScorringForOneGame(getContractNumber(), d);

        setShortDescription(getContractDescription(whoPlay ? isAreWeVunerable() : isAreTheyVunerable()));

        d.setResultsWe(whoPlay,getUnderThey()+getAboveWe());
        setUnderAbovePoints(d);

        setOurWiningScorring(areWePlay() ? isEndOfTheGame() : -isEndOfTheGame());
       return areWePlay() ? getAboveWe() + getUnderWe() : getAboveThey() + getUnderThey();
       //  return getOurWiningScorring();
       //  return  d.getDeclarerUnderPoints()+d.getDeclarerOverPoints();
    }

    public int fillOneContract(boolean whoPlay, int contractLevel, String contractSuit,
                               boolean isContractDouble, boolean isContractRedouble,
                               int numberOfTrickTakenByDeclarer)
            throws BridgeException {
            return fillOneContract(whoPlay, new CalculatedOneDealRubberScorring(contractLevel, contractSuit,
                isContractDouble, isContractRedouble, (whoPlay ? isAreWeVunerable() : isAreTheyVunerable()),
                numberOfTrickTakenByDeclarer) );
    }


    protected void setSumm() { //todo change it with ourWinningScorring
        int summ = 0;
        for (int key : new TreeSet<>(getScorringForOneGame().keySet())) {
//            if (scorringForOneGame.get(key).getPointsInBothDeclarerHands() != 0)
         //   summ = summ + getScorringForOneGame().get(key).getResultsWe(getScorringForOneGame().get(key).areWePlay());
            setResultsDescription(getResultsDescription() + "Wynik rozdania " + key + " jest: "
                    + getScorringForOneGame().get(key).getDeclarerResluts()
                    + " \t Do tej pory  wynik jest: " + summ + " \n");
        }

        setSumm(areWePlay() ? isEndOfTheGame() : -isEndOfTheGame());

    }

    private void setUnderAbovePoints(CalculatedOneDealRubberScorring d) throws BridgeException {

        setUnderWe(areWePlay() ? d.getDeclarerUnderPoints() : 0);
        setAboveWe(areWePlay() ? d.getDeclarerOverPoints() : 0);
        setUnderThey(areWePlay() ? 0 : d.getDeclarerUnderPoints());
        setAboveThey(areWePlay() ? 0 : d.getDeclarerOverPoints());

        addUnderWeSumm(getUnderWe());
        addUnderTheySumm(getUnderThey());
        addAboveTheySumm(getAboveThey());
        addAboveWeSumm(getAboveWe());

        if (getUnderTheySumm() >= 100 || getUnderWeSumm() >= 100) {

            if (getUnderTheySumm() >= 100) {

                if (isAreTheyVunerable())  setWinThey(true);
                else setAreTheyVunerable(true);
            }

            if (getUnderWeSumm() >= 100) {
                if (isAreWeVunerable())  setWinWe(true);
                else setAreWeVunerable(true); 
            }
            addAboveTheySumm(getUnderTheySumm());
            setUnderTheySumm(0);
            addAboveWeSumm(getUnderWeSumm());
            setUnderWeSumm(0);
        }

    }

    public int isEndOfTheGame() {
        int results = 0;
        int a =getAboveWeSumm() + getUnderWeSumm() -(getAboveTheySumm() + getUnderTheySumm());
        if (isWinWe()) results = a + (isAreTheyVunerable() ? 300 : 500);
        if (isWinThey()) results = -a + (isAreWeVunerable() ? 300 : 500);

        return results;
    }


    //getters and setters

    private void setScorringForOneGame(Integer contractNumber, CalculatedOneDealRubberScorring d) {
           this.scorringForOneGame.put(contractNumber, d);
    }

    public Map<Integer, OneDeal> getScorringForOneGame() {
        return scorringForOneGame;
    }


    public int getAboveWe() {
        return aboveWe;
    }

    private void setAboveWe(int aboveWe) {
        this.aboveWe = aboveWe;
    }

    public int getAboveThey() {
        return aboveThey;
    }

    private void setAboveThey(int aboveThey) {
        this.aboveThey = aboveThey;
    }

    public int getUnderWe() {
        return underWe;
    }

    private void setUnderWe(int underWe) {
        this.underWe = underWe;
    }

    public int getUnderThey() {
        return underThey;
    }

    private void setUnderThey(int underThey) {
        this.underThey = underThey;
    }

    public int getAboveWeSumm() {
        return aboveWeSumm;
    }

    private void setAboveWeSumm(int aboveWeSumm) {
        this.aboveWeSumm = aboveWeSumm;
    }

    public int getAboveTheySumm() {
        return aboveTheySumm;
    }

    private void setAboveTheySumm(int aboveTheySumm) {
        this.aboveTheySumm = aboveTheySumm;
    }

    public int getUnderWeSumm() {
        return underWeSumm;
    }

    private void setUnderWeSumm(int underWeSumm) {
        this.underWeSumm = underWeSumm;
    }

    public int getUnderTheySumm() {
        return underTheySumm;
    }

    private void setUnderTheySumm(int underTheySumm) {
        this.underTheySumm = underTheySumm;
    }

    private void addAboveWeSumm(int aboveWeSumm) {
        this.aboveWeSumm += aboveWeSumm;
    }

    private void addAboveTheySumm(int aboveTheySumm) {
        this.aboveTheySumm += aboveTheySumm;
    }

    private void addUnderWeSumm(int underWeSumm) {
        this.underWeSumm += underWeSumm;
    }

    private void addUnderTheySumm(int underTheySumm) {
        this.underTheySumm += underTheySumm;
    }

    public boolean isAreWeVunerable() {
        return areWeVunerable;
    }

    public void setAreWeVunerable(boolean areWeVunerable) {

        this.areWeVunerable = areWeVunerable;
    }

    public boolean isAreTheyVunerable() {
        return areTheyVunerable;
    }

    public void setAreTheyVunerable(boolean areTheyVunerable) {
        this.areTheyVunerable = areTheyVunerable;
    }

    public boolean isWinWe() {
        return winWe;
    }

    public void setWinWe(boolean winWe) {
        this.winWe = winWe;
    }

    public boolean isWinThey() {
        return winThey;
    }

    public void setWinThey(boolean winThey) {
        this.winThey = winThey;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setOurWiningScorring(int ourWiningScorring) {
        this.ourWiningScorring = ourWiningScorring;
    }

    public int getOurWiningScorring() {
        return ourWiningScorring;
    }

    public boolean areWePlay() {
        return wePlay;
    }

    public void setWePlay(boolean whoPlay) {
        this.wePlay = whoPlay;
    }

    private void setContractParameter(boolean whoPlay,
                                      int contractLevel, String contractSuit,
                                      int nDRSig,
                                      int numerOfTricskTakenByDeclarere
    )
    throws BridgeException{
        setWePlay(whoPlay);
        setContractNumber(getContractNumber()+1);
        setContractLevel(contractLevel);
        setContractSuit(contractSuit);
        setNoDoubleReSignature(nDRSig);
        setNuberoftricsTakenWe(numerOfTricskTakenByDeclarere);
        setDeclarerVulnerable(whoPlay ? isAreTheyVunerable():isAreTheyVunerable());

    }
}


