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

import pl.waw.rubach.points.exceptions.*;

import java.util.Arrays;
import java.util.List;


public abstract class AbstractOneDeal implements ResultsInterface{

  public static final int IS_DOUBLE = 2;
  public static final int IS_REDOUBLE = 4;
  public static final int IS_UNDOUBTED = 1;
  public static final int NUMBER_OF_TRICKS = 13;
  public static final int MIN_CONTRACT_LEVEL = 0;
  public static final int MAX_CONTRACT_LEVEL = 7;
  public static final List<String> SUITS
      = Arrays.asList("S", "H", "D", "C", "NT", "N");

  /**
   * Indicates who is Declarer - but all value here for declarer.
   */
  protected boolean wePlay = true;

  /**
   * The number of tricks that (when added to the book of six tricks)
   * a bid or contract states will be taken to win.
   * in future from other part of application - declarerResults of biding part or user input
   */
  protected int contractLevel;

  /**
   * Cards suits [denomination or strain] that denotes the proposed trump suit or no trump.
   * Thus, there are five denominations – no trump, spades, hearts, diamonds and clubs.
   */
  protected String contractSuit;

  /**
   * Signature that shows is it undoubted (=1), double (=2) or redouble (=4) contract.
   */
  private int noDoubleReSignature;

  /**
   * ATTENTION : It should not be Number of tricks taken in game by we !!!
   * (Pair who make scoring not always playing pair)
   *
   * <p>Number of tricks taken in game  by declarer -
   * the player whose bid establishes the suit of the contract
   * and who must therefore play both their own hand and the exposed hand of the dummy.
   * (opponent takes 13 - numberOfTrickTakenByDeclarer)
   */
  private int declarerNumberOfTrickTaken;


  /**
   * Auction Assumption for opponents of Playing Pair means if pair is  vulnerable or invulnerable.
   * ATTENTION Auction assumption opponents  not they!!
   */
  private boolean opponentVulnerable;


  /**
   * Auction Assumption for Playing Pair means if pair is  vulnerable or invulnerable.
   * ATTENTION Auction assumption declarer not we!!!
   */
  private boolean declarerVulnerable;


  /**
   * Points for contract calculated with modify bridge rules according to 4 play scoring.
   * Points  achieved by playing contract with some addition or lacking tricks
   * (calculated from duplicate bridge scoring)
   * calculated by DuplicateBridgeScoring by we!!! (Pair who make scoring not always playing pair)
   * //calculatedPointForContract -> contractScoringPoint
   * * because it is no point exactly but scoring (zapis)
   */
  private int declarerContractScoringPoints;


  /**
   * Number of Victory Point - could be IMP Points   if 0 is equal, if -1 is one less etc ...
   * (for we - Pair who make scoring not always playing pair)
   */
  private int declarerResults;

  /**
   * shortDescription -  only with contract main parameter.
   */
  private String shortDescription;


  protected AbstractOneDeal() {
  }

  protected AbstractOneDeal(int contractLevel, String contractSuit,
                            int noDoubleReSignature, int declarerNumberOfTrickTaken,
                            boolean auctionAssumptionDeclarer)
      throws BridgeException {

    setContractLevel(contractLevel);
    setContractSuit(contractSuit);
    setNoDoubleReSignature(noDoubleReSignature);
    setDeclarerVulnerable(auctionAssumptionDeclarer);
    setDeclarerNumberOfTrickTaken(declarerNumberOfTrickTaken);


  }

  /**
   * Description of contract.
   *
   * @return short description of contract
   */
  public String getContractDescription() {
    return getContractDescription(isDeclarerVulnerable());
  }

  public String getContractDescription(boolean assumptionB) {
    String assumption = assumptionB ? ": po parti, " : ": przed partią, ";
    //fixme poprawić wczytywanie założeń
    String lew = tricksDeclination(getDeclarerNumberOfTrickTaken());

    if (getNoDoubleReSignature() == IS_DOUBLE) {
      return " Kontrakt jest: " + getContractLevel()
          + getContractSuit() + assumption + " z kontrą, rozgrywający zebrał "
          + getDeclarerNumberOfTrickTaken() + lew;
    } else if (getNoDoubleReSignature() == IS_REDOUBLE) {
      return " Kontrakt jest: " + getContractLevel()
          + getContractSuit() + assumption + " z rekontrą, rozgrywający zebrał "
          + getDeclarerNumberOfTrickTaken() + lew;
    } else {
      return " Kontrakt jest: " + getContractLevel()
          + getContractSuit() + assumption + " rozgrywający zebrał "
          + getDeclarerNumberOfTrickTaken() + lew;
    }
  }

  private String tricksDeclination(int num_trick) {
    if (num_trick == 1) {
      return "tylko lewę.";
    } else if (num_trick > 1 && num_trick < 5) {
      return "lewy";
    } else {
      return "lew";
    }
  }

  //Getters and Setters
  public boolean areWePlay() {
    return wePlay;
  }

  public void setWePlay(boolean wePlay) {
    this.wePlay = wePlay;
  }

  public int getContractLevel() {
    return contractLevel;
  }

  public void setContractLevel(int contractLevel) throws InvalidContractLevelException {
    //checking if contractLevel is correct
    if (contractLevel > MAX_CONTRACT_LEVEL || contractLevel < MIN_CONTRACT_LEVEL) {
      throw new InvalidContractLevelException(contractLevel);
    }

    this.contractLevel = contractLevel;
  }

  public String getContractSuit() {
    return contractSuit;
  }

  public void setContractSuit(String contractSuit) throws InvalidContractSuitException {
    for (String s : SUITS) {
      if (s.equals(contractSuit.toUpperCase())) {
        this.contractSuit = contractSuit;
        break;
      }
    }
    if (this.contractSuit == null) {
      throw new InvalidContractSuitException(contractSuit);
    }

  }

  public int getNoDoubleReSignature() {
    return noDoubleReSignature;
  }

  public void setNoDoubleReSignature(int noDoubleReSignature) throws BridgeException {
    //checking if double/ redouble or undoubted signature  is correct
    if (!(noDoubleReSignature == IS_UNDOUBTED || noDoubleReSignature == IS_DOUBLE
        || noDoubleReSignature == IS_REDOUBLE)) {
      throw new InvalidNDRSignatureException(noDoubleReSignature);
    }
    this.noDoubleReSignature = noDoubleReSignature;
  }

  public boolean areWeVulnerable(boolean wePlay) {
    return wePlay ? isDeclarerVulnerable() : isOpponentVulnerable();
  }

  public void setWeVulnerable(boolean wePlay, boolean areWeVulnerable) {
    if (wePlay) {
      this.declarerVulnerable = areWeVulnerable;
    } else {
      this.opponentVulnerable = areWeVulnerable;
    }
  }

  public boolean areTheyVulnerable(boolean wePlay) {
    return wePlay ? isOpponentVulnerable() : isDeclarerVulnerable();
  }

  public void setTheyVulnerable(boolean wePlay, boolean areTheyVulnerable) {
    if (wePlay) {
      this.declarerVulnerable = areTheyVulnerable;
    } else {
      this.opponentVulnerable = areTheyVulnerable;
    }
  }

  public boolean isDeclarerVulnerable() {
    return declarerVulnerable;
  }

  public void setDeclarerVulnerable(boolean declarerVulnerable) {
    this.declarerVulnerable = declarerVulnerable;
  }

  public boolean isOpponentVulnerable() {
    return opponentVulnerable;
  }

  public void setOpponentVulnerable(boolean opponentVulnerable) {
    this.opponentVulnerable = opponentVulnerable;
  }


  //Others

  public int getDeclarerNumberOfTrickTaken() {
    return declarerNumberOfTrickTaken;
  }

  public void setDeclarerNumberOfTrickTaken(int declarerNumberOfTrickTaken)
      throws InvalidNumberOfTrickTakenException {
    //checking if number of tricks is correct
    if (declarerNumberOfTrickTaken > NUMBER_OF_TRICKS || declarerNumberOfTrickTaken < 0) {
      throw new InvalidNumberOfTrickTakenException(declarerNumberOfTrickTaken);
    }
    this.declarerNumberOfTrickTaken = declarerNumberOfTrickTaken;
  }

  public int getNumberOfTricksTakenWe() {
    if (areWePlay()) {
      return getDeclarerNumberOfTrickTaken();
    } else {
      return 13 - getDeclarerNumberOfTrickTaken();
    }
  }

  public void setNumberOfTricksTakenWe(int numberOfTricksTakenWe)
      throws InvalidNumberOfTrickTakenException {
    if (numberOfTricksTakenWe > NUMBER_OF_TRICKS || numberOfTricksTakenWe < 0) {
      throw new InvalidNumberOfTrickTakenException(declarerNumberOfTrickTaken);
    }
    this.declarerNumberOfTrickTaken = areWePlay()
        ? numberOfTricksTakenWe : NUMBER_OF_TRICKS - numberOfTricksTakenWe;

  }

  public int getDeclarerContractScoringPoints() {
    return declarerContractScoringPoints;
  }

  public void setDeclarerContractScoringPoints(int declarerContractScoringPoints) {
    this.declarerContractScoringPoints = declarerContractScoringPoints;
  }

  public int getContractScoringPointsWe() {
    return areWePlay() ? getDeclarerContractScoringPoints() : -getDeclarerContractScoringPoints();
  }

  public int getContractScoringPointsWe(boolean whoPlay) {
    if (whoPlay) {
      return getDeclarerContractScoringPoints();
    } else {
      return -getDeclarerContractScoringPoints();
    }
  }

  public int getDeclarerResults() {
    return declarerResults;
  }

  public void setDeclarerResults(int declarerResults) {
    this.declarerResults = declarerResults;
  }


  public void setResultsWe(boolean wePlay, int weResults) {
    setDeclarerResults(wePlay ? weResults : -weResults);
  }

  public int getResultsWe(boolean wePlay) {
    return wePlay ? declarerResults : -declarerResults;
  }

  public int getResultsWe(boolean wePlay, int declarerResults) {
    return wePlay ? declarerResults : -declarerResults;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public void setShortDescription() {
    this.shortDescription = getContractDescription();
  }

}
