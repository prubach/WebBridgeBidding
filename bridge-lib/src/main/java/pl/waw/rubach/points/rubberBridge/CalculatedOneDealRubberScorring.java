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

import pl.waw.rubach.points.InternationalBridgeScoring;
import pl.waw.rubach.points.exceptions.BridgeException;

public class CalculatedOneDealRubberScorring extends InternationalBridgeScoring {


     private int declarerOverPoints =0, declarerUnderPoints =0;


    public CalculatedOneDealRubberScorring(int contractLevel, String contractSuit, int nDRSignature,
                                           boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer) throws BridgeException {
        super(contractLevel, contractSuit, nDRSignature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);

    setDeclarerUnderPoints();
    setDeclarerOverPoints();

    }

    public CalculatedOneDealRubberScorring(int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble,
                                           boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)  throws  BridgeException {
        this(contractLevel, contractSuit, isContractDouble? IS_DOUBLE: isContractRedouble? IS_REDOUBLE: IS_UNDOUBTED,
                auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);

    }


    public CalculatedOneDealRubberScorring(boolean whoPlay, int contractLevel, String contractSuit, int nDRSignature,
                                           boolean auctionAssumptionWe,boolean auctionAssumptionThey, int numberOfTrickTakenByWe)  throws  BridgeException {
        this(contractLevel, contractSuit, nDRSignature,
                whoPlay? auctionAssumptionWe: auctionAssumptionThey,
                whoPlay? numberOfTrickTakenByWe: NUMBEROFTRICS -numberOfTrickTakenByWe);

        setWePlay(whoPlay);
    }

    public int getDeclarerUnderPoints(){
        return declarerUnderPoints;
    }

    public int getDeclarerOverPoints(){
        return declarerOverPoints;
    }


    private void setDeclarerOverPoints()  {
        this.declarerOverPoints = getDeclarerContractScoringPoints() - getDeclarerUnderPoints();
    }

    private void setDeclarerUnderPoints() throws BridgeException {
        this.declarerUnderPoints = made ? getContractPoints(getContractLevel()) * getNoDoubleReSignature() :0 ;
    }
}
