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

package pl.waw.rubach.points;

public abstract class AbstractWholeGameScoring extends AbstractOneDeal {

    /**
     * game (for contract) ID - not sure if needed but probably for something?  - could be added in future:
     * date and time
     * place
     * names of players
     * photos etc ...
     * should be done automatically increasing of 1 - but how???? - I saw it somewhere...
     */
    private int gameID;

    /**
     * Which typ of scoring the game is
     */
    private String gameType;
    /**
     * rubberSpecialDescription describe parameter of game - if accessible and results describe results of rubber
     */
    private String rubberSpecialDescription, resultsDescription;


    /**
     * Result of 4 games - imp points
     */
    private int sum;



    protected AbstractWholeGameScoring(int gameID, String des) {
        setGameID(gameID);
        setRubberSpecialDescription(" \n *** Nowa seria gier "+ des +" z numerem:  " + getGameID() + ". ***  \n");
        setResultsDescription(" \nWyniki: \n");
        setSum(0);

    }


  /*  abstract public int fillOneContract(int contractNumber, boolean whoPlay, int contractLevel, String contractSuit,
                                        boolean isContractDouble, boolean isContractRedouble,
                                        int numberOfTrickTakenByDeclarer) throws BridgeException; */

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

     public int getSum() {
        return this.sum;
    }

    protected void setSum(int sum) {
        this.sum = sum;
    }

}
