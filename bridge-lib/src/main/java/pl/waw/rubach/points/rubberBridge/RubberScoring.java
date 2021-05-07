/*
 *
 *  *                     GNU GENERAL PUBLIC LICENSE
 *  *                        Version 0.8 ,  8. 05. 2021
 *  *
 *  *                   "Bridge-lib"  application as help
 *  *                  for bidding, and points counting
 *   *     Copyright (C) {2017}  {Paweł Rubach, Magdalena Wilska}
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *      any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *
 *
 */

package pl.waw.rubach.points.rubberBridge;

import pl.waw.rubach.points.AbstractWholeGameScoring;
import pl.waw.rubach.points.AbstractOneDeal;
import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.EndOfRubberException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RubberScoring extends AbstractWholeGameScoring {


    /**
     * Map number of game with scorring for one game
     */
    private Map<Integer, AbstractOneDeal> scorringForOneGame = new HashMap<>();
    private boolean areWeVunerable, areTheyVunerable, wePlay;
    private boolean winWe, winThey;
    private int ourWiningScorring;
    private int overWe = 0, overThey = 0, underWe = 0, underThey = 0;
    private int overWeSumm = 0, overTheySumm = 0, underWeSumm = 0, underTheySumm = 0;
    private int contractNumber=0;

    public RubberScoring(ArrayList<CalculatedOneDealRubberScorring> d) throws BridgeException {
        this(1);

        for (CalculatedOneDealRubberScorring calculatedOneDealRubberScorring : d)
            fillOneContract(calculatedOneDealRubberScorring.areWePlay(), calculatedOneDealRubberScorring);

        setResultsDescription("Końcowy wynik gry do rozdania numer:" + getGameID() + " dla nas jest: " + getSum() + " \n");

    }

    //defut constructor adding number of game 1 - possible use other constructor with special number of game with next constructor
    public RubberScoring() {
        this(1);
    }

    //create special game with special gameID
    public RubberScoring(int gameID) {
        super(gameID, " robrowej ");
        setGameType("RUBER");
        setAreWeVunerable(false);
        setAreTheyVunerable(false);
        setWinWe(false);
        setWinThey(false);
        setOurWiningScorring(0);
        setContractNumber(0);
    }


    public int fillOneContract(boolean whoPlay, CalculatedOneDealRubberScorring d) throws BridgeException {

     if(isWinWe() || isWinThey()) throw new EndOfRubberException(getOurWiningScorring(),isWinWe() ,isWinThey() );

     setContractParameter(whoPlay, d.getContractLevel(),
             d.getContractSuit(), d.getNoDoubleReSignature(), d.getDeclarerNumberOfTrickTaken());

        setScorringForOneGame(getContractNumber(), d);
        setShortDescription(getContractDescription(whoPlay ? isAreWeVunerable() : isAreTheyVunerable()));
        setUnderAbovePoints(d);
        d.setResultsWe(whoPlay,getOverWe() + getUnderWe()+getOverThey()+getOverWe());


        setOurWiningScorring(areWePlay() ? isEndOfTheGame() : -isEndOfTheGame());
       return d.getResultsWe(areWePlay());
       //  return getOurWiningScorring();
       //  return  d.getDeclarerUnderPoints()+d.getDeclarerOverPoints();
    }

    public int fillOneContract(boolean whoPlay, int contractLevel, String contractSuit,
                               int nDRsig, int numberOfTrickTakenByDeclarer)
            throws BridgeException {
        return fillOneContract(whoPlay, new CalculatedOneDealRubberScorring(contractLevel, contractSuit,
                nDRsig, (whoPlay ? isAreWeVunerable() : isAreTheyVunerable()),
                numberOfTrickTakenByDeclarer) );
    }


    public int fillOneContract(boolean whoPlay, int contractLevel, String contractSuit,
                               boolean isContractDouble, boolean isContractRedouble,
                               int numberOfTrickTakenByDeclarer)
            throws BridgeException {
            return fillOneContract(whoPlay, contractLevel, contractSuit,
                    (isContractDouble ? 2 : (isContractRedouble ? 4:1)),numberOfTrickTakenByDeclarer );
    }


    protected void setSumm() { //todo change it with ourWinningScorring
        int summ = 0;
        for (int key : new TreeSet<>(getScorringForOneGame().keySet())) {
//            if (scorringForOneGame.get(key).getPointsInBothDeclarerHands() != 0)
         //   summ = summ + getScorringForOneGame().get(key).getResultsWe(getScorringForOneGame().get(key).areWePlay());
            setSum(getUnderWeSumm()+ getOverWeSumm()-getUnderTheySumm()- getOverTheySumm());
            setResultsDescription(getResultsDescription() + "Wynik rozdania " + key + " jest: "
                    + getScorringForOneGame().get(key).getDeclarerResults()
                    + " \t Do tej pory  wynik jest: " + summ + " \n");
        }

        setSum(getUnderWeSumm()+ getOverWeSumm()-getUnderTheySumm()- getOverTheySumm());
       // setSum(areWePlay() ? isEndOfTheGame() : -isEndOfTheGame());

    }

    private void setUnderAbovePoints(CalculatedOneDealRubberScorring d) {

        setUnderWe(d);
        setOverWe(d);
        setUnderThey(d);
        setOverThey(d);

        addUnderWeSumm(getUnderWe());
        addUnderTheySumm(getUnderThey());
        addAboveTheySumm(getOverThey());
        addAboveWeSumm(getOverWe());

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
        int a = getOverWeSumm() + getUnderWeSumm() -(getOverTheySumm() + getUnderTheySumm());
        if (isWinWe()) results = a + (isAreTheyVunerable() ? 500 : 700);
        if (isWinThey()) results = -a + (isAreWeVunerable() ? 500 : 700);

        return results;
    }


    //getters and setters

    private void setScorringForOneGame(Integer contractNumber, CalculatedOneDealRubberScorring d) {
           this.scorringForOneGame.put(contractNumber, d);
    }

    public Map<Integer, AbstractOneDeal> getScorringForOneGame() {
        return scorringForOneGame;
    }


    private int getOverWe() {
        return overWe;
    }

    private void setOverWe(CalculatedOneDealRubberScorring d)  {
        this.overWe = areWePlay() ? d.getDeclarerOverPoints() : 0;

    }

    private int getOverThey() {
        return overThey;
    }

    private void setOverThey(CalculatedOneDealRubberScorring d)  {
        this.overThey = areWePlay() ? 0 : d.getDeclarerOverPoints();
    }

    public int getUnderWe() {
        return underWe;
    }

    private void setUnderWe(CalculatedOneDealRubberScorring d)  {
        this.underWe = areWePlay() ? d.getDeclarerUnderPoints() : 0;

    }

    public int getUnderThey() {
        return underThey;
    }

    private void setUnderThey(CalculatedOneDealRubberScorring d) {
        this.underThey = areWePlay() ? 0 : d.getDeclarerUnderPoints();
    }


    public int getOverWeSumm() {
        return overWeSumm;
    }

    private void setOverWeSumm(int overWeSumm) {
        this.overWeSumm = overWeSumm;
    }

    public int getOverTheySumm() {
        return overTheySumm;
    }

    private void setOverTheySumm(int overTheySumm) {
        this.overTheySumm = overTheySumm;
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
        this.overWeSumm += aboveWeSumm;
    }

    private void addAboveTheySumm(int aboveTheySumm) {
        this.overTheySumm += aboveTheySumm;
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
        setNumberOfTricksTakenWe(numerOfTricskTakenByDeclarere);
        setDeclarerVulnerable(whoPlay ? isAreTheyVunerable():isAreTheyVunerable());

    }




}


