package pl.waw.rubach.points;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.Assert;
import org.junit.Test;
import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.Map;

public class PredictionTest extends CalculatedImpPointsForOneDealFirstTest {

    @Test
    public void testPrediction() throws BridgeException {

        for (
            Map.Entry<MultiKey<? extends Float>, Integer> entry : testCountingPointsBothBeforNoFitMap.entrySet()) {

            float pointsInBothHands = entry.getKey().getKey(0);
            float pointsOfContractFloat = entry.getKey().getKey(1);
            int imps = entry.getValue();
            int diff = (ImpTable.findingDifferenceFromImp(Math.abs(imps))[1]-ImpTable.findingDifferenceFromImp(Math.abs(imps))[0]);


            float pointsOfContract = new Prediction(imps,pointsInBothHands,false,false,false,false,true).getDeclarerContractScoringPoints();
            logger.info("Dla " + pointsInBothHands + " pkt, jeżeli chcesz ugrać "+ imps + " impów,["+diff+" ; "+ pointsOfContractFloat +"] musisz zdobyć średnio "+ pointsOfContract+" punktów za kontrakt. Obie przed, obie bez fitu");
            Assert.assertEquals(Math.abs(pointsOfContractFloat), Math.abs(pointsOfContract), diff);
              }
    }

}