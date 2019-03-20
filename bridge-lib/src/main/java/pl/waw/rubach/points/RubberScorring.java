package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RubberScorring extends AbstractWholeGameScorring {


    /**
     * Map number of game with scorring for one game
     */
    protected Map<Integer, DeclarerPointsForOneDeal > scorringForOneGame = new HashMap<>();


    //defut constructor adding number of game 1 - possible use other constructor with special number of game with next constructor
    public RubberScorring() {
        this(1);
    }

    //create special game with special gameID
    public RubberScorring(int gameID) {
        super(gameID," robrowej ");
        setGameType("RUBER");
    }



    public int fillOneContract(int contractNumber, CalculatedRubberPoints d){
        setScorringForOneGame(contractNumber, d);
        setSumm();
        return d.getResults();
    }

    public int fillOneContract(int contractNumber,int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble,
                               boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)
    throws BridgeException
    {
        CalculatedRubberPoints d = new CalculatedRubberPoints(contractLevel,contractSuit,isContractDouble,isContractRedouble,auctionAssumptionDeclarer,numberOfTrickTakenByDeclarer);
        return fillOneContract(contractNumber, d);
    }


    private void setScorringForOneGame(Integer contractNumber, DeclarerPointsForOneDeal d) {
        this.scorringForOneGame.put(contractNumber, d);
    }

    public Map<Integer, DeclarerPointsForOneDeal > getScorringForOneGame() {
        return scorringForOneGame;
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


}
