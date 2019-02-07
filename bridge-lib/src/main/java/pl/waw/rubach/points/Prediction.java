package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;

/**
 * class to calculate which contract (in future) should be playde with assumption, poinst on Hands fit to reach imp points
 */
public class Prediction extends DeclarerPointsForOneDeal{

    private String des;
    /**
     * number of poinst which should be to have requaierd results (imp points)
     */
    private int precictedPointsForContract;


    /**
     * diference betwenn assumpted result from ExpectedResults table and point reach playng contract (contractScoringPoints)
     */
    private int pointDifferent;

    /**
     * expected Points number acording assumption and fit of those who have more poinst. If the same those who have fit in major suit. If both those who have fit in spades.
     */
    private int expectedPoints;

    public Prediction(int imps, float points,boolean assumptionWe, boolean assumptionThey, boolean fitWe,boolean fitThey) throws BridgeException {
        setResults(imps);
        setDeclarerFit(fitWe);
        setOpponensFit(fitThey);
        setDeclarerVulnerable(assumptionWe);
        setOpponentVulnerable(assumptionThey);
        setPointsInBothDeclarerHands(points);

        setExpectedPoints(ExpectedResultsTable.getInstance().getPoints(getPointsInBothDeclarerHands(),
                isDeclarerFit(), isOpponensFit(), isDeclarerVulnerable(), isOpponentVulnerable()));

        int wynikUjemnyMin = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[1];
        int wynikMax = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[1];

        int wynikUjemnyMax = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[0];
        int wynikMin = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[0];


setDes(" \n Oczekiwane wyniki przy "+getPointsInBothDeclarerHands()+ " to "+ getExpectedPoints() + " punktów za kontrakt .  "
         +"</B>  <BR> Aby uzyskać "+ imps +  impDeclination(imps)+ " różnica punktów musi być między: " +ImpTable.findingDifferenceFromImp(imps)[0] + " a " + ImpTable.findingDifferenceFromImp(imps)[1]
         +"</B>  <BR> Aby uzyskać wynik "+ imps +  impDeclination(imps)+ " , przy " +getPointsInBothDeclarerHands()+ " na ręku, musisz ugrać (zdobyć) pomiędzy "  + wynikMin + " a "+ wynikMax+"."
         +"</B>  <BR>Przeciwnicy uzyskają wynik "+ imps  +  impDeclination(imps)+ " , gdy ty masz  " + getPointsInBothDeclarerHands()+ " na ręku (czyli oni mają : "+ (40 - getPointsInBothDeclarerHands()) +"),"
         +"</B>  <BR> a ty ugrasz (zdobędziesz) pomiędzy "  + wynikUjemnyMin + " a "+ wynikUjemnyMax);
    }


    public static void main(String[] args){
        int imps = 0;
         System.out.println("Aby uzyskać "+ imps + " impów różnica punktów musi być między: " +ImpTable.findingDifferenceFromImp(imps)[0] + " a " + ImpTable.findingDifferenceFromImp(imps)[1]);

float poinst = 23.0f;

try {
    int ex = ExpectedResultsTable.getInstance().getPoints(poinst, true, false, false, false);
    System.out.print("Oczekiwane wyniki przy "+poinst+ " to "+ ex + " punktów za kontrakt  ");

    int wynikUjemnyMin = ex - ImpTable.findingDifferenceFromImp(imps)[1];
    int wynikMax = ex + ImpTable.findingDifferenceFromImp(imps)[1];

    int wynikUjemnyMax = ex - ImpTable.findingDifferenceFromImp(imps)[0];
    int wynikMin = ex + ImpTable.findingDifferenceFromImp(imps)[0];

    System.out.print("\n Aby uzyskać wynik "+ imps + " impów, przy " + poinst+ " na ręku, musisz ugrać (zdobyć) pomiędzy "  + wynikMin + " a "+ wynikMax);

    System.out.print("\n Przeciwnicy uzyskają wynik "+ imps + " impów, gdy ty masz  " + poinst+ " na ręku (czyli oni mają : "+ (40 - poinst) +"), a ty ugrasz (zdobędziesz) pomiędzy "  + wynikUjemnyMin + " a "+ wynikUjemnyMax);
} catch (Exception e) {
System.out.print(e.getMessage());
}

    }


    private String impDeclination(int i) {
        String imp;
        if (i == 1) imp = " imp";
        else if (i < 5) imp = " impy";
        else imp = " impów";
        return imp;


    }

//getters and setters
    public int getPrecictedPointsForContract() {
        return precictedPointsForContract;
    }

    public void setPrecictedPointsForContract(int precictedPointsForContract) {
        this.precictedPointsForContract = precictedPointsForContract;
    }

    public int getPointDifferent() {
        return pointDifferent;
    }

    public void setPointDifferent(int pointDifferent) {
        this.pointDifferent = pointDifferent;
    }

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
