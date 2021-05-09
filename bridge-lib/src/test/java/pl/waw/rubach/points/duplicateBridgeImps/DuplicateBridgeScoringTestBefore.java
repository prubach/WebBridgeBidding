package pl.waw.rubach.points.duplicateBridgeImps;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.InternationalBridgeScoringTestBefore;
import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.Map;
import java.util.TreeSet;

/**
 * 20 test of diferent case of counting points for one deal according to duplicate bridge scorring
 * with assumption unvunerability (befor) - to see afrer - class before :)
 */
public class DuplicateBridgeScoringTestBefore extends InternationalBridgeScoringTestBefore {

  protected Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

  @Before
  public void fillTestPointsMap() {
    premiaZaKoncowke = beforeAfter ? 500 : 300;
    premiaZaCzesciowke = 50;
    super.fillTestPointsMap();
  }


  @Test
  public void testMajorContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForMajorContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      Integer resA = new DuplicateBridgeScoring(p, "s", 1, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym wynik jest: " + res + "/" + resA + " punktów.");
      Assert.assertEquals(testPointsForMajorContract.get(p), res);
      Assert.assertEquals(testPointsForMajorContract.get(p), resA);
    }
    //  }
    //  @Test
    //  public void testMajorDoubleContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForMajorDoubleContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", 2, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z kontrą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMajorDoubleContract.get(p), res);
    }
    // }
    //  @Test
    //  public void testMajorReDoubleContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForMajorReDoubleContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", 4, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z rekontrą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMajorReDoubleContract.get(p), res);
    }
  }

  @Test
  public void testMinorContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForMinorContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "d", false, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMinorContract.get(p), res);
    }
    //  }
    //   @Test
    //   public void testMinorDoubleContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForMinorDoubleContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "d", true, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMinorDoubleContract.get(p), res);
    }
    //   }
    //   @Test
    //   public void testMinorReDoubleContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForMinorReDoubleContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "d", false, true, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMinorReDoubleContract.get(p), res);
    }
  }

  @Test
  public void testNoTrumphContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForNoTrumphContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "n", false, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bez atu wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForNoTrumphContract.get(p), res);
    }
//    }
//    @Test
//    public void testNoTrumphDoubleContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForNoTrumphDoubleContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "n", true, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bez atu z kontrą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForNoTrumphDoubleContract.get(p), res);
    }
//    }
//    @Test
//    public void testNoTrumphReDoubleContract() throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsForNoTrumphReDoubleContract.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "n", false, true, beforeAfter, p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bez atu z rekontrą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForNoTrumphReDoubleContract.get(p), res);
    }
  }

  //TESTS NADRÓBKI


  @Test
  public void testMinorContractOneMore() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "d", false, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym  z jedną nadróbką wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMinorContractOneMore.get(p), res);
      //   }
    }
  }

  @Test
  public void testMinorContractOneMoreDouble() throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMoreDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "d", true, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMinorContractOneMoreDouble.get(p), res);
      //  }
    }
  }

  @Test
  public void testNoTrumphContractOneMoreDouble() throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMoreDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", true, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsOneMoreDouble.get(p), res);
      //  }
    }
  }

  @Test
  public void testMajorContractOneMoreDouble() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMoreDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", true, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z kontrą i jedną nadróbką wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMajorContractOneMoreDouble.get(p), res);
      //   }
    }
  }


  @Test
  public void testMinorContractOneMoreReDouble() throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMoreReDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "d", false, true, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMinorContractOneMoreReDouble.get(p), res);
      //  }
    }
  }

  @Test
  public void testNoTrumphContractOneMoreReDouble() throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMoreReDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", false, true, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu z rekotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsOneMoreReDouble.get(p), res);
      //  }
    }
  }

  @Test
  public void testMajorContractOneMoreReDouble() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMoreReDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", false, true, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z rekontrą i jedną nadróbką wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMajorContractOneMoreReDouble.get(p), res);
      //   }
    }
  }

  @Test
  public void testMajorContractOverTricks() throws BridgeException {
    //  public void testMajorContractOneMore() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z jedną nadróbką wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForMajorContractOneMore.get(p), res);
      //   }
    }
    //   }
    //   @Test
    //   public void testMajorContractTwoMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testMajorContractPointsTwoMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p + 2 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w starszy  z dwiema nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testMajorContractPointsTwoMore.get(p), res);
      //  }
    }
//    }
//    @Test
//    public void testMajorContractThreeMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testMajorContractPointsThreeMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p + 3 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w starszy  z trzema nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testMajorContractPointsThreeMore.get(p), res);
      //  }
    }
//    }
//    @Test
//    public void testMajorContractFourMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testMajorContractPointsFourMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p + 4 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w starszy  z czterema nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testMajorContractPointsFourMore.get(p), res);
      //  }
    }
//    }
//    @Test
//    public void testMajorContractFiveMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testMajorContractPointsFiveMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p + 5 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w starszy z pięcioma nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testMajorContractPointsFiveMore.get(p), res);
      //  }
    }
//    }
//    @Test
//    public void testMajorContractSixMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testMajorContractPointsSixMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p + 6 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w starszy   z sześcioma nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testMajorContractPointsSixMore.get(p), res);
      //  }
    }
  }

  //
  @Test
  public void testNoTrumphContractOverTricks() throws BridgeException {
    //   public void testNoTrumphContractOneMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu z jedną nadróbką  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsOneMore.get(p), res);
      //  }
    }
    //   }
    //   @Test
    //   public void testNoTrumphContractTwoMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsTwoMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p + 2 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z dwiema nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsTwoMore.get(p), res);
      //  }
    }
//    }
//    @Test
//    public void testNoTrumphContractThreeMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsThreeMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p + 3 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z trzema nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsThreeMore.get(p), res);
      //  }
    }
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsThreeMoreDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", true, false, beforeAfter, p + 3 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu z kontrą i  z trzema nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsThreeMoreDouble.get(p), res);
      //  }
    }
    //    }
//    @Test
//    public void testNoTrumphContractFourMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsFourMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p + 4 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z czterema nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsFourMore.get(p), res);
      //  }
    }
//    }
//    @Test
//    public void testNoTrumphContractFiveMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsFiveMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p + 5 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z pięcioma nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsFiveMore.get(p), res);
      //  }
    }
//    }
//    @Test
//    public void testNoTrumphContractSixMore()  throws BridgeException {
    //    for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testNoTrumphContractPointsSixMore.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p + 6 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z sześcioma nadróbkami  wynik jest: " + res + " punktów.");
      Assert.assertEquals(testNoTrumphContractPointsSixMore.get(p), res);
      //  }
    }
  }

  // TESTS WPADKI


  @Test
  public void testAllContractMinusOne() throws BridgeException {
    testsContractMinusOne("s", "starszym");
    testsContractMinusOne("c", "młodszym");
    testsContractMinusOne("nt", "bez atu");
  }

  protected void testsContractMinusOne(String suit, String suitDes) throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsContractMinusOne.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, suit, false, false, beforeAfter, p - 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w color: " + suitDes + "bez jednej wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsContractMinusOne.get(p), res);
      //   }
    }
  }


  @Test
  public void testAllContractMinusOneDouble() throws BridgeException {
    testsContractMinusOneDouble("s", "starszym");
    testsContractMinusOneDouble("d", "młodszym");
    testsContractMinusOneDouble("nt", "bez atu");
  }

  protected void testsContractMinusOneDouble(String suit, String suitDes) throws BridgeException {
    for (int p : new TreeSet<Integer>(testPointsContractMinusOneDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(p, suit, true, false, beforeAfter, p - 1 + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości " + p + " w kolorze: " + suitDes + "  bez jednej z kontą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsContractMinusOneDouble.get(p), res);
    }
  }

  @Test
  public void testContractAllLevelMinusMoreAll() throws BridgeException {

    for (int level = 1; level < 8; level++) {

      testsContractMinusMore(testPointsContractMinusMore, "s", "starszym", level, 1, " ");
      testsContractMinusMore(testPointsContractMinusMore, "h", "starszym", level, 1, " ");
      testsContractMinusMore(testPointsContractMinusMore, "d", "młodszym", level, 1, " ");
      testsContractMinusMore(testPointsContractMinusMore, "nt", "bez atu", level, 1, " ");

      testsContractMinusMore(testPointsForContractMinusMoreDouble, "s", "starszym", level, 2, " z kontrą ");
      testsContractMinusMore(testPointsForContractMinusMoreDouble, "d", "młodszym", level, 2, " z kontrą ");
      testsContractMinusMore(testPointsForContractMinusMoreDouble, "nt", "bez atu", level, 2, " z kontrą ");

      testsContractMinusMore(testPointsForContractMinusMoreReDouble, "s", "starszym", level, 4, " z rekontrą ");
      testsContractMinusMore(testPointsForContractMinusMoreReDouble, "d", "młodszym", level, 4, " z rekontrą ");
      testsContractMinusMore(testPointsForContractMinusMoreReDouble, "nt", "bez atu", level, 4, " z rekontrą ");
    }
  }

  protected void testsContractMinusMore(Map map, String suit, String suitDes, int level, int doubleRe, String doubleReDes) throws BridgeException {
    for (int p : new TreeSet<Integer>(map.keySet())) {
      if (level + 6 - p >= 0) {
        Integer res = new DuplicateBridgeScoring(level, suit, doubleRe, beforeAfter, level - p + 6).getDeclarerContractScoringPoints();
        logger.info("Dla kontraktu o wysokości 3 w kolorze: " + suitDes + "  bez " + p + " " + doubleReDes + "wynik jest: " + res + " punktów.");
        Assert.assertEquals(map.get(p), res);
      }
    }
  }


  //old version of this above
  @Test
  public void testMajor3ContractMinusMore() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsContractMinusMore.keySet())) {
      if (3 + 6 - p >= 0) {
        Integer res = new DuplicateBridgeScoring(3, "s", false, false, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
        logger.info("Dla kontraktu o wysokości 3 w starszym  bez  " + p + "   wynik jest: " + res + " punktów.");
        Assert.assertEquals(testPointsContractMinusMore.get(p), res);
      }
    }
  }

  @Test
  public void testMajor3ContractMinusMoreDouble() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForContractMinusMoreDouble.keySet())) {

      if (3 + 6 - p >= 0) {
        Integer res = new DuplicateBridgeScoring(3, "s", true, false, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
        logger.info("Dla kontraktu o wysokości 3 w starszym  bez  " + p + "  z kontą wynik jest: " + res + " punktów.");
        Assert.assertEquals(testPointsForContractMinusMoreDouble.get(p), res);
      }
    }
  }

  @Test
  public void testMajor3ContractMinusMoreReDouble() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForContractMinusMoreReDouble.keySet())) {
      if (3 + 6 - p >= 0) {
        Integer res = new DuplicateBridgeScoring(3, "s", true, true, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
        logger.info("Dla kontraktu o wysokości 3 w starszym  bez  " + p + "  z rekontą wynik jest: " + res + " punktów.");
        Assert.assertEquals(testPointsForContractMinusMoreReDouble.get(p), res);
      }
    }
  }

  @Test
  public void testNoTrumph3ContractMinusMore() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForContractLevel3MinusMore.keySet())) {

      Integer res = new DuplicateBridgeScoring(3, "nt", false, false, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości 3 w bez Atu  bez " + p + " wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForContractLevel3MinusMore.get(p), res);

    }
  }

  @Test
  public void testNoTrumph3ContractMinusMoreDouble() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForContractLevel3MinusMoreDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(3, "nt", true, false, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości 3 w bez Atu bez  " + p + "  z kontą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForContractLevel3MinusMoreDouble.get(p), res);
      //   }
    }
  }

  @Test
  public void testNoTrumph3ContractMinusMoreReDouble() throws BridgeException {
    //for(int i=1; i<2; i++){
    for (int p : new TreeSet<Integer>(testPointsForContractLevel3MinusMoreReDouble.keySet())) {
      Integer res = new DuplicateBridgeScoring(3, "nt", true, true, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
      logger.info("Dla kontraktu o wysokości 3 w bez Atu  bez  " + p + "  z rekontą wynik jest: " + res + " punktów.");
      Assert.assertEquals(testPointsForContractLevel3MinusMoreReDouble.get(p), res);
      //   }
    }
  }

}
