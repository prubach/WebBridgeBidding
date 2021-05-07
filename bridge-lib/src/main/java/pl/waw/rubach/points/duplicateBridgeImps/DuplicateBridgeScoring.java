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

package pl.waw.rubach.points.duplicateBridgeImps;

import pl.waw.rubach.points.InternationalBridgeScoring;
import pl.waw.rubach.points.exceptions.BridgeException;

//This was  PointsForContract(...)
public class DuplicateBridgeScoring extends InternationalBridgeScoring {


    /**
     * Constructor of scoring for duplicate Bridge game for Declarer hand  -  setting contract parameter and calculated scoring points for Contract
     * extended DealerPointsForOneDeal class where are field for all parameter for declarer.
     * Contract parameter:
     *
     * @param contractLevel                The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken to win.
     *                                     in future from other part of application - results of biding part or user input
     * @param contractSuit                 Cards suits [denomination or strain] that denotes the proposed trump suit or no trump.
     *                                     Thus, there are five denominations – no trump, spades, hearts, diamonds and clubs.
     * @param nDRSignature                 Signature that shows is it undouble (=1), double (=2) or redouble (=4) contract.
     * @param auctionAssumptionDeclarer    ATTENTION Auction assumption declarer not we!!!
     *                                     Auction Assumption for playing Pair means if pair is  vulnerable or unvulnerable
     * @param numberOfTrickTakenByDeclarer ATTENTION : Number of tricks taken in game by we !!!(Pair who make scoring not always playing pair)
     *                                     <p>
     *                                     Number of tricks taken in game  by declarer - the player whose bid establishes the suit of the contract
     *                                     and who must therefore play both their own hand and the exposed hand of the dummy.
     *                                     (opponent takes 13 - numberOfTrickTakenByDeclarer)
     * @throws BridgeException if some bridge parameter have  incorrect value:
     *                         InvalidNumberOfTrickTakenException if number of tricks is less then 0 or more then 13 (52:4)
     *                         InvalidContractSuitException       if suit is no one of 5 bridge strain (colors + nt)
     *                         InvalidContractLevelException      if level is not between 1 to 7 (according bridge rules etc
     */
    public DuplicateBridgeScoring(int contractLevel, String contractSuit, int nDRSignature,
                                  boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)
            throws BridgeException {
        super(contractLevel, contractSuit, nDRSignature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);

    }

    //OLD constructor with boolean double redouble - exist in all test so cant be delayed
    public DuplicateBridgeScoring(int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble,
                                  boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)
            throws BridgeException {
        this(contractLevel, contractSuit, isContractRedouble ? 4 : (isContractDouble ? 2 : 1), auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);
    }


    /**
     * In duplicate bridge only, game and partial-game bonuses are awarded at the conclusion of each deal as follows:
     * any partial contract, i.e. one scoring less than 100 contract points, scores a bonus of 50 points, and
     * any game contract, i.e. one scoring 100 or more points, scores a game bonus of 300 if not vulnerable and 500 if vulnerable.
     *
     * @param calculatedPointsForContract - are points for Contract - not overtricks included - so to know is is more then 100.
     * @return game/ part game or slam bonnus
     */
    @Override
    protected int getGamePartGameBonus(int calculatedPointsForContract) {

        if (calculatedPointsForContract >= 100 && made) {
            description = description + " + punkty za ugraną końcówkę (zależnie od założeń).";
            return (isDeclarerVulnerable()) ? 500 : 300;
        } else if (calculatedPointsForContract > 0) {
            description = description + " + 50 pkt za częściówkę.";
            return 50;
        } else
            return 0;
    }


}
