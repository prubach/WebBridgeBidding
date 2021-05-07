package pl.waw.rubach.points.exceptions;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.duplicateBridgeImps.CalculatedImpPointsForOneDeal;
import pl.waw.rubach.points.rubberBridge.RubberScoring;

import static org.junit.Assert.*;
import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.*;

public class BridgeExceptionTest {

  private static final Logger logger = LoggerFactory.getLogger(BridgeException.class);
  private static final double DELTA = 0.0001;
  //introducing good value of each parameter
  boolean wePlay = true;
  boolean isRedouble = false;
  boolean isDouble = false;
  boolean auctionAssumptionWe = false;
  boolean isAuctionAssumptionThey = false;
  boolean fitWe = false;
  boolean fitThey = false;
  String contractSuit = "N";
  float pointsInBothHandsWe = 23f;
  int contractLevel = 5;
  int numberOfTrickTakenByWe = 9;

  @Test
  public void testShouldThrowBridgeExceptionWithMessage() throws BridgeException {
    shouldThrowBridgeExceptionWithMessage(155f, contractSuit, contractLevel, numberOfTrickTakenByWe);
    shouldThrowBridgeExceptionWithMessage(-3f, contractSuit, contractLevel, numberOfTrickTakenByWe);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, -5, numberOfTrickTakenByWe);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, 10, numberOfTrickTakenByWe);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, contractLevel, -4);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, contractLevel, 14);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, "A", contractLevel, numberOfTrickTakenByWe);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, "Pik", contractLevel, numberOfTrickTakenByWe);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, contractLevel, numberOfTrickTakenByWe, 6);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, contractLevel, numberOfTrickTakenByWe, -1);
  }

  private void shouldThrowBridgeExceptionWithMessage(
      float pointsInBothHandsWe, String contractSuit, int contractLevel, int numberOfTrickTakenByWe)
      throws BridgeException {
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, contractLevel, numberOfTrickTakenByWe, 2);
  }

  private void shouldThrowBridgeExceptionWithMessage(
      float pointsInBothHandsWe, String contractSuit, int contractLevel, int numberOfTrickTakenByWe, int NDR)
      throws BridgeException {
    try {
      CalculatedImpPointsForOneDeal c = new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, contractLevel,
          contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
          auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
      c.setNoDoubleReSignature(NDR);
      fail("Exception wasn't thrown!");
    } catch (InvalidContractLevelException exception) {
      assertEquals(contractLevel, exception.getContractLevel());
      assertFalse(exception.getContractLevel() <= MAX_CONTRACT_LEVEL && exception.getContractLevel() > 0);
      logger.info(exception.getMessage());
    } catch (InvalidContractSuitException exception) {
      String suit = exception.getContractSuit();
      assertEquals(contractSuit, suit);
      assertFalse(suit.equals("NT") | suit.equals("N") | suit.equals("S") |
          suit.equals("D") | suit.equals("C") | suit.equals("H"));
      logger.info(exception.getMessage());
    } catch (InvalidNDRSignatureException exception) {
      assertFalse(exception.getNDRSignature() == 1
          | exception.getNDRSignature() == 4 | exception.getNDRSignature() == 2);
      logger.info(exception.getMessage());
    } catch (InvalidNumberOfPointsException exception) {
      assertEquals(pointsInBothHandsWe, exception.getPointsGiven(), DELTA);
      assertFalse(exception.getPointsGiven() <= MAX_NUMBER_OF_POINTS
          && exception.getPointsGiven() > MIN_NUMBER_OF_POINTS);
      logger.info(exception.getMessage());
    } catch (InvalidNumberOfTrickTakenException exception) {
      assertEquals(numberOfTrickTakenByWe, exception.getNumberOfTricksTaken());
      assertFalse(exception.getNumberOfTricksTaken() > 0 && exception.getNumberOfTricksTaken() <= 13);
      logger.info(exception.getMessage());
    }
  }

  @Test
  public void shouldTrowInvalidPointToCalculateImpsException() {
    try {
      helperInvalidPointToCalculateImpsException(MAX_NUMBER_OF_POINTS_BETWEEN_EXPECTED + 111);
    } catch (InvalidPointToCalculateImpsException exception) {
      assertEquals(10001, exception.getPointDifference());
    } catch (BridgeException e) {
      e.printStackTrace();
    }
  }

  @Test(expected = InvalidPointToCalculateImpsException.class)
  public void shouldTrowInvalidPointToCalculateImpsException_1() throws BridgeException {
    helperInvalidPointToCalculateImpsException(MAX_NUMBER_OF_POINTS_BETWEEN_EXPECTED * 10);
  }

  @Test(expected = InvalidPointToCalculateImpsException.class)
  public void shouldTrowInvalidPointToCalculateImpsException_2() throws BridgeException {
    helperInvalidPointToCalculateImpsException(-MAX_NUMBER_OF_POINTS_BETWEEN_EXPECTED);
  }

  private void helperInvalidPointToCalculateImpsException(int num) throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, num,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
  }

  @Test(expected = InvalidNumberOfTrickTakenException.class)
  public void shouldTrowInvalidNumberOfTrickTakenException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, contractLevel,
        contractSuit, isDouble, isRedouble, 15,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
  }

  @Test(expected = InvalidNumberOfPointsException.class)
  public void shouldThrowInvalidNumberOfPointsException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, 2300f, contractLevel,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        isAuctionAssumptionThey, isAuctionAssumptionThey, fitWe, fitThey);
  }

  @Test(expected = InvalidNDRSignatureException.class)
  public void testNDSignature() throws BridgeException {
    CalculatedImpPointsForOneDeal c = new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, contractLevel,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
    c.setNoDoubleReSignature(6);
  }


  @Test(expected = InvalidFitException.class)
  public void shouldTrowInvalidFitException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, 20, contractLevel,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, true, true);
  }

  @Test(expected = InvalidContractSuitException.class)
  public void shouldTrowInvalidContractSuitException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, contractLevel,
        "SPADES", isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
  }

  @Test(expected = InvalidContractLevelException.class)
  public void shouldThrowInvalidCInteractLevelException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, 166,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
  }

  @Test(expected = EndOfRubberException.class)
  public void shouldThrowEndOfRubberException() throws BridgeException {
    RubberScoring r = new RubberScoring(1);
    for (int i = 0; i < 3; i++) {  //two times for rubber and then third trying to add next contract after rubber
      r.fillOneContract(wePlay, 3, "NT", isDouble, isRedouble, 9);
    }
  }

  @Test
  public void shouldThrowEndOfRubberExceptionWithMessage() throws BridgeException {
    RubberScoring r = new RubberScoring(1);
    try {
      for (int i = 0; i < 3; i++) {  //two times for rubber and then third trying to add next contract after rubber
        r.fillOneContract(wePlay, 3, "NT", isDouble, isRedouble, 9);
      }
    } catch (EndOfRubberException exception) {
      logger.info(exception.getMessage());
    }
  }

}



