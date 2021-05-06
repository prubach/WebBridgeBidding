package pl.waw.rubach.points.exceptions;

import org.junit.Test;
import pl.waw.rubach.points.duplicateBridgeImps.CalculatedImpPointsForOneDeal;
import pl.waw.rubach.points.rubberBridge.RubberScoring;

import static org.junit.Assert.*;
import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.*;

public class BridgeExceptionTest {

  boolean wePlay = true;
  boolean isRedouble = false;
  boolean isDouble = false;
  boolean auctionAssumptionWe = false;
  boolean isAuctionAssumptionThey = false;
  boolean fitWe = false;
  boolean fitThey = false;
  String contractSuit = "NT";
  float pointsInBothHandsWe = 23f;
  int contractLevel = 3;
  int numberOfTrickTakenByWe = 9;


  @Test
  public void testShouldThrowBridgeExceptionWithMessage() throws BridgeException {
    shouldThrowBridgeExceptionWithMessage(155f, contractSuit, 10, 20);
    shouldThrowBridgeExceptionWithMessage(-3f, contractSuit, -5, 14);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, -5, numberOfTrickTakenByWe);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, contractLevel, 14);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, "A", contractLevel, numberOfTrickTakenByWe);
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, contractLevel, numberOfTrickTakenByWe,6);
  }

  private void shouldThrowBridgeExceptionWithMessage(
      float pointsInBothHandsWe, String contractSuit, int contractLevel, int numberOfTrickTakenByWe)
      throws BridgeException {
    shouldThrowBridgeExceptionWithMessage(pointsInBothHandsWe, contractSuit, contractLevel, numberOfTrickTakenByWe,2);
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
      assertEquals(contractLevel, exception.getContractLevel(), 0.05);
      assertFalse(exception.getContractLevel() <= MAXCONTRACTLEVEL && exception.getContractLevel() > 0);
    } catch (InvalidNumberOfPointsException exception) {
      assertEquals(pointsInBothHandsWe, exception.getPointsGiven(), 0.05);
      assertFalse(exception.getPointsGiven() <= MAXNUBEROFPOINTS && exception.getPointsGiven() > MINNUMBEROFPOINTS);
    } catch (InvalidNumberOfTrickTakenException exception) {
      assertEquals(numberOfTrickTakenByWe, exception.getNumberOfTricksTaken(), 0.05);
      assertFalse(exception.getNumberOfTricksTaken() > 0 && exception.getNumberOfTricksTaken() <= 13);
    } catch (InvalidContractSuitException exception) {
      String suit = exception.getContractSuit();
      assertEquals(contractSuit, suit);
      assertFalse(suit.equals("NT") | suit.equals("N") | suit.equals("S") |
          suit.equals("D") | suit.equals("C") | "H".equals(suit));
    } catch (InvalidNDRSignatureException exception) {
      assertEquals("Są tylko trzy opcje (1 = bez kontry, 2 - kontra, 4 - rekontra. " +
          "Podałeś " + exception.getNDRSignature() + " - spróbuj jeszcze raz", exception.getMessage());
    }
  }

  @Test(expected = InvalidFitException.class)
  public void shouldTrowInvalidFitException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, 20, contractLevel,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, true, true);
  }

  @Test(expected = InvalidContractLevelException.class)
  public void shouldThrowInvalidCInteractLevelException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, 166,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
  }

  @Test(expected = InvalidNumberOfPointsException.class)
  public void shouldThrowInvalidNumberOfPointsException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, 2300f, contractLevel,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        isAuctionAssumptionThey, isAuctionAssumptionThey, fitWe, fitThey);
  }

  @Test(expected = InvalidNumberOfTrickTakenException.class)
  public void shouldTrowInvalidNumberOfTrickTakenException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, contractLevel,
        contractSuit, isDouble, isRedouble, 150,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
  }

  @Test(expected = InvalidContractSuitException.class)
  public void shouldTrowInvalidContractSuitException() throws BridgeException {
    new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, contractLevel,
        "piki", isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
  }



  @Test(expected = InvalidNDRSignatureException.class)
  public void testNDSignature() throws BridgeException {
    CalculatedImpPointsForOneDeal c = new CalculatedImpPointsForOneDeal(wePlay, pointsInBothHandsWe, contractLevel,
        contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe,
        auctionAssumptionWe, isAuctionAssumptionThey, fitWe, fitThey);
    c.setNoDoubleReSignature(6);


  }
  @Test(expected = EndOfRubberException.class)
  public void shouldThrowEndOfRubberException() throws BridgeException {
    RubberScoring r = new RubberScoring(1);
    r.fillOneContract(wePlay, contractLevel, contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe);
    r.fillOneContract(wePlay, contractLevel, contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe);

    r.fillOneContract(wePlay, contractLevel, contractSuit, isDouble, isRedouble, numberOfTrickTakenByWe);
  }

}



