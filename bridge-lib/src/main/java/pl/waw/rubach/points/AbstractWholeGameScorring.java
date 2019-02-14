package pl.waw.rubach.points;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class AbstractWholeGameScorring {

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
     * Wchich typ of scoriing trhe game is
     */
    private String gameType;
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


    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getGameID() {
        return gameID;
    }

    protected void setGameID(int gameID) {
        this.gameID = gameID;
    }


    public String getRubberSpecialDescription() {
        return rubberSpecialDescription;
    }

    protected void setRubberSpecialDescription(String rubberSpecialDescription) {
        this.rubberSpecialDescription = rubberSpecialDescription;
    }

    public String getResultsDescription() {
        return resultsDescription;
    }

    protected void setResultsDescription(String resultsDescription) {
        this.resultsDescription = resultsDescription;
    }

    public Map<Integer, CalculatedImpPointsForOneDeal> getScorringForOneGame() {
        return scorringForOneGame;
    }

    protected void setScorringForOneGame(Integer contractNumber, CalculatedImpPointsForOneDeal cIPfOD) {
        this.scorringForOneGame.put(contractNumber, cIPfOD);
    }


    protected void setSumm() {
        int summ = 0;
        for (int key : new TreeSet<>(getScorringForOneGame().keySet())) {
//            if (scorringForOneGame.get(key).getPointsInBothDeclarerHands() != 0)
            summ = summ + getScorringForOneGame().get(key).getResults();
            setResultsDescription(getResultsDescription() + "Wynik rozdania " + key + " jest: " + getScorringForOneGame().get(key).getResults() + " \t Do tej pory  wynik jest: " + summ + " \n");
        }

        setSumm(summ);
    }

    public int getSumm() {
        return this.summ;
    }

    protected void setSumm(int summ) {
        this.summ = summ;
    }

}
