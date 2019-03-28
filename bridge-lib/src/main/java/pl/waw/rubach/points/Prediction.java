package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;

import static pl.waw.rubach.points.AbstractImpTable.impDeclination;

/**
 * class to calculate which contract (in future) should be playde with assumption, poinst on Hands fit to reach imp points
 */
public class Prediction extends OneDeal {

    /**
     * description
     */
    private String des;


    /**
     * expected Points number acording assumption and fit of those who have more poinst. If the same those who have fit in major suit. If both those who have fit in spades.
     */
    private int expectedPoints;

    public Prediction(int imps, float points,boolean assumptionWe, boolean assumptionThey, boolean fitWe,boolean fitThey) throws BridgeException {
        setImpResults(imps);
        setDeclarerFit(fitWe);
        setOpponensFit(fitThey);
        setDeclarerVulnerable(assumptionWe);
        setOpponentVulnerable(assumptionThey);
        setPointsInBothDeclarerHands(points);

        setExpectedPoints(ExpectedResultsTable.getInstance().getPoints(getPointsInBothDeclarerHands(),
                isDeclarerFit(), isOpponensFit(), isDeclarerVulnerable(), isOpponentVulnerable()));

        int wynikMax = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[1];
        int wynikMin = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[0];

        int wynikUjemnyMax = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[0];
        int wynikUjemnyMin = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[1];

      if(imps>0)
          setContractScoringPoints((wynikMax+wynikMin)/2);

        else {
        imps = -imps;
          wynikMax = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[1];
          wynikMin = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[0];

          wynikUjemnyMax = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[0];
          wynikUjemnyMin = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[1];
          setContractScoringPoints(-(wynikUjemnyMax + wynikUjemnyMin) / 2);

        }

setDes(" \n Oczekiwane wyniki przy "+getPointsInBothDeclarerHands()+ " to "+ getExpectedPoints() + " punktów za kontrakt .  "
         +"</B>  <BR> Aby uzyskać "+ imps +  impDeclination(imps)+ " różnica punktów musi być między: " +ImpTable.findingDifferenceFromImp(imps)[0] + " a " + ImpTable.findingDifferenceFromImp(imps)[1]
         +"</B>  <BR> Aby uzyskać wynik "+ imps +  impDeclination(imps)+ " , przy " +getPointsInBothDeclarerHands()+ " na ręku, musisz ugrać (zdobyć) pomiędzy "  + wynikMin + " a "+ wynikMax+"."
         +"</B>  <BR>Przeciwnicy uzyskają wynik "+ imps  +  impDeclination(imps)+ " , gdy ty masz  " + getPointsInBothDeclarerHands()+ " na ręku (czyli oni mają : "+ (40 - getPointsInBothDeclarerHands()) +"),"
         +"</B>  <BR> a ty ugrasz (zdobędziesz) pomiędzy "  + wynikUjemnyMin + " a "+ wynikUjemnyMax+ " czyli średnio" +getContractScoringPoints()+ " pkt.");
    }


    public static void main(String[] args){
        int imps = 1;
         System.out.println("Aby uzyskać "+ imps + " impów różnica punktów musi być między: " +ImpTable.findingDifferenceFromImp(imps)[0] + " a " + ImpTable.findingDifferenceFromImp(imps)[1]);

float poinst = 23.0f;

try {
    Prediction ex =new Prediction(imps,poinst, false, false, false, false);

    System.out.print("\n "+ex.getDes());
} catch (Exception e) {
System.out.print(e.getMessage());
}

    }




//getters and setters


    public int getExpectedPoints() {
        return expectedPoints;
    }

    public void setExpectedPoints(int expectedPoints) {
        this.expectedPoints = expectedPoints;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
