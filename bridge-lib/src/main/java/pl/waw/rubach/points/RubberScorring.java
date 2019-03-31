package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RubberScorring extends AbstractWholeGameScorring {


    /**
     * Map number of game with scorring for one game
     */
    protected Map<Integer, OneDeal> scorringForOneGame = new HashMap<>();
    boolean areWeBefore, areTheyBefore;
    boolean winWe, winThey;

    public int getWiningScorring() {
        return winingScorring;
    }

    private int winingScorring=0;
    private int aboveWe = 0, aboveThey = 0, underWe = 0, underThey = 0;
    private int aboveWeSumm = 0, aboveTheySumm = 0, underWeSumm = 0, underTheySumm = 0;

    //defut constructor adding number of game 1 - possible use other constructor with special number of game with next constructor
    public RubberScorring() {
        this(1);
    }

    //create special game with special gameID
    public RubberScorring(int gameID) {
        super(gameID, " robrowej ");
        setGameType("RUBER");
        setAreWeBefore(false);
        setAreTheyBefore(false);
    }


    public int fillOneContract(int contractNumber, boolean whoPlay, CalculatedRubberPoints d) throws BridgeException {
        setScorringForOneGame(contractNumber, d);

        setUnderAbovePoints(d);
        this.winingScorring = isEndOfTheGame();
        //setSumm();
        return d.getDeclarerUnderPoints()+d.getDeclarerOverPoints();

//        return whoPlay ? d.getDeclarerUnderPoints()+d.getDeclarerOverPoints() : -d.getDeclarerUnderPoints()+d.getDeclarerOverPoints();
    }

    public int fillOneContract(int contractNumber, boolean whoPlay, int contractLevel, String contractSuit,
                               boolean isContractDouble, boolean isContractRedouble,
                               int numberOfTrickTakenByDeclarer)
            throws BridgeException {
        CalculatedRubberPoints d = new CalculatedRubberPoints(contractLevel, contractSuit,
                isContractDouble, isContractRedouble, (whoPlay ? isAreWeBefore() : isAreTheyBefore())  ,
                numberOfTrickTakenByDeclarer );
        return fillOneContract(contractNumber, whoPlay, d);
    }


    private void setScorringForOneGame(Integer contractNumber, CalculatedRubberPoints d) {
        this.scorringForOneGame.put(contractNumber, d);
    }

    public Map<Integer, OneDeal> getScorringForOneGame() {
        return scorringForOneGame;
    }


    protected void setSumm() {
        int summ = 0;
        for (int key : new TreeSet<>(getScorringForOneGame().keySet())) {
//            if (scorringForOneGame.get(key).getPointsInBothDeclarerHands() != 0)
            summ = summ + getScorringForOneGame().get(key).getResultsWe(getScorringForOneGame().get(key).areWePlay());
            setResultsDescription(getResultsDescription() + "Wynik rozdania " + key + " jest: "
                    + getScorringForOneGame().get(key).getDeclarerResluts()
                    + " \t Do tej pory  wynik jest: " + summ + " \n");
        }

        setSumm(summ);
    }

    private void setUnderAbovePoints(CalculatedRubberPoints d) throws BridgeException {
        boolean whoPlay = d.areWePlay();
        if (whoPlay) {
            setUnderWe(d.getDeclarerUnderPoints());
            setAboveWe(d.getDeclarerOverPoints());
            setAboveThey(0);
            setUnderThey(0);
        } else {
            setUnderThey(d.getDeclarerUnderPoints());
            setAboveThey(d.getDeclarerOverPoints());
            setAboveWe(0);
            setUnderWe(0);
        }

        addUnderWeSumm(getUnderWe());
        addUnderTheySumm(getUnderThey());
        addAboveTheySumm(getAboveThey());
        addAboveWeSumm(getAboveWe());

        if (getUnderTheySumm() >= 100 || getUnderWeSumm() >= 100) {

            if (getUnderThey() >= 100) {

                if (isAreTheyBefore()) {setWinThey(true);
                }
                if (!isAreTheyBefore()) setAreTheyBefore(true);
            }

            if (getUnderWe() >= 100) {

                if (isAreWeBefore()) {setWinWe(true);
                }
                if (!isAreWeBefore()) setAreWeBefore(true);
            }
            addAboveWeSumm(getUnderWeSumm());
            setUnderWeSumm(0);
            addAboveTheySumm(getUnderTheySumm());
            setUnderTheySumm(0);
        }

    }
    public int isEndOfTheGame() {
        int results=0 ;
        int a = getAboveTheySumm() + getUnderTheySumm() - getAboveWeSumm() - getUnderWeSumm();
        if(isWinWe()) results = -a+ (isAreTheyBefore() ? 300 :500);
        if(isWinThey()) results =a+ (isAreWeBefore() ? 300 :500);


        return results;
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

    public boolean isAreWeBefore() {
        return areWeBefore;
    }

    public void setAreWeBefore(boolean areWeBefore) {
        this.areWeBefore = areWeBefore;
    }

    public boolean isAreTheyBefore() {
        return areTheyBefore;
    }

    public void setAreTheyBefore(boolean areTheyBefore) {
        this.areTheyBefore = areTheyBefore;
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
}


