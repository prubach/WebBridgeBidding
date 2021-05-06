package pl.waw.rubach.points;

import java.util.Arrays;
import java.util.List;
import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidContractLevelException;
import pl.waw.rubach.points.exceptions.InvalidContractSuitException;
import pl.waw.rubach.points.exceptions.InvalidNDRSignatureException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfTrickTakenException;


public abstract class AbstractOneDeal {

  public static final int IS_DOUBLE = 2;
  public static final int IS_REDOUBLE = 4;
  public static final int IS_UNDOUBTED = 1;
  public static final int NUMBEROFTRICS = 13;
  public static final int MINCONTRACTLEVEL = 0;
  public static final int MAXCONTRACTLEVEL = 7;
  public static final List<String> SUITS = Arrays.asList("S", "H", "D", "C", "NT", "N");

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
   *  Auction Assumption for Playing Pair means if pair is  vulnerable or invulnerable.
   * ATTENTION Auction assumption declarer not we!!!
   */
  private boolean declarerVulnerable;


  /**
   * Points for contract calculated with modify bridge rules according to 4 play scoring.
   * Points  achieved by playing contract with some addition or lacking tricks
   * (calculated from duplicate bridge scoring)
   * calculated by DuplicateBridgeScoring by we!!! (Pair who make scoring not always playing pair)
   * //calculatedPointForContract -> contractScoringPoint
   *    * because it is no point exactly but scoring (zapis)
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
    String lew = getDeclarerNumberOfTrickTaken() == 1
        ? " tylko lewę. " : getDeclarerNumberOfTrickTaken() > 1
                          && getDeclarerNumberOfTrickTaken() < 5
        ? " lewy." : " lew.";

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
    if (contractLevel > MAXCONTRACTLEVEL || contractLevel < MINCONTRACTLEVEL) {
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
    if (declarerNumberOfTrickTaken > NUMBEROFTRICS || declarerNumberOfTrickTaken < 0) {
      throw new InvalidNumberOfTrickTakenException(declarerNumberOfTrickTaken);
    }
    this.declarerNumberOfTrickTaken = declarerNumberOfTrickTaken;
  }

  public void setNumberOfTricksTakenWe(int numberOfTricksTakenWe)
      throws InvalidNumberOfTrickTakenException {
    if (numberOfTricksTakenWe > NUMBEROFTRICS || numberOfTricksTakenWe < 0) {
      throw new InvalidNumberOfTrickTakenException(declarerNumberOfTrickTaken);
    }
    this.declarerNumberOfTrickTaken = areWePlay()
        ? numberOfTricksTakenWe : NUMBEROFTRICS - numberOfTricksTakenWe;

  }

  public int getNumberOfTricksTakenWe() {
    if (areWePlay()) {
      return getDeclarerNumberOfTrickTaken();
    } else {
      return 13 - getDeclarerNumberOfTrickTaken();
    }
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

  public int getResultsWe(boolean wePlay, int declarerResluts) {
    return wePlay ? declarerResluts : -declarerResluts;
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
